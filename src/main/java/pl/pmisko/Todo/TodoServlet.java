package pl.pmisko.Todo;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
class TodoServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(TodoServlet.class);
    private TodoRepository todoRepository;
    private TodoService todoService;

    TodoServlet(TodoRepository todoRepository, TodoService todoService){
        this.todoRepository = todoRepository;
        this.todoService=todoService;
    }

    @GetMapping
    private ResponseEntity<List<Todo>> findAll() {
        logger.info("Got request");
        return todoService.findAllTodo();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Todo> toggleTodo(@PathVariable Integer id) {
       return todoService.getTodoIfPresentAndToggleIsDone(id);
    }

    @PostMapping
    private ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
       return todoService.addTodo(todo);
    }

    @DeleteMapping
    private ResponseEntity delete(@RequestBody Todo todo) {return todoService.deleteTodo(todo);}
}
