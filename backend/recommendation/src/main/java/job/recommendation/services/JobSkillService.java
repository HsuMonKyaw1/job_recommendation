package job.recommendation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import job.recommendation.models.JobSkill;
import job.recommendation.models.Skill;
import job.recommendation.repositories.JobSkillRepository;
import job.recommendation.repositories.SkillRepository;

@Service
public class JobSkillService {

    @Autowired
    private JobSkillRepository jobSkillRepository;

    public List<String> getSkillNamesByJobId(Integer jobid) {
    List<JobSkill> jobSkills = jobSkillRepository.findByJobId(jobid);
    List<String> skillNames = new ArrayList<>();
    for (JobSkill jobSkill : jobSkills) {
        Skill skill = jobSkill.getSkill();
        if (skill != null) {
            skillNames.add(skill.getSkillName());
        }
    }
    return skillNames;
}
}
