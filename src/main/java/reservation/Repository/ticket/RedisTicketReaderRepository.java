package reservation.Repository.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.Duration;

@RequiredArgsConstructor
public class RedisTicketReaderRepository implements TicketReaderRepository{


    @Autowired private final RedisTemplate<String, String> ticketRedisTemplate;

    private final ValueOperations<String, String> valueOperation;
    private final ZSetOperations<String, String> zSetOperations;
    private final String rankKey = "rank-key";

    public RedisTicketReaderRepository(RedisTemplate<String, String> ticketRedisTemplate) {
        this.ticketRedisTemplate = ticketRedisTemplate;
        this.zSetOperations = this.ticketRedisTemplate.opsForZSet();
        this.valueOperation = this.ticketRedisTemplate.opsForValue();
    }
    @Override
    public long readWaitingNumber(String serviceName, String token) {
        String value = this.valueOperation.get(token);
        if (value == null) {
            return -1L;
        }
        this.valueOperation.set(token, "0", Duration.ofMinutes(5));
        Long rank = this.zSetOperations.rank(rankKey,token);
        return rank + 1;
    }
}
