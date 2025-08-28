package ro.studenthub.backend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;

    public UserDTO(Long id, String username, String email, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public UserDTO() {
        this.id = null;
        this.username = null;
        this.email = null;
        this.phone = null;
    }
}
