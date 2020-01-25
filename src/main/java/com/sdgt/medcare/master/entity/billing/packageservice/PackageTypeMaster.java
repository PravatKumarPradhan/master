package com.sdgt.medcare.master.entity.billing.packageservice;

import com.sdgt.medcare.master.entity.BaseMaster;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
@Entity
@Table(name = "m_package_type", schema = "service")
public class PackageTypeMaster extends BaseMaster {
    // We are only referencing to the data members from the parent entity.
}
