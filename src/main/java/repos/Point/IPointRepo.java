package repos.Point;

import domain.Point.Point;

import java.util.List;

public interface IPointRepo {
    List<Point> getAll(long userId, long gameId);
    List<Point> getAllByGame(long gameId);
    void save(Point point);
}