package org.sp.board.model.board;

import java.util.List;

import org.sp.board.domain.Board;
import org.sp.board.domain.BoardFile;
import org.sp.board.exception.BoardException;
import org.sp.board.exception.BoardFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
//서비스 인터페이스를 구현한 하위 서비스 객체
public class BoardServiceImpl implements BoardService{
	
	//DAO는 서비스에서 보유해야 한다. model 영역의 업무이므로
	//따라서 두 개 이상의 테이블에 대한  DML 상황에 있어 트랜잭션 처리 또한 서비스 객체의 몫이다.
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private BoardFileDAO boardFileDAO;

	//dao로 부터 전달받은 예외 객체는 서비스가 방치하지 말고 controller까지 전달을 해줘야
	//웹브라우저인 클라이언트에게 적절한 에러 화면을 제공할 수 있다.
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Board board) throws BoardException, BoardFileException{
		//두개의 dao를 이용하여 글 등록 업무 처리
		boardDAO.insert(board);//mybatis에 의해 board_idx
		
		List<BoardFile>  fileList = board.getBoardFileList();
		for(int i=0; i<fileList.size(); i++) {
			BoardFile boardFile=fileList.get(i);
			boardFileDAO.insert(boardFile);//이미지 테이블에 insert
		}
	}

	public List selectAll() {
		return boardDAO.selectAll();
		
	}

	public Board select(int board_idx) {
		boardDAO.updateHit(board_idx);
		return boardDAO.select(board_idx);
	}

	public void update(Board board) {
		//수정하기
		boardDAO.update(board);
		
	
		
	}

	public void delete(int board_idx) throws BoardException, BoardFileException{
		//이미지 레코드 삭제
		boardFileDAO.deleteByBoardIdx(board_idx);
		
		//보드 삭제
		boardDAO.delete(board_idx);
		
	}
	

	public void updateHit(int board_idx) {
		boardDAO.updateHit(board_idx);
		
	}

}
