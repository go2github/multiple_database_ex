package two_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import two_db.pub_entity.Publisher;
import two_db.sub_entity.Advertiser;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })


public class MultiPleDataSourceApplication implements CommandLineRunner {

    @Autowired
    PublisherDao publisherDao;
    @Autowired
    SubscriberDao subscriberDao;

    public static void main(String[] args) {
        SpringApplication.run(MultiPleDataSourceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Publisher publisher=new Publisher();
//        publisher.setId(12L);
//        publisher.setName("test");
//        publisherDao.save(publisher);
        Advertiser advertiser=new Advertiser();
        advertiser.setId(45L);
        advertiser.setName("de");
        subscriberDao.save(advertiser);
    }
}
