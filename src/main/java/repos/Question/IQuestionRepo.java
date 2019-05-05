package repos.Question;

import domain.Question.Question;

import java.util.List;

public interface IQuestionRepo {
    List<Question> getAll(long gameId);
    void save(Question question);
}
