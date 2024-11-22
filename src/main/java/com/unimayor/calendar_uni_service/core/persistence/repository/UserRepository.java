package com.unimayor.calendar_uni_service.core.persistence.repository;

import com.unimayor.calendar_uni_service.core.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends CrudRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

    @Query(value = "SELECT u from UserEntity u WHERE u.username = :username")
    Optional<UserEntity> findUsername(@Param("username") String username);

    @Query(value = "SELECT u from UserEntity u")
    List<UserEntity> findAll();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM UserEntity u WHERE u.username = :username")
    void deleteByUsername(@Param("username") String username);
}
