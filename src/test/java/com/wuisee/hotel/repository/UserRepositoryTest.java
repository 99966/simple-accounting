package com.wuisee.hotel.repository;

import com.wuisee.hotel.dataobject.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final String USER_ACCOUNT = "zhangsan";

    @Test
    public void saveTest() {
        User user = new User();
        user.setUserName("张三");
        user.setUserPassword("123");
        user.setUserAccount("zhangsan1");

        User result = userRepository.save(user);

        assertNotNull(result);
    }

    @Test
    public void findTest() {
        User result = userRepository.findUserByUserAccount(USER_ACCOUNT);

        assertEquals(USER_ACCOUNT, result.getUserAccount());
    }

}