package reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import reservation.Repository.ConcertShowRepository;
import reservation.Repository.JpaConcertShowCoreRepository;
import reservation.Repository.ticket.RedisTicketReaderRepository;
import reservation.Repository.ticket.RedisTicketWriterRepository;
import reservation.Repository.ticket.TicketReaderRepository;
import reservation.Repository.ticket.TicketWriterRepository;

@Configuration
public class SpringConfig {
    @Autowired RedisTemplate<String, String> redisTemplate;

    @Bean
    public TicketWriterRepository ticketWriterRepository() {
        return new RedisTicketWriterRepository(redisTemplate);
    }

    @Bean
    public TicketReaderRepository ticketReaderRepository() {
        return new RedisTicketReaderRepository(redisTemplate);
    }

}
