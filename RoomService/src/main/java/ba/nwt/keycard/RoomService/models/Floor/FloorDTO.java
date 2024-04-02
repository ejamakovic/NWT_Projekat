package ba.nwt.keycard.RoomService.models.Floor;

public class FloorDTO {
    private Long id;
    private Integer floorNumber;
    private Long buildingId;

    // getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    @Override
    public String toString() {
        return "FloorDTO{" +
                "id=" + id +
                ", floorNumber=" + floorNumber +
                ", buildingId=" + buildingId +
                '}';
    }
}