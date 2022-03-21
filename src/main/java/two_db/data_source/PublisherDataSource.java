package two_db.data_source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
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
public class PublisherDataSource {
    @Autowired
    private Environment env;

    @Bean
    public DataSource ds1Datasource() {

        System.out.println(env.getProperty("spring.datasource.url.pub"));
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/publisher");
        dataSource.setUsername("root");
        dataSource.setPassword("root@123");


        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean ds1EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ds1Datasource());

        em.setPackagesToScan(new String[] { Constants.PUB_PACKAGE });
        em.setPersistenceUnitName(Constants.PUB_JPA_UNIT_NAME); // Important !!

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
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
    @Primary
    public PlatformTransactionManager ds1TransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(ds1EntityManager().getObject());
        return transactionManager;
    }
}
