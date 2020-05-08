package com.zipcoder.fakeazon.repositories;

import com.zipcoder.fakeazon.models.ShoppingCart;
import com.zipcoder.fakeazon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {

    Optional<ShoppingCart> findShoppingCartByOwner_Id(Integer id);


}
