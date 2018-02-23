package com.vache.products.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Данный класс служит для хранения данных из конфигурационного файла по адресу
 * указанному в переменной filePath.
 */
@XmlRootElement(name = "products")
@Component
public class ExternalConfig {

    private static Logger logger = LoggerFactory.getLogger(ExternalConfig.class);

    // Путь до xml-файла конфигурации
    private static String filePath = "config/appl_config.xml";

    // Статическое поле хранящее экземпляр класса XMLConfig
    private static ExternalConfig externalConfig;

    private static Database database;

    /**
     * Инициализация данного класса.
     * Выполняет чтение, обработку данных из xml-файла по адресу "filePath" и создает экземпляр данного класса
     * записывает результат в поле "xmlConfig"
     */
    @PostConstruct
    private static void init() {
        try {
            JAXBContext context = JAXBContext.newInstance(ExternalConfig.class);
            Unmarshaller unMarshaller = context.createUnmarshaller();
            externalConfig = (ExternalConfig) unMarshaller.unmarshal(new FileInputStream(filePath));

            logger.info("Data from configuration file '" + filePath + "' received correspondingly.");

        } catch (JAXBException | FileNotFoundException e) {
            logger.error("Can not init configuration from xml file " + filePath + "! Message: " + e.getMessage());
        }
    }

    /**
     * Получение экземпляра данного класса
     * @return экземпляр обьекта хранящего уонфигурационные обьекты
     */
    public static ExternalConfig getInstance() {

        if (externalConfig == null) init();

        return externalConfig;
    }

    public Database getDatabase() {
        return database;
    }
    public void setDatabase(Database database) {
        ExternalConfig.database = database;
    }

    // Данный класс содержит данные авторизации базы данных
    public static class Database {

        private String host;

        private Integer port;

        private String database;

        private String username;

        private String password;

        private Boolean autoreconnect;

        private Boolean usessl;

        private String useunicode;

        private String characterencoding;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Boolean getAutoreconnect() {
            return autoreconnect;
        }

        public void setAutoreconnect(Boolean autoreconnect) {
            this.autoreconnect = autoreconnect;
        }

        public Boolean getUsessl() {
            return usessl;
        }

        public void setUsessl(Boolean usessl) {
            this.usessl = usessl;
        }

        public String getUseunicode() {
            return useunicode;
        }

        public void setUseunicode(String useunicode) {
            this.useunicode = useunicode;
        }

        public String getCharacterencoding() {
            return characterencoding;
        }

        public void setCharacterencoding(String characterencoding) {
            this.characterencoding = characterencoding;
        }

        @Override
        public String toString() {
            return "Database{" +
                    "host='" + host + '\'' +
                    ", port=" + port +
                    ", database='" + database + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + "*******" + '\'' +
                    ", autoreconnect=" + autoreconnect +
                    ", usessl=" + usessl +
                    ", useunicode='" + useunicode + '\'' +
                    ", characterencoding='" + characterencoding + '\'' +
                    '}';
        }
    }


}
