package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @PostMapping("/create/{userName}")
    public ResponseEntity<JournalEntry> saveEntry(@RequestBody JournalEntry journalEntry,@PathVariable String userName){
        try{
            journalEntryService.saveEntry(journalEntry,userName);
//            user.setJournalEntries(journalEntry);

            return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get")
    public List<JournalEntry> getALL(){
        return journalEntryService.getall();
    }
    @GetMapping("/get/{getId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId getId){

        Optional<JournalEntry> journalEntry = journalEntryService.getById(getId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @GetMapping("/get/name/{userName}")
    public ResponseEntity<?> getByName(@PathVariable String userName) {
        List<?> entry = userService.getEntries(userName);
        if (entry != null && !entry.isEmpty()) {
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    @DeleteMapping("/delete/{getId}")
//    public ResponseEntity<?> deleteById(@PathVariable ObjectId getId){
//        journalEntryService.deleteById(getId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


    @DeleteMapping("/delete/{userName}/{getId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId getId,@PathVariable String userName){
        userService.delUserJourEntry(getId,userName);
        journalEntryService.deleteById(getId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<?> update(@PathVariable ObjectId Id,@RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.getById(Id).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle()!= null && !newEntry.getTitle().equals("")?newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!=null&& !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
