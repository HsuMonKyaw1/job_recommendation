package job.recommendation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import job.recommendation.models.UserSkill;

public interface UserSkillRepository extends JpaRepository<UserSkill, Integer> {
    List<UserSkill> findByUserId(Integer userid);

}
