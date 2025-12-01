package BrightFund.com.Models;

import BrightFund.com.Auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class ScholarshipApplication extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String scholarshipName;


    private String status;

    private LocalDate dateSubmitted;


    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
