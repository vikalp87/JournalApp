package Jio.JournalApp.Service;

import Jio.JournalApp.Entity.Users;
import Jio.JournalApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Users user= userRepository.findByUserName(username);
         if(user!=null)
         {
            return  User
                     .builder()
                     .username(user.getUserName())
                     .password(user.getPassword())
                     .roles(user.getRoles().toArray(new String[0]))
                    .build() ;
         }
         throw  new UsernameNotFoundException("Username not found");
    }
}
