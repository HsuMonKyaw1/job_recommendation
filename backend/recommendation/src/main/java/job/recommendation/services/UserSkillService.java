package job.recommendation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import job.recommendation.models.UserSkill;
import job.recommendation.models.Skill;
import job.recommendation.models.User;
import job.recommendation.repositories.UserSkillRepository;
import job.recommendation.repositories.SkillRepository;
import job.recommendation.repositories.UserRepository;
import java.util.Set;
import java.util.Optional;
import java.util.HashSet;


@Service
public class UserSkillService {

    @Autowired
    private UserSkillRepository userSkillRepository;
    private UserRepository userRepository;
    private SkillRepository skillRepository;

    // public Set<String> processUserSkills(String email, List<String> selectedSkillsList) {
    //     Optional<User> userOptional = userRepository.findByEmail(email);
    //     if (userOptional.isPresent()) {
    //         User user = userOptional.get();

    //         Set<String> processedSkills = new HashSet<>();
    //         for (String skillName : selectedSkillsList) {
    //             Skill skill = skillRepository.findBySkillName(skillName);
    //             if (skill != null) {
    //                 UserSkill userSkill = new UserSkill();
    //                 userSkill.setUserId(user.getId());
    //                 userSkill.setSkillId(skill.getId());
    //                 userSkillRepository.save(userSkill);
    //                 processedSkills.add(skillName); // Add the skill name to the set of processed skills
    //             } else {
    //                 throw new IllegalArgumentException("Skill '" + skillName + "' not found.");
    //             }
    //         }
    //         return processedSkills;
    //     } else {
    //         throw new IllegalArgumentException("User with email " + email + " not found.");
    //     }
    // }

}
