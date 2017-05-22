package action;

import beans.Movie;
import beans.Player;
import beans.User;
import service.MovieService;
import service.PlayerService;
import service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigator")
@RequestScoped
public class Navigator {

	public String init() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");

		User user1 = new User();
		user1.setUsername("t");
		user1.setPassword("t");

		UserService userService = new UserService();
		userService.add(user);
		userService.add(user1);

		PlayerService playerService = new PlayerService();
		MovieService movieService = new MovieService();

		Player p1 = new Player();
		p1.setName("sd");
		playerService.add(p1);

		Player p2 = new Player();
		p2.setName("asd");
		playerService.add(p2);

		Movie movie = new Movie();
		movie.setTitle("slol");
		movie.setDirector("wac");
		movie.setGenre("xiao");
		movie.setYear(2015);
		movie.addPlayer(p1);
		movie.addPlayer(p2);
		movieService.add(movie);

		Movie movie1 = new Movie();
		movie1.setTitle("asf");
		movie1.setDirector("reh");
		movie1.setGenre("jtr");
		movie1.setYear(2017);
		movie1.addPlayer(p1);
		movieService.add(movie1);

		return "";
	}

}
