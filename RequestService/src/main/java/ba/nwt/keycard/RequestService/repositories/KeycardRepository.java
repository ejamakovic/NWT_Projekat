package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.Keycard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeycardRepository extends JpaRepository<Keycard, Integer> {
}
