package ba.nwt.keycard.RequestService.models.Request;

import ba.nwt.keycard.RequestService.models.Team.TeamDTO;
import ba.nwt.keycard.RequestService.models.User.UserDTO;
import ba.nwt.keycard.RequestService.models.dtos.RoomDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponseDTO {

    private Long requestId;
    private Boolean status;
    private RoomDTO room;
    private UserDTO user;
    private TeamDTO team;
}
