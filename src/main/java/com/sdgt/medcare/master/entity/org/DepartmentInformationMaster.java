package com.sdgt.medcare.master.entity.org;

import com.fasterxml.jackson.databind.JsonNode;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Entity
@Table(name = "m_department_info")
public class DepartmentInformationMaster extends BaseMaster {

    @Column(unique = true, length = 64)
    String title;

    @Column(unique = true, length = 256)
    String titleDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentMaster departmentMaster;

    @Type(type = "jsonb")
    @Column(name = "images", length = 10485760, columnDefinition = "jsonb")
    JsonNode images;

    @Type(type = "jsonb")
    @Column(name = "diagnoses", length = 10485760, columnDefinition = "jsonb")
    JsonNode diagnoses;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleDescription() {
        return titleDescription;
    }

    public void setTitleDescription(String titleDescription) {
        this.titleDescription = titleDescription;
    }

    public DepartmentMaster getDepartmentMaster() {
        return departmentMaster;
    }

    public void setDepartmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
    }

    public JsonNode getImages() {
        return images;
    }

    public void setImages(JsonNode images) {
        this.images = images;
    }

    public JsonNode getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(JsonNode diagnoses) {
        this.diagnoses = diagnoses;
    }
}
