package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.entity.billing.packageservice.PackageItemServiceDetail;
import com.sdgt.medcare.master.entity.billing.packageservice.PackageTariffConfigurationMaster;
import lombok.Data;

import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class Configuration {
    private PackageTariffConfigurationMaster packageTariffConfigurationMaster;
    private List<PackageItemServiceDetail> packageItemServiceDetailList;
}
