package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageCapDetail;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageItemServiceDetail;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageMaster;
import lombok.Data;

import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class SaveCapPackageRequest extends BaseDTO {
    private PackageMaster packageMaster;
    private List<PackageCapDetail> packageCapDetailList;
    private List<PackageItemServiceDetail> packageItemServiceDetailList;
}
