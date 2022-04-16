package com.ead.course.controllers;

import com.ead.course.clients.CourseClient;
import com.ead.course.dtos.UserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/courses/{courseId}/users")
public class CourseUserController {

    @Autowired
    CourseClient courseClient;

    @GetMapping()
    public ResponseEntity<Page<UserDto>> getAllUsersByCourse(@PathVariable(value = "courseId") UUID courseId,
                                                             @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
                                                                       Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(courseClient.getAllUsersByCourse(courseId, pageable));
    }
}
