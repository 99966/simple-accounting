package com.wuisee.hotel.repository;

import com.wuisee.hotel.dataobject.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Integer> {
}
