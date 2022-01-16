package job4j.auth.controller;

import job4j.auth.model.Employee;
import job4j.auth.model.Person;
import job4j.auth.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final static String API = "http://localhost:8080/person/";
    private final static String API_ID = "http://localhost:8080/person/{id}";

    @Autowired
    private EmployeeRepository employees;

    @Autowired
    private RestTemplate rest;

    @GetMapping("/")
    public List<Employee> getAll() {
        List<Employee> empl = StreamSupport.stream(employees.findAll().spliterator(), false)
                .collect(Collectors.toList());
        List<Person> persons = rest.exchange(
                API,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() { }
        ).getBody();

        return empl;
    }

    @PostMapping("/")
    public ResponseEntity<Person> addAccount(@RequestBody Person person) {
        Person el = rest.postForObject(API, person, Person.class);
        return new ResponseEntity<>(el, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateAccount(@RequestBody Person person) {
        rest.put(API, person, Person.class);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }
}
