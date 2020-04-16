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
        basePackages = "com.dev.multipledb.repositories.product",
        entityManagerFactoryRef = "productsEntityManager",
        transactionManagerRef = "productsTransactionManager"
)
public class ProductDatabaseConfig {
    @Value("${ms-config.username}")
    private String username;
    @Value("${ms-config.password}")
    private String password;
    @Value("${ms-config.driver-class}")
    private String driverClass;
    @Value("${ms-config.hibernate-dialect}")
    private String hibernateDialect;

    @Value("${ms-config.databases.products.url}")
    private String ulrProducts;

    @Bean
    public DataSource datasourceProducts() {
        return DataSourceBuilder.create().driverClassName(driverClass).username(username)
                .password(password).url(ulrProducts).build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean productsEntityManager() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
                = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName("productsEntityManager");
        entityManagerFactoryBean.setDataSource(datasourceProducts());
        entityManagerFactoryBean.setPackagesToScan("com.dev.multipledb.entities.product");
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
    public PlatformTransactionManager productsTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                productsEntityManager().getObject());
        return transactionManager;
    }

}
