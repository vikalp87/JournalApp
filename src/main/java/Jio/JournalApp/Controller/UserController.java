package Jio.JournalApp.Controller;

import Jio.JournalApp.Entity.Users;
import Jio.JournalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @PutMapping
    public ResponseEntity<?> Update(@RequestBody Users users)
    {
       return  userService.updateUser(users);
    }
    @DeleteMapping
    public void deleteUser()
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUserName(authentication.getName());

    }


}
