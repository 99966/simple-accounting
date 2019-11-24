package com.wuisee.hotel.repository;

import com.wuisee.hotel.dataobject.RoomCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceCategoryRepositoryTest {

    @Autowired
    private RoomCategoryRepository repository;

    @Test
    public void save() {
        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setCategoryType(1);
        roomCategory.setCategoryName("101房间");
        RoomCategory result = repository.save(roomCategory);

        assertNotNull(result);

    }
}