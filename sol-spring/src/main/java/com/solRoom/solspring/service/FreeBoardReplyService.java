package com.solRoom.solspring.service;

import com.solRoom.solspring.controller.dto.FreeBoardReplyDTO;
import com.solRoom.solspring.domain.FreeBoard;
import com.solRoom.solspring.domain.FreeBoardReply;
import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.repository.FreeBoardReplyRepository;
import com.solRoom.solspring.repository.FreeBoardRepository;
import com.solRoom.solspring.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
