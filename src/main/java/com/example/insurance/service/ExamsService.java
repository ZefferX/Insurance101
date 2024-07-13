package com.example.insurance.service;

import com.example.insurance.dto.NewExamRequest;
import com.example.insurance.model.Exams;
import com.example.insurance.repository.ExamsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExamsService {
    private final ExamsRepository examsRepository;

    public void addExam(NewExamRequest request){
        Exams newExams = new Exams();
        newExams.setExamName(request.name());
        newExams.setPrice(request.price());
        newExams.setQuantity(request.quantity());
        examsRepository.save(newExams);
    }

    public Exams findExamById(Integer id){
        return examsRepository.findById(id).get();
    }

    public List<Exams> allExams (){
        return examsRepository.findAll();
    }

    public String deleteExamById(Integer id){
         Optional<Exams> exams = examsRepository.findById(id);
         if (exams.isEmpty()) return "Exam not found";
         examsRepository.deleteById(id);
         return "Exam of Id " + id + " deleted succesfully";
    }

    public Exams updateExams(NewExamRequest request, Integer id){
        Exams exams = examsRepository.findById(id).get();
        exams.setExamName(request.name());
        exams.setPrice(request.price());
        exams.setQuantity(request.quantity());
        return examsRepository.save(exams);

    }

}
