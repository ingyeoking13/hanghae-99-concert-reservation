package reservation.Repository.ticket;

import org.springframework.stereotype.Repository;

@Repository
public interface TicketReaderRepository {
    long readWaitingNumber(String serviceName, String token);
}
