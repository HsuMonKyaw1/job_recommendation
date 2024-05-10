import javax.persistence.*;
import Entities.Job;

@Entity
@Table(name = "JobSkills")
public class JobSkills {

    @Id
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    // Getters and setters, Constructors, Equals and hashCode same as previous example
    // ToString
    @Override
    public String toString() {
        return "JobSkills{" +
                "job=" + job +
                ", skill=" + skill +
                '}';
    }
}
