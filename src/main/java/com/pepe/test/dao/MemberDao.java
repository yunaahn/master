package com.pepe.test.dao;

import com.pepe.test.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
  int insertMember(MemberDto memberDto);
  int idCheck(MemberDto memberDto);
  MemberDto loginMember(MemberDto memberDto);
  int modifyMember(MemberDto memberDto);
  int deleteMember(MemberDto memberDto);
}
