package BrightFund.com.Repositories;

import BrightFund.com.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Integer> {
        Student findByEmail(String email);
}
