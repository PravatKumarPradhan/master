package com.sdgt.medcare.master.controller.common;

import com.sdgt.medcare.master.model.MarkupDetailSaveRequest;
import com.sdgt.medcare.master.service.common.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common")
@CrossOrigin
public class CommonController {

    @Autowired
    ICommonService iCommonService;

    @RequestMapping(value = {"/markup", "/Markup"}, method = RequestMethod.POST, consumes = {
            MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getObjectByOneQuery(@RequestBody MarkupDetailSaveRequest markupDetailSaveRequest) throws Exception {
        try {
            return new ResponseEntity<>(iCommonService.addMarkupMaster(markupDetailSaveRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/getItemByItemTypeId/{itemTypeId}"}, method = RequestMethod.GET, consumes = {
            MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getItemByItemTypeId(@PathVariable Long itemTypeId) throws Exception {
        try {
            return new ResponseEntity<>(iCommonService.getItemByItemTypeId(itemTypeId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/getItemsByKitId/{kitId}"}, method = RequestMethod.GET, consumes = {
            MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getItemByKitId(@PathVariable Long kitId) throws Exception {
        try {
            return new ResponseEntity<>(iCommonService.getItemsByKitId(kitId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = { "/getServiceProvider" }, method = RequestMethod.GET, consumes = {
            MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> getServiceProvider() throws Exception {
        try {
            return new ResponseEntity<>(iCommonService.getServiceProvider(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),  HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
