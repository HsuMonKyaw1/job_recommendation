package job.recommendation.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import job.recommendation.repositories.CategoryRepository;
import job.recommendation.models.Category;
import java.util.ArrayList;  

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
         List<Category> categories = categoryRepository.findAll();
        return categories;
    }
    public Category getCategoryById(int id)   
    {  
        return categoryRepository.findById(id).get();  
    }

}