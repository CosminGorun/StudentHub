package ro.studenthub.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ro.studenthub.backend.model.CodeTypes;
import ro.studenthub.backend.model.ValidationCode;

public interface ValidationCodeRepository extends JpaRepository<ValidationCode, Integer> {
    public ValidationCode findByDescriptorAndType(String descriptor, CodeTypes type);
    @Transactional
    public void deleteByDescriptorAndType(String descriptor,CodeTypes type);
}
