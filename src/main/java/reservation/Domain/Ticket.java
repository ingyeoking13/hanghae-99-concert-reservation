package reservation.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("ticket")
public class Ticket {
    @Id String ticketNumber;
}
