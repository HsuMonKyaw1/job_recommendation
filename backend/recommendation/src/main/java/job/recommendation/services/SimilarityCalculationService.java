// import java.util.List;
// import java.util.stream.Collectors;
// import job.recommendation.repositories.UserSkillRepository;
// import job.recommendation.recommendation.repositories.JobSkillRepository;
// import job.recommendation.repositories.JobRepository;

// @Service
// public class SimilarityCalculationService{

//     @Autowired
//     private UserSkillRepository userSkillRepository;

//     @Autowired
//     private JobRepository jobRepository;

//     @Autowired
//     private JobSkillRepository jobSkillRepository;

//     public void calculateSimilarity(List<String> userSkills) {
//         // Step 1: Retrieve all job IDs
//         List<Integer> jobIds = getAllJobIds();

//         // Iterate through each job
//         for (Integer jobId : jobIds) {
//             // Step 3: Retrieve skills for the current job
//             List<String> jobSkills = getSkillNamesByJobId(jobId);

//             // Step 4: Calculate cosine similarity
//             double similarity = calculateCosineSimilarity(userSkills, jobSkills);

//             // Store or process the similarity value as needed
//             System.out.println("Job ID: " + jobId + ", Similarity: " + similarity);
//         }
//     }

//     private List<Integer> getAllJobIds() {
//         return jobRepository.findAll().stream()
//                 .map(Job::getId)
//                 .collect(Collectors.toList());
//     }

//     private List<String> getJobSkills(Integer jobId) {
//         return jobSkillRepository.findByJobId(jobId).stream()
//                 .map(JobSkill::getSkill)
//                 .collect(Collectors.toList());
//     }
//      public <List<String>> getSkillNamesByJobId(Integer jobid) {
//         List<Skill> skills =  getJobSkills(jobid);
//         List<String> skillNames = skills.stream()
//             .map(skill -> skill.getSkillName())
//             .collect(Collectors.toList());
//         return skillNames;
//     }

//     private double calculateCosineSimilarity(List<String> vector1, List<String> vector2) {
        
//         return Math.random();
//     }
// }
