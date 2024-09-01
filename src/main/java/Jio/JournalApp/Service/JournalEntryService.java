package Jio.JournalApp.Service;

import Jio.JournalApp.Entity.JournalEntry;
import Jio.JournalApp.Entity.Users;
import Jio.JournalApp.Repository.JournalEntryRepository;
import Jio.JournalApp.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

@Service
public class JournalEntryService {

    @Autowired
    JournalEntryRepository journalEntryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;




    @Transactional
    public ResponseEntity<?>saveUserJournalEntries(JournalEntry journalEntry,String username)
    {
        Users user= userRepository.findByUserName(username);
        journalEntry.setCreatedOn(LocalDateTime.now().plusMinutes(330));
        JournalEntry saved=journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveExistingUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    public ResponseEntity<?> getAllUserJournalEntries(String username)
    {      Users user= userRepository.findByUserName(username);
           List<JournalEntry>journalEntries=user.getJournalEntries();

      if(journalEntries!=null||!journalEntries.isEmpty())
          return new ResponseEntity<>(journalEntries,HttpStatus.OK);


      return new ResponseEntity<>("No Journal Entries",HttpStatus.OK);
    }
    public ResponseEntity<?> getById(ObjectId id)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        Users user=userRepository.findByUserName(name);
        List<JournalEntry>newlist= user.getJournalEntries().stream().filter((x)->x.getId().equals(id)).collect(Collectors.toList());
        if(!newlist.isEmpty())
        {
            Optional<JournalEntry> op=journalEntryRepository.findById(id);
            if(op.isPresent())
                return new ResponseEntity<>(op.get(),HttpStatus.OK);


        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<?> deleteById(ObjectId id,String username)
    {
        Users user= userRepository.findByUserName(username);
        List<JournalEntry>list=user.getJournalEntries();
       Iterator<JournalEntry>iterator= list.iterator();
        List<JournalEntry>filteredList= list.stream().filter((x)->x.getId().equals(id)).collect(Collectors.toList());
       if(!filteredList.isEmpty()) {
           while (iterator.hasNext()) {
               JournalEntry journalEntry = iterator.next();
               if (journalEntry.getId().equals(id)) {
                   iterator.remove();
               }
           }

           journalEntryRepository.deleteById(id);
           userService.saveExistingUser(user);
           return new ResponseEntity<>(HttpStatus.OK);

       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> updateById(ObjectId id,JournalEntry journalEntry)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Users user=userRepository.findByUserName(authentication.getName());
       List<JournalEntry>filteredList= user.getJournalEntries().stream().filter((x)->x.getId().equals(id)).collect(Collectors.toList());
        if(!filteredList.isEmpty())
        {
            Optional<JournalEntry>op=journalEntryRepository.findById(id);
            if(op.isPresent()) {
                JournalEntry journalEntryOld = op.get();
                if (journalEntry.getTitle() != null || journalEntry.getContent() != null)
                    journalEntryOld.setUpdatedOn(LocalDateTime.now().plusMinutes(330));
                journalEntryOld.setTitle(journalEntry.getTitle() != null ? journalEntry.getTitle() : journalEntryOld.getTitle());
                journalEntryOld.setContent(journalEntry.getContent() != null ? journalEntry.getContent() : journalEntryOld.getContent());
                journalEntryRepository.save(journalEntryOld);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
