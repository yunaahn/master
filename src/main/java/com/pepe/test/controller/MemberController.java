package com.pepe.test.controller;

import com.pepe.test.dto.MemberDto;
import com.pepe.test.service.MemberService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

  @Autowired
  MemberService memberService;

  @GetMapping("/join")
  public String join(Model model) {
    model.addAttribute("memberDto", new MemberDto());
    return "/member/join";
  }

  @PostMapping("/join")
  //@ResponseBody
  public String joinProcess(
    @Valid MemberDto memberDto,
    BindingResult bindingResult,
    Model model
  ) {
    log.info("============memberDto===============");
    log.info(memberDto);
    if (bindingResult.hasErrors()) {
      model.addAttribute("memberDto", memberDto);
      return "/member/join";
    }
    int result = memberService.insertMember(memberDto);
    return "redirect:/";
  }

  @PostMapping("/idCheck")
  public ResponseEntity<?> idCheck(MemberDto memberDto) {
    log.info(memberDto);
    int result = memberService.idCheck(memberDto);
    log.info("result====" + result);
    Map<String, Integer> resultMap = new HashMap<>();
    resultMap.put("result", result);
    return ResponseEntity.status(HttpStatus.OK).body(resultMap);
  }

  @GetMapping("/login")
  public String login() {
    return "/member/login";
  }

  @PostMapping("/login")
  public String loginProcess(MemberDto memberDto, HttpSession session) {
    MemberDto loggedMember = memberService.loginMember(memberDto);
    if (loggedMember != null) {
      session.setAttribute("loggedMember", loggedMember);
      log.info(loggedMember.toString());
      return "redirect:/";
    }
    //model.addAttribute("loggedMember", loggedMember);
    return "redirect:/member/login";
  }

  @GetMapping("/logout")
  public String logOut(HttpSession session) {
    session.invalidate(); // session삭제
    return "redirect:/";
  }

  @GetMapping("/info")
  public String info() {
    return "/member/info";
  }

  @GetMapping("/modify")
  public String modify(HttpSession session, Model model) {
    // session값은 object이다. 형변화(up casting)
    MemberDto loggedMember = (MemberDto) session.getAttribute("loggedMember");
    model.addAttribute("memberDto", loggedMember);
    return "/member/modify";
  }

  @PostMapping("/modify")
  public String modifyProcess(
    @Valid MemberDto memberDto,
    BindingResult bindingResult,
    Model model,
    HttpSession session
  ) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("memberDto", memberDto);
      return "/member/modify";
    }
    int result = memberService.modifyMember(memberDto);
    session.setAttribute("loggedMember", memberDto);
    //session.invalidate();
    return "redirect:/";
  }

  @GetMapping("/delete")
  public String delete() {
    return "/member/delete";
  }

  @PostMapping("/delete")
  public String deleteProcess(MemberDto memberDto, HttpSession session) {
    log.info("134====" + memberDto.toString());
    int result = memberService.deleteMember(memberDto);

    if (result > 0) {
      session.invalidate();
      return "redirect:/";
    }
    return "/member/delete";
  }
}
