package reservation.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reservation.Repository.ticket.TicketReaderRepository;
import reservation.Repository.ticket.TicketWriterRepository;
import reservation.DTO.Ticket;
import reservation.DTO.Exception.TokenUnavailableException;
import reservation.DTO.Exception.WaitingException;

@Service
@Slf4j
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

    public boolean poolingWaitingQueue(String token) throws TokenUnavailableException, WaitingException {
        long result = ticketReaderRepository.readAndDeleteWaitingNumber(token);
        log.info("VALLL");
        if (result < 0) {
            log.info(result + "00");
            throw new TokenUnavailableException();
        }
        if (result != 0) {
            throw new WaitingException((int)(result));
        }
        return true;
    }

}
