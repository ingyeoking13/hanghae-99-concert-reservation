package reservation.Repository.ticket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reservation.Domain.Ticket;

import java.time.LocalDateTime;

@Repository
public interface TicketWriterRepository {
    public String writeNewTicket(String serviceName, int userId);
}
