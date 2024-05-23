package com.solRoom.solspring.controller;

import com.solRoom.solspring.config.auth.CustomUserDetails;
import com.solRoom.solspring.controller.dto.FreeBoardDTO;
import com.solRoom.solspring.controller.dto.FreeBoardReplyDTO;
import com.solRoom.solspring.domain.FreeBoard;
import com.solRoom.solspring.service.FreeBoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("freeBoard")
public class FreeBoardController {
    @Autowired
    private final FreeBoardService boardService;
    @GetMapping("/saveForm")
    public String SaveForm() {
        return "/freeBoard/saveForm"; // saveForm.html 파일의 이름
    }

    @PostMapping("/save")
    public String saveForm(FreeBoardDTO boardDTO, @AuthenticationPrincipal CustomUserDetails userDetails){
        boardService.savePost(boardDTO,userDetails.getMember());
        return "redirect:/freeBoard/boardList";
    }

    @GetMapping("/boardList")
    public String boardList(Model model, @PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC)
                            Pageable pageable){
        model.addAttribute("boards",boardService.boardList(pageable));
        return "/freeBoard/boardList";
    }

    @GetMapping("/{Id}")
    public String findById(@PathVariable("Id") Long Id,Model model){
         FreeBoard board = boardService.viewDetail(Id);
         model.addAttribute("board",board);
        model.addAttribute("replyDTO", new FreeBoardReplyDTO());
         return "/freeBoard/detail";
    }

    @PostMapping("/delete")
    public String deleteBoard(@RequestParam("id")Long id){
        boardService.deleteBoard(id);
        return "redirect:/freeBoard/boardList";
    }


}
