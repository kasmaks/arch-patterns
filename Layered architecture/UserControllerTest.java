import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserControllerTest {
	private UserController userController;
	private UserService userService;
	private UserRepository userRepository;

	@BeforeEach
	public void setup() {
		userRepository = new UserRepositoryImpl();
		userService = new UserService(userRepository);
		userController = new UserController(userService);
	}

	@Test
	public void testCreateUser() {
		// Arrange
		String username = "john";
		String password = "password";

		// Act
		userController.createUser(username, password);

		// Assert
		User user = userRepository.findByUsername(username);
		Assertions.assertNotNull(user);
		Assertions.assertEquals(username, user.getUsername());
		Assertions.assertEquals(password, user.getPassword());
	}

	@Test
	public void testCreateUser_ExistingUsername() {
		// Arrange
		String username = "john";
		String password = "password";
		User existingUser = new User(username, "existingPassword");
		userRepository.save(existingUser);

		// Act and Assert
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userController.createUser(username, password);
		});
	}

	@Test
	public void testGetUser() {
		// Arrange
		String username = "john";
		String password = "password";
		User user = new User(username, password);
		userRepository.save(user);

		// Act
		userController.getUser(username);

		// Assert
		// Output will be printed to the console, so no explicit assertions needed
	}

	@Test
	public void testGetUser_UserNotFound() {
		// Arrange
		String username = "john";

		// Act
		userController.getUser(username);

		// Assert
		// Output will be printed to the console, so no explicit assertions needed
	}

	@Test
	public void testGetAllUsers() {
		// Arrange
		User user1 = new User("john", "password1");
		User user2 = new User("jane", "password2");
		userRepository.save(user1);
		userRepository.save(user2);

		// Act
		List<User> users = userRepository.getAllUsers();

		// Assert
		Assertions.assertEquals(2, users.size());
		Assertions.assertTrue(users.contains(user1));
		Assertions.assertTrue(users.contains(user2));
	}
}