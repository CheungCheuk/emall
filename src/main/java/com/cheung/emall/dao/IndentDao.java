package com.cheung.emall.dao;

import com.cheung.emall.pojo.Indent;

// import org.springframework.data.domain.Sort;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.List;

/**
 * IndentDao
 */
public interface IndentDao extends JpaRepository<Indent, Integer>{    
}