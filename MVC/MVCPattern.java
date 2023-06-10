class UserModel {
    private String username;
    private String password;
    private String email;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

class UserView {
    public void displayUserDetails(String username, String email) {
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
    }
}

class UserController {
    private UserModel model;
    private UserView view;

    public UserController(UserModel model, UserView view) {
        this.model = model;
        this.view = view;
    }

    public void setUsername(String username) {
        model.setUsername(username);
    }

    public void setPassword(String password) {
        model.setPassword(password);
    }

    public void setEmail(String email) {
        model.setEmail(email);
    }

    public void updateUserView() {
        String username = model.getUsername();
        String email = model.getEmail();
        view.displayUserDetails(username, email);
    }
}

public class MVCPattern {
    public static void main(String[] args) {
        UserModel model = new UserModel();
        UserView view = new UserView();
        UserController controller = new UserController(model, view);

        controller.setUsername("maksym.kasner");
        controller.setEmail("maksym.kasner@example.com");

        controller.updateUserView();
    }
}