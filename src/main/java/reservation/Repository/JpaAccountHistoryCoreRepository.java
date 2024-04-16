package reservation.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reservation.Domain.Account;
import reservation.Domain.AccountHistory;

@Repository
public class JpaAccountHistoryCoreRepository {
    @Autowired private AccountHistoryRepository accountHistoryRepository;

    public AccountHistory createPaymentAccountHistory(Account account, int amount, String method){
        AccountHistory accountHistory = AccountHistory.builder()
                .account(account)
                .amount(amount)
                .method(method)
                .build();
        accountHistoryRepository.save(accountHistory);
        return accountHistory;
    }

}
