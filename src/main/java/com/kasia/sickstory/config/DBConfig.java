package com.kasia.sickstory.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class DBConfig {
    /*@Bean
    @ConfigurationProperties("app.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }    @Bean
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }*/

    /*@Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(HikariDataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        //emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("com.kasia.sickstory");
        emf.setPersistenceUnitName("sickstory");
        emf.afterPropertiesSet();
        return emf.getObject();
    }*/

    /*@Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean entityManagerFactoryBean
                = new LocalEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName("sickstory");
        return entityManagerFactoryBean;
    }*/

    /*@Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager =
                new JpaTransactionManager(entityManagerFactory);
        return jpaTransactionManager;
    }*/
}
