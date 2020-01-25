package com.sdgt.medcare.master.controller.common;

import com.sdgt.medcare.master.dto.otims.PreferenceCardDTO;
import com.sdgt.medcare.master.entity.global.PreferenceCardMaster;
import com.sdgt.medcare.master.service.common.PreferenceCardMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preferenceCard")
@CrossOrigin
public class PreferenceCardMasterController {

    @Autowired
    PreferenceCardMasterService preferenceCardMasterService;

    @CrossOrigin
    @PostMapping("/save")
    public PreferenceCardMaster createPreference(@RequestBody PreferenceCardDTO preferenceCardDTO)
    {
        return preferenceCardMasterService.create(preferenceCardDTO);
    }

    @CrossOrigin
    @PatchMapping("/update/{id}")
    public PreferenceCardMaster updatePreferenceCardDetails(@PathVariable long id,@RequestBody PreferenceCardDTO preferenceCardDTO)
    {
        return preferenceCardMasterService.updatePreferenceCard(id, preferenceCardDTO);
    }

}
