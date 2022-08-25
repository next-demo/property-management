package com.property.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.property.models.Role;



public interface RoleRepo extends JpaRepository<Role, Integer> {

}
