package ba.nwt.keycard.RoomService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrant;
import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrantDTO;
import ba.nwt.keycard.RoomService.repositories.TempAccessGrantsRepository;
import ba.nwt.keycard.RoomService.models.TempAccessGrant.TempAccessGrantMapper;

@Service
public class RoomAccessService {

    private final TempAccessGrantMapper tempAccessGrantMapper;
    private final TempAccessGrantsRepository repository;

    @Autowired
    public RoomAccessService(TempAccessGrantMapper tempAccessGrantMapper, TempAccessGrantsRepository repository) {
        this.tempAccessGrantMapper = tempAccessGrantMapper;
        this.repository = repository;
    }

    @RabbitListener(queues = "roomAccessQueue")
    public void receiveMessage(TempAccessGrantDTO accessGrantInput) {
        TempAccessGrant grant = tempAccessGrantMapper.toEntity(accessGrantInput);
        repository.save(grant);
    }
}