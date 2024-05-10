package reservation.Repository.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.Duration;

public class RedisTicketWriterRepository implements TicketWriterRepository {

    @Autowired private final RedisTemplate<String, String> ticketRedisTemplate;
    private final ZSetOperations<String, String> zSetOperations;
    private final ValueOperations<String, String> valueOperation;
    private final String counterKey = "token-counter";
    private final String rankKey = "rank-key";

    public RedisTicketWriterRepository(RedisTemplate<String, String> redisTemplate) {
        this.ticketRedisTemplate = redisTemplate;
        this.zSetOperations = this.ticketRedisTemplate.opsForZSet();
        this.valueOperation = this.ticketRedisTemplate.opsForValue();
    }

    @Override
    public String writeNewTicket(String serviceName, int userId) {
        valueOperation.increment(counterKey);
        int current = Integer.valueOf(valueOperation.get(counterKey));

        String token = serviceName + "-" + userId;
        setTimeOutKey(token);
        zSetOperations.add(rankKey, token, current);
        return token;
    }

    public void cleanTicketWriter(){
        zSetOperations.removeRange(rankKey, 0, -1);
    }

    private void setTimeOutKey(String token) {
        valueOperation.set(token, "1", Duration.ofMinutes(5));
    }
}
