import org.assertj.core.api.Assertions;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {
    private UserRepository userRepository;
    User user1 = new User("test1", "qwerty1");
    User user2 = new User("test2", "qwerty2");
    User user3 = new User("test3", "qwerty3");

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void getNullUserList() {
        Collection<User> expected = userRepository.getAllUsers();
        Assertions.assertThat(expected).hasSize(0);
    }


    @Test
    public void getAllUsersList() {
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);
        Collection<User> expected = userRepository.getAllUsers();
        Collection<User> actual = new ArrayList<>();
        actual.add(user1);
        actual.add(user2);
        actual.add(user3);
        assertEquals(expected, actual);
    }

    @Test
    public void testUserLogin() {
        userRepository.addUser(user1);
        Optional<User> user1 = userRepository.findUserByLogin("test1");
        assertTrue(user1.isPresent());
    }

    @Test
    public void testNonUserLogin() {
        userRepository.addUser(user3);
        Optional<User> user3 = userRepository.findUserByLogin("test12");
        assertFalse(user3.isPresent());
    }

    @Test
    public void testUserLoginAndPassword() {
        userRepository.addUser(user3);
        Optional<User> user3 = userRepository.findUserByLoginAndPassword("test3", "qwerty3");
        assertTrue(user3.isPresent());
    }
    @Test
    public void testEqualsPasswordDifferentLogin() {
        userRepository.addUser(user2);
        Optional<User> user2 = userRepository.findUserByLoginAndPassword("test1", "qwerty2");
        assertTrue(user2.isPresent());
    }
    @Test
    public void testEqualsLoginDifferentPassword() {
        userRepository.addUser(user1);
        Optional<User> user1 = userRepository.findUserByLoginAndPassword("test1", "qwerty2");
        assertTrue(user1.isPresent());
    }

}
