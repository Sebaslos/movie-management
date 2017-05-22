package beans;


import service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@ManagedBean(name = "user")
@SessionScoped
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Movie> movies = new ArrayList<>();

	public void isLogin(ComponentSystemEvent event) throws IOException {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		User user = (User) ec.getSessionMap().get("user");
		if (user.getUsername() == null) {
			ec.redirect("/");
		}
	}

	public String login() {
		UserService service = new UserService();
		try {
			User user = service.authenticate(this.username, this.password);
			this.id = user.id;
			this.username = user.username;
			this.password = user.password;
			this.movies = user.getMovies();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Unknown login, try again."));
			return null;
		}
		return "show";
	}

	public void logout() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		ec.redirect("/");
	}

	public boolean hasMovie(Movie movie) {
		return this.movies.contains(movie);
	}

	public void toggleMovie(Movie movie) {
		UserService userService = new UserService();
		if (hasMovie(movie)) {
			this.movies.remove(movie);
		} else  {
			this.movies.add(movie);
		}
		userService.update(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public void addMovie(Movie movie) {
		this.movies.add(movie);
	}
}
