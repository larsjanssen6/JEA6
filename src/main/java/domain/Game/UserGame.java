package domain.Game;

import domain.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserGame")
public class UserGame implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Game game;

    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;

    public UserGame() {
    }

    public UserGame(UserGameDTO userGameDTO) {
        this.id = userGameDTO.getId();
        this.user = userGameDTO.getUser();
        this.game = userGameDTO.getGame();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}