package com.solRoom.solspring.controller;

import com.solRoom.solspring.config.auth.CustomUserDetails;
import com.solRoom.solspring.domain.Board;
import com.solRoom.solspring.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class BoardController {
    @Autowired
    private final BoardService boardService;
    @GetMapping("/board/saveForm")
    public String SaveForm() {
        return "/board/saveForm"; // saveForm.html 파일의 이름
    }

    @PostMapping("board/save")
    public String saveForm(Board board,@AuthenticationPrincipal CustomUserDetails details){
        boardService.savePost(board,details.getMember());
        System.out.println("저장완료!");
        return "redirect:/";
    }

    @GetMapping("board/boardList")
    public String boardList(Model model, @PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC)
                            Pageable pageable){
        model.addAttribute("boards",boardService.boardList(pageable));
        return "/board/boardList";
    }

    @GetMapping("/board/{Id}")
    public String findById(@PathVariable("Id") Long Id,Model model){
         Board board = boardService.viewDetail(Id);
         model.addAttribute("board",board);
         return "/board/detail";
    }

    @PostMapping("/board/delete")
    public String deleteBoard(@RequestParam("id")Long id){
        System.out.println("Dddd");
        boardService.deleteBoard(id);
        return "redirect:/board/boardList";
    }


}
