package com.waleed.task18.Service;

import com.waleed.task18.Api.APIException;
import com.waleed.task18.DTO.TeacherDTO;
import com.waleed.task18.Entity.Teacher;
import com.waleed.task18.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public void addTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher(null, teacherDTO.getName(), teacherDTO.getAge(), teacherDTO.getEmail(), teacherDTO.getSalary(), null);
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null)
            throw new APIException("Teacher not found");

        teacher.setName(teacherDTO.getName());
        teacher.setAge(teacherDTO.getAge());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setSalary(teacherDTO.getSalary());

        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null)
            throw new APIException("Teacher not found");
        teacherRepository.delete(teacher);
    }

    // Endpoint: Return all teacher details by ID
    public Teacher getTeacherDetails(Long id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null)
            throw new APIException("Teacher not found");
        return teacher;
    }
}