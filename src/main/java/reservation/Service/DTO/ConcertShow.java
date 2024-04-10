package reservation.Service.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConcertShow {
    private Long id;
    private String name;
    private LocalDateTime datetime;
    private int price;
}
