package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {

    @Id
    private ObjectId Id;

    @Indexed(unique = true)  //mongodb annotation, we have to add a config in application prop manually telling SB to auto index

    @NonNull                //lombok annotation
    private String userName;
    @NonNull
    private String password;

    @DBRef  //mongo annotation (reference created for journal entries by the user)
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
