package Jio.JournalApp.service;

import Jio.JournalApp.Repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void before()
    {
        System.out.println("test cases are started");
    }
@AfterEach
public void after()
{
    System.out.println("test case completed");
}

    @ParameterizedTest
    @ValueSource(strings = {
           "Aman2.Agarwal",
           "Atul2.Prakash"
   })
    public void findByUserName(String username)
    {
        Assertions.assertNotNull(userRepository.findByUserName(username),"Failed for "+username);
    }


}
