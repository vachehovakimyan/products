var sideMenu = document.getElementById("side-menu");

document.addEventListener("DOMContentLoaded", getCategoryElements);

// Обьект продукта, который открыт в данный момент
var openProduct;

// Получаем список категорий с сервера
function getCategoryElements() {
    getJSON("/get_all_categories", function (categoryElementArray) {
        for (var i = 0; i < categoryElementArray.length; i++) {
            addMenuElement(categoryElementArray[i]);
        }
    });
}

// Добавление элементов в бокавое меню
function addMenuElement(categoryElement) {

    var newProdNameCategoryList = document.getElementById('newProdNameCategory');

    var option = document.createElement("option");
    option.setAttribute("value", categoryElement.id);
    option.innerHTML = categoryElement.name;

    newProdNameCategoryList.appendChild(option);


    var li = document.createElement("li");
    var a = document.createElement("a");
    a.setAttribute('href', '#');


    li.appendChild(a);

    if (categoryElement.productList.length !== 0) {

        a.innerHTML = '<i class="fa fa-bar-chart-o fa-fw"></i> ' + categoryElement.name + '<span class="fa arrow"></span>';

        var ul = document.createElement("ul");
        ul.classList = 'nav nav-second-level';

        for (var i = 0; i < categoryElement.productList.length; i++) {
            var product = categoryElement.productList[i];

            var pLi = document.createElement("li");

            var pA = document.createElement("a");
            pA.innerHTML = product.name;
            pA.setAttribute("onclick", 'showProduct(' + product.id + ')');

            pLi.appendChild(pA);

            ul.appendChild(pLi);
        }

        li.appendChild(ul);
    } else {
        a.innerHTML = '<i class="fa fa-bar-chart-o fa-fw"></i> ' + categoryElement.name;
    }

    sideMenu.appendChild(li);
}

/**
 * Функция предназначена для отображения информации о продукте в панели на главной странице
 * @param id идентификатор продукта
 */
function showProduct(id) {
    // Делаем панель видимой (изначально она не видима)
    var panel = document.getElementById('prodPanel');
    panel.style.visibility = 'visible';

    // Запрашиваем у сервера обьект продукта
    getJSON("/get_product?product_id=" + id, function (product) {

        // Записываем в переменную обьект продукта на случай, если пользователь захочет его модифицировать
        openProduct = product;

        // В заголовок панели записываем название продукта и добавляем две кнопки
        // Первая кнопка служит для удаления продукта и вызывает функцию "deleteProduct(id)" при нажатии
        // Вторая кнопка служит для модификации продукта и вызывает функцию "addDataToModify()" при нажатии
        document.getElementById('prodPanelHeading').innerHTML = "Product name - " + product.name +
            "  <a type=\"button\" class=\"btn btn-danger btn-xs pull-right\" onclick='deleteProduct(" + id + ")'>Delete</a>  " +
        "  <a class=\"btn btn-success btn-xs pull-right\" data-toggle=\"modal\" onclick='addDataToModify()' data-target=\"#createProductModal\"> Modify</a>  ";

        // Получаем тело таблицы в панели и опустошаем его, чтобы заполнить новой информацией
        var prodPanelTBody = document.getElementById('prodPanelTBody');
        prodPanelTBody.innerHTML = '';

        // В обьекте продукта есть список его свойств
        // Пробегаем по нему и записываем данные в таблицу
        for (var i = 0; i < product.attributeList.length; i++) {

            // Обьект очередного свойства
            var attr = product.attributeList[i];

            var tr = document.createElement('tr');

            var tdName = document.createElement('td');
            tdName.innerHTML = attr.name;
            var tdCapacity = document.createElement('td');
            tdCapacity.innerHTML = attr.capacity;

            tr.appendChild(tdName);
            tr.appendChild(tdCapacity);

            prodPanelTBody.appendChild(tr);
        }
    });


}

/**
 * Функция предназначена для удаления продукта и вызыается при нажатии на кнопку Delete в панели отображения
 * @param id идентификатор продукта
 */
function deleteProduct(id) {

    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/delete_prod?id=" + id, true);
    xhr.responseType = 'json';

    // Перезагружаем локацию для обновления данных
    xhr.onload = function () {
        location.reload();
    };

    xhr.send();


}

/**
 * Функция очищения модального окна (приовдит его в стандартное состояния создания нового продукта)
 * Необходима из-за того, что одно и тоже модальное кно используется как для создания нового продукта
 * так и для модификации старых.
 */
function cleanModal() {

    var modalTitle = document.getElementById("modalTitle");
    modalTitle.innerHTML = 'Create new Product';

    var createProductBtn = document.getElementById("createProductBtn");
    createProductBtn.style.display = 'inline';

    var updateProductBtn = document.getElementById("updateProductBtn");
    updateProductBtn.style.display = 'none';

    var prodName = document.getElementById("newProdName");
    prodName.value = '';

    var formTBody = document.getElementById("formTBody");
    formTBody.innerHTML = "";


}

