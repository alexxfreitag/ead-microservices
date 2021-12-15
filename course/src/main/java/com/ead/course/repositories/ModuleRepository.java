package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID> {

    List<ModuleModel> findAllByCourseId(UUID courseId);
    Optional<ModuleModel> findByIdAndCourseId(UUID moduleId, UUID courseId);
}
