package com.dev.multipledb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.dev.multipledb.repositories.client",
        entityManagerFactoryRef = "clientsEntityManager",
        transactionManagerRef = "clientsTransactionManager"
)
public class ClientDatabaseConfig {
    @Value("${ms-config.username}")
    private String username;
    @Value("${ms-config.password}")
    private String password;
    @Value("${ms-config.driver-class}")
    private String driverClass;
    @Value("${ms-config.hibernate-dialect}")
    private String hibernateDialect;

    @Value("${ms-config.databases.clients.url}")
    private String ulrClients;

    @Bean
    public DataSource datasourceClients() {
        return DataSourceBuilder.create().driverClassName(driverClass).username(username)
                .password(password).url(ulrClients).build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean clientsEntityManager() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
                = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName("clientsEntityManager");
        entityManagerFactoryBean.setDataSource(datasourceClients());
        entityManagerFactoryBean.setPackagesToScan("com.dev.multipledb.entities.client");
        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", hibernateDialect);
        entityManagerFactoryBean.setJpaPropertyMap(properties);
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        return entityManagerFactoryBean;
    }

    @Primary
    @Bean
    public PlatformTransactionManager clientsTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                clientsEntityManager().getObject());
        return transactionManager;
    }

}
