package com.solRoom.solspring.controller;

import com.solRoom.solspring.config.auth.CustomUserDetails;
import com.solRoom.solspring.controller.dto.BarterBoardDTO;
import com.solRoom.solspring.controller.dto.BoardImageUploadDTO;
import com.solRoom.solspring.controller.dto.RecipeBoardDTO;
import com.solRoom.solspring.service.RecipeBoardService;
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

import java.util.Arrays;

@AllArgsConstructor
@Controller
@RequestMapping("/RecipeBoard")
public class RecipeBoardController {

    @Autowired
    private final RecipeBoardService boardService;

    @GetMapping("/")
    public String home(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)
    Pageable pageable, @RequestParam(name = "searchText" ,required = false, defaultValue = "") String searchText
    ){
        Page<RecipeBoardDTO> boards = boardService.findSearchList(searchText,searchText,pageable);
        model.addAttribute("boards",boards);
        return "/recipeBoard/home";
    }

    @GetMapping("/registration")
    public String recipeRegistrationForm(Model model){
        model.addAttribute("recipeBoard", new RecipeBoardDTO());
        return "recipeBoard/registrationForm";
    }

    @PostMapping("/registration")
    public String recipeRegistration(@AuthenticationPrincipal CustomUserDetails userDetails,
                                     @Valid @ModelAttribute("recipeBoard")RecipeBoardDTO recipeBoardDTO,
                                     BoardImageUploadDTO boardImageUploadDTO,
                                     BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "recipeBoard/registrationForm";
        }
        System.out.println(recipeBoardDTO);
        System.out.println(recipeBoardDTO);
        System.out.println(recipeBoardDTO.getIngredients());
        boardService.savePost(recipeBoardDTO,boardImageUploadDTO,userDetails.getMember());

        return "redirect:/RecipeBoard/";
    }



}
