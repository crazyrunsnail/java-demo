package xyz.uniofun.prospring.ch6.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import xyz.uniofun.prospring.ch6.jdbc.PropertyPlaceConguration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@Import(PropertyPlaceConguration.class)
@EnableTransactionManagement
public class JpaConfiguration implements TransactionManagementConfigurer {

    @Autowired
    private DataSource dataSource;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setDataSource(dataSource);
        factoryBean.setPersistenceUnitName("default");
        factoryBean.setSharedCacheMode(SharedCacheMode.NONE);
        factoryBean.setPackagesToScan("xyz.uniofun.prospring");
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        return adapter;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProp.put("hibernate.hbm2ddl.auto", "create-drop");
        //hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProp;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        return jpaTransactionManager;
    }


    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}