/**
 * Добавление данных продукта в модальное окно, чтобы предоставить возможность модифицировать его и сохранить изменения
 * Вызывается при нажатии на кнопку Modify в панели отображения продукта
 */
function addDataToModify() {

    // Очищаем окно от старых данных
    cleanModal();

    // Изменяем заголовок окна
    var modalTitle = document.getElementById("modalTitle");
    modalTitle.innerHTML = 'Modify Product';

    // Убираем кнопку "Создать"
    var createProductBtn = document.getElementById("createProductBtn");
    createProductBtn.style.display = 'none';

    // Показываем кноку "Обновить"
    var updateProductBtn = document.getElementById("updateProductBtn");
    updateProductBtn.style.display = 'inline';

    // Записываем Имя продукта в соответсвующее поле
    var prodName = document.getElementById("newProdName");
    prodName.value = openProduct.name;

    // Выставляем необходимую категорию как выбранную
    $('#newProdNameCategory').val(openProduct.categoryId);

    // Получаем обьект тела таблицы со свойствами продукта
    var formTBody = document.getElementById("formTBody");

    // Пробегаем по свойствам продукта и добавляем их в таблицу
    for (var i = 0; i < openProduct.attributeList.length; i++) {

        var attr = openProduct.attributeList[i];

        var tr = document.createElement('tr');

        var tdName = document.createElement('td');
        tdName.setAttribute("contenteditable", "true");
        tdName.innerHTML = attr.name;
        var tdCapacity = document.createElement('td');
        tdCapacity.setAttribute("contenteditable", "true");
        tdCapacity.innerHTML = attr.capacity;

        tr.appendChild(tdName);
        tr.appendChild(tdCapacity);

        formTBody.appendChild(tr);
    }


}

/**
 * Модификация продукта на сервере
 * Вызывается при нажатии на кнопку "Обновить в модальном окне"
 */
function modifyProduct() {

    var formTable = document.getElementById("formTable");

    var tmpName = document.getElementById("newProdName").value;
    var tmpCategoryFiled = document.getElementById("newProdNameCategory");

    var tmpCategory = tmpCategoryFiled.options[tmpCategoryFiled.selectedIndex].value;

    var attrArray = [];

    for ( var i = 1; i < formTable.rows.length; i++ ) {
        attrArray.push({
            name: formTable.rows[i].cells[0].innerHTML,
            capacity: formTable.rows[i].cells[1].innerHTML
        });
    }

    var product = {id: openProduct.id, name: tmpName, categoryId: tmpCategory, attributeList: attrArray};

    console.log(product);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', "/modify_prod", true);
    xhr.setRequestHeader("Content-type", "application/json");

    xhr.onload = function () {
        showProduct(openProduct.id)
    };

    xhr.send(JSON.stringify(product));

}

/**
 * Функция предназначена для добавления новых атрибутов в таблицу создания нового продукта в модальном окне
 */
function newFormAddAttr() {

    var formAttributeName = document.getElementById("formAttributeName");
    var formAttributeCapacity = document.getElementById("formAttributeCapacity");

    var formTBody = document.getElementById("formTBody");

    var tr = document.createElement('tr');

    var tdName = document.createElement('td');
    // Содержание поля должно быть редактируемым
    tdName.setAttribute("contenteditable", "true");
    tdName.innerHTML = formAttributeName.value;
    var tdCapacity = document.createElement('td');
    // Содержание поля должно быть редактируемым
    tdCapacity.setAttribute("contenteditable", "true");
    tdCapacity.innerHTML = formAttributeCapacity.value;

    tr.appendChild(tdName);
    tr.appendChild(tdCapacity);

    formTBody.appendChild(tr);

    formAttributeName.value = '';
    formAttributeCapacity.value = '';


}

/**
 * Создание нового продукта
 * Вызывается при нажатии на кнопку "Сохранить" в модальном окне
 */
function newFormCreateProd() {

    var formTable = document.getElementById("formTable");

    var tmpName = document.getElementById("newProdName").value;
    var tmpCategoryFiled = document.getElementById("newProdNameCategory");

    var tmpCategory = tmpCategoryFiled.options[tmpCategoryFiled.selectedIndex].value;

    var attrArray = [];

    for ( var i = 1; i < formTable.rows.length; i++ ) {
        attrArray.push({
            name: formTable.rows[i].cells[0].innerHTML,
            capacity: formTable.rows[i].cells[1].innerHTML
        });
    }

    var product = {name: tmpName, categoryId: tmpCategory, attributeList: attrArray};

    var xhr = new XMLHttpRequest();
    xhr.open('POST', "/create_product", true);
    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send(JSON.stringify(product));

    location.reload();

}

/**
 * Делает запрос к серверу и возвращает результат
 * @param url адрес назначения
 * @param callback функция обработки полученных данных
 */
var getJSON = function (url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.responseType = 'json';

    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            callback(xhr.response);
        }
    };

    xhr.onerror = function () {
        alert('Сервер умер! Да здравствует сервер!');
    };

    xhr.send();

};







