package reservation.Repository.ticket;

import org.springframework.stereotype.Repository;

@Repository
public interface TicketReaderRepository {
    long peekWaitingNumber(String token);
    long readAndDeleteWaitingNumber(String token);
}
