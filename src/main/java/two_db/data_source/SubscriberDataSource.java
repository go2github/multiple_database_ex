package two_db.data_source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import two_db.Constants;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySources({@PropertySource("classpath:application.properties")})
public class SubscriberDataSource {
    @Autowired
    private Environment env;

    @Bean
    public DataSource ds2Datasource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.2"));
        dataSource.setUrl(env.getProperty("spring.datasource.url.2"));
        dataSource.setUsername("root");
        dataSource.setPassword("root@123");

        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean ds2EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ds2Datasource());

        em.setPackagesToScan(new String[] { Constants.SUB_PACKGE });
        em.setPersistenceUnitName(Constants.SUB_JPA_UNIT_NAME); // Important !!

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        properties.put("hibernate.hbm2ddl.auto", "update");
        // Solved Error: PostGres createClob() is not yet implemented.
        // PostGres Only:
        // properties.put("hibernate.temp.use_jdbc_metadata_defaults",  false);

        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();

        return em;

    }
    @Bean
    public PlatformTransactionManager ds2TransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ds2EntityManager().getObject());
        return transactionManager;
    }
}
