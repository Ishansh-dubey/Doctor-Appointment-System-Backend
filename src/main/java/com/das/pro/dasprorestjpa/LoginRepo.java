package com.das.pro.dasprorestjpa;

import org.springframework.data.repository.CrudRepository;

public interface LoginRepo extends CrudRepository<UserInfoEntity, String>  {

}
