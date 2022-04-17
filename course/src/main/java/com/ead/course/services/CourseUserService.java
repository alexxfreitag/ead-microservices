package com.ead.course.services;

import com.ead.course.models.CourseUserModel;

import java.util.UUID;

public interface CourseUserService {
    boolean existsByCourseIdAndUserId(UUID courseId, UUID userId);
    CourseUserModel save(CourseUserModel courseUserModel);
}
