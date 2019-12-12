package pl.pmisko.Todo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;
    private TodoService todoService;

    @Before
    public void setUp() {
        todoService = new TodoService(todoRepository);
    }

    @Test
    public void getTodoAndToggleIsDoneIfPresent() {
        final Todo todo = new Todo(1, "Do something", false);
        final boolean isFalse = todo.isDone();
        final int expectedBooleanValue = 200;
        final HttpStatus expectedHttpStatus = HttpStatus.OK;

        Mockito.when(todoRepository.findById(1)).thenReturn(java.util.Optional.of(todo));

        final ResponseEntity<Todo> todoResponseEntity = todoService.getTodoIfPresentAndToggleIsDone(1);

        final HttpStatus resultHttpStatus = todoResponseEntity.getStatusCode();
        final int resultBooleanValue = todoResponseEntity.getStatusCodeValue();

        assertEquals(expectedBooleanValue, resultBooleanValue);
        assertEquals(expectedHttpStatus, resultHttpStatus);
        assertNotEquals(isFalse, todo.isDone());
    }

    @Test
    public void shouldReturnAllTodoFromTodoRepository() {
        final List<Todo> todoList = new ArrayList<>();
        Todo todo = new Todo(1, "Workout", true);
        todoList.add(todo);

        Mockito.when(todoRepository.findAll()).thenReturn(todoList);

        final ResponseEntity<List<Todo>> allTodo = todoService.findAllTodo();
        assertEquals(allTodo.getBody().get(0), todoList.get(0));
    }

    @Test
    public void addTodoShouldAddTodoToRepository() {
        final List<Todo> allTodo = todoRepository.findAll();
        final int size = 1;
        final int statusCode = 200;

        Todo todo = new Todo(0, "Test", false);

        allTodo.add(todo);

        final ResponseEntity<Todo> todoResponseEntity = todoService.addTodo(todo);

        assertEquals(size, allTodo.size());
        assertEquals(todoResponseEntity.getStatusCodeValue(), statusCode);
    }

    @Test
    public void deleteTodoShouldDeleteTodoFromRepositoryIfPresent() {
        Todo todo = new Todo(1, "Test", true);
        Todo todo1 = new Todo(2, "Test", true);
        Todo todo2 = new Todo(3, "Test", true);

        final List<Todo> todoList = Arrays.asList(todo, todo1, todo2);

        Todo todo3 = new Todo(4, "Test", true);


        Mockito.when(todoRepository.findAll()).thenReturn(todoList);

        final ResponseEntity<Todo> responseWithSuccess = todoService.deleteTodo(todo);
        final ResponseEntity<Todo> responseWithoutSuccess = todoService.deleteTodo(todo3);

        final int successCodeValue = responseWithSuccess.getStatusCodeValue();
        final int failureCodeValue = responseWithoutSuccess.getStatusCodeValue();


        assertEquals(200, successCodeValue);
        assertEquals(404, failureCodeValue);
    }
}