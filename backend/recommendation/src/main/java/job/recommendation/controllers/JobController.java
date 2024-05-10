package job.recommendation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import job.recommendation.services.JobService;
import job.recommendation.models.Job;
import job.recommendation.services.JobSkillService;
import job.recommendation.services.SkillService;
import job.recommendation.repositories.JobRepository;
import job.recommendation.models.JobSkill;
import job.recommendation.models.Skill;
import java.util.List;
import java.util.ArrayList;
import java.net.URI;
import java.util.stream.Collectors;


@RestController
public class JobController {

    @Autowired
    private JobService jobService; // Assuming you have a JobService class to handle business logic
    
    @Autowired
    private JobSkillService jobSkillService;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillService skillService;

    
    //get all jobs
    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
    @GetMapping("/jobs")
    public List<Job> findJobWithCategoryName() {
        return jobService.findJobWithCategoryName();
    }
        
    //get jobs by id
    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
    @GetMapping("/jobs/{jobid}")  
    private Job getJobs(@PathVariable("jobid") int jobid)   
    {  
    return jobService.getJobsById(jobid);  
    } 

    //create new job
    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job savedJob = jobRepository.save(job);
        return ResponseEntity.created(URI.create("/jobs" + savedJob.getId())).body(savedJob);
    }

 
    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
    // Endpoint to update a job
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateJobById(@PathVariable Integer id, @RequestBody Job updatedJob) {
        Job job = jobService.updateJobById(id, updatedJob);
        if (job != null) {
            return ResponseEntity.ok().body(job);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
    //new code
    @GetMapping("/jobs/{jobid}/skills")
    public ResponseEntity<List<String>> getSkillNamesByJobId(@PathVariable Integer jobid) {
        List<Skill> skills = jobService.getSkillsByJobId(jobid);
        List<String> skillNames = skills.stream()
            .map(skill -> skill.getSkillName())
            .collect(Collectors.toList());
        return ResponseEntity.ok(skillNames);
    }

    //search by title
    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
    @GetMapping("/jobs/search")
    public List<Job> searchJobsByTitle(@RequestParam String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }
//old working code
//     @GetMapping("/jobs/{jobid}/skills")
//     public ResponseEntity<List<Skill>> getSkillsByJobId(@PathVariable Integer jobid) {
//         List<Skill> skills = jobService.getSkillsByJobId(jobid);
//         return ResponseEntity.ok(skills);
// }


}
