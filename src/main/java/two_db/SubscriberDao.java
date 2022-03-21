package two_db;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import two_db.pub_entity.Publisher;
import two_db.sub_entity.Advertiser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

@Repository
public class SubscriberDao {

    @PersistenceContext( unitName= Constants.SUB_JPA_UNIT_NAME)
    private EntityManager entityManager;

    @Transactional
    public void save(Advertiser publisher){
       entityManager.persist(publisher);




    }
}
