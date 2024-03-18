package ba.nwt.keycard.RoomService;

import ba.nwt.keycard.RoomService.models.*;
import ba.nwt.keycard.RoomService.services.InitalizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoomServiceApplication implements CommandLineRunner {

	@Autowired
	private InitalizeService initalizeService;

	public static void main(String[] args) {
		SpringApplication.run(RoomServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Building building = new Building();
		building.setName("ETF");
		initalizeService.saveBuilding(building);

		Floor floor = new Floor();
		floor.setFloorNumber(1);
		floor.setBuilding(building);
		initalizeService.saveFloor(floor);

		Room room = new Room();
		room.setName("1-15");
		room.setFloor(floor);
		room.setAccessLevel(2);
		initalizeService.saveRoom(room);

	}
}