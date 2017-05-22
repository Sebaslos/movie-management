package beans;


import service.MovieService;
import service.PlayerService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@ManagedBean(name = "movie")
//@SessionScoped
@ViewScoped
public class Movie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "title can't be null")
	@Column(nullable = false)
	private String title;

	@NotNull(message = "director can't be null")
	@Column(nullable = false)
	private String director;

	@ManyToMany
	private List<Player> players = new ArrayList<>();

	@NotNull(message = "year can't be null")
	@Max(value = 2017, message = "year can't greater than 2017")
	@Min(value = 1900, message = "year can't less than 1900")
	@Column(nullable = false)
	private int year;

	@NotNull(message = "genre can't be null")
	@Column(nullable = false)
	private String genre;

	@Transient
	private String search;

	@Transient
	private List<Movie> searchResult;

	public String saveMovie() {
		PlayerService playerService = new PlayerService();
		for (Player player : players) {
			if (playerService.findByName(player.getName()) == null) {
				playerService.add(player);
			}
		}

		MovieService movieService = new MovieService();
		movieService.add(this);

		System.out.println("add movie succeed");
		return "show";
	}

	public void addPlayer(String playername) {
		Player player = new Player();
		player.setName(playername);

		if (!players.contains(player))
			addPlayer(player);
	}

	public void searchFromMovieDB(ValueChangeEvent evt) {
		this.search = (String) evt.getNewValue();
		MovieService movieService = new MovieService();
		this.searchResult = movieService.searchMovie(this.search);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void addPlayer(Player player) {
		this.players.add(player);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Movie> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<Movie> searchResult) {
		this.searchResult = searchResult;
	}
}
