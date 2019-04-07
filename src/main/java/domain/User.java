package domain;

import Authentication.Group;
import Authentication.UserDTO;
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
    @Column(unique=true, nullable=false, length=128)
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

    @ElementCollection(targetClass = Group.class)
    @CollectionTable(name = "USERS_GROUPS",
            joinColumns       = @JoinColumn(name = "email", nullable=false),
            uniqueConstraints = { @UniqueConstraint(columnNames={"email","groupname"}) } )
    @Enumerated(EnumType.STRING)
    @Column(name="groupname", length=64, nullable=false)
    private List<Group> groups;

    @Transient
    private List<Link> links = new ArrayList<>();

    public User(){

    }

    public User(UserDTO user){

        if (user.getPassword1() == null || user.getPassword1().length() == 0
                || !user.getPassword1().equals(user.getPassword2()) )
            throw new RuntimeException("Password 1 and Password 2 have to be equal (typo?)");

        this.email        = user.getEmail();
        this.firstName    = user.getFirstName();
        this.lastName     = user.getLastName();
        this.password     = DigestUtils.sha512Hex(user.getPassword1() );
        this.registeredOn = new Date();
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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", firstName=" + firstName
                + ", lastName=" + lastName + ", password=" + password
                + ", registeredOn=" + registeredOn + ", groups=" + groups + "]";
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