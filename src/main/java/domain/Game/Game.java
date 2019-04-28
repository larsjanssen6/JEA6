package domain.Game;

import domain.User;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Game")
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false, length=128)
    private String name;

    @Column(nullable=false, length=128)
    private String description;

    @Column(nullable=false)
    private Date created_at;

    public Game() {
    }

    public Game(GameDTO gameDTO) {
        this.id = gameDTO.getId();
        this.name = gameDTO.getName();
        this.description = gameDTO.getDescription();
        this.created_at = gameDTO.getCreated_at();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}