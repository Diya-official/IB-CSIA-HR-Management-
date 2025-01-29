package com.backend.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.backend.erp.entity.Leaves;

@Service
public interface DataOperationLeaves extends JpaRepository<Leaves,String>{

}
