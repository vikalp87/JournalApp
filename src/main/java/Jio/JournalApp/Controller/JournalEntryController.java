package Jio.JournalApp.Controller;


import Jio.JournalApp.Entity.JournalEntry;
import Jio.JournalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @PostMapping("/save")
      public ResponseEntity<?> saveUserJournalEntries(@RequestBody JournalEntry journalEntry)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        return journalEntryService.saveUserJournalEntries(journalEntry,authentication.getName());
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUserJournalEntries()
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
       return  journalEntryService.getAllUserJournalEntries(authentication.getName());
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable ObjectId id)
    {
        return journalEntryService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

       return  journalEntryService.deleteById(id,authentication.getName());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry)
    {
       return  journalEntryService.updateById(id,journalEntry);
    }





}
