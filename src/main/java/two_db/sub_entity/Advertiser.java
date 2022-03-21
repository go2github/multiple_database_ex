package two_db.sub_entity;

import org.hibernate.annotations.GeneratorType;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "ADVERTISERS")
public class Advertiser implements Serializable {

    private static final long serialVersionUID = 746237126088051312L;

    @Id
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name", length = 255, nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
