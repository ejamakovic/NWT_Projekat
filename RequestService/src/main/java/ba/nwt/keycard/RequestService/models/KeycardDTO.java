package ba.nwt.keycard.RequestService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeycardDTO {
    private Integer id;
    private Boolean active;
}