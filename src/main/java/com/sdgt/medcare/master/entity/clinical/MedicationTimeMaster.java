package com.sdgt.medcare.master.entity.clinical;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity()
@Table(name = "m_medication_times",schema = "clinical")
public class MedicationTimeMaster extends BaseMaster {

    public long getBaxter() {
        return baxter;
    }

    public void setBaxter(long baxter) {
        this.baxter = baxter;
    }

    private long baxter;

}
