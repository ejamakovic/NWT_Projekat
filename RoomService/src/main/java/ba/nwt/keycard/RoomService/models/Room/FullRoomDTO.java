package ba.nwt.keycard.RoomService.models.Room;

// needs to contain all the fields from RoomDTO, but also the fields from Building and Floor
public class FullRoomDTO {
    private Long id;
    private String name;
    private Long floorId;
    private Long buildingId;
    private Integer floorNumber;
    private String buildingName;

    public FullRoomDTO(Long id, String name, Long floorId, Long buildingId, Integer floorNumber, String buildingName) {
        this.id = id;
        this.name = name;
        this.floorId = floorId;
        this.buildingId = buildingId;
        this.floorNumber = floorNumber;
        this.buildingName = buildingName;
    }

    // getters and setters...
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Override
    public String toString() {
        return "FullRoomDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", floorId=" + floorId +
                ", buildingId=" + buildingId +
                ", floorNumber='" + floorNumber + '\'' +
                ", buildingName='" + buildingName + '\'' +
                '}';
    }
}
