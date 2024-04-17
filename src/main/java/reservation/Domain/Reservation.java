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

    @OneToOne
    @JoinColumn(name="user_id")
    User user;

    @OneToOne
    @JoinColumn(name="show_id")
    Show show;

    @Column
    int payment;

    @Column(name="show_name")
    String showName;

    @Column(name="seat_number")
    int seatNumber;
}
