//package com.example.springsecurity.service;
//
//import com.example.springsecurity.model.Role;
//import com.example.springsecurity.repositories.RoleRepository;
//
//import java.util.List;
//
//public class Roleservice {
//
//    private final RoleRepository roleRepository;
//
//
//    public Roleservice(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    public void  CreateRole() {
//
//        Role roleAdmin=new Role("Admin", "manage everything");
//        Role saveRole = roleRepository.save(roleAdmin);
//
//        roleRepository.saveAll(List.of(roleAdmin));
//
//
//    }
//
//
//}
