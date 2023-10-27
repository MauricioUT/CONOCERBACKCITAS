package org.conocer.citas.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManagerFactory", transactionManagerRef = "mysqlTransactionManager",
        basePackages = { "org.conocer.citas.entity", "org.conocer.citas.repository"}) // indica a que paquetes usara la configuracion de una  db especifica
public class DBconfig {

    @Autowired
    Environment env;

    private DriverManagerDataSource dataSource;

    @Bean(name = "citas_Datasource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource primaryDataSource() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }


   
   /* @Bean(name = "db_si_DataSource")
    @ConfigurationProperties(prefix="spring.secondDatasource")
    public DataSource secondaryDataSource() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.secondDatasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.secondDatasource.url"));
        dataSource.setUsername(env.getProperty("spring.secondDatasource.username"));
        dataSource.setPassword(env.getProperty("spring.secondDatasource.password"));
        return dataSource;
    }*/
    
 /*   @Bean(name = "db_asec_DataSource")
    @ConfigurationProperties(prefix="spring.thirdDatasource")
    public DataSource thirdDataSource() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.thirdDatasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.thirdDatasource.url"));
        dataSource.setUsername(env.getProperty("spring.thirdDatasource.username"));
        dataSource.setPassword(env.getProperty("spring.thirdDatasource.password"));
        return dataSource;
    }*/

    @Bean(name = "MYSQL_Datasource")
    @ConfigurationProperties(prefix="spring.fourthDataSource")
    public DataSource fourthDataSource() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.fourthDataSource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.fourthDataSource.url"));
        dataSource.setUsername(env.getProperty("spring.fourthDataSource.username"));
        dataSource.setPassword(env.getProperty("spring.fourthDataSource.password"));
        return dataSource;
    }

    @Bean(name = "mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(fourthDataSource());
        em.setPackagesToScan("org.conocer.citas.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.fourthDataSource.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql", env.getProperty("spring.fourthDataSource.jpa.show.sql"));
        properties.put("hibernate.dialect", env.getProperty("spring.fourthDataSource.jpa.database-platform"));

        em.setJpaPropertyMap(properties);

        return em;

    }

    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
