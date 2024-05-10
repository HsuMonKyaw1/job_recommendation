package job.recommendation.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import job.recommendation.services.UserService;
import job.recommendation.models.User;
import job.recommendation.models.UserDTO;
import job.recommendation.models.Job;
import job.recommendation.models.Skill;
import job.recommendation.models.UserSkill;
import job.recommendation.repositories.UserSkillRepository;
import job.recommendation.repositories.UserRepository;
import job.recommendation.repositories.SkillRepository;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.persistence.EntityNotFoundException;
import job.recommendation.services.UserSkillService;
import job.recommendation.services.JobService;
import java.util.Optional;
import org.springframework.http.HttpMethod;
import org.springframework.core.ParameterizedTypeReference;
// import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.net.URI;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;



@RestController
public class UserController {

    @Autowired
    private UserService userService; 
    @Autowired
    private JobService jobService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private UserSkillRepository userSkillRepository;

    @Autowired
    private UserSkillService userSkillService;
 

    // @Autowired
    // // private SimilarityCalculationService similarityCalculationService;
    
    @GetMapping("/users")  
    public List<User> getAllUsers()  {
    return userService.getAllUsers(); 
    } 
    @GetMapping("/users/{userid}")  
    private User getUsers(@PathVariable("userid") int userid)   
    {  
    return userService.getUsersById(userid);  
    }  

