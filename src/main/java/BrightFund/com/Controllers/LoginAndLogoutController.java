package BrightFund.com.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginAndLogoutController {

    @RequestMapping(value="/signin",method = {RequestMethod.GET,RequestMethod.POST})
    public String signinmethod(@RequestParam(value="error",required = false)String error
            ,@RequestParam(value="logout",required = false)String logout, Model model){
        String errormessage="";
        if(error!=null){
            errormessage="Invalid username or password";
        }
        if(logout!=null){
            errormessage="Successfully logged out";
        }
        model.addAttribute("errormessage",errormessage);
        return "customlogin";

    }
}
