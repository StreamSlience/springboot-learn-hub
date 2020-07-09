package com.streamslience.springdatajpa.japmultidatasource.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 获取配置信息方式二
 * <p>
 * 以下配置数据库的方式可扩展性更高,不同的数据源我们可以独立设置数据库连接池参数,甚至可以同时使用不用的数据库连接池。
 *
 * <p><strong>扩展:</strong></p>
 * 这里仅仅是简单的进行多数据源配置,
 * 实际上我们可以在配置文件中详细的设定数据库连接信息,将不同的数据源相关参数分别封装到不同的bean对象中,从这些bean对象中获取相关参数,这样就可以减少当前类中的参数数量。
 *
 * @author StreamSlience
 * @date 2020-06-25 11:02
 */
@Configuration
public class DataSourceConfigurationPatternSecond {

    @Value("${spring.datasource.primary.jdbc-url}")
    private String jdbcUrl1;

    @Value("${spring.datasource.primary.username}")
    private String username1;

    @Value("${spring.datasource.primary.password}")
    private String password1;

    @Value("${spring.datasource.primary.driver-class-name}")
    private String driverClassName1;

    @Value("${spring.datasource.secondary.jdbc-url}")
    private String jdbcUrl2;

    @Value("${spring.datasource.secondary.username}")
    private String username2;

    @Value("${spring.datasource.secondary.password}")
    private String password2;

    @Value("${spring.datasource.secondary.driver-class-name}")
    private String driverClassName2;

    @Primary
    @Bean
    public DataSource primaryDataSource() {
        return getHikariDataSource(username1, password1, jdbcUrl1, driverClassName1);
    }

    @Bean
    public DataSource secondaryDataSource() {
        return getDruidDataSource(username2, password2, jdbcUrl2, driverClassName2);
    }

    private HikariDataSource getHikariDataSource(String username, String password, String jdbcUrl, String driverClassName) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMinimumIdle(10);
        dataSource.setMaximumPoolSize(10);
        dataSource.setAutoCommit(true);
        dataSource.setIdleTimeout(12000);
        dataSource.setMaxLifetime(30000);
        dataSource.setConnectionInitSql("SET names utf8mb4 ");
        dataSource.setConnectionTestQuery("SELECT 1");
        dataSource.setConnectionTimeout(10000);
        dataSource.setReadOnly(false);

        return dataSource;
    }

    private DruidDataSource getDruidDataSource(String username, String password, String url, String driverClassName) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(5);
        datasource.setMinIdle(5);
        datasource.setMaxActive(20);
        datasource.setMaxWait(6000);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setMinEvictableIdleTimeMillis(300000);
        datasource.setValidationQuery("SELECT 1 FROM DUAL");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setPoolPreparedStatements(true);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        datasource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500");

        return datasource;
    }
}
