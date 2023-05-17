package com.pepe.test.service;

import com.pepe.test.dao.MemberDao;
import com.pepe.test.dto.MemberDto;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  MemberDao memberDao;

  @Override
  public int insertMember(MemberDto memberDto) {
    int result = memberDao.insertMember(memberDto);
    return result;
  }

  @Override
  public int idCheck(MemberDto memberDto) {
    int result = memberDao.idCheck(memberDto);
    return result;
  }

  @Override
  public MemberDto loginMember(MemberDto memberDto) {
    MemberDto loginMember = memberDao.loginMember(memberDto);
    return loginMember;
  }

  @Override
  public int modifyMember(MemberDto memberDto) {
    int result = memberDao.modifyMember(memberDto);
    return result;
  }

  @Override
  public int deleteMember(MemberDto memberDto) {
    int result = memberDao.deleteMember(memberDto);
    return result;
  }
}
