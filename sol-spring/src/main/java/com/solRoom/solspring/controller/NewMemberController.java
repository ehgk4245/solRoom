package solroom.solspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewMemberController {
    @GetMapping("/newMember.html")
    public String showNewMemberPage() {
        return "newMember"; // newMember.html 파일로 연결
    }

    @GetMapping("/newMember")
    public String redirectToNewMemberPage() {
        return "newMember"; // newMember.html 파일로 연결
    }
}
