package ro.studenthub.backend.dto;

import lombok.Data;
import ro.studenthub.backend.model.CodeTypes;
import ro.studenthub.backend.model.ValidationCode;

import java.util.Date;
@Data
public class ValidationCodeDTO {
    private String code;
    private CodeTypes type;
    private String descriptor;

    public ValidationCode toDAO(int timeToLive){

        ValidationCode validationCode = new ValidationCode();
        validationCode.setCode(this.code);
        validationCode.setType(type);
        validationCode.setDescriptor(descriptor);
        Date now = new Date();
        validationCode.setCreationDate(now);
        long expirationMillis = now.getTime() + (timeToLive * 60L * 1000L);
        validationCode.setExpirationDate(new Date(expirationMillis));
        return validationCode;
    }
}
