package com.ead.authuser.repositories;

import com.ead.authuser.models.UserCourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID> {
    boolean existsByUserIdAndCourseId(UUID userId, UUID courseId);
    List<UserCourseModel> findAllByUserId(UUID userId);
}
