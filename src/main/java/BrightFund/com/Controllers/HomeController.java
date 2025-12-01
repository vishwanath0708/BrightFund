package BrightFund.com.Controllers;


import BrightFund.com.Models.*;
import BrightFund.com.Repositories.StudentRepo;
import BrightFund.com.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Controller
public class HomeController {





    @Autowired
    StudentService studentService;


    @GetMapping(value={"/home","/"})
    public String Homecontrollermethod(){
        return "Home";
    }

    @GetMapping("/apply")
    public String applycontroller(Model model){


        model.addAttribute("student", new Student());

        return "apply";
    }

    @GetMapping("/create")
    public String createcontroller(Model model){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("student", new Studentlogin());
        return  "create";
    }


    @PostMapping("/register")
    public String registercontroller(@Valid @ModelAttribute ("student")Studentlogin studentlogin, BindingResult bindingResult, Model model) {

        // Validate Email
        if (!studentlogin.getEmail().equals(studentlogin.getConfirmemail())) {
            bindingResult.rejectValue("confirmemail", "error.studentlogin", "Emails do not match");
        }

        // Validate Password
        if (!studentlogin.getPassword().equals(studentlogin.getConfirmpassword())) {
            bindingResult.rejectValue("confirmpassword", "error.studentlogin", "Passwords do not match");
        }

        // If validation fails → return to create page
        if (bindingResult.hasErrors()) {
            return "create";  // your create.html page
        }

        // Call service to save student
        boolean success = studentService.StudentRegistration(studentlogin);

        if (success) {
            model.addAttribute("successmessage", "Account Created Successfully");
            return "customlogin";  // redirect to login page
        } else {
            model.addAttribute("errormessage", "Registration Failed. Please try again.");
            return "create";
        }
    }


    @GetMapping("/about")
    public String aboutcontroller(){

        return "about";
    }


    @GetMapping("/contact")
    public String contactcontroller(){

        return "contact";
    }


    @GetMapping("/dashboard")
    public String dashcontroller(Authentication authentication ,Model model,
                                 @RequestParam(value = "msg", required = false) String msg){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // This gives you the "principal", which you returned in your AuthenticationProvider
        Student student =  studentService.getStudent(auth.getName()); // in your provider, you used student.getName()


        model.addAttribute("name",student.getName());
        if(student.getScholarshipApplication()!=null){
            model.addAttribute("date",student.getScholarshipApplication().getDateSubmitted());
        }


        model.addAttribute("email",student.getEmail());
        model.addAttribute("course",student.getCourse());
        model.addAttribute("semester",student.getSemester());
        if(student.getScholarshipApplication()!=null){
            model.addAttribute("status",student.getScholarshipApplication().getStatus());
        }else{
            model.addAttribute("status","NOT_SUBMITTED");
        }
        model.addAttribute("msg",msg);


        // your Thymeleaf page

        return "Dashboard";
    }


    @PostMapping("/submitApplication")
    public String submittapplication(@ModelAttribute("student") Student student,  Model model) {

        boolean b=studentService.submitapplication(student);
        if(b){
            return "redirect:/dashboard";
        }
        return "redirect:/apply";
    }

    @GetMapping("/updateprofile")
    public String updateprofilecontroller(Model model,
                                          @RequestParam(value = "msg", required = false) String msg   ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student student =  studentService.getStudent(auth.getName());
        model.addAttribute("student",student);
        model.addAttribute("msg",msg);
        return  "updateprofile";
    }
    @PostMapping("/savechanges")
    public String savechanges(@ModelAttribute("student") Student student, Model model) {
        boolean b= studentService.updateprofile(student);
        if(b){
            return "redirect:/dashboard?msg=updated";
        }
        return "redirect:/updateprofile?msg=failed";
    }
    @GetMapping("/semester")
    public String showSemesterPage(Authentication authentication, Model model) {
        // 1️⃣ Fetch logged-in student
        Student student = studentService.getStudent(authentication.getName());

        // 2️⃣ Fetch all semester results
        List<Result> results = studentService.getAllResults(student);
        Map<Integer, Result> semesterResults = new HashMap<>();
        for (Result r : results) {
            semesterResults.put(r.getSemester(), r);
            System.out.println("Semester: " + r.getSemester() + " | File: " + r.getFileName()); // Debug
        }
        model.addAttribute("semesterResults", semesterResults);


        int sem = 0;
        try {
            sem = Integer.parseInt(student.getSemester());
        } catch (NumberFormatException e) {
            sem = 1; // default value
        }
        model.addAttribute("sem", sem);
// assuming getSemester() returns int

        // 4️⃣ Fetch fee receipts
        List<FeeReceipt> receipts = studentService.findAllFeeReceipt(student);
        Map<Integer, FeeReceipt> feeReceipts = new HashMap<>();
        for (FeeReceipt f : receipts) {
            feeReceipts.put(f.getYear(), f);
        }
        int year=1;
        if((sem/2)+1>1&&(sem%2)==1){
            year=(sem/2)+1;
        }
        model.addAttribute("feeReceipts", feeReceipts);
        model.addAttribute("year", year);
        return "semester";
    }




