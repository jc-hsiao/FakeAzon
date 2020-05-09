package com.zipcoder.fakeazon.repositories;
import com.zipcoder.fakeazon.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    Optional<List<Order>> findOrdersByUser(Integer id);

}
