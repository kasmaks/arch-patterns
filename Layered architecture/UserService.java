public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void createUser(User user) {
		if (userRepository.findByUsername(user.getUsername()) != null) {
			throw new IllegalArgumentException("Username already exists.");
		}

		userRepository.save(user);

	}

	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}
}
