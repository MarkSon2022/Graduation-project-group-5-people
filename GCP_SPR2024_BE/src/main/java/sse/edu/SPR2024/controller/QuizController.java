package sse.edu.SPR2024.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sse.edu.SPR2024.dto.request.QuizRequestDTO;
import sse.edu.SPR2024.dto.response.LessonResponseDTO;
import sse.edu.SPR2024.dto.response.QuizDetailHiddenResponseDTO;
import sse.edu.SPR2024.dto.response.QuizDetailResponseDTO;
import sse.edu.SPR2024.dto.response.QuizResponseDTO;
import sse.edu.SPR2024.service.IQuizService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final IQuizService quizService;

    @PostMapping
    public ResponseEntity<QuizDetailResponseDTO> createQuiz(@RequestBody QuizRequestDTO quizRequestDTO){
        QuizDetailResponseDTO quiz = quizService.createQuiz(quizRequestDTO);
        if(Objects.nonNull(quiz)){
            return new ResponseEntity<>(quiz, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponseDTO>  getQuizByLessonId(@PathVariable String id){
        LessonResponseDTO lessonWithQuiz = quizService.getQuizByLessonId(id);
        if(Objects.nonNull(lessonWithQuiz)){
            return new ResponseEntity<>(lessonWithQuiz, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/valid/{id}")
    public ResponseEntity<LessonResponseDTO> getQuizValidByLessonId(@PathVariable String id){
        LessonResponseDTO lessonWithQuiz = quizService.getQuizByLessonId(id);
        if(Objects.nonNull(lessonWithQuiz)){
            return new ResponseEntity<>(lessonWithQuiz, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<QuizDetailHiddenResponseDTO> getQuizById(@PathVariable Long id){
        QuizDetailHiddenResponseDTO quiz = quizService.getQuizById(id);
        if(Objects.nonNull(quiz)){
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<QuizDetailResponseDTO>> getAllQuizzes(){
        List<QuizDetailResponseDTO> quizzes = quizService.getAllQuizzes();
        if(Objects.nonNull(quizzes)){
            return new ResponseEntity<>(quizzes, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<QuizDetailHiddenResponseDTO> getDetailQuizById(@PathVariable Long id){
        QuizDetailHiddenResponseDTO quiz = quizService.getQuizById(id);
        if(Objects.nonNull(quiz)){
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/deactivate")
    public ResponseEntity<QuizDetailResponseDTO> deactivateQuiz(@RequestBody QuizRequestDTO quizRequestDTO){
        QuizDetailResponseDTO quiz = quizService.deactivateQuiz(quizRequestDTO);
        if(Objects.nonNull(quiz)){
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}