package ba.nwt.keycard.RoomService.models.TempAccessGrant;

import java.time.LocalDate;
import java.util.List;

import ba.nwt.keycard.RoomService.models.Floor.Floor;
import ba.nwt.keycard.RoomService.models.Room.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tempAccessGrants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempAccessGrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "User ID must not be null")
    @Column(name = "roomId")
    private Long roomId;

    @NotNull(message = "User ID must not be null")
    @Column(name = "userId")
    private Long userId;

    @NotNull(message = "Timestamp must not be null")
    @Column(name = "timestamp")
    private LocalDate timestamp;

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRoomId() {
        return this.roomId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDate getTimestamp() {
        return this.timestamp;
    }

    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id", insertable = false, updatable = false)
    private Room room;
}