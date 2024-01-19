package com.ra.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
@MappedSuperclass
public class GeneralClass {
    @CreatedDate
    @Column(name = "create_date")
    private Date createDate;
    @LastModifiedDate
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "del_flag")
    private boolean deleteFlag = false;
}
