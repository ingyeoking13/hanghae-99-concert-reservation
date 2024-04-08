package reservation.Domain;

import io.lettuce.core.dynamic.annotation.CommandNaming;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(name = "show")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="datetime")
    private LocalDateTime dateTime;

    @Column
    private int price;

    private String name;

    @JoinColumn(name="concert_id")
    @ManyToOne
    private Concert concert;
}
