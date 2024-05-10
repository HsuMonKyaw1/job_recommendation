package job.recommendation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import job.recommendation.services.JobSkillService;

import java.util.List;

@RestController
@RequestMapping("/job-skills")
public class JobSkillController {

    private JobSkillService jobSkillService;

    @Autowired
    public JobSkillController(JobSkillService jobSkillService) {
        this.jobSkillService = jobSkillService;
    }

    @GetMapping("/job/{jobid}")
    public List<String> getSkillNamesByJobId(@PathVariable Integer jobid) {
        return jobSkillService.getSkillNamesByJobId(jobid);
    }
    
}
