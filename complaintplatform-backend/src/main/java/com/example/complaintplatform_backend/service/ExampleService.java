package com.example.complaintplatform_backend.service;

import com.example.complaintplatform_backend.dao.ExampleDao;
import com.example.complaintplatform_backend.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExampleService {

    @Autowired
    private ExampleDao exampleDao;

    public List<Example> findByNameContaining(String name) {
        return exampleDao.findByNameContaining(name);
    }

    public Optional<Example> findById(Long id) {
        return exampleDao.findById(id);
    }

    public long countByName(String name) {
        return exampleDao.countByName(name);
    }

    public int insert(Example example) {
        return exampleDao.insert(example);
    }

    public int update(Example example) {
        return exampleDao.update(example);
    }

    public int deleteById(Long id) {
        return exampleDao.deleteById(id);
    }
}
