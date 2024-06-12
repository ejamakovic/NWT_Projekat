package ba.nwt.keycard.RoomService.models.TempAccessGrant;

import java.time.LocalDate;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TempAccessGrantDTO {
    private Long roomId;
    private Long userId;
    private LocalDate timestamp;

    public TempAccessGrantDTO(Long roomId, Long userId, LocalDate timestamp) {
        this.roomId = roomId;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public Long getRoomId() {
        return this.roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}
