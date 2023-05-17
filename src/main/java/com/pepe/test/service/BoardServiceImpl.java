package com.pepe.test.service;

import com.pepe.test.dao.BoardDao;
import com.pepe.test.dto.BoardDto;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

  @Autowired
  BoardDao boardDao;

  @Override
  public int writeBoard(BoardDto boardDto) {
    boardDto.setBoardGroup(getMaxGroup() + 1); //글쓰면 증가
    // boardDto.setBoardLevel(boardLevel:1);
    // boardDto.setBoardStep(boardStep:1);
    int result = boardDao.writeBoard(boardDto);
    return result;
  }

  @Override
  public List<BoardDto> getBoardList() {
    List<BoardDto> boardList = boardDao.getBoardList();
    return boardList;
  }

  @Override
  public BoardDto getBoardOne(int no) {
    // business logic 이 잔득 들어간다.
    updateHit(no);
    BoardDto boardDto = boardDao.getBoardOne(no);
    return boardDto;
  }

  @Override
  public int updateHit(int no) {
    int result = boardDao.updateHit(no);
    return result;
  }

  @Override
  public int getMaxGroup() {
    int max = boardDao.getMaxGroup();
    return max;
  }

  @Override
  public int replyBoard(BoardDto boardDto) {
    int boardGroup = boardDto.getBoardGroup();
    int boardLevel = boardDto.getBoardLevel();
    int boardStep = boardDto.getBoardStep();
    return 0;
  }
  // @Override
  // public List<BoardDto> getImageBoardList() {
  //   List<BoardDto> imageboardList = boardDao.getImageBoardList();
  //   return imageboardList;
  // }
  // @Override
  // public int writeimageBoard(BoardDto boardDto) {
  //   boardDto.setBoardGroup(getMaxGroup() + 1); //글쓰면 증가
  //   // boardDto.setBoardLevel(boardLevel:1);
  //   // boardDto.setBoardStep(boardStep:1);
  //   int result = boardDao.writeimageBoard(boardDto);
  //   return result;
  // }
}
