package reservation.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reservation.Domain.User;

@Repository
public class JpaUserCoreRepository {
    @Autowired private UserRepository userRepository;

    public User getById(Long id){
        return userRepository.findById(id).get();
    }
}
