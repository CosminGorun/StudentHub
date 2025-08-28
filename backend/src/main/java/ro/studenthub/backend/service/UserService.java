package ro.studenthub.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.studenthub.backend.dto.UserDTO;
import ro.studenthub.backend.model.User;
import ro.studenthub.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean save(User user){
        try{
            userRepository.save(user);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public List<UserDTO> findAllUsers(){

        List<User> users=userRepository.findAll();
        List<UserDTO> userDTOs=new ArrayList<>();
        for(User user:users){
            userDTOs.add(user.toDTO());
        }
        return userDTOs;
    }
    public User findByUsername(String username){return userRepository.findByUsername(username).orElse(null);}
    public User createUser(User user){
        return userRepository.save(user);
    }
    public boolean findByEmail(String email){
        return !userRepository.findByEmail(email).isEmpty();
    }
    public UserDTO loginWithUsername(String username, String password){
        try {
            User user = findByUsername(username);
            if(user==null){return null;}
            if (user.getPassword().equals(password)) {
                return user.toDTO();
            }
            return null;
        }
        catch(Exception e){
            return null;
        }
    }
}
