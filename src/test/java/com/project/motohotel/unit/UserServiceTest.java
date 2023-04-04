package com.project.motohotel.unit;

import com.project.motohotel.binding.UserAdd;
import com.project.motohotel.entity.Role;
import com.project.motohotel.entity.User;
import com.project.motohotel.repository.RoleRepository;
import com.project.motohotel.repository.UserRepository;
import com.project.motohotel.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    @Mock
    private PasswordEncoder encoder;
    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void findUserByUsernameShouldReturnUser() {
        //generate data for mock user
        User user = new User("test", "1234", null);
        Mockito.when(repository.findByUsername(anyString())).thenReturn(user);
        //call method to test
        User user1 = userService.findByUsername("test");
        Assertions.assertEquals(user1.getUsername(), user.getUsername());
        Assertions.assertEquals(user1.getPassword(), user.getPassword());
    }
    @Test
    public void getAllUsersShouldReturnUsers(){
        User userOne = new User("test", "1234", null);
        User userTwo = new User("test2", "1234", null);
        Mockito.when(repository.findAll()).thenReturn(List.of(userOne, userTwo));
        List<User> users = userService.getAllUsers();
        Assertions.assertEquals(users.size(), 2);
        Assertions.assertEquals(users.get(0).getUsername(), userOne.getUsername());
        Assertions.assertEquals(users.get(1).getUsername(), userTwo.getUsername());
    }

    @Test
    public  void deleteUserShouldDelete(){
        userService.deleteUser("1");
        Mockito.verify(repository, Mockito.times(1)).deleteById("1");
    }
    @Test
    public void findByIdShouldReturnUser(){
        User user = new User("test", "1234", null);
        Mockito.when(repository.findById(anyString())).thenReturn(Optional.of(user));
        User user1 = userService.findById("1");
        Assertions.assertEquals(user1.getUsername(), user.getUsername());
        Assertions.assertEquals(user1.getPassword(), user.getPassword());

    }
    //implement to test the method addUser in UserServiceImpl
    @Test
    public void testAddUser(){
//        UserAdd add = new UserAdd("test","1234", "ADMIN");
//        List<Role> admin = List.of(new Role("ADMIN"));
//        User test = new User("test", "1234",admin);
//        Mockito.when(roleRepository.findAll()).thenReturn(admin);
//        Mockito.when(mapper.map(add, User.class)).thenReturn(test);
//        userService.addUser(add);
//        Mockito.verify(repository, Mockito.times(1)).save(any());

    }


}




