package BrightFund.com.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "fee_receipt")
@Getter
@Setter
public class FeeReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private int year; // 1 to 4

    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;

    @Lob  // important for large objects
    @Column(name = "file", nullable = false)
    private byte[] File;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate = LocalDateTime.now();
}
