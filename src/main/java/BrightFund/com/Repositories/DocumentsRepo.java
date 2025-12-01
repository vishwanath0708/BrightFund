package BrightFund.com.Repositories;

import BrightFund.com.Models.Documents;
import BrightFund.com.Models.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface DocumentsRepo extends JpaRepository<Documents, Integer> {
    List<Documents> findByStudent(Student student);
    Documents findByStudentAndFileName(Student student, String fileName);
}
