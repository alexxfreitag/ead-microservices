package com.ead.authuser.services;

import com.ead.authuser.models.UserCourseModel;

import java.util.UUID;

public interface UserCourseService {
    boolean existsByUserIdAndCourseId(UUID userId, UUID courseId);
    UserCourseModel save(UserCourseModel userCourseModel);
}
