package reservation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reservation.Repository.ticket.TicketReaderRepository;
import reservation.Repository.ticket.TicketWriterRepository;
import reservation.DTO.Ticket;
import reservation.Service.Exception.TokenUnavailableException;
import reservation.Service.Exception.WaitingException;

@Service
public class TicketService {

    @Autowired TicketReaderRepository ticketReaderRepository;

    @Autowired TicketWriterRepository ticketWriterRepository;
    private final int poolSize = 50;

    public TicketService(TicketReaderRepository ticketReaderRepository,
                         TicketWriterRepository ticketWriterRepository) {
        this.ticketReaderRepository = ticketReaderRepository;
        this.ticketWriterRepository = ticketWriterRepository;
    }

    public Ticket supplyToken(String serviceName, int userId){
        String result = ticketWriterRepository.writeNewTicket(serviceName, userId);
        return Ticket.builder().ticketId(result).build();
    }

    public boolean poolingWaitingQueue(String serviceName, String token) throws TokenUnavailableException, WaitingException {
        long result = ticketReaderRepository.readWaitingNumber(serviceName, token);
        if (result < 0) {
            throw new TokenUnavailableException();
        }
        if (result > poolSize) {
            throw new WaitingException((int)(result - poolSize));
        }
        return true;
    }

}
