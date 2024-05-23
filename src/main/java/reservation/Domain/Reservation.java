package reservation.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne
    @JoinColumn(name="seat_id")
    Seat seat;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    @ManyToOne
    @JoinColumn(name="show_id")
    Show show;

    @Column
    int payment;

    @Column(name="show_name")
    String showName;

    @Column(name="seat_number")
    int seatNumber;
}
