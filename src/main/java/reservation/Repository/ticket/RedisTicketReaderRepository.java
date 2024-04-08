package reservation.Repository.ticket;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.Duration;

public class RedisTicketReaderRepository implements TicketReaderRepository{

    private final ValueOperations<String, String> valueOperation;
    private final ZSetOperations<String, String> zSetOperations;
    private final String rankKey = "rank-key";

    public RedisTicketReaderRepository(ValueOperations<String, String> valueOperation,
                                       ZSetOperations<String, String> zSetOperations) {
        this.valueOperation = valueOperation;
        this.zSetOperations = zSetOperations;
    }
    @Override
    public long readWaitingNumber(String serviceName, String token) {
        String value = this.valueOperation.get(token);
        if (value == null) {
            return -1L;
        }
        this.valueOperation.set(token, "0", Duration.ofMinutes(5));
        Long rank = this.zSetOperations.rank(rankKey,token);
        return rank;
    }
}
