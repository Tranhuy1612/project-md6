package com.ra.model.dto;

import com.ra.model.entity.Agents;
import com.ra.model.entity.Roles;
import lombok.*;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountDTO {
    private Long id;
    private String loginId;
    private String passWord;
    private Set<Roles> roleId;
    private Agents agentId;
    private Long ownerId;
    private Long shopId;
    private Boolean activatedFlag;
    private Date createDate;
    private Date updateDate;
    private boolean deleteFlag = false;
}
