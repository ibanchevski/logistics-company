package com.student.logisticscompany.repository;

import com.student.logisticscompany.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.name ilike :searchTerm or u.username ilike :searchTerm")
    List<UserEntity> searchBySearchTerm(String searchTerm);

    @Query(value = "SELECT u.* FROM users u JOIN users_authorities ur on u.id = ur.users_id JOIN role r on r.id = ur.authorities_id WHERE r.authority = 'USER' ", nativeQuery = true)
    List<UserEntity> findAllWithRoleUser();

}
