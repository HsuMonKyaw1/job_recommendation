package job.recommendation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import job.recommendation.models.User;
import job.recommendation.models.Skill;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

@Entity
@Table(name = "userskill")
public class UserSkill {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    // @Column(name = "user_id")
    // private Integer userId;

    // @Column(name = "skill_id")
    // private Integer skillId;
    
    // Constructors, getters, and setters
    // Constructor
    public UserSkill() {}


     public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
    
        this.id = id;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
    // public Integer getUserId() {
    //     return userId;
    // }

    // public void setUserId(Integer userId) {
    //     this.userId =  userId;
    // }

    // public Integer getSkillId() {
    //     return skillId;
    // }

    // public void setSkillId(Integer skillId) {
    //     this.skillId = skillId;
    // }
}

