package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Services.JobService;
import Entities.Job;
@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService; // Assuming you have a JobService class to handle business logic
    
    // Endpoint to create a new job
    // @PostMapping
    // public ResponseEntity<Job> createJob(@RequestBody Job job) {
    //     Job createdJob = jobService.createJob(job);
    //     return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    // }
    
    // Endpoint to get a job by ID
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Integer id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Endpoint to update a job
    // @PutMapping("/{id}")
    // public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job) {
    //     Job updatedJob = jobService.updateJob(id, job);
    //     if (updatedJob != null) {
    //         return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
    
    // Endpoint to delete a job
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
    //     boolean deleted = jobService.deleteJob(id);
    //     if (deleted) {
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
}
