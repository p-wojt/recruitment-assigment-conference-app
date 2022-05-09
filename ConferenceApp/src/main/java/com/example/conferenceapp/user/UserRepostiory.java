package com.example.conferenceapp.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepostiory extends CrudRepository<User, Long> {
}
