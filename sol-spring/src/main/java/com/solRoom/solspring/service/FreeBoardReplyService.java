package com.solRoom.solspring.service;

import com.solRoom.solspring.controller.dto.FreeBoardReplyDTO;
import com.solRoom.solspring.domain.freeBoard.FreeBoard;
import com.solRoom.solspring.domain.freeBoard.FreeBoardReply;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.repository.FreeBoardReplyRepository;
import com.solRoom.solspring.repository.FreeBoardRepository;
import com.solRoom.solspring.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FreeBoardReplyService {
    @Autowired
    private final FreeBoardRepository freeBoardRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private FreeBoardReplyRepository freeBoardReplyRepository;


    public void saveReply(FreeBoardReplyDTO replyDTO, Long id, String email) {
        FreeBoard board = freeBoardRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        FreeBoardReply boardReply = replyDTO.toEntity(board,member);
        freeBoardReplyRepository.save(boardReply);
    }

    public List<FreeBoardReplyDTO> replyList(Long id) {
        FreeBoard board = freeBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        List<FreeBoardReply> replies = freeBoardReplyRepository.findByBoard(board);

        return replies.stream()
                .map(reply -> FreeBoardReplyDTO.builder()
                        .content(reply.getContent())
                        .nickname(reply.getMember().getNickname())
                        .createDate(reply.getCreateDate())
                        .build())
                .collect(Collectors.toList());
    }

}