    @PostMapping("/uploadResult")
    public String uploadResult(@RequestParam("semester") int semester,
                               @RequestParam("file") MultipartFile file,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {

        Student student = studentService.getStudent(authentication.getName());

        try {
            // Check if this semester result already exists
            Result result = studentService.getResult(student, semester);

            if (result == null) {
                result = new Result();
                result.setStudent(student);
                result.setSemester(semester);
            }

            result.setFileName(file.getOriginalFilename());
            result.setFile(file.getBytes());

            // Save to DB
            boolean b=studentService.saveResult(result);
            if(b) {

                redirectAttributes.addFlashAttribute("message", "Semester " + semester + " Uploaded Successfully!");
                redirectAttributes.addFlashAttribute("success", true);

            }
            else{
                redirectAttributes.addFlashAttribute("message", "Semester " + semester + " Upload Failed!");
                redirectAttributes.addFlashAttribute("success", false);

            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Semester " + semester + " Upload Failed!");
            redirectAttributes.addFlashAttribute("success", false);
            e.printStackTrace();
        }

        return "redirect:/semester";
    }


    @PostMapping("/uploadReceipt")
    public String uploadFeeReceipt(@RequestParam("year") int year,
                                   @RequestParam("feeFile") MultipartFile file,
                                   Authentication authentication,
                                   RedirectAttributes redirectAttributes) {
        Student student = studentService.getStudent(authentication.getName());

        try {
            FeeReceipt receipt = studentService.getFeeReceipt(student, year);

            if (receipt == null) {
                receipt = new FeeReceipt();
                receipt.setStudent(student);
                receipt.setYear(year);
            }

            receipt.setFileName(file.getOriginalFilename());
            receipt.setFile(file.getBytes());

            boolean saved = studentService.saveFeeReceipt(receipt);
            if (saved) {
                redirectAttributes.addFlashAttribute("message", "Fee Receipt for Year " + year + " Uploaded Successfully!");
                redirectAttributes.addFlashAttribute("success", true);
            } else {
                redirectAttributes.addFlashAttribute("message", "Fee Receipt Upload Failed!");
                redirectAttributes.addFlashAttribute("success", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Failed to upload fee receipt!");
            redirectAttributes.addFlashAttribute("success", false);
        }

        return "redirect:/semester";
    }

    @GetMapping("/documents")
    public String documentsHome(Authentication authentication, Model model) {

        Student student = studentService.getStudent(authentication.getName());

        // Fetch documents as a map: documentType -> uploaded file (can be null)
        Map<String, Documents> documentsMap = new LinkedHashMap<>();
        documentsMap.put("Aadhar Card", studentService.getDocuments(student, "adharCard"));
        documentsMap.put("Bank Passbook", studentService.getDocuments(student, "bankPassbook"));
        documentsMap.put("Photo", studentService.getDocuments(student, "photo"));
        documentsMap.put("Signature", studentService.getDocuments(student, "signature"));
        documentsMap.put("Income Certificate", studentService.getDocuments(student, "incomeCertificate"));
        documentsMap.put("College Admission Order", studentService.getDocuments(student, "collegeAdmissionOrder"));

        model.addAttribute("documents", documentsMap);

        return "documents";
    }



    @PostMapping("/savedocument")
    public String uploadDocument(Authentication authentication,
                                 @RequestParam("docType") String docType,
                                 @RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) {

        try {
            String email = authentication.getName();
            Student student = studentService.getStudent(email);

            if (file != null && !file.isEmpty()) {

                Documents existing = studentService.getDocuments(student, docType);

                if (existing != null) {
                    existing.setFile(file.getBytes());
                } else {
                    existing = new Documents();
                    existing.setStudent(student);
                    existing.setFileName(docType);
                    existing.setFile(file.getBytes());
                }

                boolean saved = studentService.saveDocuments(existing);

                if (saved) {
                    redirectAttributes.addFlashAttribute("message", "Document uploaded successfully!");
                    redirectAttributes.addFlashAttribute("success", true);
                } else {
                    redirectAttributes.addFlashAttribute("message", "Failed to upload document!");
                    redirectAttributes.addFlashAttribute("success", false);
                }
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to upload: " + e.getMessage());
            redirectAttributes.addFlashAttribute("success", false);
        }

        return "redirect:/documents";  // MUST redirect
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable int id) {
        Documents doc = studentService.findbyId(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getFileName() + "\"")
                .body(doc.getFile());
    }


    @GetMapping("/achievements")
    public String achievementHome(Authentication authentication, Model model) {
        Student student = studentService.getStudent(authentication.getName());
        List<Achievements> achievements=studentService.findAllAchievements(student);
        model.addAttribute("achievements", achievements);
        return "Achievements";
    }

    @PostMapping("/saveAchievement")
    public String saveAchievement(
            Authentication authentication,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String date,
            @RequestParam(required = false) MultipartFile file,
            Model model) {

        try {
            Student student = studentService.getStudent(authentication.getName());

            if (student == null) {
                model.addAttribute("message", "Student not found!");
                model.addAttribute("success", false);
                return "redirect:/achievements";
            }

            Achievements achievement = new Achievements();
            achievement.setTitle(title);
            achievement.setDescription(description);
            achievement.setDate(LocalDate.parse(date));
            achievement.setStudent(student);

            // If file uploaded
            if (file != null && !file.isEmpty()) {
                achievement.setFile(file.getBytes());
                achievement.setFileName(file.getOriginalFilename());
            }

            studentService.saveAchievement(achievement);

            model.addAttribute("message", "Achievement added successfully!");
            model.addAttribute("success", true);

        } catch (Exception ex) {
            model.addAttribute("message", "Error while saving achievement!");
            model.addAttribute("success", false);
            ex.printStackTrace();
        }

        return "redirect:/achievements";
    }









}
