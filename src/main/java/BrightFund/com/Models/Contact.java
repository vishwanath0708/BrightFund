package BrightFund.com.Models;

import BrightFund.com.Auditing.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact extends BaseEntity {
    private String Name;
    private String Email;
    private String message;
    public Contact() {
    }
    public Contact(String name, String email, String message) {
        Name = name;
    }
}
