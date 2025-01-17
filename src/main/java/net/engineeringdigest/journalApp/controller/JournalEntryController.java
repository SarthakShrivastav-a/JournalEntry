package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

}
