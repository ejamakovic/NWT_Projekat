package ba.nwt.keycard.PermissionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import ba.nwt.keycard.PermissionService.models.*;
import ba.nwt.keycard.PermissionService.repositories.*;

@SpringBootApplication
@EnableDiscoveryClient
public class PermissionServiceApplication implements CommandLineRunner {

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private KeycardRepository keycardRepository;

	@Autowired
	private KeycardPermissionRepository keycardPermissionRepository;

	public static void main(String[] args) {
		SpringApplication.run(PermissionServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
