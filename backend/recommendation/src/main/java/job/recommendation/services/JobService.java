package job.recommendation.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import job.recommendation.repositories.JobRepository;
import job.recommendation.repositories.JobSkillRepository;
import job.recommendation.models.Job;
import job.recommendation.models.Skill;
import job.recommendation.models.JobSkill;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository; 

    @Autowired
    private JobSkillRepository jobSkillRepository;
    // public Job createJob(Job job) {
    //     return jobRepository.save(job);
    // }
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    //Method to get all jobs
    public List<Job> findJobWithCategoryName() {
        return jobRepository.findAll();
    }

    //Method to get jobs by id
    public Job getJobsById(int id)   {  
        return jobRepository.findById(id).get();  
    }

    // Method to update a job by its ID
    public Job updateJobById(Integer id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            // Update job details with values from updatedJob
            job.setCompanyName(updatedJob.getCompanyName());
            job.setDescription(updatedJob.getDescription());
            // Update other fields as needed

            // Save the updated job
            return jobRepository.save(job);
        } else {
            return null; // Return null if the job with the given ID does not exist
        }
    }
    
    //get all jobIds
    public List<Integer> getAllJobIds() {
        return jobRepository.findAll().stream()
            .map(Job::getId)
            .collect(Collectors.toList());
    }

    //new code
    public List<Skill> getSkillsByJobId(Integer jobId) {
        return jobSkillRepository.findByJobId(jobId).stream()
                .map(JobSkill::getSkill)
                .collect(Collectors.toList());
    }

    //old working method
//     public List<Skill> getSkillsByJobId(Integer jobid) {
//         return jobSkillRepository.findByJobId(jobid).stream()
//             .map(JobSkill::getSkill)
//             .collect(Collectors.toList());
// }

}