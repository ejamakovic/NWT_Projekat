package ba.nwt.keycard.RoomService.models.TempAccessGrant;

import java.time.LocalDateTime;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TempAccessGrantDTO {
    private Long roomId;
    private Long userId;
    private LocalDateTime timestamp;

    public TempAccessGrantDTO(Long roomId, Long userId, LocalDateTime timestamp) {
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

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
