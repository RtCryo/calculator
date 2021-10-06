package org.example.dao;

import org.example.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsersRepository extends CrudRepository<UserModel, Long> {
}
