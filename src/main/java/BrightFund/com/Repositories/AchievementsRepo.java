package BrightFund.com.Repositories;

import BrightFund.com.Models.Achievements;
import BrightFund.com.Models.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface AchievementsRepo extends JpaRepository<Achievements, Integer> {

    List<Achievements> findByStudent(Student student);
}
