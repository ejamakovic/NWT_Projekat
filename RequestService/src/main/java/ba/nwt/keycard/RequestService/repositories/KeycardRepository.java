package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.Keycard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeycardRepository extends JpaRepository<Keycard, Integer> {
    Optional<Keycard> findById(Long id);
}