    //Create a new User
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.created(URI.create("/users" + savedUser.getId())).body(savedUser);
    }

    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        try {
            // Call the signUp method in the UserService to create a new user
            User newUser = userService.signUp(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
            } catch (RuntimeException e) {
            // Return a 400 Bad Request response if the signup fails
            return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

    //endpoint for login
    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        // Validate request
        if (loginUser.getEmail() == null || loginUser.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }

        // Attempt to authenticate user
        User user = userService.authenticate(loginUser.getEmail(), loginUser.getPassword());
        if (user != null) {
            // Convert user entity to DTO
            UserDTO userDTO = new UserDTO(user.getId(), user.getEmail());
            // Authentication successful
            return ResponseEntity.ok(userDTO);
        } else {
            // Authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    //endpoint to get skills by userid
    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
    @GetMapping("/users/{userid}/skills")
    public ResponseEntity<List<String>> getSkillNamesByUserId(@PathVariable Integer userid) {
        List<Skill> skills = userService.getSkillsByUserId(userid);
        List<String> skillNames = skills.stream()
            .map(skill -> skill.getSkillName())
            .collect(Collectors.toList());
        return ResponseEntity.ok(skillNames);
    }

    //endpoint to update user data (min-max salary included)
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/users/jobdata")
    public ResponseEntity<String> submitForm(@RequestBody Map<String, Object> formData) {
        String email = (String) formData.get("email");
        String expLevel = (String) formData.get("exp_level");
        String jobType = (String) formData.get("jobType");
        String location = (String) formData.get("location");
        // String salaryRange = (String) formData.get("salary_range");
        Integer minSalary = null;
        Integer maxSalary = null;
        try {
            minSalary = (Integer) formData.get("minSalary");
            maxSalary = (Integer) formData.get("maxSalary");
        } catch (ClassCastException e) {
        e.printStackTrace();
    }
        List<String> selectedSkills = (List<String>) formData.get("selectedSkills");
    
        userService.saveUserWithSkills(email, expLevel, jobType, location, minSalary, maxSalary, selectedSkills);
    
        return ResponseEntity.ok("Form submitted successfully!");
    }


   //endpoint for recommendation (cosine+euclidean WORK!!)
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users/{userId}/recommendations")
    public ResponseEntity<List<Job>> getRecommendations(@PathVariable Integer userId) {
         // Retrieve user's skills by userId directly from the database
        List<Skill> skills = userService.getSkillsByUserId(userId);
        List<String> skillNames = skills.stream()
            .map(skill -> skill.getSkillName())
            .collect(Collectors.toList());

    // Retrieve user's salary range from database and normalize
    User user = userService.getUsersById(userId);
    double userMinSalary = user.getMinSalary() / 100000.0;
    double userMaxSalary = user.getMaxSalary() / 100000.0;
    String userJobType = user.getJobType();
    String userLocation = user.getLocation();

    // Retrieve all jobs from database
    List<Job> allJobs = jobService.getAllJobs();

    // Calculate total similarity for each job
    Map<Job, Double> jobTotalSimilarityMap = new HashMap<>();
    for (Job job : allJobs) {
        // Calculate cosine similarity
        List<Skill> jobSkills = jobService.getSkillsByJobId(job.getId());
        double cosineSimilarity = calculateCosineSimilarity(skillNames, jobSkills);

        // Calculate Euclidean distance for salary range
        double jobMinSalary = job.getMinSalary() / 100000.0;
        double jobMaxSalary = job.getMaxSalary() / 100000.0;
        double euclideanDistance = calculateEuclideanDistance(userMinSalary, userMaxSalary, jobMinSalary, jobMaxSalary);
        // Calculate similarity for job type
       double jobTypeSimilarity = userJobType.equalsIgnoreCase(job.getJobType().toString()) ? 1.0 : 0.0;
        // Calculate similarity for location
        double locationSimilarity = userLocation.equalsIgnoreCase(job.getLocation()) ? 1.0 : 0.0;

        // Salary-range: 0.1, 
        // Job type: 0.2 
        // Location: 0.2 
        // Skills:  0.3 

        // Calculate total similarity
        double totalSimilarity;
        if (euclideanDistance == 0) {
            totalSimilarity = jobTypeSimilarity *0.2 + locationSimilarity*0.2 + cosineSimilarity*3+ (1 / (euclideanDistance + 1))*0.1;
        } else {
            totalSimilarity =jobTypeSimilarity*0.2 + locationSimilarity*0.2 + cosineSimilarity *3+ (1 / euclideanDistance)*0.1;
        }
        jobTotalSimilarityMap.put(job, totalSimilarity);
    }

    // Sort jobs based on total similarity
    List<Job> recommendedJobs = jobTotalSimilarityMap.entrySet().stream()
            .sorted(Map.Entry.<Job, Double>comparingByValue().reversed())
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

    return ResponseEntity.ok(recommendedJobs);
    }


//     //cosine similarity
    private double calculateCosineSimilarity(List<String> userSkills, List<Skill> jobSkills) {
    // Convert job skills to a list of skill names
    List<String> jobSkillNames = jobSkills.stream().map(Skill::getSkillName).collect(Collectors.toList());

    // Create a list of all unique skills
    List<String> allSkills = new ArrayList<>(userSkills);
    for (String skill : jobSkillNames) {
        if (!allSkills.contains(skill)) {
            allSkills.add(skill);
        }
    }
    System.out.println(allSkills);

    // Initialize vectors for user skills and job skills
    double[] userVector = new double[allSkills.size()];
    double[] jobVector = new double[allSkills.size()];

    // Populate vectors
    for (int i = 0; i < allSkills.size(); i++) {
        String skill = allSkills.get(i);
        userVector[i] = userSkills.contains(skill) ? 1 : 0;
        jobVector[i] = jobSkillNames.contains(skill) ? 1 : 0;
    }
    System.out.println(userVector);
    System.out.println(jobVector);

    // Compute dot product
    double dotProduct = 0;
    for (int i = 0; i < allSkills.size(); i++) {
        dotProduct += userVector[i] * jobVector[i];
    }
    System.out.println(dotProduct);

    // Compute magnitudes
    double userMagnitude = 0;
    double jobMagnitude = 0;
    for (int i = 0; i < allSkills.size(); i++) {
        userMagnitude += Math.pow(userVector[i], 2);
        jobMagnitude += Math.pow(jobVector[i], 2);
    }
    userMagnitude = Math.sqrt(userMagnitude);
    jobMagnitude = Math.sqrt(jobMagnitude);

    // Compute cosine similarity
    double cosineSimilarity = dotProduct / (userMagnitude * jobMagnitude);
    System.out.println(cosineSimilarity);

    return cosineSimilarity;
}

//euclidean calculation 
    private double calculateEuclideanDistance(double userMinSalary, double userMaxSalary, double jobMinSalary, double jobMaxSalary) {
        double minDifference = userMinSalary - jobMinSalary;
        double maxDifference = userMaxSalary - jobMaxSalary;
        return Math.sqrt(Math.pow(minDifference, 2) + Math.pow(maxDifference, 2));
    }

}   

    // updated controller with skills (work!!!)
    // @CrossOrigin(origins = "http://localhost:3000")
    // @PostMapping("/users/jobdata")
    // public ResponseEntity<String> submitForm(@RequestBody Map<String, Object> formData) {
    //     String email = (String) formData.get("email");
    //     String expLevel = (String) formData.get("exp_level");
    //     String jobType = (String) formData.get("jobType");
    //     String location = (String) formData.get("location");
    //     // String salaryRange = (String) formData.get("salary_range");
    //     Integer minSalary = (Integer) formData.get("minSalary");
    //     Integer maxSalary = (Integer) formData.get("maxSalary");
    //     List<String> selectedSkills = (List<String>) formData.get("selectedSkills");
        
    //     userService.saveUserWithSkills(email, expLevel, jobType, location, minSalary,maxSalary, selectedSkills);
        
    //     return ResponseEntity.ok("Form submitted successfully!");
    // }



    //previous working endpoint for updating user info without skills
//     @CrossOrigin(origins = "http://localhost:3000")
//     @PostMapping("/users/jobdata")
//     public ResponseEntity<String> saveUser(@RequestBody User user) {
//         try {
//             // Assuming you have job data in the request body
//             String email = user.getEmail();
//             String user_location = user.getLocation();
//             String exp_level = user.getExp_level();
//             String salary_range = user.getSalary_range();
//             String job_type = user.getJobType();
//             // List<String> selectedSkills = user.getSelectedSkills();
            
//             // Call the UserService method to update the user's job data
//             User updatedUser = userService.updateUserJobData(email, user_location, exp_level, salary_range, job_type);


//             if (updatedUser != null) {
//                 return ResponseEntity.ok("Job data saved successfully");
//             } else {
//                 return ResponseEntity.badRequest().body("Failed to update job data");
//             }
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
            
//         }
// }


 // endpoint for recommendation (only cosine. WORK!!!)
    // @CrossOrigin(origins = "http://localhost:3000")
    // @GetMapping("/users/{userId}/recommendations")
    // public ResponseEntity<List<Job>> getRecommendations(@PathVariable Integer userId) {
    //     // Retrieve user's skills by userId directly from the database
    //     // List<String> userSkillNames = userService.getSkillsByUserId(userId);
    //     List<Skill> skills = userService.getSkillsByUserId(userId);
    //     List<String> skillNames = skills.stream()
    //         .map(skill -> skill.getSkillName())
    //         .collect(Collectors.toList());
    //     // Retrieve all jobs
    //     List<Job> allJobs = jobService.getAllJobs();

    //     // Create a map to store job and similarity pairs
    //     Map<Job, Double> jobSimilarityMap = new HashMap<>();

    //     // Calculate cosine similarity for each job
    //     for (Job job : allJobs) {
    //         List<Skill> jobSkills = jobService.getSkillsByJobId(job.getId());
    //         double similarity = calculateCosineSimilarity(skillNames, jobSkills);
    //         jobSimilarityMap.put(job, similarity);
    //     }

    //     // Sort the jobs based on similarity values
    //     List<Job> recommendedJobs = jobSimilarityMap.entrySet().stream()
    //         .sorted(Map.Entry.<Job, Double>comparingByValue().reversed())
    //         .map(Map.Entry::getKey)
    //         .collect(Collectors.toList());

    //     return ResponseEntity.ok(recommendedJobs);
    // }