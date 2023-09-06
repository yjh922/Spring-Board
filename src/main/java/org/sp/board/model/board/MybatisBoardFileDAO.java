package org.sp.board.model.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.sp.board.domain.BoardFile;
import org.sp.board.exception.BoardFileException;
import org.sp.board.mybatis.MybatisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisBoardFileDAO implements BoardFileDAO{
	@Autowired
	private MybatisConfig mybatisConfig;
	
	public void insert(BoardFile boardFile) throws BoardFileException{
		SqlSession sqlSession=mybatisConfig.getSqlSession();
		int result=sqlSession.insert("BoardFile.insert", boardFile);
		sqlSession.commit();
		mybatisConfig.release(sqlSession);
		
		//result=0;//일부러 에러 테스트
		
		if(result==0) {
			throw new BoardFileException("글 등록에 실패하였습니다.");
		}
	}

	public List selectAll() {
		return null;
	}

	public BoardFile select(int board_file_idx) {
		return null;
	}

	public void update(BoardFile boardFile) {
		
	}

	public void delete(int board_file_idx) {
		
	}
	
}