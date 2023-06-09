import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;


public class UserRepositoryImpl implements UserRepository {
	private final Map<String, User> users = new HashMap<>();

	@Override
	public void save(User user) {
		users.put(user.getUsername(), user);
		System.out.println("User saved: " + user.getUsername());
	}

	@Override
	public User findByUsername(String username) {
		return users.get(username);
	}

	@Override
	public List<User> getAllUsers() {
		return new ArrayList<>(users.values());
	}
}