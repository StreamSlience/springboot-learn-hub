package com.streamslience.springdatajpa.dynamicdatasource.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

/**
 * <br><strong>{@code @EnableJpaRepositories}说明:</strong>
 * <br><strong>entityManagerFactoryRef:</strong>???
 * <br><strong>transactionManagerRef:</strong>???
 * <br><strong>basePackages:</strong>持久层接口所在的路径。
 *
 * <p>
 * <br><strong>{@code @Qualifier}说明:</strong>
 * 此注释可以在字段或参数上用作限定符自动装配时选择指定名称的bean。它也可以用于注释其他自定义注释，这些注释随后可以用作限定符。
 *
 * <p>
 *
 * @author StreamSlience
 * @date 2020-06-24 16:06
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerPrimary",
        basePackages = {"com.streamslience.springdatajpa.dynamicdatasource.primary.dao"})
public class PrimaryConfig {

    private final DataSource primaryDataSource;

    private final JpaProperties jpaProperties;

    private final HibernateProperties hibernateProperties;

    @Autowired
    public PrimaryConfig(@Qualifier("primaryDataSource") DataSource primaryDataSource, JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
        this.primaryDataSource = primaryDataSource;
        this.jpaProperties = jpaProperties;
        this.hibernateProperties = hibernateProperties;
    }

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Primary
    @Bean
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        //return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
        return Objects.requireNonNull(entityManagerFactoryPrimary(builder).getObject()).createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                .packages("com.streamslience.springdatajpa.dynamicdatasource.primary.entity")
                .persistenceUnit("primaryPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}
