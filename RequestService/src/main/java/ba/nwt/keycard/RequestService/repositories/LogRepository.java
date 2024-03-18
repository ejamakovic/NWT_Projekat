package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
}
