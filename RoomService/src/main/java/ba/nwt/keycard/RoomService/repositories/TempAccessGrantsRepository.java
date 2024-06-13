package ba.nwt.keycard.RoomService.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrant;

public interface TempAccessGrantsRepository extends JpaRepository<TempAccessGrant, Long> {

    List<TempAccessGrant> findByUserId(Long userId);

    @Query("SELECT t FROM TempAccessGrant t WHERE t.userId = :userId AND t.timestamp >= :cutoff")
    List<TempAccessGrant> findByUserIdWithRecentTimestamp(@Param("userId") Long userId,
            @Param("cutoff") LocalDateTime cutoff);
}