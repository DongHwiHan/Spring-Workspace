package com.kh.spring.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.ReplyDAO;
import com.kh.spring.board.model.vo.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO dao;
	
	// 댓글 등록
	public int insertReply(Reply reply) {
		return dao.insertReply(reply);
	}
	
	// 댓글 목록 조회
	public List<Reply> selectReplyList(int bno){
		return dao.selectReplyList(bno);
	}
	
	// 댓글 삭제
	public int deleteReply(int replyNo) {
		return dao.deleteReply(replyNo);
	}
	
	// 댓글 수정
	public int updateReply(Reply reply) {
		return dao.updateReply(reply);
	}
}
