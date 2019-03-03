package com.sample.sonarsample.repository;

import com.sample.sonarsample.model.Sonar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
public class TemplateRepositoryImpl implements TemplateRepositoryCustom {

    private static final Class<Sonar> entityClass = Sonar.class;

    @Autowired
    MongoTemplate mongoTemplate;

    //Example Start
    @Override
    public Optional<Sonar> findByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return Optional.ofNullable(mongoTemplate.findOne(query, entityClass));
    }

    //Example End

}
