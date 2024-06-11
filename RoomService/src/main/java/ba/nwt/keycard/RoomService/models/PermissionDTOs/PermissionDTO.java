package ba.nwt.keycard.RoomService.models.PermissionDTOs;

public class PermissionDTO {
    private Long id;
    private String role; // potencijalno izbaciti
    private Long buildingId;
    private Long floorId;
    private Long roomId;

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public Long getRoomId() {
        return roomId;
    }
}
