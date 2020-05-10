package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.Address;
import com.zipcoder.fakeazon.models.Shop;
import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
public class UserServicesTest {

    @Autowired
    private UserServices userService;

    @MockBean
    private UserRepository userRepo;

    private final User mockUser = new User();

    @Test
    public void save(){
        given(userRepo.save(mockUser)).willReturn(mockUser);
        assertNotNull(mockUser);
    }

    @Test
    public void createUserTest(){
        given(userRepo.save(mockUser)).willReturn(mockUser);
        assertNotNull(userService.createUser(mockUser));
    }

    @Test
    public void findByIdTest(){
        given(userRepo.findById(1)).willReturn(Optional.of(mockUser));
        assertTrue(userService.findUserById(1).isPresent());
    }

    @Test
    public void findAllUsersTest(){
        List<User> users = new ArrayList<>(Arrays.asList(mockUser, new User(), new User()));
        given(userRepo.findAll()).willReturn(users);
        assertEquals(3, userService.findAllUsers().size());
    }

    @Test
    public void changeFirstName() {
        User user = new User();
        user.setFirstName("John");
        doReturn(Optional.of(user)).when(userRepo).findById(1);
        doReturn(user).when(userRepo).save(user);
        String newFirstName = "Jake";
        String actual = userService.changeFirstName(1,newFirstName).getFirstName();

        assertEquals(newFirstName,actual);
    }

    @Test
    public void changeLastName() {
        User user = new User();
        user.setLastName("Egbert");
        doReturn(Optional.of(user)).when(userRepo).findById(1);
        doReturn(user).when(userRepo).save(user);
        String newLastName = "Harley";
        String actual = userService.changeLastName(1,newLastName).getLastName();

        assertEquals(newLastName,actual);
    }

    @Test
    public void changeEmail() {
        User user = new User();
        user.setEmail("ectoBiologist@sburb.com");
        doReturn(Optional.of(user)).when(userRepo).findById(1);
        doReturn(user).when(userRepo).save(user);
        String newEmail = "golgothasTerror@sburb.com";
        String actual = userService.changeEmail(1,newEmail).getEmail();

        assertEquals(newEmail,actual);
    }

    @Test
    public void changeAddress() {
        Address oldAddress = new Address();
        User user = new User();
        user.setAddress(oldAddress);
        doReturn(Optional.of(user)).when(userRepo).findById(1);
        doReturn(user).when(userRepo).save(user);

        Address newAddress = new Address();
        Address actual = userService.changeAddress(1,newAddress).getAddress();

        assertEquals(newAddress,actual);
    }

    @Test
    public void changeShop() {
        Shop oldShop = new Shop();
        User user = new User();
        user.setShop(oldShop);
        doReturn(Optional.of(user)).when(userRepo).findById(1);
        doReturn(user).when(userRepo).save(user);

        Shop newShop = new Shop();
        Shop actual = userService.changeShop(1,newShop).getShop();

        assertEquals(newShop,actual);
    }

    @Test
    public void checkIfUserExistTest_fail(){
        doReturn(Optional.of(mockUser)).when(userRepo).findById(1);
        Assertions.assertThrows(Exception.class,
                    () -> userService.checkIfUserExists(2));
    }

    @Test
    public void createUserTest_fail(){
        User u = new User();
        String email = "yyy@xxx.zzz";
        u.setEmail(email);
        doReturn(Optional.of(u)).when(userRepo).findUserByEmail(email);
        Assertions.assertThrows(Exception.class,
                () -> userService.createUser(u));
    }

    @Test
    public void saveUserTest(){
        doReturn(mockUser).when(userRepo).save(mockUser);
        Assert.assertEquals(mockUser, userService.save(mockUser));
    }


}