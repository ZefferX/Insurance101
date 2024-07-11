package com.example.insurance.controller;

import com.example.insurance.dto.NewExamRequest;
import com.example.insurance.model.Exams;
import com.example.insurance.service.ExamsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exams")
@AllArgsConstructor
public class ExamsController {
    private final ExamsService examsService;


    @GetMapping("/{Id}")
    public Exams findExamById(@PathVariable("Id") Integer id){
        return examsService.findExamById(id);
    }

    @PostMapping public void addExam (@RequestBody NewExamRequest request){
        examsService.addExam(request);
    }

    @GetMapping()
    public List<Exams> getAllExams(){
        return examsService.allExams();
    }

    @DeleteMapping("/{Id}") public String deleteExam(@PathVariable("Id") Integer id){
        return examsService.deleteExamById(id);
    }

    @PutMapping("/{Id}") public Exams updateExamsById(@PathVariable("Id") Integer id, @RequestBody NewExamRequest request){
        return examsService.updateExams(request, id);
    }

}
