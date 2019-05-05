package domain.Point;

import domain.Game.Game;
import domain.Question.Question;
import domain.User.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Point")
public class Point implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Game game;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Question question;

    @Column(nullable=false, length=128)
    private long score;

    public Point() {}

    public Point(PointDTO pointDTO) {
        this.id = pointDTO.getId();
        this.game = pointDTO.getGame();
        this.user = pointDTO.getUser();
        this.question = pointDTO.getQuestion();
        this.score = pointDTO.getScore();
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}