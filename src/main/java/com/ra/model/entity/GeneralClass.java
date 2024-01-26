package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class GeneralClass {
    @CreatedDate
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyyy-mm-dd hh:mm:ss")
    private Date createDate;
    @LastModifiedDate
    @Column(name = "update_date")
    @JsonFormat(pattern = "yyyyy-mm-dd hh:mm:ss")
    private Date updateDate;
    @Column(name = "del_flag")
    private boolean deleteFlag = false;
}
