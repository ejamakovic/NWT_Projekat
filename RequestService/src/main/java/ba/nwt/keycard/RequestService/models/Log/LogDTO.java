package ba.nwt.keycard.RequestService.models.Log;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {

    @NotNull(message = "RoomId is required")
    private Long roomId;

    @NotNull(message = "Entry type is required")
    private String entryType;

    @NotNull(message = "Log must have userId")
    private Long userId;

    @NotNull(message = "Description is required")
    private String description;

}
