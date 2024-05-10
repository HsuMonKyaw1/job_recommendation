package job.recommendation.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import job.recommendation.services.CategoryService;
import job.recommendation.models.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;




@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService; 
    
    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{categoryid}")  
    private Category getCategory(@PathVariable("categoryid") int categoryid)   
    {  
    return categoryService.getCategoryById(categoryid);  
    }  
    
}