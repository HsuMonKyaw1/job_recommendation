package job.recommendation.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import job.recommendation.repositories.UserRepository;
import job.recommendation.repositories.SkillRepository;
import job.recommendation.repositories.UserSkillRepository;
import jakarta.persistence.EntityNotFoundException;
import job.recommendation.models.User;
import job.recommendation.models.Skill;
import job.recommendation.models.UserSkill;
//import objectmapper jackson
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Collectors;



import java.util.ArrayList;  

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSkillRepository userSkillRepository;

    @Autowired 
    private SkillRepository skillRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    //Method to get all users
    public List<User> getAllUsers()   
    {  
        List<User> users = userRepository.findAll();
        return users;
    }  

    //Method to get users by id
    public User getUsersById(int id)   
    {  
    return userRepository.findById(id).get();  
    }
    
    //Method to create new user
    public User createUser(User user){
        user.setEmail(user.getEmail());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setLocation(user.getLocation());
        // user.setSalary_range(user.getSalary_range());
        user.setMinSalary(user.getMinSalary());
        user.setMaxSalary(user.getMaxSalary());
        user.setExp_level(user.getExp_level());
        user.setCatId(user.getCatId());

        return userRepository.save(user);
    }
    // Method to create a new user (signup)
    public User signUp(User user) {
        // Check if the email is already registered
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Save the user to the database
        return userRepository.save(user);
    }
    //login
    public User authenticate(String email, String password) {
        // Find user by email
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Check if password matches
            if (user.getPassword().equals(password)) {
                // Password matches
                return user;
            }
        }
        return null; // Authentication failed
    }


    //updated service method with skills
    public void saveUserWithSkills(String email, String expLevel, String jobType, String location, Integer minSalary,Integer maxSalary, List<String> selectedSkills) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            System.out.println("userOptional is present");
            User user = userOptional.get();
            user.setEmail(email);
            user.setExp_level(expLevel);
            user.setJobType(jobType);
            user.setLocation(location);
            // user.setSalary_range(salaryRange);
            user.setMinSalary(minSalary);
            user.setMaxSalary(maxSalary);
            userRepository.save(user);
            for (String skillName : selectedSkills) {
            Skill skill = skillRepository.findBySkillName(skillName);
            if (skill != null) {
                UserSkill userSkill = new UserSkill();
                userSkill.setUser(user);
                userSkill.setSkill(skill);
                userSkillRepository.save(userSkill);
            }
        }
    }
        }
    public List<Skill> getSkillsByUserId(Integer userId) {
        return userSkillRepository.findByUserId(userId).stream()
                .map(UserSkill::getSkill)
                .collect(Collectors.toList());
    }
        
      


//previous working service without skills
//  public User updateUserJobData(String email, String user_location, String exp_level, String salary_range, String job_type) {
//         System.out.println("func starts");
//         Optional<User> userOptional = userRepository.findByEmail(email);
        

//         if (userOptional.isPresent()) {
//             System.out.println("userOptional is present");
//             User user = userOptional.get();
//             // Update user's job data
//             user.setLocation(user_location);
//             user.setSalary_range(salary_range);
//             user.setExp_level(exp_level);
//             user.setJobType(job_type);
//             userRepository.save(user); // Save the updated user
            
//             return user;
//         } else {
//             // Handle case when user is not found
//             System.out.println("userOptional is not present");
//             return null;
//         }

//     }


}