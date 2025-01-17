package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "journal_entries")
//@Getter
//@Setter  from project lombok
@Data // this single annotation from lombok does all the config itself
public class JournalEntry {
    //  pojo class
    @Id
    private ObjectId id;
    private LocalDate date;
    @NonNull
    private String title;
    private String content;
}
