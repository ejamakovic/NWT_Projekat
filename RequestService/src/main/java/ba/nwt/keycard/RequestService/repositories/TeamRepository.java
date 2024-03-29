package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    Optional<Team> findById(Long id);
}
