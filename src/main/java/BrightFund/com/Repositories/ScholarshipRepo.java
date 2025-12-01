package BrightFund.com.Repositories;

import BrightFund.com.Models.ScholarshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScholarshipRepo extends JpaRepository<ScholarshipApplication,Integer> {

}
