package ro.studenthub.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mail {
    private String to;
    private String subject;
    private String body;

}
