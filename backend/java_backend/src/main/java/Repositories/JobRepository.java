package Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Entities.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    // Custom query methods can be defined here if needed
}
