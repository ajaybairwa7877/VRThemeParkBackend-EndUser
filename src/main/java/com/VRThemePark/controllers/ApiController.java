package com.VRThemePark.controllers;

import com.VRThemePark.entity.HeaderSection;
import com.VRThemePark.entity.User;
import com.VRThemePark.models.MessageResponse;
import com.VRThemePark.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class ApiController {

    @Autowired
    private ApiService apiService;


    @GetMapping
    public String getAppStatus(){
        return "Application is Running...";
    }


    @GetMapping("/getSection")
    public MessageResponse getSectionData(@RequestParam Integer id) {
        MessageResponse sectionData = apiService.getSection(id);
        if(sectionData!=null){
            return sectionData;
        }
        return new MessageResponse(500,false,null,"Invalid id");
    }
}
