package BrightFund.com.Models;

import BrightFund.com.Auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Student extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private Date dateOfBirth;


    private String role;

    private String phone;

    private String course;

    private String semester;

    private String institute;

    private String instituteaddress;

    private String address;

    private Long income;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ScholarshipApplication scholarshipApplication;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Result> results;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<FeeReceipt> feeReceipts;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Documents> documents;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Achievements> achievements;



}
