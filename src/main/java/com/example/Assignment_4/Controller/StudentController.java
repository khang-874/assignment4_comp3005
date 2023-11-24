package com.example.Assignment_4.Controller;

import com.example.Assignment_4.DAO.Student;
import com.example.Assignment_4.Repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    private StudentRepository studentRepository;
    StudentController(){}
    @Autowired
    StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @GetMapping("/list")
    public String getAllStudent(Model theModel){
        //get all student
        theModel.addAttribute("students", studentRepository.findAll());
        return "student-list";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        // create model attribute to bind form data
        Student student = new Student();
        // set the current date as the default value
        student.setEnrollmentDate(new Date());
        theModel.addAttribute("student", student);
        return "student-form";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") int id, Model theModel){
        //Retrieve the student from database
        Optional<Student> result = studentRepository.findById(id);
        //Check if the student exists
        if(result.isEmpty()){
            throw new RuntimeException("Cannot find the student, invalid ID");
        }
        Student student = result.get();
        //add student to the model to display
        theModel.addAttribute("student", student);
        return "student-form";
    }
    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") int id){
        //delete the student
        studentRepository.deleteById(id);
        //redirect to the students list
        return "redirect:/students/list";
    }
    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model){
        if(result.hasErrors()){
            //show the form again indicate error
            return "student-form";
        }

        //Check for existed email
        String studentEmail = student.getEmail();
        Optional<Student> checkStudent = studentRepository.findOneByEmail(studentEmail);
        if(checkStudent.isPresent()){
            //add attribute to indicate error
            model.addAttribute("notUniqueEmail", true );
            model.addAttribute("errorMessage", "Duplicate email");
            //show the form again indicate error
            return "student-form";
        }

        //Save new student or update the existing one
        studentRepository.save(student);
        //redirect to the student list to see the result
        return "redirect:/students/list";
    }
}
