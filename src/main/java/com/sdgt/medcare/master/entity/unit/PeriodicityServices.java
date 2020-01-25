package com.sdgt.medcare.master.entity.unit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Table(name="m_periodicity_services",schema = "service")
@Getter
@Setter
@Entity
public class PeriodicityServices extends BaseMaster {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_service_mapper_id", referencedColumnName = "id")
    private UnitServiceMapper unitServiceMapper;
    @Column(name = "duration")
    private String duration;
    @Column(name = "modality_master_id")
    private String modalityMasterId;
}
