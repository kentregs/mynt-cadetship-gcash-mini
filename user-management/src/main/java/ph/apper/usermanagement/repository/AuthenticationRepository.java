package ph.apper.usermanagement.repository;

import ph.apper.usermanagement.domain.Authentication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends CrudRepository<Authentication, Long> {

}
