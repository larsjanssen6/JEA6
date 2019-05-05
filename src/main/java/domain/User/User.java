package domain.User;

import Authentication.UserDTO;
import domain.Link.Link;
import org.apache.commons.codec.digest.DigestUtils;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false, length=128)
    private String email;

    @Column(nullable=false, length=128)
    private String firstName;

    @Column(nullable=false, length=128)
    private String lastName;

    @Column(nullable=false, length=128) //sha-512 + hex
    private String password;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date registeredOn;

    @Transient
    private List<Link> links = new ArrayList<>();

    public User() {}

    public User(UserDTO user) {
        this.id           = user.getId();
        this.email        = user.getEmail();
        this.firstName    = user.getFirstName();
        this.lastName     = user.getLastName();
        this.password     = DigestUtils.sha512Hex(user.getPassword() );
        this.registeredOn = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password in SHA512 HEX representation
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String url, String rel)
    {
        Link link = new Link();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }
}