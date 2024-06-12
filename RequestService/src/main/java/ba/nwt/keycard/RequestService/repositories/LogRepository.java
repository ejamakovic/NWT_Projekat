package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.Log.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
    Optional<Log> findById(Long id);
    List<Log> findByUserId(Long userId);
}
