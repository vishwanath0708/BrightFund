package BrightFund.com.Repositories;

import BrightFund.com.Models.Result;
import BrightFund.com.Models.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ResultRepo extends JpaRepository<Result, Long> {

    Result findByStudentAndSemester(Student student, int semester);
     List<Result> findByStudent(Student student);
}
