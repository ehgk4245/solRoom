package com.solRoom.solspring.controller.boardController;

import com.solRoom.solspring.config.auth.CustomUserDetails;
import com.solRoom.solspring.controller.dto.BarterBoardDTO;
import com.solRoom.solspring.controller.dto.BoardImageUploadDTO;
import com.solRoom.solspring.service.BarterBoardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("barterBoard")
public class BarterBoardController {
    @Autowired
    private final BarterBoardService boardService;
    @GetMapping("")
    public String home(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)
    Pageable pageable,@RequestParam(name = "searchText" ,required = false, defaultValue = "") String searchText
                       ){
        //List<BarterBoardDTO>boards = boardService.findAll();
        Page<BarterBoardDTO>boards = boardService.findSearchList(searchText,searchText,pageable);
        model.addAttribute("boards",boards);
        return "/barterBoard/home";
    }

    @GetMapping("/registration")
    public String productRegistrationForm(Model model){
        model.addAttribute("barterBoard", new BarterBoardDTO());
        return "barterBoard/registrationForm";
    }

    @PostMapping("/registration")
    public String productRegistration(@AuthenticationPrincipal CustomUserDetails userDetails,
                                      @Valid @ModelAttribute("barterBoard") BarterBoardDTO barterBoardDTO,
                                      BindingResult bindingResult,
                                      @ModelAttribute BoardImageUploadDTO boardImageUploadDTO,
                                      Model model){
        System.out.println(boardImageUploadDTO.getFiles());
        if(boardImageUploadDTO.getFiles().isEmpty()){
            return "barterBoard/registrationForm";
        }
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "barterBoard/registrationForm";
        }
        boardService.savePost(barterBoardDTO,boardImageUploadDTO,userDetails.getMember());


        return "redirect:/barterBoard";
    }

    @GetMapping("/category/{id}")
    public String boardList(@PathVariable("id")Long id, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)
    Pageable pageable, Model model){

        Page<BarterBoardDTO> boards = boardService.findByCategoryId(id, pageable);
        model.addAttribute("boards", boards);
        return "barterBoard/boardList";
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        BarterBoardDTO board = boardService.viewDetail(id);
        model.addAttribute("board",board);
        return "/barterBoard/detail";
    }


    @PostMapping("/delete")
    public String deleteBoard(@RequestParam("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/barterBoard";
    }

    @GetMapping("/updateForm/{Id}")
    public String updateForm(@PathVariable("Id") Long Id, Model model) {
        BarterBoardDTO board = boardService.viewDetail(Id);
        model.addAttribute("board", board);
        return "/barterBoard/updateForm";
    }

    @PostMapping("/update/{Id}")
    public String update(@PathVariable("Id") Long Id, BarterBoardDTO boardDTO) {
        System.out.println(Id);
        boardService.updateBoard(Id, boardDTO);
        return "redirect:/barterBoard";
    }


}
