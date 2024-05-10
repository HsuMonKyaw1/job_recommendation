package job.recommendation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import job.recommendation.models.Skill;
import java.util.List;



@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    // Custom query methods can be defined here if needed 
    List<Skill> findByCatId(Integer cat_id);  
    Skill findBySkillName(String skillName);
}