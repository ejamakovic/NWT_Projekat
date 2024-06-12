package ba.nwt.keycard.RoomService.services;

import java.time.LocalDate;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrant;
import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrantDTO;
import ba.nwt.keycard.RoomService.repositories.TempAccessGrantsRepository;

import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrantMapper;;

@Service
public class RoomAccessService {

    @Autowired
    private TempAccessGrantMapper tempAccessGrantMapper;

    @Autowired
    private TempAccessGrantsRepository repository;

    @RabbitListener(queues = "roomAccessQueue")
    public void receiveMessage(TempAccessGrantDTO accessGrantInput) {
        TempAccessGrant grant = new TempAccessGrant();
        grant = tempAccessGrantMapper.toEntity(accessGrantInput);
        repository.save(grant);
    }
}