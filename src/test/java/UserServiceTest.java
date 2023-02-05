import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
@Mock
    UserRepository userRepository;
@InjectMocks
    UserService userService;

    @Test
    void whenGetNonLoginUser() {
        when(userRepository.getAllUsers()).
                thenReturn(List.of(new User("test1", "qwerty1")));

    }
}
