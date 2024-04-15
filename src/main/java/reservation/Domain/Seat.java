package reservation.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="seat_info")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int seat_number;

    @Column
    private String occupied_status;

    @Column
    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name="show_id")
    private Show concertShow;
}
