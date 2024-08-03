package com.VRThemePark.services;

import com.VRThemePark.entity.HeaderSection;
import com.VRThemePark.entity.User;
import com.VRThemePark.models.MessageResponse;
import com.VRThemePark.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiService {


    @Autowired
    public ApiRepository apiRepository;

    public MessageResponse getSection(Integer id) {
         HeaderSection headerSection = apiRepository.findById(id).orElse(null);

         if(headerSection!=null){
             return new MessageResponse(200,true,headerSection,"success");
         }
        return null;
    }

}
