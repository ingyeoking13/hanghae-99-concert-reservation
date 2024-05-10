package reservation.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="seat_info", indexes = {
    @Index(name = "IDX_show_id", columnList = "show_id")
})
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="seat_number")
    private int seatNumber;

    @Column(name="occupied_status")
    private String occupiedStatus;

    @Column
    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name="show_id")
    private Show concertShow;

    public reservation.DTO.Seat toSeatDTO(){
        return  reservation.DTO.Seat.builder()
                .id(this.id)
                .seatNumber(this.seatNumber)
                .occupiedStatus(this.occupiedStatus)
                .build();
    }
}
