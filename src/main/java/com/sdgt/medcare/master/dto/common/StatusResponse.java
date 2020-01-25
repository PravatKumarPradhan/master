package com.sdgt.medcare.master.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StatusResponse {
    private String entity;
    private String id;
    private StatusEnum status;
    private boolean isExecutionComplete = false;
    private String errorMessage;
}
