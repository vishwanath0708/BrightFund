package BrightFund.com.Models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Entity
@Transactional
@Getter
@Setter
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "file_name", nullable = false, length = 255,unique = true)
    private String fileName;

    @Lob  // important for large objects
    @Column(name = "file", nullable = false)
    private byte[] file;



}
