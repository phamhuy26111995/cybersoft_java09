package com.hto.admin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserDTO extends BaseDTO {
    private String name;
    private String image;
    private String email;
    private String phone;
    private Date birthday;
    private List<PermissionDTO> userPermission;

    public UserDTO(long id, String name, String image, String email, String phone
            , Date birthday, Date createdAt, long createdBy
            , Date updatedAt, long updatedBy, String status) {
        super.setId(id);
        this.name = name;
        this.image = image;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        super.setCreatedAt(createdAt);
        super.setCreatedBy(createdBy);
        super.setUpdatedAt(updatedAt);
        super.setUpdatedBy(updatedBy);
        super.setStatus(status);
    }
}
