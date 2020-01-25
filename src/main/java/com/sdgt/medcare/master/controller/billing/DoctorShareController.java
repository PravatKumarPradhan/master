package com.sdgt.medcare.master.controller.billing;

import com.core.base.rest.controller.RestWSController;
import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.sdgt.medcare.master.entity.unit.DoctorShareMaster;
import com.sdgt.medcare.master.repository.billing.DoctorShareRepository;
import com.sdgt.medcare.master.service.billing.DoctorShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/DoctorShare")
public class DoctorShareController {

    @Autowired
    private DoctorShareRepository doctorShareRepository;

    @Autowired
    private DoctorShareService doctorShareService;

    /**
     * @param doctorShareMasterlist
     *
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity<List<DoctorShareMaster>> saveDoctorShareMaster(@RequestBody List<DoctorShareMaster> doctorShareMasterlist) {

        return ResponseEntity.ok(doctorShareService.create(doctorShareMasterlist));
    }


    /**
     * @param pageable
     *
     * @return
     */
    @GetMapping ("/getAll")
    public Page<DoctorShareMaster> getAllDoctorShare(Pageable pageable) {
        return doctorShareService.getAllDoctorShare(pageable);
    }

    /**
     * @param id
     * @param doctorShare
     *
     * @return DoctorShare Entity
     */
    @PatchMapping ("/deActiveDoctorShare/{id}")
    public DoctorShareMaster deActiveDoctorShare(@PathVariable Long id, @RequestBody String doctorShare) {
        return doctorShareService.deActiveDoctorShare(id, doctorShare);

    }

    /**
     * @param id pass the Id of entity to get the Entity object
     *
     * @return Entity
     */
    @GetMapping ("/getById/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
            return ResponseEntity.ok(doctorShareService.findById(id));
        }


    /***
     *
     * @param id
     * @param doctorShareMaster
     * @return
     */

    @PutMapping ("/updateDoctorShare/{id}")
    public ResponseEntity<DoctorShareMaster> updateDoctorShareData(@PathVariable Long id, @RequestBody DoctorShareMaster doctorShareMaster) {
        Optional<DoctorShareMaster> doctorShareMasterOptional = doctorShareService.findById(id);
        if (!doctorShareMasterOptional.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        doctorShareMaster.setUpdatedBy(HttpUtils.getHeader(HttpHeaders.USER_NAME));
        return ResponseEntity.ok(doctorShareService.update(doctorShareMaster, id.toString()));
    }

    /**
     * @param servicecode
     *
     * @return DoctorShare Entity
     */
    @GetMapping ("/getDoctorShareRule/{servicecode}")
    public DoctorShareMaster getDoctorShareRule(@PathVariable String servicecode, @QueryParam("doctorId") Long doctorId,
                                                      @QueryParam("docDeptId") Long docDeptId) {
        return doctorShareService.getDoctorShareRule(servicecode,doctorId,docDeptId);

    }

}
