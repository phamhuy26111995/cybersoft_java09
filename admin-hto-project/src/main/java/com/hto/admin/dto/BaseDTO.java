package com.hto.admin.dto;

import lombok.Data;

import java.util.Date;


@Data
public class BaseDTO {

    protected long id;

    protected Date createdAt;

    protected long createdBy;


    protected Date updatedAt;



    protected long updatedBy;


    protected boolean isDeleted;

    protected String status;
}
