package ro.studenthub.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table(name="validationcodes")
public class ValidationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "codetypes")
    private CodeTypes type;
    private String descriptor;
    @Column(name="creationdate")
    private Date creationDate;
    @Column(name="expirationdate")
    private Date expirationDate;
}
