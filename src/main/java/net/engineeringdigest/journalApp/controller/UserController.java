package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            userService.saveEntry(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//    @PutMapping("/update/{username}")
//    public ResponseEntity<?> updateUser(@PathVariable String username,@RequestBody User user) {
//        try {
//            User userInDb = userService.findByUserName(username);
//            if (userInDb != null) {
//                userInDb.setUserName(user.getUserName() != null && !user.getUserName().equals("") ? user.getUserName() : userInDb.getUserName());
//                userInDb.setPassword(user.getPassword() != null && !user.getPassword().equals("") ? user.getPassword() : userInDb.getPassword());
//                userService.saveEntry(userInDb, userName);
//                return new ResponseEntity<>(userService.findByUserName(username), HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }



}
