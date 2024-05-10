package Entities;
import javax.persistence.*;
import Models.JobType;

@Entity
@Table(name = "Job")
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

    @Column(name = "cat_id")
    private Integer categoryId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_logo")
    private String companyLogo;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type")
    private JobType jobType;

    private String location;

    @Column(name = "salary_range")
    private String salaryRange;

   public Job(String contactEmail, String title, String description, String responsibilities, String requirements, Integer categoryId, String companyName, String companyLogo, JobType jobType, String location, String salaryRange) {
        this.contactEmail = contactEmail;
        this.title = title;
        this.description = description;
        this.responsibilities = responsibilities;
        this.requirements = requirements;
        this.categoryId = categoryId;
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.jobType = jobType;
        this.location = location;
        this.salaryRange = salaryRange;
    }
    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
