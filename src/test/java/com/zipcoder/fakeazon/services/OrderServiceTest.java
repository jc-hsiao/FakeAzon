package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.Order;
import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.repositories.OrderRepository;
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
public class OrderServiceTest {

    @Autowired
    private OrderServices service;

    @MockBean
    private OrderRepository orderRepo;

    private Order mockOrder = new Order();

    @Test
    public void saveOrderTest() throws Exception{
        given(orderRepo.save(mockOrder)).willReturn(mockOrder);
        assertNotNull(service.save(mockOrder));
    }

    @Test
    public void createOrderTest() throws Exception{
        given(orderRepo.save(mockOrder)).willReturn(mockOrder);
        assertNotNull(service.createOrder(mockOrder));
    }

    @Test
    public void findOneTest() throws Exception{
        given(orderRepo.findById(1)).willReturn(Optional.of(mockOrder));
        assertTrue(service.findOne(1).isPresent());
    }

    @Test
    public void findAllOrdersTest() throws Exception {
        List<Order> orders = new ArrayList<>(Arrays.asList(mockOrder, new Order(), new Order()));
        given(orderRepo.findAll()).willReturn(orders);
        assertEquals(3, service.findAll().size());
    }

    @Test
    public void findOrdersByUserTest() throws Exception{
        User user = new User();
        List<Order> orders = new ArrayList<>(Arrays.asList(mockOrder, new Order(), new Order()));
        given(orderRepo.findOrdersByUser(user.getId())).willReturn(Optional.of(orders));
        assertTrue(service.findOrdersByUser(user.getId()).isPresent());
    }

    @Test
    public void updateStatusTest() throws Exception{
        mockOrder.setStatus(0);
        given(orderRepo.getOne(1)).willReturn(mockOrder);
        service.updateStatus(1);
        assertEquals(1, mockOrder.getStatus());
    }

}
