package com.kh.spring.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kh.spring.board.model.service.ReplyService;
import com.kh.spring.board.model.vo.Reply;

/*
 * Rest(Representaion State Transfer);
 * - 자원을 이름으로 구분하여 자원의 상태를 주고받는것
 * 
 * -> 특정한 이름으로 요청이오면 "값" 그 자체로 응답
 * 
 * RestController : 요청에대한 응답이 모두 값 그자체인 ㅋㄴ트롤러
 * -> @Controller + @ResponseBody
 */


@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	// 댓글 등록
	@PostMapping("/insert")
	public int insertReply(Reply reply) {
		// ResponseBody 생략되어있음
		return replyService.insertReply(reply); // "1, 5"
	}
	// 댓글 조회
	@GetMapping("/selectReplyList")
	public String selectReplyList(int bno) {
		
		List<Reply> list = replyService.selectReplyList(bno);
		return new Gson().toJson(list);
	}
	// 댓글 삭제
	
	// 댓글 수정
}