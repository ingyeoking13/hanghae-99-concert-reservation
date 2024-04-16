package reservation.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name="reservation_id")
    Reservation reservation;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    @Column
    int amount;
}
