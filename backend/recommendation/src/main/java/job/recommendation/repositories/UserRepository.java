package job.recommendation.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import job.recommendation.models.User;
import job.recommendation.models.UserSkill;
import java.util.Optional;
import java.util.List;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom query methods can be defined here if needed  
    
    User findByUsername(String username);
    // User findByUsername(String username);

    Optional<User> findByEmail(String email);

}
