package com.load.simple.config;

import com.load.simple.workflow.IWorkflow;
import com.load.simple.workflow.WaggMonthlyCarrier;
import com.load.simple.workflow.WfTfctAirlineDataset;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


@PropertySource("classpath:db.properties")
@PropertySource("classpath:file_names.properties")
public class AirlineDataConfiguration {

    @Autowired
    private Environment env;

    @Autowired
    private SessionFactory sessionFactory;

    @Bean(destroyMethod = "close")
    DataSource dataSource() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(env.getProperty("db.driver"));
        hikariConfig.setJdbcUrl(env.getProperty("db.url"));
        hikariConfig.setUsername(env.getProperty("db.username"));
        hikariConfig.setPassword(env.getProperty("db.password"));

        hikariConfig.setMaximumPoolSize(Integer.parseInt(env.getProperty("hc.size")));
        hikariConfig.setConnectionTestQuery(env.getProperty("hc.testQuery"));
        hikariConfig.setPoolName(env.getProperty("hc.poolName"));

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }

    @Bean
    NamedParameterJdbcTemplate jdbcTemplate() throws SQLException {
        return new NamedParameterJdbcTemplate(dataSource());
    }


    @Bean
    public SessionFactory sessionFactory () throws Exception {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", env.getProperty("jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("jpa.show-sql"));
        properties.put("current_session_context_class", env.getProperty("jpa.properties.hibernate.current_session_context_class"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("jpa.hibernate.ddl-auto"));

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        // Package contain entity classes
        factoryBean.setPackagesToScan(new String[] { "" });
        factoryBean.setDataSource(dataSource());
        factoryBean.setHibernateProperties(properties);
        factoryBean.afterPropertiesSet();
        //
        SessionFactory sf = factoryBean.getObject();
        System.out.println("## getSessionFactory: " + sf);
        return sf;
    }


    @Bean
    public HibernateTransactionManager transactionManager()  throws Exception {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

        return transactionManager;
    }

    @Bean
    IWorkflow tfctAirlineWorkflow() throws Exception{
        return new WfTfctAirlineDataset(jdbcTemplate());
    }

    @Bean
    IWorkflow aggregationWorkflow () throws Exception{
        return new WaggMonthlyCarrier();
    }
}
