package reservation.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="account_history")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    long id;

    @OneToOne
    @JoinColumn(name="account_id")
    Account account;

    @Column
    int amount;

    @Column
    String method;
}
