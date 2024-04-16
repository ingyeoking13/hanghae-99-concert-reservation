package reservation.Domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
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
