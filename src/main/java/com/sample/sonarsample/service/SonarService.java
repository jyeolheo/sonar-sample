package com.sample.sonarsample.service;

import com.sample.sonarsample.model.Sonar;
import com.sample.sonarsample.repository.TemplateRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class SonarService {

    @Autowired
    TemplateRepository templateRepository;

    //Example Start
    public List<Sonar> getAll() {
        return templateRepository.findAll();
    }
    public Optional<Sonar> getByName(String name) {
        return templateRepository.findByName(name);
    }
    public Sonar set(Sonar sonar) {
        return templateRepository.insert(sonar);
    }
    //Example End

}
