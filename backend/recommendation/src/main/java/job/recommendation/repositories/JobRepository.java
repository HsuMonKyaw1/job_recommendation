package job.recommendation.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import job.recommendation.models.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;



@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    // Custom query methods can be defined here if needed
     @Query("SELECT j FROM Job j JOIN j.category c WHERE j.id = :jobId")
    Job findJobWithCategoryName(@Param("jobId") Integer jobId);
     List<Job> findByTitleContainingIgnoreCase(String title);
}

