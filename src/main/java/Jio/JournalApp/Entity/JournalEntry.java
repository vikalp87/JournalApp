package Jio.JournalApp.Entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "journalEntries")
public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private  String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
