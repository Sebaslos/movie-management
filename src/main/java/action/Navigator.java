package action;

import beans.User;
import service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigator")
@RequestScoped
public class Navigator {

	public String init() {
		User user = new User();
		user.setUsername("wlm");
		user.setPassword("lol");

		UserService userService = new UserService();
		userService.add(user);

		return "";
	}

}
