package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
}
