package Controller;

import Model.Todo;
import Model.User;
import Service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    @GetMapping("/getall")
    public ResponseEntity getAllTodos(){
        return ResponseEntity.status(200).body(todoService.findAll());
    }
    @GetMapping("/getmy")
    public ResponseEntity getMyTodos(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(todoService.getMyTodos(user.getId()));
    }
    @PostMapping("/add")
    public ResponseEntity addTodos(@AuthenticationPrincipal User user , @RequestBody @Valid Todo todo){
        todoService.addTodo(user.getId(), todo);
        return ResponseEntity.status(200).body("todo added");
    }

    }
