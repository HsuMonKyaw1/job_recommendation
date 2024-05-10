package job.recommendation.models;

import job.recommendation.common.JobType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.Set;
import java.util.HashSet;
import job.recommendation.models.Category;
import java.util.List;

@Entity
@Table(name="skill")
public class Skill{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "cat_id")
    private Integer catId;

    // @JoinTable(name = "skill_category",
    //            joinColumns = @JoinColumn(name = "skill_id"),
    //            inverseJoinColumns = @JoinColumn(name = "cat_id")) 
    // private Set<Category> categories = new HashSet<>();
    public Skill() {
        // Default constructor
    }
    public Skill(String skillName,Integer catId){
        this.skillName=skillName;
        this.catId=catId;
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public String getSkillName(){
        return skillName;
    }
    public void setSkillName(String skillName){
        this.skillName=skillName;
    }
    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }
}