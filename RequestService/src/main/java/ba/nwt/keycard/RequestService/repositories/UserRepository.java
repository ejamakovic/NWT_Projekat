package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query("SELECT u.id FROM User u WHERE u.keycard_id = :keycardId")
    Long getUserIdByCardId(Long keycardId);
}
