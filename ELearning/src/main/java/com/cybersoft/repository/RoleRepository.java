package com.cybersoft.repository;

import com.cybersoft.dto.RoleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.entity.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String roleName);

    @Query("SELECT new com.cybersoft.dto.RoleDto(r.id, r.name, r.description) FROM Role r WHERE r.name <> 'ROLE_ADMIN' ")
    List<RoleDto> findAllRoleIsNotAdmin();
}
