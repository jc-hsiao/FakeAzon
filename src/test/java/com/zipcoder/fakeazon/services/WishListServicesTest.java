package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.exception.IllegalArgumentException;
import com.zipcoder.fakeazon.exception.NotFoundException;
import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.models.WishList;
import com.zipcoder.fakeazon.repositories.WishListRepository;
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

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
public class WishListServicesTest {

    @Autowired
    private WishListServices service;
    @MockBean
    private UserServices userServices;
    @MockBean
    private ItemServices itemServices;
    @MockBean
    private WishListRepository repo;

    WishList mockList = new WishList();

    @Test
    public void findById() {
        given(repo.findById(1)).willReturn(Optional.of(mockList));
        assertTrue(service.findById(1).isPresent());
    }

    @Test
    public void createWishList() {
        given(repo.save(mockList)).willReturn(mockList);
        given(userServices.findUserById(1)).willReturn(Optional.of(new User()));
        Assert.assertEquals(mockList, service.createWishList(mockList,1));
    }

    @Test
    public void changeName() {
        WishList list = new WishList();
        list.setName("olf list name");
        given(repo.findById(1)).willReturn(Optional.of(list));
        given(repo.save(list)).willReturn(list);
        String newListName = "sport items";
        list.setName(newListName);

        Assert.assertEquals(newListName, service.changeName(1, newListName).getName());
    }

    @Test
    public void addItem() {
        WishList list = new WishList();
        Item item1 = new Item();
        int itemId = 1;
        item1.setId(itemId);
        list.getItems().add(item1);

        Item item2 = new Item();
        int itemId2 = 2;
        item2.setId(itemId2);

        given(repo.findById(1)).willReturn(Optional.of(list));
        given(itemServices.checkIfItemExists(2)).willReturn(item2);

        given(repo.save(list)).willReturn(list);

        Assert.assertEquals(2, service.addItem(1,2).getItems().size());
    }

    @Test
    public void addItem_fail() {
        WishList list = new WishList();
        Item item = new Item();
        int itemId = 1;
        item.setId(itemId);
        list.getItems().add(item);
        given(repo.findById(1)).willReturn(Optional.of(list));
        given(itemServices.checkIfItemExists(1)).willReturn(item);
        given(repo.save(list)).willReturn(list);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.addItem(1,1));
    }

    @Test
    public void removeItem() {
        WishList list = new WishList();
        Item item = new Item();
        int itemId = 1;
        item.setId(itemId);
        list.getItems().add(item);

        given(repo.findById(1)).willReturn(Optional.of(list));
        given(itemServices.checkIfItemExists(1)).willReturn(item);
        given(repo.save(list)).willReturn(list);
        Assert.assertEquals(0, service.removeItem(1,1).getItems().size());
    }

    @Test
    public void removeItem_fail() {
        WishList list = new WishList();
        Item item = new Item();
        int itemId = 1;
        item.setId(itemId);
        list.getItems().add(item);

        Item item2 = new Item();
        int itemId2 = 2;
        item2.setId(itemId2);

        given(repo.findById(1)).willReturn(Optional.of(list));
        given(itemServices.checkIfItemExists(2)).willReturn(item2);

        Assertions.assertThrows(NotFoundException.class,
                () -> service.removeItem(1,2));
    }


    @Test
    public void deleteWishList() {
        given(repo.findById(1)).willReturn(Optional.of(mockList));
        assertEquals(mockList,service.deleteWishList(1));
    }

    @Test
    public void checkIfWishListExists_fail() {
        doReturn(Optional.of(mockList)).when(repo).findById(1);
        Assertions.assertThrows(NotFoundException.class,
                () -> service.checkIfWishListExists(2));
    }

}