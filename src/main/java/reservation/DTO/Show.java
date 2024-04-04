package reservation.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Show extends Concert {
    @JsonProperty("datetime")
    private LocalDateTime dateTime;
    private int point;
}
