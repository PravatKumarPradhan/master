package com.sdgt.medcare.master.service.common;

import com.core.exceptions.DataException;
import com.core.service.BaseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sdgt.medcare.master.entity.org.BedStatus;
import com.sdgt.medcare.master.entity.unit.BedMaster;
import com.sdgt.medcare.master.repository.common.BedRepository;
import com.sdgt.medcare.master.repository.common.BedStatusRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Pravat Kumar Pradhan
 */
@Service
public class BedMasterService extends BaseService<BedMaster> {

    @Autowired
    BedRepository bedRepository;
    private BedStatusRepository bedStatusRepository;

    public BedMasterService(BedRepository bedRepository, BedStatusRepository bedStatusRepository) {
        this.bedRepository = bedRepository;
        this.bedStatusRepository = bedStatusRepository;
    }

    /**
     * @param id
     * @return
     */
    public BedMaster updateBedStatus(String id, String patchObject) {
        Gson gson = new GsonBuilder().setLenient().create();
        JsonObject obj = new JsonParser().parse(patchObject).getAsJsonObject();

        Optional<BedMaster> bedMaster = bedRepository.findById(Long.parseLong(id));
        BedMaster bedMasterDetails = new BedMaster();
        if (bedMaster.isPresent()) {
            bedMasterDetails = bedMaster.get();
            if (obj.get("sourceType") != null && StringUtils.isNotBlank(obj.get("sourceType").getAsString())) {
                if (obj.get("sourceType").getAsString().equalsIgnoreCase("admissionencounter".trim())) {
                    if (bedMasterDetails.getBedStatus().getId().equalsIgnoreCase("1")) {
                        Optional<BedStatus> bedStatus = bedStatusRepository.findById(2L);
                        if (bedStatus.isPresent()) {
                            BedStatus status = bedStatus.get();
                            bedMasterDetails.setBedStatus(status);
                        }
                    }
                }

                if (obj.get("sourceType").getAsString().equalsIgnoreCase("blockedbed".trim())) {
                    bedMasterDetails.setBlocked(true);
                }

                if (obj.get("sourceType").getAsString().equalsIgnoreCase("unblockedbed".trim())) {
                    bedMasterDetails.setBlocked(false);
                }

                if (obj.get("sourceType").getAsString().equalsIgnoreCase("admissionencountercancelled".trim())) {
                    if (bedMasterDetails.getBedStatus().getId().equalsIgnoreCase("2")) {
                        Optional<BedStatus> bedStatus = bedStatusRepository.findById(1L);
                        if (bedStatus.isPresent()) {
                            BedStatus status = bedStatus.get();
                            bedMasterDetails.setBedStatus(status);
                        }
                    }
                }

                if (obj.get("sourceType").getAsString().equalsIgnoreCase("phydischargerelease".trim())) {
                    if (bedMasterDetails.getBedStatus().getId().equalsIgnoreCase("2")) {
                        Optional<BedStatus> bedStatus = bedStatusRepository.findById(4L);
                        if (bedStatus.isPresent()) {
                            BedStatus status = bedStatus.get();
                            bedMasterDetails.setBedStatus(status);
                        }
                    }
                }

                if (obj.get("sourceType").getAsString().equalsIgnoreCase("transferreqacp".trim())) {
                    if (bedMasterDetails.getBedStatus().getId().equalsIgnoreCase("1")) {
                        Optional<BedStatus> bedStatus = bedStatusRepository.findById(3L);
                        if (bedStatus.isPresent()) {
                            BedStatus status = bedStatus.get();
                            bedMasterDetails.setBedStatus(status);
                        }
                    }
                }
                if (obj.get("sourceType").getAsString().equalsIgnoreCase("Housekeeping".trim())) {
                        Optional<BedStatus> bedStatus = bedStatusRepository.findById(4L);
                        if (bedStatus.isPresent()) {
                            BedStatus status = bedStatus.get();
                            bedMasterDetails.setBedStatus(status);
                        }

                }

                if (obj.get("sourceType").getAsString().equalsIgnoreCase("Vacant".trim())) {
                        Optional<BedStatus> bedStatus = bedStatusRepository.findById(1L);
                        if (bedStatus.isPresent()) {
                            BedStatus status = bedStatus.get();
                            bedMasterDetails.setBedStatus(status);
                        }

                }

                if (obj.get("sourceType").getAsString().equalsIgnoreCase("transferreqphyacp".trim())) {
                    if (bedMasterDetails.getBedStatus().getId().equalsIgnoreCase("3")) {
                        Optional<BedStatus> bedStatus = bedStatusRepository.findById(2L);
                        if (bedStatus.isPresent()) {
                            BedStatus status = bedStatus.get();
                            bedMasterDetails.setBedStatus(status);
                        }
                    }
                    Optional<BedMaster> currentBedMaster = bedRepository.findById(Long.parseLong(obj.get("currentBedNoId").getAsString()));
                    if (currentBedMaster.isPresent()) {
                        BedMaster currBedMasterDetails = currentBedMaster.get();
                        if (currBedMasterDetails.getBedStatus().getId().equalsIgnoreCase("2")) {
                            Optional<BedStatus> bedStatus = bedStatusRepository.findById(1L);
                            if (bedStatus.isPresent()) {
                                BedStatus status = bedStatus.get();
                                currBedMasterDetails.setBedStatus(status);
                            }
                        }
                    }
                }
                if (obj.get("sourceType").getAsString().equalsIgnoreCase("transferreq".trim())) {
                    if (bedMasterDetails.getBedStatus().getId().equalsIgnoreCase("1")) {
                        Optional<BedStatus> bedStatus = bedStatusRepository.findById(2L);
                        if (bedStatus.isPresent()) {
                            BedStatus status = bedStatus.get();
                            bedMasterDetails.setBedStatus(status);
                        }
                    }
                    Optional<BedMaster> currentBedMaster = bedRepository.findById(Long.parseLong(obj.get("currentBedNoId").getAsString()));
                    if (currentBedMaster.isPresent()) {
                        BedMaster currBedMasterDetails = currentBedMaster.get();
                        if (currBedMasterDetails.getBedStatus().getId().equalsIgnoreCase("2")) {
                            Optional<BedStatus> bedStatus = bedStatusRepository.findById(1L);
                            if (bedStatus.isPresent()) {
                                BedStatus status = bedStatus.get();
                                currBedMasterDetails.setBedStatus(status);
                            }
                        }
                    }
                }
            }

        }
        return patch(bedMasterDetails, id);
    }

