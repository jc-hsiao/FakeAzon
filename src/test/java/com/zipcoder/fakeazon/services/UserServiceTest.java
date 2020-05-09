package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
public class UserServiceTest {

    @Autowired
    private UserServices userService;

    @MockBean
    private UserRepository userRepo;

    private User mockUser = new User();

    @Test
    public void createUserTest() throws Exception{
        given(userRepo.save(mockUser)).willReturn(mockUser);
        assertNotNull(userService.createUser(mockUser));
    }

    @Test
    public void findByIdTest() throws Exception{
        given(userRepo.findById(1)).willReturn(Optional.of(mockUser));
        assertTrue(userService.findUserById(1).isPresent());
    }

    @Test
    public void findAllUsersTest() throws Exception{
        List<User> users = new ArrayList<>(Arrays.asList(mockUser, new User(), new User()));
        given(userRepo.findAll()).willReturn(users);
        assertEquals(3, userService.findAllUsers().size());
    }
}
