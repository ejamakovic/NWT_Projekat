package ba.nwt.keycard.RequestService;

import ba.nwt.keycard.RequestService.services.InitalizeService;
import ba.nwt.keycard.RequestService.utils.UserUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ba.nwt.keycard.RequestService.models.*;

import java.time.LocalDate;

@SpringBootApplication
public class RequestServiceApplication implements CommandLineRunner {

	@Autowired
	private InitalizeService initalizeService;

	public static void main(String[] args) {
		SpringApplication.run(RequestServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = new User();
		user.setRole("Admin");
		user.setPasswordHash(UserUtil.hashPassword("ovoono"));
		user.setUsername("ejamakovic");
		user.setEmail("ejamakovic1@etf.unsa.ba");
		user.setKeyId(1L);
		user.setActive(true);
		user.setTeamId(1L);
		initalizeService.saveUser(user);

		Request request = new Request();
		request.setUser(user);
		initalizeService.saveRequest(request);

		Notification notification = new Notification();
		notification.setUser(user);
		notification.setMessage("Selam");
		initalizeService.saveNotification(notification);

		Log log = new Log();
		log.setUser(user);
		log.setTimestamp(LocalDate.now());
		log.setDescription("Sace iftar");
		log.setEntryType("Nesto");
		initalizeService.saveLog(log);

	}
}
