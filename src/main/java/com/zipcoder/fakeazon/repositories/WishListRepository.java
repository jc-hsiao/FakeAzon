package com.zipcoder.fakeazon.repositories;

import com.zipcoder.fakeazon.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Integer> {
}
