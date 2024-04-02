package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.models.Request;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public List<Request> getAllRequests(){
        return requestRepository.findAll();
    }

    public Request getRequestById(Long id){
        Optional<Request> request = requestRepository.findById(id);
        return request.orElse(null);
    }

    public Request createRequest(Request request){
        return requestRepository.save(request);
    }

    public boolean deleteRequestById(Long id) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        if (requestOptional.isPresent()) {
            Request request = requestOptional.get();
            requestRepository.delete(request);
            return true;
        }
        return false;
    }
}
