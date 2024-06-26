package ba.nwt.keycard.RoomService.models.RequestServiceDTOs;

import java.time.LocalDateTime;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LogDTO {

    private Long id;
    private Long roomId;
    private LocalDateTime timestamp;
    private String entryType;
    private Long userId; // Assuming User is another class, we'll just use userId here
    private String description;

    // Constructors
    public LogDTO(LocalDateTime timestamp, String entryType, Long userId, String description) {
        this.timestamp = timestamp;
        this.entryType = entryType;
        this.userId = userId;
        this.description = description;
    }

    public LogDTO(LocalDateTime timestamp, String entryType, Long userId, String description, Long roomId) {
        this(timestamp, entryType, userId, description); // Call the other constructor
        this.roomId = roomId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}