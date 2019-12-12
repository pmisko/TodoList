package pl.pmisko.Todo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    ResponseEntity<Todo> getTodoIfPresentAndToggleIsDone(@PathVariable Integer id) {
        final Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(t -> {
            t.setDone(!t.isDone());
            todoRepository.save(t);
        });
        return todo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    ResponseEntity<List<Todo>> findAllTodo() {
        return ResponseEntity.ok(todoRepository.findAll());
    }

    ResponseEntity<Todo> addTodo(Todo todo) {
        return ResponseEntity.ok(todoRepository.save(todo));
    }

    //Not implemented in front-end
    ResponseEntity<Todo> deleteTodo(Todo todo) {
        final List<Todo> allTodo = todoRepository.findAll();

        final Optional<Todo> todo1;
        try {
            todo1 = Optional.ofNullable(allTodo.get(todo.getId()));
        } catch (ArrayIndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
            todo1.ifPresent(t -> {
                todoRepository.delete(t);
            });
            return todo1.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }
    }
