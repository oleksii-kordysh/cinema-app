package api.dao;

import java.util.List;
import java.util.Optional;
import api.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    Optional<Movie> get(Long id);

    List<Movie> getAll();
}
