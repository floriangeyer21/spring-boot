package com.spingboot.demo.repository;

import com.spingboot.demo.domain.User;
import com.spingboot.demo.domain.dto.UserResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT new com.spingboot.demo.domain.dto.UserResponseDto(u.profileName) "
            + "FROM Review r "
            + "JOIN r.user u "
            + "GROUP BY u.id "
            + "ORDER BY COUNT(r) DESC, u.profileName")
    List<UserResponseDto> findMostActive(Pageable pageable);
}
