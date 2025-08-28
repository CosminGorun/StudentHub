package ro.studenthub.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.studenthub.backend.dto.UserDTO;
import ro.studenthub.backend.dto.ValidationCodeDTO;
import ro.studenthub.backend.model.Mail;
import ro.studenthub.backend.model.ValidationCode;
import ro.studenthub.backend.service.MailService;
import ro.studenthub.backend.service.ValidationCodeService;

import java.util.Map;

import static ro.studenthub.backend.model.CodeTypes.EMAILVALIDATION;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
      private MailService mailSender;
    @Autowired
      private ValidationCodeService validationCodeService;

      @PostMapping("/send")
      public void sendMail(@RequestBody Mail mail) {
          mailSender.sendEmail(mail);
      }

      @PostMapping("/sendCode")
      public void sendCode(@RequestBody Mail mail) {
          String code=mailSender.getCode();
          mail.setSubject("Verification Code");
          mail.setBody("Codul de varificare al mail-ului este: " + code);
          mailSender.sendEmail(mail);
          ValidationCodeDTO vcode=new ValidationCodeDTO();
          vcode.setDescriptor(mail.getTo());
          vcode.setCode(code);
          vcode.setType(EMAILVALIDATION);
          validationCodeService.deleteDescriptionAndType(mail.getTo(), EMAILVALIDATION);
          validationCodeService.addNewValidationCode(vcode);
      }

      @PostMapping("/validateCode")
      public boolean validateCode(@RequestBody Map<String,String> body) {
          String email = body.get("email");
          String code = body.get("code");
          ValidationCode vcode= validationCodeService.getValidationCodeByDescription(email,EMAILVALIDATION);
          if(vcode==null) {return false;}
          return vcode.getCode().equals(code);
      }
}
