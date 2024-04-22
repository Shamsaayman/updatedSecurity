package Service;

import Model.Todo;
import Model.User;
import Repository.AuthRepository;
import Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
private final AuthRepository authRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public List<Todo> getMyTodos(Integer userId) {
        User user = authRepository.findUserById(userId);
        return todoRepository.findAllByUser(user);
    }

    public void addTodo(Integer userId , Todo todo) {
        User user = authRepository.findUserById(userId);
        todo.setUser(user);
        todoRepository.save(todo);
    }
}
