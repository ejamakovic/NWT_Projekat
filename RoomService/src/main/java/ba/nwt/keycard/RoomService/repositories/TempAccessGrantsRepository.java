package ba.nwt.keycard.RoomService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrant;

public interface TempAccessGrantsRepository extends JpaRepository<TempAccessGrant, Long> {
}