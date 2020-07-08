package com.grades.tracker.api.repositories;



import com.grades.tracker.api.entities.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
