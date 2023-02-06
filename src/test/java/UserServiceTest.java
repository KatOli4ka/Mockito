import org.example.exception.UserNonUniqueException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.NoInteractions;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @Test
    void whenRepositoryReturnsNullThenSomethingHappened() {
        when(userRepository.getAllUsers()).thenReturn(null);
        assertThat(userService.getAllLogins()).isEqualTo(0);
    }

    @Test
    void whenCorrectUserIsAddedThenAddUserIsCalledFromRepository() {
        when(userRepository.getAllUsers()).thenReturn(List.of());
        when(userRepository.addUser(ArgumentMatchers.any())).thenReturn(null);
        userService.addUser("test1", "qwerty1");
        verify(userRepository).addUser(any());
    }
    @Test
    void whenInvalidUserIsPassedThenServiceThrowsException() {
        assertThatThrownBy(() -> userService.addUser("", "0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Пользователь должен быть определен!");
        verify(userRepository, new NoInteractions()).getAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
    }
    @Test
    void whenExistingUserIsPassedThenServiceThrowsException() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("test2", "qwerty2")));
        assertThatThrownBy(() -> userService.addUser("test2", "qwerty2"))
                .isInstanceOf(UserNonUniqueException.class)
                .hasMessage("Пользователь не уникален!");
    }
    @Test
    void whenNetworkExceptionIsRaisedThenServiceReturnsZero() {
        when(userRepository.getAllUsers()).thenThrow(new RuntimeException());
        assertThat(userService.getAllLogins()).isEqualTo(0);
    }


}
