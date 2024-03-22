package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.Request;
import ba.nwt.keycard.RequestService.models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    Optional<Request> findById(Long id);
}
