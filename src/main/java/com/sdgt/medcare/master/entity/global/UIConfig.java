package com.sdgt.medcare.master.entity.global;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@TypeDefs({
		@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Entity
@Table(name="m_ui_config")
@JsonIgnoreProperties({"code", "desc","createdBy", "updatedBy", "updatedDate" })
public class UIConfig extends BaseMaster
{
	@Column(name="filtername")
	private String filtername;

	@Type(type = "jsonb")
	@Column(name="uiconfig",length=10485760, columnDefinition = "jsonb")
	private String uiConfig;

	public String getFiltername() {
		return filtername;
	}

	public void setFiltername(String filtername) {
		this.filtername = filtername;
	}

	public String getUiConfig() {
		return uiConfig;
	}

	public void setUiConfig(String uiConfig) {
		this.uiConfig = uiConfig;
	}
}

