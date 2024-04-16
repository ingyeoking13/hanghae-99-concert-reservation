package reservation.Repository;

import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reservation.Domain.Account;
import reservation.Domain.User;

@Repository
public class JpaAccountCoreRepository {
    @Autowired private AccountRepository accountRepository;

    public Account createUserAccount(User user){
        Account account = Account.builder()
                .user(user)
                .build();
        accountRepository.save(account);
        return account;
    }

    public Account findByUserId(Long userId) {
        return accountRepository.findByUser_Id(userId).get();
    }

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Account decreaseAmountFromAccount(Long userId, int amount) {
        Account account = accountRepository.findByUser_Id(userId).get();
        account.setAmount( account.getAmount() - amount );
        return account;
    }
}
