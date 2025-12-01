package BrightFund.com.Service;


import BrightFund.com.Models.*;
import BrightFund.com.Repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class StudentService {

    @Autowired
    StudentRepo repo;
    @Autowired
    ScholarshipRepo scholarshipRepo;
    @Autowired
    ResultRepo resultRepo;

    @Autowired
    FeeReceiptRepo feeReceiptRepo;

    @Autowired
    DocumentsRepo documentsRepo;

    @Autowired
    AchievementsRepo achievementsRepo;


    public boolean StudentRegistration(Studentlogin studentlogin) {
        Student student = new Student();

        student.setRole("Student");
        student.setName(studentlogin.getName());
        student.setEmail(studentlogin.getEmail());
        student.setPassword(studentlogin.getPassword());
        Student s=repo.save(student);
        if(s!=null){
            return true;
        }
           return false;
    }
    public void updatestudent(String email, String password) {
        Student s=repo.findByEmail(email);
        s.setPassword(password);
        repo.save(s);
    }

    public Student getStudent(String email) {
        return repo.findByEmail(email);
    }

    public boolean submitapplication(Student student) {
        Student s=repo.findByEmail(student.getEmail());
        if(s!=null){
            s.setEmail(student.getEmail());
            s.setName(student.getName());
            s.setAddress(student.getAddress());
            s.setPhone(student.getPhone());
            s.setIncome(student.getIncome());
            s.setInstitute(student.getInstitute());
            s.setInstituteaddress(student.getInstituteaddress());
            s.setSemester(student.getSemester());
            s.setCourse(student.getCourse());

            // Create scholarship application and link both sides
            ScholarshipApplication sch = new ScholarshipApplication();
            sch.setStudent(s);          // link scholarship -> student
            sch.setStatus("REVIEWING");
            sch.setDateSubmitted(LocalDate.now());

            s.setScholarshipApplication(sch); // link student -> scholarship

            repo.save(s);

            return true;
        }
        return false;
    }
    public boolean updateprofile(Student student) {
        Student s=repo.findByEmail(student.getEmail());
        if(s!=null){
            s.setEmail(student.getEmail());
            s.setName(student.getName());
            s.setAddress(student.getAddress());
            s.setPhone(student.getPhone());
            s.setIncome(student.getIncome());
            s.setInstitute(student.getInstitute());
            s.setInstituteaddress(student.getInstituteaddress());
            s.setSemester(student.getSemester());
            s.setCourse(student.getCourse());

            Student s1=repo.save(s);
            if(s1!=null){
                return true;
            }

        }
        return false;
    }


    public Result getResult(Student student, int semester) {
        return  resultRepo.findByStudentAndSemester(student, semester);
    }

    public FeeReceipt getFeeReceipt(Student student,int year) {
        return feeReceiptRepo.findByStudentAndYear(student,year);
    }

    public boolean saveResult(Result result) {
       Result r= resultRepo.save(result);
       if(r!=null){
           return true;
       }
        return false;
    }

    public boolean saveFeeReceipt(FeeReceipt feeReceipt) {
        FeeReceipt fe=feeReceiptRepo.save(feeReceipt);
        if(fe!=null){
            return true;
        }
        return false;
    }


    @Transactional
    public List<Result> getAllResults(Student student) {
        return resultRepo.findByStudent(student);
    }

    @Transactional
    public List<FeeReceipt> findAllFeeReceipt(Student student) {
        return feeReceiptRepo.findByStudent(student);
    }


    @Transactional
    public List<Documents> getAlldocuments(Student student) {
        return documentsRepo.findByStudent(student);
    }

    @Transactional
    public boolean saveDocuments(Documents documents) {
        Documents d=documentsRepo.save(documents);
        if(d!=null){
            return true;
        }
        return false;
    }


    public Documents getDocuments(Student student,String filename) {
        return documentsRepo.findByStudentAndFileName(student,filename);
    }

    public Optional<Documents>findbyId(int id) {
        return documentsRepo.findById(id);
    }

    @Transactional
    public List<Achievements>findAllAchievements(Student student) {
        return achievementsRepo.findByStudent(student);
    }
    public void saveAchievement(Achievements achievements) {
        achievementsRepo.save(achievements);
    }
}
