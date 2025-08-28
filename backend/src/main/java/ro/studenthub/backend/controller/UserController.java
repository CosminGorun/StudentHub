package ro.studenthub.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.studenthub.backend.dto.UserDTO;
import ro.studenthub.backend.model.User;
import ro.studenthub.backend.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public List<UserDTO> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/findByUsername")
    public User getByUsername(@RequestParam("username") String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/existUsername")
    public boolean existUsername(@RequestParam("username") String username) {
        if(userService.findByUsername(username) != null) {
            return true;
        }
        return false;
    }

    @PostMapping("/createAccount")
    public String createNewUser(@RequestBody User user) {
        //validare username unic
        if(existUsername(user.getUsername())){
            return "Username already exists";
        }
        //validare mail
         if(!user.getEmail().matches("[a-zA-Z0-9]+@[a-z].[a-z]")){
             return "Email address format is incorrect";
         }
        //validare numar telefon
         if(user.getPhone().length()!=10){
             return "Phone number format is incorrect";
         }
        if(userService.createUser(user)!=null){
            return "User created";
        }
        return "User creation failed";
    }

}
