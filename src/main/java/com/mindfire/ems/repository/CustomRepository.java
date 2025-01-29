package com.mindfire.ems.repository;


import org.springframework.stereotype.Repository;



@Repository
public interface CustomRepository {
    void bulkSalaryUpdate(double percentage);
}
