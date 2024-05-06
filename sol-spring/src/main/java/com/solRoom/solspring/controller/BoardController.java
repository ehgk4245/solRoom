package com.solRoom.solspring.controller;

import com.solRoom.solspring.domain.Board;
import com.solRoom.solspring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model){

        model.addAttribute("board", boardService.list());
        return "list";
    }

    @GetMapping("detail/{idx}")
    public String detail(@PathVariable int idx, Model model){

        model.addAttribute("board", boardService.detail(idx));
        return "detail";
    }

    @GetMapping("/register")
    public String registerGet(){
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(Board board){

        boardService.register(board);
        return "redirect:/board/list";
    }

    @GetMapping("/update/{idx}")
    public String updateGet(@PathVariable int idx, Model model){

        model.addAttribute("board", boardService.detail(idx));
        return "update";
    }

    @PostMapping("/update")
    public String updatePost(Board board){

        boardService.update(board);
        return "redirect:/board/list";
    }

    @GetMapping("/delete/{idx}")
    public String delete(@PathVariable int idx){

        boardService.delete(idx);
        return "list";
    }
}
