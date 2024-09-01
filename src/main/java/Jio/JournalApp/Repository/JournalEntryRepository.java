package Jio.JournalApp.Repository;

import Jio.JournalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {




}
