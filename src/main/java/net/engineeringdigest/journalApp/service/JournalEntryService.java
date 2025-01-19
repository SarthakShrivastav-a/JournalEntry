package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional  //Used to achieve atomicity like this entire piece of code is treated as  a single db operation!
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDate.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved); //get is called instead of set to preserve the existing elements of the list
            userService.saveEntry(user);
//          user.getJournalEntries().add(journalEntry);   Cannot add the journalEntry directly as we wont get id to be mapped for @DBRef
        } catch (Exception e) {
            throw new RuntimeException("Error while saving new entry",e);
        }
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getall(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getById(ObjectId getId){
        return journalEntryRepository.findById(getId);
    }
    public void deleteById(ObjectId Id){
        journalEntryRepository.deleteById(Id);
    }
}
