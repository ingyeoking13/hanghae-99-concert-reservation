package reservation.Repository.ticket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.Duration;
import reservation.aop.DistributedLock;

@Slf4j
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
    public long peekWaitingNumber(String token) {
        String value = this.valueOperation.get(token);
        if (value == "0") {
            return -1L;
        }
        Long rank = this.zSetOperations.rank(rankKey,token);
        return rank;
    }


    @Override
    @DistributedLock(key = "#token")
    public long readAndDeleteWaitingNumber(String token) {
        String value = this.valueOperation.getAndDelete(token);
        log.info(value + "***");
        if (value == null) {
            log.info("hi");
            return -1L;
        }
        Long rank = this.zSetOperations.rank(rankKey,token);
        if (rank == 0){
            this.zSetOperations.remove(rankKey, token);
        }
        return rank;
    }
}
