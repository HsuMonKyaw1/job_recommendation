package job.recommendation.models;
public class UserDTO {
    private Integer id;
    private String email;

    // Constructors, getters, and setters
    public UserDTO() {}

    public UserDTO(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}