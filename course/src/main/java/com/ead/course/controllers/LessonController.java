package com.ead.course.controllers;

import com.ead.course.dtos.LessonDto;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;
import com.ead.course.specifications.SpecificationTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/modules/{moduleId}/lessons")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonController {

    LessonService lessonService;

    ModuleService moduleService;

    public LessonController(LessonService lessonService, ModuleService moduleService) {
        this.lessonService = lessonService;
        this.moduleService = moduleService;
    }

    @PostMapping
    public ResponseEntity<Object> saveLesson(@PathVariable(value = "moduleId") UUID moduleId,
                                             @RequestBody @Valid LessonDto lessonDto) {
        Optional<ModuleModel> moduleModelOptional = moduleService.findById(moduleId);

        if (moduleModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module Not Found.");
        }

        LessonModel lessonModel = new LessonModel();
        BeanUtils.copyProperties(lessonDto, lessonModel);
        lessonModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        lessonModel.setModule(moduleModelOptional.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.save(lessonModel));
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Object> deleteLesson(@PathVariable(value = "moduleId") UUID moduleId,
                                               @PathVariable(value = "lessonId") UUID lessonId) {
        Optional<LessonModel> lessonModelOptional = lessonService.findByIdAndModuleId(lessonId, moduleId);

        if (lessonModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found for this module.");
        }

        lessonService.delete(lessonModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Lesson deleted successfully");
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<Object> updateModule(@PathVariable(value = "moduleId") UUID moduleId,
                                               @PathVariable(value = "lessonId") UUID lessonId,
                                               @RequestBody @Valid LessonDto lessonDto) {
        Optional<LessonModel> lessonModelOptional = lessonService.findByIdAndModuleId(lessonId, moduleId);

        if (lessonModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found for this module.");
        }

        LessonModel lessonModel = lessonModelOptional.get();
        lessonModel.setTitle(lessonDto.getTitle());
        lessonModel.setDescription(lessonDto.getDescription());
        lessonModel.setVideoUrl(lessonDto.getVideoUrl());

        return ResponseEntity.status(HttpStatus.OK).body(lessonService.save(lessonModel));
    }

    @GetMapping
    public ResponseEntity<Page<LessonModel>> getAllLessons(@PathVariable(value = "moduleId") UUID moduleId,
                                                           SpecificationTemplate.LessonSpec lessonSpec,
                                                           @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
                                                                       Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonService.findAllByModuleId(SpecificationTemplate.lessonModuleId(moduleId).and(lessonSpec), pageable));
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<Object> getOneLesson(@PathVariable(value = "moduleId") UUID moduleId,
                                               @PathVariable(value = "lessonId") UUID lessonId) {
        Optional<LessonModel> lessonModelOptional = lessonService.findByIdAndModuleId(lessonId, moduleId);

        if (lessonModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found for this module.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(lessonModelOptional.get());
    }
}
