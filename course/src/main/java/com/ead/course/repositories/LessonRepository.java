package com.ead.course.repositories;

import com.ead.course.models.LessonModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonModel, UUID> {

    List<LessonModel> findAllByModuleId(UUID moduleId);
    Optional<LessonModel> findByIdAndModuleId(UUID lessonId, UUID moduleId);
}
