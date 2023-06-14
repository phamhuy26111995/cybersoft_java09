package com.cybersoft.common;

import com.cybersoft.config.YAMLConfig;
import com.cybersoft.entity.Role;
import com.cybersoft.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MyDataInitialize implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private YAMLConfig myConfig;

    @Override
    public void run(String... args) throws Exception {
//        List<Role> roles = Arrays.asList(
//                new Role("ROLE_ADMIN", "ADMIN"),
//                new Role("ROLE_TEACHER", "TEACHER"),
//                new Role("ROLE_STUDENT", "STUDENT"));
//        roleRepository.saveAll(roles);
    }
}
