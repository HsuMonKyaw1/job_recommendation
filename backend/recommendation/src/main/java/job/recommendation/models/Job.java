package job.recommendation.models;
// import javax.persistence.*;
import job.recommendation.common.JobType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import job.recommendation.models.Category;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contact_email")
    private String contactEmail;

    private String title;

    private String description;

    private String responsibilities;

    private String requirements;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;

    @Column(name = "company_name")
    private String companyName;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type")
    private JobType jobType;

    @Column(name = "location")
    private String location;

    @Column(name="min_salary")
    private Integer minSalary;

    @Column(name="max_salary")
    private Integer maxSalary;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<JobSkill> jobSkills;

    @CreationTimestamp
    private Date createdAt;

    public Job() {
        // Default constructor
    }

   public Job(String contactEmail, String title, String description, String responsibilities, String requirements, Category category, String companyName,JobType jobType, String location, Integer minSalary,Integer maxSalary,Date createdAt) {
        this.contactEmail = contactEmail;
        this.title = title;
        this.description = description;
        this.responsibilities = responsibilities;
        this.requirements = requirements;
        this.category = category;
        this.companyName = companyName;
        // this.companyLogo = companyLogo;
        this.jobType = jobType;
        this.location = location;
        this.minSalary=minSalary;
        this.maxSalary=maxSalary;
        this.createdAt=createdAt;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getContactMail(){
        return contactEmail;
    }
    public void setContactEmail(String contactEmail){
        this.contactEmail=contactEmail;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getResponsibilities(){
        return responsibilities;
    }
    public void setResponsibilities(String responsibilities){
        this.responsibilities=responsibilities;
    }
    public String getRequirements(){
        return requirements;
    }
    public void setRequirements(String requirements){
        this.requirements=requirements;
    }
    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category){
        this.category=category;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    // public String getCompanyLogo(){
    //     return companyLogo;
    // }
    // public void setCompanyLogo(String companyLogo){
    //     this.companyLogo=companyLogo;
    // }
    public JobType getJobType(){
        return jobType;
    }
    public void setJobType(JobType jobType){
        this.jobType=jobType;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String Location){
        this.location=location;
    }
    public Integer getMinSalary(){
        return minSalary;
    }
    public void setMinSalary(Integer minSalary){
        this.minSalary=minSalary;
    }
    public Integer getMaxSalary(){
        return maxSalary;
    }
    public void setMaxSalary(Integer maxSalary){
        this.maxSalary=maxSalary;
    }

    public List<JobSkill> getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(List<JobSkill> jobSkills) {
        this.jobSkills = jobSkills;
    }
    public Date getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(Date createdAt){
        this.createdAt=createdAt;
    }


}
