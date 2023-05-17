package com.pepe.test.service;

import com.pepe.test.dto.BoardDto;
import java.util.List;
import javax.validation.Valid;

public interface BoardService {
  int writeBoard(BoardDto boardDto);
  List<BoardDto> getBoardList();
  // List<BoardDto> getImageBoardList();
  BoardDto getBoardOne(int no);
  int updateHit(int no);
  int getMaxGroup();
  int replyBoard(BoardDto boardDto);
  // int writeimageBoard(BoardDto boardDto);
}
