package com.sdgt.medcare.master.entity.billing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Getter
@Setter
@Entity
@Table(name = "m_order_set_details", schema = "service")
public class OrderSetDetails extends BaseMaster {
    @Column(name = "is_drug", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDrug;

    @ManyToOne
    @JoinColumn(name = "order_set_id", nullable = false)
    @JsonIgnoreProperties("orderSetDetailsList")
    private OrderSetMaster orderSetMaster;

    @OneToOne
    @JoinColumn(name = "service_id")
    private ServiceMaster serviceMaster;

    @OneToOne
    @JoinColumn(name = "item_id")
    private ItemMaster itemMaster;

    @Column(name = "qty", nullable = false, columnDefinition = "bigint default 1")
    private Long qty;
}
