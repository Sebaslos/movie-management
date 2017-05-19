package beans;


import service.MovieService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@ManagedBean(name = "movie")
@SessionScoped
public class Movie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String director;

	@ManyToMany
	private List<Player> players = new ArrayList<>();

	@Column(nullable = false)
	private int year;

	@Column(nullable = false)
	private String genre;

	@Transient
	private String search;

	@Transient
	private List<Movie> searchResult;

	public String addMovie() {
		System.out.println("add movie succeed");
		return "show";
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
