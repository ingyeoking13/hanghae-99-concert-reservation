package reservation.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "concert_show")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="datetime")
    private LocalDateTime dateTime;

    @Column
    private int price;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="concert_id")
    private Concert concert;
}
