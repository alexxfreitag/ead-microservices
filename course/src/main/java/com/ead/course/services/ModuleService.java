package com.ead.course.services;

import com.ead.course.models.ModuleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleService {
    void delete(ModuleModel moduleModel);
    ModuleModel save(ModuleModel moduleModel);
    Optional<ModuleModel> findByIdAndCourseId(UUID courseId, UUID moduleId);
    List<ModuleModel> findAllByCourseId(UUID courseId);
    Optional<ModuleModel> findById(UUID moduleId);
    Page<ModuleModel> findAllByCourseId(Specification<ModuleModel> specification, Pageable pageable);
}
