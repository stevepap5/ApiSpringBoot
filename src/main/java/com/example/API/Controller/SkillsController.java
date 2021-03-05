package com.example.API.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillsController {

    @Autowired
    SkillRepository skillRepository;

    // Get All Notes
    @GetMapping("/skills")
    public List<Skills> getAllSkills() {
        return skillRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/skills")
    public Skills createSkills(@Validated @RequestBody Skills skills) {
        return skillRepository.save(skills);
    }

    // Get a Single Note
    @GetMapping("/skills/{id}")
    public Skills getSkillById(@PathVariable(value = "id") Long skillsId) throws SkillsNotFoundException {
        return skillRepository.findById(skillsId)
                .orElseThrow(() -> new SkillsNotFoundException(skillsId));
    }

    // Update a Note
    @PutMapping("/skills/{id}")
    public Skills updateNote(@PathVariable(value = "id") Long skillId,
                           @RequestBody Skills skillsDetails) throws SkillsNotFoundException {

        Skills skills = skillRepository.findById(skillId)
                .orElseThrow(() -> new SkillsNotFoundException(skillId));

        skills.setSkillName(skillsDetails.getSkillName());
        skills.setSkillDescription(skillsDetails.getSkillDescription());


        Skills updatedSkills = skillRepository.save(skills);

        return updatedSkills;
    }

    // Delete a Note
    @DeleteMapping("/skills/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long skillsId) throws SkillsNotFoundException {
        Skills skills = skillRepository.findById(skillsId)
                .orElseThrow(() -> new SkillsNotFoundException(skillsId));

        skillRepository.delete(skills);

        return ResponseEntity.ok().build();
    }
}
