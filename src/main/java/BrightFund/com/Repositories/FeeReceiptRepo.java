package BrightFund.com.Repositories;

import BrightFund.com.Models.FeeReceipt;
import BrightFund.com.Models.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public interface FeeReceiptRepo extends JpaRepository<FeeReceipt, Integer> {

    List<FeeReceipt> findByStudent(Student student);

    FeeReceipt findByStudentAndYear(Student student,int year);
}
