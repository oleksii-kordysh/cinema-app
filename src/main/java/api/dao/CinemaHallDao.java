package api.dao;

import api.model.CinemaHall;
import java.util.List;
import java.util.Optional;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    Optional<CinemaHall> get(Long id);

    List<CinemaHall> getAll();
}
