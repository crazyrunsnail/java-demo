package xyz.uniofun.prospring.ch6.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@PropertySource("classpath:jdbc/jdbc.properties")
public class PropertyPlaceConguration {
    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    @Bean
    @Lazy
    public DataSource dataSource() {
        try {
            SimpleDriverDataSource dataSource =
                    new SimpleDriverDataSource();
            Class<? extends Driver> driver =
                    (Class<? extends Driver>) Class.forName(driverClassName);
            dataSource.setDriverClass(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        } catch (Exception e) {
            return null;
        }
    }

}
