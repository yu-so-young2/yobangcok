package com.ssafy.yobangcok.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yobangcok.model.dto.Board;
import com.ssafy.yobangcok.model.dto.Comment;
import com.ssafy.yobangcok.model.service.BoardService;
import com.ssafy.yobangcok.model.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//Swagger RestAPI 테스팅 url
//http://localhost:8080/swagger-ui.html#/board-controller
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
@Api("게시판 컨트롤러 API V1")
public class BoardController {

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentService commentService;

////////////////////////////////////////////////////////////////////////////////
//								게시판 & 게시글 서비스 
////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/{category}/all")
	@ApiOperation(value = "해당 게시판에 등록된 모든 게시글을 반환한다.")
	public ResponseEntity<?> selectAll(@PathVariable int category) {
		System.out.println(category+" 게시판의 게시글을 불러옵니다.");
		try {
			List<Board> boards = boardService.selectAll(category);
			if (boards != null && boards.size() > 0) {
				return new ResponseEntity<List<Board>>(boards, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/regist")
	@ApiOperation(value = "새 게시글을 등록한다.")
	public ResponseEntity<?> registBoard(@RequestBody Board board) {
		System.out.println("새 게시글을 등록합니다.");
		try {
			int result = boardService.insert(board);
			int result2 = boardService.createVote(board.getNo());

			return new ResponseEntity<Void>(HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/{boardNo}")
	@ApiOperation(value = "게시글을 조회한다.")
	public ResponseEntity<?> selectBoard(@PathVariable int boardNo) {
		try {
			System.out.println(boardNo);
			int result = boardService.updateView(boardNo);
			Board board = boardService.select(boardNo);
			
			if (board != null) {
				System.out.println("게시글 조회 "+ board.getTitle());
				return new ResponseEntity<Board>(board, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/{boardNo}")
	@ApiOperation(value = "게시글을 수정한다.")
	public ResponseEntity<?> modifyBoard(@RequestBody Board board) {
		try {
			int result = boardService.update(board);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/{boardNo}")
	@ApiOperation(value = "게시글을 삭제한다.")
	public ResponseEntity<?> deleteBoard(@PathVariable int boardNo) {
		try {
			int result = boardService.delete(boardNo);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/good/{boardNo}")
	@ApiOperation(value = "게시글을 추천한다.")
	public ResponseEntity<?> voteGood(@PathVariable int boardNo) {
		try {
			int result = boardService.voteGood(boardNo);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/bad/{boardNo}")
	@ApiOperation(value = "게시글을 비추천한다.")
	public ResponseEntity<?> voteBad(@PathVariable int boardNo) {
		try {
			int result = boardService.voteBad(boardNo);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

////////////////////////////////////////////////////////////////////////////////
//									댓글 서비스 
////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/comment/{boardNo}")
	@ApiOperation(value = "해당 게시글의 댓글을 조회한다.")
	public ResponseEntity<?> selectComment(@PathVariable int boardNo) {
		System.out.println("댓글조회 "+boardNo);
		try {
			List<Comment> comments = commentService.select(boardNo);
			if (comments != null || comments.size() > 0) {
				return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/comment/{boardNo}")
	@ApiOperation(value = "해당 게시글에 댓글을 작성한다.")
	public ResponseEntity<?> insertComment(@RequestBody Comment comment, @PathVariable int boardNo) {
		try {
			comment.setBoardNo(boardNo);
			int result = commentService.insert(comment);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/comment/{boardNo}")
	@ApiOperation(value = "해당 게시글의 댓글을 수정한다.")
	public ResponseEntity<?> modifyComment(@RequestBody Comment comment) {
		try {
			int result = commentService.update(comment);
				return new ResponseEntity<Void>(HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/comment/{no}")
	@ApiOperation(value = "해당 댓글을 삭제한다.")
	public ResponseEntity<?> deleteComment(@PathVariable int no) {
		try {
			int result = commentService.delete(no);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
