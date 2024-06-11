package ba.nwt.keycard.RoomService.models.PermissionServiceDTOs;

public class CheckPermissionDTO {
    private Long keycardId;
    private Long buildingId;
    private Long floorId;
    private Long roomId;

    public CheckPermissionDTO(Long keycardId, Long buildingId, Long floorId, Long roomId) {
        this.keycardId = keycardId;
        this.buildingId = buildingId;
        this.floorId = floorId;
        this.roomId = roomId;
    }
}