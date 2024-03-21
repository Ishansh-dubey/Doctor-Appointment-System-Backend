package com.das.pro.dasprorestjpa;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepo extends CrudRepository<PatientInfoEntity, String> {

}
