package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntry.setDate(LocalDate.now());
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
