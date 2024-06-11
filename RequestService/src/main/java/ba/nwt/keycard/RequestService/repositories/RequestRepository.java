package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.Request.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    Optional<Request> findById(Long id);

    List<Request> findByUser_Id(Long userId);
}
