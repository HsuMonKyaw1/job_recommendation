package job.recommendation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import job.recommendation.models.JobSkill;

public interface JobSkillRepository extends JpaRepository<JobSkill, Integer> {
  
  List<JobSkill> findByJobId(Integer jobid);
}
