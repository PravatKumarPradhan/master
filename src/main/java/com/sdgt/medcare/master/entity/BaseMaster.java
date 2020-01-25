package com.sdgt.medcare.master.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra (Modified to abstract code)
 * @author Pravat Kumar Pradhan
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseMaster extends AbstractBaseEntity {
    @MastersFieldCustomAnnotation(displayName = "Code",sequence = 5,nullable = false,editableByUser = false)
    @Column(name = "code",unique = true,length = 50)
    protected String code;

    @MastersFieldCustomAnnotation(displayName = "Description",sequence = 6,nullable = false)
    @Column(name = "description")
    protected String desc;

    /**
     * Get list of description from given list of {@link BaseMaster}
     *
     * @param tList given.
     * @return list of descriptions.
     */
    public static Collection<String> getDescList(Collection<? extends BaseMaster> tList) {
        Set<String> descList = new HashSet<>(tList.size());
        for (BaseMaster entity: tList) {
            descList.add(entity.getDesc());
        }

        return descList;
    }

    /**
     * Get description by entity.
     *
     * @param entity given.
     * @return Description from object. null if given {@link BaseMaster} is null.
     */
    public static String getDesc(BaseMaster entity) {
        if (entity == null) {
            return null;
        }

        return entity.getDesc();
    }

    /**
     * Initial setup of {@link AbstractBaseEntity}.
     *
     * @param master a valid master that extends the {@link AbstractBaseEntity}
     * @param dto a valid dto that extends {@link BaseDTO}
     */
    public static void initialPersistenceSetup(final AbstractBaseEntity master, final BaseDTO dto) {
        master.setCreatedBy(dto.getCreatedBy());
        //Move data code to Date Assistant
        master.setCreatedDate(Date.from(Instant.now().atZone(ZoneId.systemDefault()).toInstant()));
        master.setActive(true);
    }

    /**
     * Get list of code from given list of {@link BaseMaster}
     *
     * @param tList given.
     * @return list of codes.
     */
    public static Collection<String> getCodeList(Collection<? extends BaseMaster> tList) {
        return tList.stream().map(t -> t.getCode()).collect(Collectors.toList());
    }

    /**
     * Get Code by entity.
     *
     * @param baseMaster given.
     * @return code. null if given {@link BaseMaster} is null.
     */
    public static String getCode(BaseMaster baseMaster) {
        if (baseMaster == null) {
            return null;
        }

        return baseMaster.getCode();
    }
 }
