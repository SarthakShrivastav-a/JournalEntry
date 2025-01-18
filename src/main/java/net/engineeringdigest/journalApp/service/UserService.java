package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    public void deleteById(ObjectId Id){
        userRepository.deleteById(Id);
    }

    public void delUserJourEntry(ObjectId id,String userName){
        User user = userRepository.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userRepository.save(user);
    }

    public List<?> getEntries(String userName){
        User user = userRepository.findByUserName(userName);
        return user.getJournalEntries();
    }

}
