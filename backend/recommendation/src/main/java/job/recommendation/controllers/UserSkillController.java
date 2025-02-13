package job.recommendation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import job.recommendation.services.UserSkillService;
import job.recommendation.models.UserSkill;
import org.springframework.web.bind.annotation.RequestParam;
import job.recommendation.common.UserSkillRequest;

import java.util.List;

@RestController
public class UserSkillController {
    @Autowired
    private UserSkillService userSkillService;

//     @PostMapping("/userskills")
// public UserSkill createUserSkill(@RequestBody UserSkillRequest request) {
//     return userSkillService.saveUserSkill(request.getUserId(), request.getSkillId());
// }
}