    @Override
    public BedMaster patch(BedMaster objectTopatch, String id) {
        if (objectTopatch == null)
            throw new DataException("There should exist an object already");
        if (StringUtils.isNotBlank(id)) {
            try {
                objectTopatch.setId(id);
                return bedRepository.save(objectTopatch);
            } catch (Exception e) {
                throw new DataException("persistance error from save", e);
            }
        }
        return null;
    }

    public List<BedMaster> getBedByWardAndVirtual(Long wardMaster, Long unitId) {
        List<BedMaster> bedMasterList = new ArrayList<>();
        bedMasterList = bedRepository.findAllByWardMasterIdAndUnitMasterIdAndIsVirtualFalse(wardMaster, unitId);
        int totalNoOfBed = bedMasterList.size();
        int noOfThresholdBed = getNumberOfThresholdBed(totalNoOfBed, bedMasterList);
        List<BedMaster> bedMasters = bedRepository.findAllByWardMasterIdAndUnitMasterIdAndBedStatusIdAndIsVirtualFalseAndIsBlockedFalse(wardMaster, unitId, 1L);
        int vacantBed = bedMasters.size();
        if(vacantBed < noOfThresholdBed){
            List<BedMaster> bedMasterListOfVirtualBed = bedRepository.findAllByWardMasterIdAndUnitMasterIdAndIsVirtualTrue(wardMaster, unitId);
            int noOfReleaseBed = getNumberOfReleaseBed(bedMasterListOfVirtualBed);
            List<BedMaster> bedMasterListOfAvailableVirtualBed = bedRepository.findAllByWardMasterIdAndUnitMasterIdAndBedStatusIdAndIsVirtualTrueAndIsBlockedFalse(wardMaster, unitId, 1L);
            int availableVirtualBed = bedMasterListOfAvailableVirtualBed.size();
            addVirtualBed(bedMasters, noOfReleaseBed, bedMasterListOfAvailableVirtualBed, availableVirtualBed);
        }

        return bedMasters;
    }

