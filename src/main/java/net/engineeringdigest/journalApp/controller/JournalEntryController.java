package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/create")
    public String saveEntry(@RequestBody JournalEntry journalEntry){
        journalEntryService.saveEntry(journalEntry);
        return "Added Successfully";
    }
    @GetMapping("/get")
    public List<JournalEntry> getALL(){
        return journalEntryService.getall();
    }
    @GetMapping("/get/{getId}")
    public Optional<JournalEntry> getById(@PathVariable ObjectId getId){
        return Optional.ofNullable(journalEntryService.getById(getId).orElse(null));
    }
    @GetMapping("/delete/{getId}")
    public boolean deleteById(@PathVariable ObjectId getId){
        journalEntryService.deleteById(getId);
        return true;
    }
    @PutMapping("/update/{Id}")
    public boolean update(@PathVariable ObjectId Id,@RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.getById(Id).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle()!= null && !newEntry.getTitle().equals("")?newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!=null&& !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        assert old != null;
        journalEntryService.saveEntry(old);
        return true;
    }

}
