package com.solRoom.solspring.controller;


import com.solRoom.solspring.config.auth.CustomUserDetails;
import com.solRoom.solspring.controller.dto.FreeBoardReplyDTO;
import com.solRoom.solspring.service.FreeBoardReplyService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/freeBoard")
public class FreeBoardReplyController {

    @Autowired
    private final FreeBoardReplyService freeBoardReplyService;

    @PostMapping("/{id}/save")  // {id} -> board의 id
    public String saveReply(@PathVariable("id")Long id, @AuthenticationPrincipal CustomUserDetails userDetails,
                            FreeBoardReplyDTO replyDTO){
        freeBoardReplyService.saveReply(replyDTO,id,userDetails.getUsername());
        return "redirect:/freeBoard/" + id;
    }


}
