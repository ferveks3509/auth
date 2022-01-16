package job4j.auth.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String secondname;
    private String inn;
    @Column(name = "hire_date")
    private Timestamp hireDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Person> persons = new ArrayList<>();

    public static Employee of(int id, String firstname,
                              String secondname,
                              String inn,
                              List<Person> persons) {
        Employee employee = new Employee();
        employee.id = id;
        employee.firstname = firstname;
        employee.inn = inn;
        employee.hireDate = Timestamp.valueOf(LocalDateTime.now());
        employee.persons = persons;
        return employee;
    }
    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Timestamp getCreateDate() {
        return hireDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.hireDate = createDate;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
