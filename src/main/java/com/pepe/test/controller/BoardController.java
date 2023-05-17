package com.pepe.test.controller;

import com.pepe.test.dto.BoardDto;
import com.pepe.test.dto.MemberDto;
import com.pepe.test.service.BoardService;
import com.pepe.test.service.MemberService;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired
  BoardService boardService;

  @Autowired
  MemberService memberService;

  @GetMapping("/write")
  public String write(Model model) {
    model.addAttribute("boardDto", new BoardDto());
    return "/board/write";
  }

  @PostMapping("/write")
  public String writeProcess(
    @Valid BoardDto boardDto,
    BindingResult bindingResult,
    Model model
  ) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("boardDto", boardDto);
      return "/board/write";
    }
    int result = boardService.writeBoard(boardDto);
    return "redirect:/board/list";
  }

  // @GetMapping("/imagewrite")
  // public String imagewrite(Model model) {
  //   model.addAttribute("boardDto", new BoardDto());
  //   return "/board/imagewrite";
  // }

  // @GetMapping("/imagewrite")
  // public String imagewrite(Model model, HttpSession session) {
  //   MemberDto loggedMember = (MemberDto) session.getAttribute("loggedMember");
  //   if (loggedMember != null) {
  //     BoardDto boardDto = new BoardDto();
  //     boardDto.setUserName(loggedMember.getUserName());
  //     model.addAttribute("boardDto", boardDto);
  //     return "/board/imagewrite";
  //   } else {
  //     // 로그인하지 않은 경우 로그인 페이지로 리다이렉트 또는 다른 처리를 수행할 수 있습니다.
  //     return "redirect:/member/login";
  //   }
  // }

  // @PostMapping("/imagewrite")
  // public String imagewriteProcess(
  //   @Valid BoardDto boardDto,
  //   BindingResult bindingResult,
  //   Model model
  // ) {
  //   if (bindingResult.hasErrors()) {
  //     model.addAttribute("boardDto", boardDto);
  //     return "/board/imagewrite";
  //   }
  //   MultipartFile imageUrl = boardDto.getImageUrl();
  //   if (imageUrl != null && !imageUrl.isEmpty()) {
  //     // 파일 업로드 로직 작성
  //     // 업로드된 파일을 저장하고, 그에 대한 URL을 imageUrl 속성에 설정
  //   }

  //   int result = boardService.writeimageBoard(boardDto);
  //   return "redirect:/board/imagelist";
  // }

  @GetMapping("/list")
  public String list(Model model) {
    List<BoardDto> boardList = boardService.getBoardList();
    model.addAttribute("boardList", boardList);
    return "/board/list";
  }

  @GetMapping("/view")
  public String view(Model model, int no) {
    BoardDto boardDto = boardService.getBoardOne(no);
    model.addAttribute("boardDto", boardDto);
    return "/board/view";
  }

  @GetMapping("/reply")
  public String reply(Model model) {
    model.addAttribute("boardDto", new BoardDto());
    return "/board/reply";
  }

  @PostMapping("/reply")
  public String replyProcess(
    @Valid BoardDto boardDto,
    BindingResult bindingResult,
    Model model
  ) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("boardDto", boardDto);
      return "/board/write";
    }
    int result = boardService.replyBoard(boardDto);
    return "redirect:/board/list";
  }
  // @GetMapping("/imagelist")
  // public String imagelist(Model model) {
  //   List<BoardDto> imageboardList = boardService.getImageBoardList();
  //   model.addAttribute("imageboardList", imageboardList);
  //   return "/board/imagelist";
  // }
}
