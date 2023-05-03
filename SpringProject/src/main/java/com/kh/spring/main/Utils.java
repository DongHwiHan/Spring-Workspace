package com.kh.spring.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Utils { // 보통 utils 공용으로 쓰는 클래스 내부에는 static으로 선언하는 게 규칙

   // 변경된 이름을 돌려주면서, 원본파일을 변경된 파일이름으로 서버에 저장시키는 메소드
   static public String saveFile(MultipartFile upfile, String savePath) throws IllegalStateException, IOException {
      
      String originName = upfile.getOriginalFilename(); // "user.jpg"
      String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
      
      int random = (int)(Math.random() * 90000 + 10000); //고유한 파일명을 위한 랜덤값 설정
      
      String ext = originName.substring(originName.lastIndexOf("."));
      
      String changeName = currentTime + random + ext;
      
      upfile.transferTo(new File(savePath + changeName)); // upfile객체를 이 경로로 이동시켜주는 것
      
      //그럼에도 똑같은 파일명이 생길 수 있기에 예외처리 해줄 것.
      
      return changeName;
      
   }
   
}