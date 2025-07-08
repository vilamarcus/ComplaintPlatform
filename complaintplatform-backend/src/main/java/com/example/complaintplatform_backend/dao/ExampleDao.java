package com.example.complaintplatform_backend.dao;

import com.example.complaintplatform_backend.entity.Example;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Optional;

/**
 * 样例数据访问接口 - MyBatis实现
 */
@Mapper
public interface ExampleDao {
    
    List<Example> findByNameContaining(@Param("name") String name);
    
    Optional<Example> findById(@Param("id") Long id);
    
    long countByName(@Param("name") String name);
    
    int insert(Example example);
    
    int update(Example example);
    
    int deleteById(@Param("id") Long id);
}
