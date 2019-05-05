package domain.Question;

import domain.Game.Game;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Question")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Game game;

    @Column(nullable=false, length=128)
    private String question;

    @Column(nullable=false, length=128)
    private String answer;

    @Column(nullable=false, length=128)
    private long points;

    public Question() { }

    public Question(QuestionDTO questionDTO) {
        this.id = questionDTO.getId();
        this.game = questionDTO.getGame();
        this.question = questionDTO.getQuestion();
        this.answer = questionDTO.getAnswer();
        this.points = questionDTO.getPoints();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }
}