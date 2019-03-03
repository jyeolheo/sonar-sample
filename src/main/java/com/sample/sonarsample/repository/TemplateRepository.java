package com.sample.sonarsample.repository;

import com.sample.sonarsample.model.Sonar;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemplateRepository extends MongoRepository<Sonar, String>, TemplateRepositoryCustom {
}
