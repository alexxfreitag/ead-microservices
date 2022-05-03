package com.ead.course.repositories;

import com.ead.course.models.CourseUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseUserRepository extends JpaRepository<CourseUserModel, UUID> {
    boolean existsByCourseIdAndUserId(UUID courseId, UUID userId);
    List<CourseUserModel> findAllByCourseId(UUID courseId);
}
