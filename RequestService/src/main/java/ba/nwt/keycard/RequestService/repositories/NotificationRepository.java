package ba.nwt.keycard.RequestService.repositories;

import ba.nwt.keycard.RequestService.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
