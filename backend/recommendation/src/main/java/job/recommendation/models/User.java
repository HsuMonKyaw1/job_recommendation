package job.recommendation.models;
// import javax.persistence.*;
import job.recommendation.common.JobType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import job.recommendation.models.Category;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Transient;
import jakarta.persistence.Convert;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;
import java.util.Set;

// import job.recommendation.models.StringListConverter;
@Entity
@Table(name = "user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="user_location")
    private String user_location;

    @Column(name="min_salary")
    private Integer minSalary;

    @Column(name="max_salary")
    private Integer maxSalary;

    @Column(name="exp_level")
    private String exp_level;

    @Column(name="job_type")
    private String job_type;

    @Column(name = "cat_id")
    private Integer catId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserSkill> userSkills;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", insertable = false, updatable = false)
    private Category category;

    // @Convert(converter = StringListConverter.class)
    @Column(name="user_skills" ,columnDefinition = "json")
    private List<String> selectedSkills;

    //default constructor (necessary for updating user data partially)
    public User(){}
    public User( Integer id, String email, String username, String password,String user_location,Integer minSalary,Integer maxSalary,String exp_level,String job_type,Integer catId){
        this.id = id;
        this.email=email;
        this.username=username;
        this.password=password;
        this.user_location=user_location;
        this.minSalary=minSalary;
        this.maxSalary=maxSalary;
        this.exp_level=exp_level;
        this.job_type=job_type;
        this.catId=catId;
        // this.userSkills=userSkills;
        
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
}
// username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    // password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    // email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getLocation(){
        return user_location;
    }
        public void setLocation(String user_location){
        this.user_location=user_location;
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
    public String getExp_level(){
        return exp_level;
    }
    public void setExp_level(String exp_level){
        this.exp_level=exp_level;
    }
    public String getJobType() {
        return job_type;
    }
    public void setJobType(String job_type) {
        this.job_type = job_type;
    }
    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }
    public List<UserSkill> getUserSkills() {
        return userSkills;
    }

    public void setUserSkills(List<UserSkill> userSkills) {
        this.userSkills = userSkills;
    }
 



}