package com.sdgt.medcare.master.entity.billing;

import com.sdgt.medcare.master.entity.BaseMaster;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * {@link ChargeCategoryMaster} is category list containing <p>{SERVICE, SERVICE-GROUP, SERVICE-SUB-GROUP,
 * ITEM,ITEM-GROUP, ITEM-CATEGORY... etc}</p>
 *
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
@Entity
@Table(name = "m_charge_category", schema = "service")
public class ChargeCategoryMaster extends BaseMaster {
    // We are only referencing to the data members from the parent entity.
}
