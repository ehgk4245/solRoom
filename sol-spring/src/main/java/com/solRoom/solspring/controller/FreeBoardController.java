package com.solRoom.solspring.controller;

import com.solRoom.solspring.config.auth.CustomUserDetails;
import com.solRoom.solspring.controller.dto.BoardImageUploadDTO;
import com.solRoom.solspring.controller.dto.FreeBoardDTO;
import com.solRoom.solspring.controller.dto.FreeBoardReplyDTO;
import com.solRoom.solspring.controller.dto.MemberDTO;
import com.solRoom.solspring.domain.*;
import com.solRoom.solspring.service.FreeBoardReplyService;
import com.solRoom.solspring.service.FreeBoardService;
import com.solRoom.solspring.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("freeBoard")
public class FreeBoardController {
    @Autowired
    private final FreeBoardService boardService;

    @Autowired
    private final LikeService likeService;

    @Autowired
    private final FreeBoardReplyService replyService;


    @GetMapping("/saveForm")
    public String saveForm() {
        return "/freeBoard/saveForm"; // saveForm.html 파일의 이름
    }

    @PostMapping("/save")
    public String saveForm(FreeBoardDTO boardDTO, @AuthenticationPrincipal CustomUserDetails userDetails,
                           @ModelAttribute BoardImageUploadDTO boardImageUploadDTO) {
        boardService.savePost(boardDTO, boardImageUploadDTO,userDetails.getMember());
        return "redirect:/freeBoard/boardList";
    }
    @GetMapping("/boardList")
    public String boardList(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)
    Pageable pageable) {
        model.addAttribute("boards", boardService.boardList(pageable));
        return "/freeBoard/boardList";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        FreeBoardDTO board = boardService.viewDetail(id);
        List<FreeBoardReplyDTO>replyDTO =replyService.replyList(id);
        System.out.println(replyDTO);
        model.addAttribute("board", board);
        model.addAttribute("replyDTO", replyDTO);
        boardService.upViewCount(id);
        boolean liked = likeService.isLikedByMember(userDetails.getMember(), id, BoardType.FREE);
        model.addAttribute("liked", liked);

        // 댓글 작성용 빈 DTO 추가
        FreeBoardReplyDTO newReplyDTO = new FreeBoardReplyDTO();
        model.addAttribute("newReplyDTO", newReplyDTO);



        return "/freeBoard/detail";

    }

    @PostMapping("/delete")
    public String deleteBoard(@RequestParam("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/freeBoard/boardList";
    }

    @GetMapping("/updateForm/{Id}")
    public String updateForm(@PathVariable("Id") Long Id, Model model) {
        FreeBoardDTO board = boardService.viewDetail(Id);
        model.addAttribute("boardDTO", board);
        return "/freeBoard/updateForm";
    }

    @PostMapping("/update/{Id}")
    public String update(@PathVariable("Id") Long Id, FreeBoardDTO boardDTO) {
        System.out.println(Id);
        boardService.updateBoard(Id, boardDTO);
        return "redirect:/freeBoard/boardList";
    }

    @PostMapping("/{boardId}/like")
    public String likePost(@PathVariable("boardId") Long boardId,
                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        Member member = userDetails.getMember();
        likeService.likePost(member, boardId, BoardType.FREE);
        boardService.upLikeCount(boardId);
        return "redirect:/freeBoard/" + boardId;
    }

    @PostMapping("/{boardId}/unlike")
    public String unlikePost(@PathVariable("boardId") Long boardId,
                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        Member member = userDetails.getMember();
        likeService.unlikePost(member, boardId, BoardType.FREE);
        boardService.downLikeCount(boardId);
        return "redirect:/freeBoard/" + boardId;
    }

}
