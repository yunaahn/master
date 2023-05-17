package com.pepe.test.service;

import com.pepe.test.dto.MemberDto;

//정의 만 내리는 사람 부장
public interface MemberService {
  int insertMember(MemberDto memberDto);
  int idCheck(MemberDto memberDto);
  MemberDto loginMember(MemberDto meberDto);
  int modifyMember(MemberDto memberDto);
  int deleteMember(MemberDto memberDto);
}
