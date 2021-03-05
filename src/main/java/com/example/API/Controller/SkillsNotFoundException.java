package com.example.API.Controller;

public class SkillsNotFoundException extends Exception {

    private long skillsId;
    public SkillsNotFoundException(long skillsId) {
        super(String.format("Book is not found with id : '%s'", skillsId));
    }
}
