package api;

import api.exception.AuthenticationException;
import api.exception.RegistrationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import api.lib.Injector;
import api.model.CinemaHall;
import api.model.Movie;
import api.model.MovieSession;
import api.model.Order;
import api.model.User;
import api.security.AuthenticationService;
import api.service.CinemaHallService;
import api.service.MovieService;
import api.service.MovieSessionService;
import api.service.OrderService;
import api.service.ShoppingCartService;
import api.service.UserService;

public class Main {
    private static final Injector injector = Injector.getInstance("api");

    public static void main(String[] args) throws RegistrationException, AuthenticationException {
        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);

        CinemaHall firstCinemaHall = new CinemaHall();
        firstCinemaHall.setCapacity(100);
        firstCinemaHall.setDescription("first hall with capacity 100");

        CinemaHall secondCinemaHall = new CinemaHall();
        secondCinemaHall.setCapacity(200);
        secondCinemaHall.setDescription("second hall with capacity 200");

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(firstCinemaHall);
        cinemaHallService.add(secondCinemaHall);

        System.out.println(cinemaHallService.getAll());
        System.out.println(cinemaHallService.get(firstCinemaHall.getId()));

        MovieSession tomorrowMovieSession = new MovieSession();
        tomorrowMovieSession.setCinemaHall(firstCinemaHall);
        tomorrowMovieSession.setMovie(fastAndFurious);
        tomorrowMovieSession.setShowTime(LocalDateTime.now().plusDays(1L));

        MovieSession yesterdayMovieSession = new MovieSession();
        yesterdayMovieSession.setCinemaHall(firstCinemaHall);
        yesterdayMovieSession.setMovie(fastAndFurious);
        yesterdayMovieSession.setShowTime(LocalDateTime.now().minusDays(1L));

        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(tomorrowMovieSession);
        movieSessionService.add(yesterdayMovieSession);

        System.out.println(movieSessionService.get(yesterdayMovieSession.getId()));
        System.out.println(movieSessionService.findAvailableSessions(
                        fastAndFurious.getId(), LocalDate.now()));

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        authenticationService.register("email@email.com", "pass");
        authenticationService.login("email@email.com", "pass");

        UserService userService =
                (UserService) injector.getInstance(UserService.class);
        User userFromDB = userService.findByEmail(("email@email.com")).get();
        System.out.println(userFromDB);

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        System.out.println(shoppingCartService.getByUser(userFromDB));
        shoppingCartService.addSession(yesterdayMovieSession, userFromDB);
        System.out.println(shoppingCartService.getByUser(userFromDB));

        OrderService orderService =
                (OrderService) injector.getInstance(OrderService.class);
        Order yesterdayOrder =
                orderService.completeOrder(shoppingCartService.getByUser(userFromDB));
        System.out.println(yesterdayOrder);

        shoppingCartService.addSession(tomorrowMovieSession, userFromDB);
        Order tomorrowOrder =
                orderService.completeOrder(shoppingCartService.getByUser(userFromDB));
        System.out.println(tomorrowOrder);
        orderService.getOrdersHistory(userFromDB).forEach(System.out::println);
    }
}
