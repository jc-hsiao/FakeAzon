package com.zipcoder.fakeazon.repositories;

import com.zipcoder.fakeazon.models.ItemCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCountRepository extends JpaRepository<ItemCount, Integer> {
}
