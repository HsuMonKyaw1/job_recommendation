package Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import Repositories.JobRepository;
import Entities.Job;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository; // Assuming you have a JobRepository interface

    // public Job createJob(Job job) {
    //     return jobRepository.save(job);
    // }

    public Job getJobById(Integer id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        return optionalJob.orElse(null);
    }

    // public Job updateJob(Long id, Job job) {
    // Optional<Job> optionalJob = jobRepository.findById(id);
    // if (optionalJob.isPresent()) {
    //     Job existingJob = optionalJob.get();
    //     existingJob.setCompanyName(job.getCompanyName()); 
    //     existingJob.setDescription(job.getDescription()); 
    //     return jobRepository.save(existingJob);
    // } else {
    //     return null; 
    // }
    // }
    // public boolean deleteJob(Long id) {
    //     if (jobRepository.existsById(id)) {
    //         jobRepository.deleteById(id);
    //         return true;
    //     } else {
    //         return false; 
    //     }
    // }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}
