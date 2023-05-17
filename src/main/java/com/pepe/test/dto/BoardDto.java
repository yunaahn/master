package com.pepe.test.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardDto {

  private int no; //고유번호

  @NotEmpty(message = "필수 입력사항입니다.")
  private String userName;

  @NotEmpty(message = "필수 입력사항입니다.")
  private String title;

  @Size(min = 10, max = 3000, message = "10글자 이상 쓰셔야 합니다.")
  private String contents;

  private int boardGroup;
  private int boardLevel;
  private int boardStep;

  private String regDate;
  private int hit;
  private int available; //1. 글 존재 , 0: 글 삭제
  private int num; //순서정하기

  @NotEmpty(message = "필수 입력사항입니다.")
  private String userPw;

  //private String imageUrl;
  private int boardType;

  private MultipartFile imageUrl;

  // 다른 속성들...

  public MultipartFile getImageUrl() {
    return imageUrl;
  }

  public void setImageFile(MultipartFile imageUrl) {
    this.imageUrl = imageUrl;
  }
}
