package ro.studenthub.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import ro.studenthub.backend.dto.UserDTO;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;

    public UserDTO toDTO(){
        return new UserDTO(this.id,this.username,this.email,this.phone);
    }
}
