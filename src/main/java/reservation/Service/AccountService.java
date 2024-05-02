package reservation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reservation.AOP.DistributedLock;
import reservation.Repository.AccountRepository;
import reservation.Repository.JpaAccountCoreRepository;

@Service
public class AccountService {
    @Autowired
    JpaAccountCoreRepository jpaAccountCoreRepository;

    public int getAmount(Long userId){
        return jpaAccountCoreRepository.getAmountByUserId(userId);
    };

    @DistributedLock(key = "#userId")
    @Transactional
    public boolean charge(Long userId, int amount){
        return jpaAccountCoreRepository.chargeAmount(userId, amount);
    };

}
