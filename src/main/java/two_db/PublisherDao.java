package two_db;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import two_db.pub_entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

@Repository
public class PublisherDao {

    @PersistenceContext( unitName= Constants.PUB_JPA_UNIT_NAME)
    private EntityManager entityManager;



    @Transactional
    public void save(Publisher publisher){

           entityManager.persist(publisher);


    }
}
