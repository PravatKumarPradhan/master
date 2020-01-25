package com.sdgt.medcare.master.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResourceDetailDTO {
    private String resourceDetailId;
    private String resourceDetailCode;
    private String resourceDetailDesc;
    private String resourceType;
    private Long numberOfResource;
    private String  resourceName;
}
