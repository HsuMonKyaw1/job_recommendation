package job.recommendation.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import job.recommendation.models.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Custom query methods can be defined here if needed   
}