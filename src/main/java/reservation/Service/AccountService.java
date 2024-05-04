package reservation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reservation.aop.DistributedLock;
import reservation.Repository.JpaAccountCoreRepository;

@Service
public class AccountService {
    @Autowired
    JpaAccountCoreRepository jpaAccountCoreRepository;

    public int getAmount(Long userId){
        return jpaAccountCoreRepository.getAmountByUserId(userId);
    };

    @DistributedLock(key = "#userId")
    public boolean charge(Long userId, int amount){
        return jpaAccountCoreRepository.chargeAmount(userId, amount);
    };

}
