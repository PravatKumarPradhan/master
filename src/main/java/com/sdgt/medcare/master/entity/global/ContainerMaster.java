package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "m_container", schema = "lab")
 public class ContainerMaster extends BaseMaster {

   @MastersFieldCustomAnnotation(visibleToUser = false)
    @Column(name="container_code")
    private String containerCode ;

   public String getContainerCode() {
      return containerCode;
   }

   public void setContainerCode(String containerCode) {
      this.containerCode = containerCode;
   }
}
