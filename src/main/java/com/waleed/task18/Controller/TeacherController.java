package com.waleed.task18.Controller;

import com.waleed.task18.DTO.TeacherDTO;
import com.waleed.task18.Entity.Teacher;
import com.waleed.task18.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTeacher(@RequestBody @Valid TeacherDTO teacherDTO) {
        teacherService.addTeacher(teacherDTO);
        return ResponseEntity.status(201).body("Teacher added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTeacher(@PathVariable Long id, @RequestBody @Valid TeacherDTO teacherDTO) {
        teacherService.updateTeacher(id, teacherDTO);
        return ResponseEntity.ok("Teacher updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Teacher> getTeacherDetails(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherDetails(id));
    }
}