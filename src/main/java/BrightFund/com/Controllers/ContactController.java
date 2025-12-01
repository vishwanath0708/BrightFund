package BrightFund.com.Controllers;

import BrightFund.com.Models.Contact;
import ch.qos.logback.core.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ContactController {
    private static final Logger log = LoggerFactory.getLogger(ContactController.class);
    @PostMapping ("/getcontact")
    public String contact(Contact contact, Model model) {
        log.info(contact.toString());
        log.info(contact.getMessage());
        log.info(contact.getName());
        return "contact";
    }
}
