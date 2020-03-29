package com.github.fwidder.statrechner.dao;

import com.github.fwidder.statrechner.model.Resource;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
}
