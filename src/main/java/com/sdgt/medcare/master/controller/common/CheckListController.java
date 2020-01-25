package com.sdgt.medcare.master.controller.common;


import com.sdgt.medcare.master.dto.otims.CheckListDTO;
import com.sdgt.medcare.master.entity.global.CheckListMaster;
import com.sdgt.medcare.master.repository.common.CheckListRepository;
import com.sdgt.medcare.master.service.common.CheckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkList")
@CrossOrigin
public class CheckListController {

    @Autowired
    CheckListRepository checkListRepository;

    @Autowired
    CheckListService checkListService;

    @CrossOrigin
    @PostMapping("/save")
    public CheckListMaster createCheckList(@RequestBody CheckListDTO checkListDTO)
    {
        return checkListService.create(checkListDTO);
    }

    @CrossOrigin
    @PatchMapping("/update/{id}")
    public CheckListMaster updateCheckList(@PathVariable long id, @RequestBody CheckListDTO checkListDTO)
    {
        return checkListService.updateCheckList(id, checkListDTO);
    }



}
