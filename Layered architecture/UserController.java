public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	public void createUser(String username, String password) {

		User newUser = new User(username, password);
		userService.createUser(newUser);

		System.out.println("User created successfully.");
	}

	public void getUser(String username) {
		User user = userService.getUser(username);

		if (user != null) {
			System.out.println("Username: " + user.getUsername());
			System.out.println("Password: " + user.getPassword());
		} else {
			System.out.println("User not found.");
		}
	}
}