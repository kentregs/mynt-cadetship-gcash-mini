package ph.apper.usermanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ph.apper.usermanagement.domain.VerificationCode;

import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends CrudRepository<VerificationCode, Long> {

    Optional<VerificationCode> findByEmailAndCode(String email, String code);
}
