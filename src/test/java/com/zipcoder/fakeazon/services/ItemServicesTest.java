package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.repositories.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class ItemServicesTest {

    @Autowired
    private ItemServices service;

    @MockBean
    private ItemRepository repository;

    Item mockItem = new Item();

    @Test
    @DisplayName("Test Check Item Existence: PASS")
    public void testItemExists() throws Exception {
        doReturn(Optional.of(mockItem)).when(repository).findById(1);
        assertNotNull(service.checkIfItemExists(1));
    }

    @Test
    @DisplayName("Test Check Item Existence: FAIL")
    public void testItemNotExist() throws Exception {
        Assertions.assertThrows(Exception.class,
                () -> service.checkIfItemExists(15));
    }

    @Test
    @DisplayName("Test Find By Id: PASS")
    public void testFindByIdPass(){
        doReturn(Optional.of(mockItem)).when(repository).findById(1);
        assertTrue(service.findOne(1).isPresent());
    }

    @Test
    @DisplayName("Test Find All Items")
    public void testFindAllItems(){
        List<Item> items = new ArrayList<>(Arrays.asList(new Item(), new Item()));
        doReturn(items).when(repository).findAll();
        assertEquals(2, service.findAll().size());
    }

    @Test
    @DisplayName("Test Find All Items By Shop")
    public void testFindAllItemsByShop(){
        List<Item> items = new ArrayList<>(Arrays.asList(new Item(), new Item()));
        doReturn(items).when(repository).findAllByShop_Id(1);
        assertEquals(2, service.findAllByShop(1).size());
    }

    @Test
    @DisplayName("Test Save Item")
    public void testSaveItem(){
        doReturn(mockItem).when(repository).save(any());
        assertNotNull(service.saveItem(mockItem));
    }

    @Test
    @DisplayName("Test Delete Item: PASS")
    public void testDeleteItem(){
        doReturn(Optional.of(mockItem)).when(repository).findById(1);
        assertTrue(service.deleteItem(1));
    }

    @Test
    @DisplayName("Test Delete Item: FAIL")
    public void testDeleteItemFail(){
        doReturn(Optional.of(mockItem)).when(repository).findById(1);
        assertFalse(service.deleteItem(5));
    }

    @Test
    @DisplayName("Test Increase Inventory Count")
    public void testIncreaseInventoryCount() throws Exception {
        Integer increase = 150;
        doReturn(Optional.of(mockItem)).when(repository).findById(1);
        doReturn(mockItem).when(repository).save(mockItem);

        Integer expected = 150;
        Integer actual = service.increaseInventoryCount(1,increase).getInventoryCount();

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test Decrease Inventory Count")
    public void testDecreaseInventoryCount() throws Exception {
        Integer decrease = 150;
        Item newMockItem = new Item();
        newMockItem.setInventoryCount(200);
        doReturn(Optional.of(newMockItem)).when(repository).findById(1);
        doReturn(newMockItem).when(repository).save(newMockItem);

        Integer expected = 50;
        Integer actual = service.decreaseInventoryCount(1,decrease).getInventoryCount();

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test Update Item Name")
    public void testUpdateItemName() throws Exception {
        Item testItem = new Item();
        testItem.setName("Test");
        doReturn(Optional.of(testItem)).when(repository).findById(1);
        doReturn(testItem).when(repository).save(testItem);

        String expected = "Updated";

        String actual = service.updateName(1,expected).getName();

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test Update Item Price")
    public void testUpdateItemPrice() throws Exception {
        Item testItem = new Item();
        testItem.setPrice(123.45);
        doReturn(Optional.of(testItem)).when(repository).findById(1);
        doReturn(testItem).when(repository).save(testItem);

        Double expected = 43.21;

        Double actual = service.updatePrice(1,expected).getPrice();

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test Update Item Image URL")
    public void testUpdateItemImageUrl() throws Exception {
        Item testItem = new Item();
        testItem.setImageUrl("imgur.com/test");
        doReturn(Optional.of(testItem)).when(repository).findById(1);
        doReturn(testItem).when(repository).save(testItem);

        String expected = "imgur.com/updated";

        String actual = service.updateImageUrl(1,expected).getImageUrl();

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test Update Item Description")
    public void testUpdateItemDescription() throws Exception {
        Item testItem = new Item();
        testItem.setDescription("Test Test Test");
        doReturn(Optional.of(testItem)).when(repository).findById(1);
        doReturn(testItem).when(repository).save(testItem);

        String expected = "Updated Updated Updated";

        String actual = service.updateDescription(1,expected).getDescription();

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test Add Item Tags")
    public void testAddItemTags() throws Exception {
        Item testItem = new Item();
        testItem.setItemTags(new ArrayList<>(Arrays.asList("Tag1","Tag2")));
        doReturn(Optional.of(testItem)).when(repository).findById(1);
        doReturn(testItem).when(repository).save(testItem);

        String expected = "Tag3";

        String actual = service.addItemTags(1,new String[]{"Tag3"}).getItemTags().get(2);

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test Remove Item Tags")
    public void testRemoveItemTags() throws Exception {
        Item testItem = new Item();
        testItem.setItemTags(new ArrayList<>(Arrays.asList("Tag1","Tag2","Tag3")));
        doReturn(Optional.of(testItem)).when(repository).findById(1);
        doReturn(testItem).when(repository).save(testItem);

        String expected = "Tag3";

        String actual = service.removeItemTags(1,new String[]{"Tag2"}).getItemTags().get(1);

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test Add Rating")
    public void testAddRating() throws Exception {
        Item testItem = new Item();
        testItem.setRating(new ArrayList<>(Arrays.asList(4.50,4.80)));
        doReturn(Optional.of(testItem)).when(repository).findById(1);
        doReturn(testItem).when(repository).save(testItem);

        Double expected = 5.00;

        Double actual = service.addRating(1,5.00).getRating().get(2);

        assertEquals(expected,actual);
    }


    @Test
    @DisplayName("Test Rating System")
    public void testGetRating() throws Exception {
        Item mockItem = new Item();
        List<Double> ratings = new ArrayList<>(Arrays.asList(2.00,3.00,4.00,5.00));
        mockItem.setRating(ratings);
        doReturn(Optional.of(mockItem)).when(repository).findById(1);

        Double expectedRating = 3.50;
        Double actualRating = service.getRating(1);
        assertEquals(expectedRating,actualRating);
    }

}
