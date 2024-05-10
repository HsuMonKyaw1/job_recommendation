import javax.persistence.*;

@Entity
@Table(name = "UserSkills")
public class UserSkills {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    // Getters and setters, Constructors, Equals and hashCode same as previous example
    // ToString
    @Override
    public String toString() {
        return "UserSkills{" +
                "user=" + user +
                ", skill=" + skill +
                '}';
    }
}
