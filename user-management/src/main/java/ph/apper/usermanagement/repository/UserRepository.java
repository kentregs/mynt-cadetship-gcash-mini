package ph.apper.usermanagement.repository;

import ph.apper.usermanagement.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface UserRepository  extends CrudRepository<User, String> {

    Optional<User> findByEmail(String email);
    Optional<User> findByAccountId(String username);
    Stream<User> findAllByIsVerifiedAndIsAuth(Boolean isVerified, Boolean isAuth);

}