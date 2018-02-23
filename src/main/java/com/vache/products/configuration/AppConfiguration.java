package com.vache.products.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Основной конфигурационный класс приложения
 */
@Configuration
public class AppConfiguration extends WebMvcConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * Инициализация сервиса полкючения к базе данных
     * @return dataSource
     */
    @Bean
    @Primary
    public DataSource dataSource() {

        logger.info("Init dataSource");

        ExternalConfig.Database database = ExternalConfig.getInstance().getDatabase();

        String url = "jdbc:mysql://" + database.getHost()
                + ":" + database.getPort()
                + "/" + database.getDatabase()
                + "?autoReconnect=" + database.getAutoreconnect()
                + "&useSSL=" + database.getUsessl()
                + "&useUnicode=" + database.getUseunicode()
                + "&characterEncoding=" + database.getCharacterencoding();

        return DataSourceBuilder
                .create()
                .username(database.getUsername())
                .password(database.getPassword())
                .url(url)
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }
    

    /**
     * Инициализация менеджера задачь для многопоточного исполнения
     * @return TaskExecutor
     */
    @Bean
    public TaskExecutor taskExecutor() {

        logger.info("Init taskExecutor");

        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(4);
        pool.setMaxPoolSize(50);
        pool.setWaitForTasksToCompleteOnShutdown(true);

        return pool;
    }


}
