package Jio.JournalApp.Controller;

import Jio.JournalApp.Entity.JournalEntry;
import Jio.JournalApp.Entity.Users;
import Jio.JournalApp.Repository.JournalEntryRepository;
import Jio.JournalApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JournalEntryRepository journalEntryRepository;
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<Users>>getAllUsers()
    {
        List<Users>usersList=userRepository.findAll();
        if(!usersList.isEmpty())
        {
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/getAllJournalEntries")
    public ResponseEntity<?> getAllJournalEntries()
    {
        List<JournalEntry>list=journalEntryRepository.findAll();
        if(!list.isEmpty())
        {
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>(list,HttpStatus.NOT_FOUND);
    }
}
