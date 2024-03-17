package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
