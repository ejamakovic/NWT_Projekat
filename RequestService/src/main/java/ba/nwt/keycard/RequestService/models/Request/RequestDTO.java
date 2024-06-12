package ba.nwt.keycard.RequestService.models.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {

    @NotNull(message = "RoomId is required")
    private Long roomId;

    @NotNull(message = "UserId is required")
    private Long userId;


}
