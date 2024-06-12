package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.Team.Team;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    Optional<Team> findById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM teams WHERE id = ?1", nativeQuery = true)
    void deleteTeamById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
