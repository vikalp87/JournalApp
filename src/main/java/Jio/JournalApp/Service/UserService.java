package Jio.JournalApp.Service;

import Jio.JournalApp.Entity.Users;
import Jio.JournalApp.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

//    private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    public void createUser(Users user)
    {
        try {
            String encodedpassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedpassword);
            user.setRoles(Arrays.asList("User"));
            userRepository.save(user);
        }
        catch (Exception e)
        {
            log.info("Username is already taken");
            log.info("error occurered for usernmae {}",user.getUserName());
        }
    }
    public void saveExistingUser(Users user)
    {
        userRepository.save(user);

    }
    public ResponseEntity<?> updateUser(Users user)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Users userInfo=userRepository.findByUserName(authentication.getName());
        if(userInfo!=null) {
            userInfo.setUserName(user.getUserName());

            userInfo.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(userInfo);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    public void deleteByUserName(String name)
    {
        userRepository.deleteByUserName(name);
    }

}
