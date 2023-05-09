package com.kh.spring.common.scheduling;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.BoardImg;
import com.kh.spring.chat.model.vo.ChatRoom;

@Component
public class FileDeleteScheduler {

	private Logger logger = LoggerFactory.getLogger(FileDeleteScheduler.class);
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	private BoardService service;
	
	// 1. BOARD_IMG 테이블안에 있는 이미지목록들 모두 조회하여
	// 2. resources/boardT 디렉토리안에 있는 이미지들과 대조하여
	// 3. 일치하지 않는 이미지 파일들을 삭제(db에는 없는데이터인데, boardT안에는 존재하는 경우)
	// 4. 우선 5초간격으로 테스트후, 정상적으로 작동한다면 매달 1일 정시에 실행되는 스케쥴러로 만들기
	
	@Scheduled(cron = "0 0 0 1 * *")
	public void feleteFile() {
		logger.info("파일 삭제 시작");
		
		// 1) board_img 테이블 안에 있는 모든 파일모록들 조회
		List<BoardImg> bImage = service.selectBoardImg();
		
		// 2) images/board폴더 아래에 존재하는 모든 이미지 파일목록 조회(File클래스 활용)
		
		// File path = new File(application.getRealPath("/resources/images/boardT"));
		// File[] files = path.listFiles(); // path가 참조하고 있는 폴더에 들어가서 모든파일 File배열로 얻어오기 
		// List<File> fileList = Arrays.asList(files);
		
		//logger.info(list.toString());
		//logger.info(fileList.toString());
		
		// 3) 두 목록을 비교해서 일치하지 않는 파일 삭제(삭제시 File클래스의 delete()활용)
		
		// 		방법 1
		// 		if(!list.isEmpty()){
		// 		for( File serverFile : fileList) {
		//		String fileName = serverFile.getName(): // 파일명만 얻어오는 메서드
		
		//		boolean isTrue = false;
		//		for(String str : list) {
		//			if(str == fileName) {
		//					isTrue = true;
		//			}
		//		}
		//		if(!isTrue) {
		//			serverFile.delete();
		//		}
		//  	방법 2 indexOf or contains 이용
		//		List.indexOf(value) : List에 vlaue과 같은 값이 있으면 인덱스를 반환 / 없으면 -1을 반환
		//		if(list.indexOf(fileName) == -1) {
		//			//select해온 db목록에는 없는데, 실제 웹서버에는 저장된 파일인경우
		//			logger.info(fileName + "파일 삭제함");
		//			serverFile.delete();
		//		}
		//	}
		//}
		
		
		File[] path = new File(application.getRealPath("/resources/images/boardT")).listFiles(); // 내 방법
		
		// 3) 두 목록을 비교해서 일치하지 않는 파일 삭제(삭제시 File클래스의 delete()활용)
		for(File file : path){
		    boolean isExist = false;
		    for(BoardImg Img : bImage) {
		    	if(Img.getChangeName().equals(file.getName())) {
		    		isExist = true;
		    	}
		    	if(!isExist) {
		    		file.delete();
		    		logger.info(file.getName() + "파일 삭제됨");
		    	}
		    }
		}
		 
		logger.info("xxx파일 삭제함");
		
		logger.info("서버 파일 삭제작업 끝");
	}
}
