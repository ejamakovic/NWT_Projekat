package ba.nwt.keycard.PermissionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import ba.nwt.keycard.PermissionService.models.*;
import ba.nwt.keycard.PermissionService.repositories.*;


@SpringBootApplication
public class PermissionServiceApplication implements CommandLineRunner{

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private KeycardRepository keycardRepository;

	public static void main(String[] args) {
		SpringApplication.run(PermissionServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Permission permission1 = new Permission();
		permission1.setRole(Role.ADMIN);
		permission1.setRoomId(1);
		permission1.setFloorId(1);
		permission1.setBuildingId(1);
		permissionRepository.save(permission1);

		Permission permission2 = new Permission();
		permission2.setRole(Role.USER);
		permission2.setRoomId(2);
		permission2.setFloorId(2);
		permission2.setBuildingId(2);
		permissionRepository.save(permission2);
	}
}
