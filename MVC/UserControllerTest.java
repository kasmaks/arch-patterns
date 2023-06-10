import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserControllerTest {
    private UserModel model;
    private UserView view;
    private UserController controller;

    @BeforeEach
    void setup() {
        model = new UserModel();
        view = Mockito.mock(UserView.class);
        controller = new UserController(model, view);
    }

    @Test
    void testUpdateUserView() {
        String username = "maksym.kasner";
        String email = "maksym.kasner@example.com";
        model.setUsername(username);
        model.setEmail(email);

        controller.updateUserView();

        Mockito.verify(view).displayUserDetails(username, email);
    }

    @Test
    void testSetUsername() {
        String username = "maksym.kasner";
        controller.setUsername(username);

        Assertions.assertEquals(username, model.getUsername());
    }

    @Test
    void testSetEmail() {
        String email = "maksym.kasner@example.com";
        controller.setEmail(email);

        Assertions.assertEquals(email, model.getEmail());
    }
}