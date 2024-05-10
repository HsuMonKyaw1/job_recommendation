package job.recommendation.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import job.recommendation.repositories.SkillRepository;
import job.recommendation.models.Skill;
import java.util.ArrayList; 

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository; // Assuming you have a JobRepository interface

    //Method to get all skills
    public List<Skill> getAllSkills()   
    {  
        List<Skill> skills = skillRepository.findAll();
        return skills;
    }  

    //Method to get Skills by id
    public Skill getSkillsById(int id)   
    {  
    return skillRepository.findById(id).get();  
    }
    
    //Method to create new Skill
    public Skill createSkill(Skill skill){
        skill.setSkillName(skill.getSkillName());
        skill.setCatId(skill.getCatId());

        return skillRepository.save(skill);
    }

    public List<Skill> getSkillsByCategoryId(Integer catId) {
        return skillRepository.findByCatId(catId);
    }

  
}