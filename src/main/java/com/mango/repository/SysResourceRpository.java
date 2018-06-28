package com.mango.repository;

import com.mango.model.SysResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysResourceRpository extends JpaRepository<SysResource, Long> {

    @Query("select s from SysResource s where s.resourceName = ?1")
    List<SysResource> findByName(String s);
}
