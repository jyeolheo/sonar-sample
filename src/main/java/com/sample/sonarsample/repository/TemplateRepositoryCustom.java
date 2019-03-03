package com.sample.sonarsample.repository;

import com.sample.sonarsample.model.Sonar;

import java.util.Optional;

public interface TemplateRepositoryCustom {

    //Example Start
    Optional<Sonar> findByName(String name);
    //Example End
}
