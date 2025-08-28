package ro.studenthub.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.studenthub.backend.dto.ValidationCodeDTO;
import ro.studenthub.backend.model.ValidationCode;
import ro.studenthub.backend.service.ValidationCodeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/codes")
public class ValidationCodeController {
    @Autowired
    private final ValidationCodeService validationCodeService;
    public ValidationCodeController(ValidationCodeService validationCodeService) {
        this.validationCodeService = validationCodeService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody ValidationCodeDTO validationCodeDTO) {
        if (validationCodeDTO.getCode() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(validationCodeService.addNewValidationCode(validationCodeDTO));
    }

//    @PostMapping("/verifie")
//    public ResponseEntity<Object> verify(@RequestBody ValidationCodeDTO validationCodeDTO) {
//
//    }
}
