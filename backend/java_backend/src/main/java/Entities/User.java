import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String location;

    @Column(name = "salary_range")
    private String salaryRange;

    @Column(name = "exp_level")
    private String expLevel;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;

    // Getters and setters, Constructors, Equals and hashCode same as previous example
    // ToString
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", salaryRange='" + salaryRange + '\'' +
                ", expLevel='" + expLevel + '\'' +
                ", category=" + category +
                '}';
    }
}
