package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/create")
    public ResponseEntity<JournalEntry> saveEntry(@RequestBody JournalEntry journalEntry){
        try{
            journalEntryService.saveEntry(journalEntry);
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

    @GetMapping("/delete/{getId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId getId){
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
