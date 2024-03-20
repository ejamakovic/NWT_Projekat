package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
}
