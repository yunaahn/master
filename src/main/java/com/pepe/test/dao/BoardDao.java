package com.pepe.test.dao;

import com.pepe.test.dto.BoardDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {
  int writeBoard(BoardDto boardDto);
  List<BoardDto> getBoardList();
  BoardDto getBoardOne(int no);
  int updateHit(int no);
  int getMaxGroup();
  List<BoardDto> getImageBoardList();
  //int writeimageBoard(BoardDto boardDto);
}
