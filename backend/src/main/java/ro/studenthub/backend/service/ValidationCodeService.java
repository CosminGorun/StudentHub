package ro.studenthub.backend.service;

import org.springframework.stereotype.Service;
import ro.studenthub.backend.dto.ValidationCodeDTO;
import ro.studenthub.backend.model.CodeTypes;
import ro.studenthub.backend.model.ValidationCode;
import ro.studenthub.backend.repository.ValidationCodeRepository;

@Service
public class ValidationCodeService {
    private final ValidationCodeRepository validationCodeRepository;

    public ValidationCodeService(ValidationCodeRepository validationCodeRepository) {
        this.validationCodeRepository = validationCodeRepository;
    }


    public ValidationCode addNewValidationCode(ValidationCodeDTO validationCodeDTO) {
        ValidationCode validationCode = validationCodeDTO.toDAO(5);
        return validationCodeRepository.save(validationCode);
    }

    public ValidationCode getValidationCodeByDescription(String description, CodeTypes codeTypes) {
        return validationCodeRepository.findByDescriptorAndType(description,codeTypes);
    }
    public void deleteDescriptionAndType(String description, CodeTypes codeTypes) {
        validationCodeRepository.deleteByDescriptorAndType(description,codeTypes);
    }
}