    private void addVirtualBed(List<BedMaster> bedMasters, int noOfReleaseBed, List<BedMaster> bedMasterListOfAvailableVirtualBed, int availableVirtualBed) {
        if (availableVirtualBed > 0) {
            int count = 0;
            for (BedMaster bedMaster : bedMasterListOfAvailableVirtualBed) {
                if (count == noOfReleaseBed)
                    break;
                bedMasters.add(bedMaster);
                count++;
            }
        }
    }

    private int getNumberOfReleaseBed(List<BedMaster> bedMasterListOfVirtualBed) {
        AtomicInteger totalNumberOfReleaseBed = new AtomicInteger();
        int totalNumberOfVirtualBed = bedMasterListOfVirtualBed.size();
        bedMasterListOfVirtualBed.stream().anyMatch(bedMaster -> {
            int releasePercentage = bedMaster.getWardMaster().getReleasePercentage();
            totalNumberOfReleaseBed.set((totalNumberOfVirtualBed * releasePercentage) / 100);
            return true;
        });

        return totalNumberOfReleaseBed.get();
    }

    private int getNumberOfThresholdBed(int totalNoOfBed, List<BedMaster> bedMasterList) {
        AtomicInteger numberOfThresholdBed = new AtomicInteger();
        bedMasterList.stream().anyMatch(bedMaster -> {
            int thresholdPercentage = bedMaster.getWardMaster().getThresholdPercentage();
            int totalNumberOfBedWithVirtual = (totalNoOfBed * thresholdPercentage)/100;
            numberOfThresholdBed.set(totalNoOfBed - totalNumberOfBedWithVirtual);
            return true;
        });
        return numberOfThresholdBed.get();
    }

    public List<BedMaster> getBedByWardAndCategoryAndVirtual(Long wardMaster, Long unitId, Long bedCategory) {
        List<BedMaster> bedMasterList = new ArrayList<>();
        bedMasterList = bedRepository.findAllByWardMasterIdAndUnitMasterIdAndBedCategoryIdAndIsVirtualFalse(wardMaster, unitId, bedCategory);
        int totalNoOfBed = bedMasterList.size();
        int noOfThresholdBed = getNumberOfThresholdBed(totalNoOfBed, bedMasterList);
        List<BedMaster> bedMasters = bedRepository.findAllByWardMasterIdAndUnitMasterIdAndBedCategoryIdAndBedStatusIdAndIsVirtualFalseAndIsBlockedFalse(wardMaster, unitId, bedCategory, 1L);
        int vacantBed = bedMasters.size();
        if(vacantBed < noOfThresholdBed){
            List<BedMaster> bedMasterListOfVirtualBed = bedRepository.findAllByWardMasterIdAndUnitMasterIdAndBedCategoryIdAndIsVirtualTrue(wardMaster, unitId, bedCategory);
            int noOfReleaseBed = getNumberOfReleaseBed(bedMasterListOfVirtualBed);
            List<BedMaster> bedMasterListOfAvailableVirtualBed = bedRepository.findAllByWardMasterIdAndUnitMasterIdAndBedCategoryIdAndBedStatusIdAndIsVirtualTrueAndIsBlockedFalse(wardMaster, unitId, bedCategory, 1L);
            int availableVirtualBed = bedMasterListOfAvailableVirtualBed.size();
            addVirtualBed(bedMasters, noOfReleaseBed, bedMasterListOfAvailableVirtualBed, availableVirtualBed);
        }

        return bedMasters;
    }

    public List<BedMaster> getAllBedListByVirtual(Long unitId) {
        List<BedMaster> bedMasterList = new ArrayList<>();
        bedMasterList = bedRepository.findAllByUnitMasterIdAndIsVirtualFalse(unitId);
        int totalNoOfBed = bedMasterList.size();
        int noOfThresholdBed = getNumberOfThresholdBed(totalNoOfBed, bedMasterList);
        List<BedMaster> bedMasters = bedRepository.findAllByUnitMasterIdAndBedStatusIdAndIsVirtualFalseAndIsBlockedFalse(unitId, 1L);
        int vacantBed = bedMasters.size();
        if(vacantBed < noOfThresholdBed){
            bedMasterList = bedRepository.findAllByUnitMasterIdAndIsBlockedFalse(unitId);
        }

        return bedMasterList;
    }
}
