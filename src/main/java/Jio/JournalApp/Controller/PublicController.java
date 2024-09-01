package Jio.JournalApp.Controller;

import Jio.JournalApp.Entity.Users;
import Jio.JournalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;

    @PostMapping
    public void saveUser(@RequestBody Users user)throws Exception
    {

        userService.createUser(user);
    }


}
