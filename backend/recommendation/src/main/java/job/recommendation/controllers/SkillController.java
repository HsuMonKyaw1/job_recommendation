package job.recommendation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.http.ResponseEntity;
import job.recommendation.services.SkillService;
import job.recommendation.models.Skill;
import job.recommendation.repositories.SkillRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.net.URI;
import java.util.List;

@RestController
public class SkillController {

    @Autowired
    private SkillService skillService; 
    @Autowired
    private SkillRepository skillRepository;
    
    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
    @GetMapping("/skills")  
    public List<Skill> getAllSkills()  {
    return skillService.getAllSkills(); 
    } 

    @GetMapping("/skills/{skillid}")  
    private Skill getUsers(@PathVariable("skillid") int skillid)   
    {  
    return skillService.getSkillsById(skillid);  
    }  

    //Create a new User

    @PostMapping("/skills")
    public ResponseEntity<Skill> createUser(@RequestBody Skill skill) {
        Skill savedSkill = skillRepository.save(skill);
        return ResponseEntity.created(URI.create("/skills" + savedSkill.getId())).body(savedSkill);
    }

    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
    @GetMapping("/skills/category/{catId}")
    public List<Skill> getSkillsByCategoryId(@PathVariable Integer catId) {
        return skillService.getSkillsByCategoryId(catId);
    }
}