package com.solRoom.solspring.service;

import com.solRoom.solspring.domain.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BarterBoardService implements BoardService{
    @Override
    public Page<FreeBoard> boardList(Pageable pageable) {
        return null;
    }

    @Override
    public FreeBoard viewDetail(Long id) {
        return null;
    }

    @Override
    public void deleteBoard(Long id) {

    }
}
