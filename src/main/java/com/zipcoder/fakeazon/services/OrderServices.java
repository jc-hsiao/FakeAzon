package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zipcoder.fakeazon.models.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServices {

    private OrderRepository orderRepo;

    @Autowired
    public OrderServices(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order save(Order order){
        return orderRepo.save(order);
    }
    // POST
    public Order createOrder(Order order){
        order.setDatePlaced(LocalDate.now());
        return orderRepo.save(order);
    }
    // GET
    public Optional<Order> findOne(int id){
        return orderRepo.findById(id);
    }

    public List<Order> findAll(){
        return orderRepo.findAll();
    }

    public Optional<List<Order>> findOrdersByUser(int id){
        return orderRepo.findOrdersByUser(id);
    }

    // PUT
    public Order updateStatus(int id){
        Order order = orderRepo.getOne(id);
        if(order.getStatus() == 0){
            order.setStatus(1);
        }
        return orderRepo.save(order);
    }
}
