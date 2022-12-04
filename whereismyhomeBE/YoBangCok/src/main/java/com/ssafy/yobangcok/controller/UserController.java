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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yobangcok.model.dto.Favorite;
import com.ssafy.yobangcok.model.dto.FavoriteGroup;
import com.ssafy.yobangcok.model.dto.Subscription;
import com.ssafy.yobangcok.model.dto.User;
import com.ssafy.yobangcok.model.service.FavorService;
import com.ssafy.yobangcok.model.service.SubsService;
import com.ssafy.yobangcok.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// Swagger RestAPI 테스팅 url
// http://localhost:8080/swagger-ui.html#/user-controller
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/users")
@Api("회원 컨트롤러 API V1")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService;
	@Autowired
	private SubsService subsService;
	@Autowired
	private FavorService favorService;

////////////////////////////////////////////////////////////////////////////////
//								 회원 관리 서비스 
////////////////////////////////////////////////////////////////////////////////

	@GetMapping("")
	@ApiOperation(value = "등록된 모든 사용자 정보를 반환한다.", response = User.class)

	public ResponseEntity<?> selectAll() {
		try {
			List<User> users = userService.selectAll();
			if (users != null && users.size() > 0) {
				return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			} else {
				// 사용자가 없음
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/regist/{id}")
	@ApiOperation(value = "해당 id 중복 여부를 반환한다.", response = HttpStatus.class)
	public ResponseEntity<?> searchIdExist(@PathVariable String id) {
		System.out.println(id);
		try {
			User result = userService.searchById(id);
			if (result == null) { // 정상 가입 가능
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else { // 이미 가입된 이름 -> 충돌 에러 보내기
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/regist")
	@ApiOperation(value = "새로운 회원 정보를 삽입한다.", response = HttpStatus.class)
	public ResponseEntity<?> createNewUser(@RequestBody User user) {
		try {
			userService.insert(user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/login")
	@ApiOperation(value = "해당 로그인 정보의 회원이 있는지 확인한다.", response = User.class)
	public ResponseEntity<?> login(@RequestBody User user) {
		System.out.println("login "+ user.getId());
		try {
			User loginUser = userService.login(user);
			if (loginUser != null) { //해당 정보(아이디, 비밀번호)의 회원이 있다면
				return new ResponseEntity<User>(loginUser, HttpStatus.OK);
			} else { //로그인 실패
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/user/{id}")
	@ApiOperation(value = "해당 id의 회원 정보를 삭제한다.", response = HttpStatus.class)
	public ResponseEntity<?> delete(@PathVariable String id) {
		System.out.println(id);
		try {
			userService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/user")
	@ApiOperation(value = "회원 정보를 수정한다.", response = HttpStatus.class)
	public ResponseEntity<?> update(@RequestBody User user) {
		try {
			userService.update(user);
			return new ResponseEntity<Integer>(HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/mypage")
	@ApiOperation(value = "{id} 해당하는 회원 정보를 반환한다.", response = User.class)
	public ResponseEntity<?> select(@PathVariable String id) {
		try {
			User user = userService.searchById(id);
			if (user != null) {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else { //해당 아이디 회원 정보가 없다면
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////
	// 								회원 구독 서비스
	////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/subscribe")
	@ApiOperation(value = "회원 구독 여부를 반환한다", response = HttpStatus.class)
	public ResponseEntity<?> search(@RequestParam String id, @RequestParam String subscriptionId) {
		try {
			Subscription subscription = new Subscription();
			subscription.setId(id);
			subscription.setSubscriptionId(subscriptionId);
			
			int result = subsService.search(subscription); // 해당 구독정보가 있는지 확인
			if(result > 0) { //구독 정보 있음
				System.out.println(result);
				return new ResponseEntity<Void>(HttpStatus.OK);				
			} else { //구독 정보 없음
				System.out.println("dd");
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/subscribe")
	@ApiOperation(value = "해당 회원을 구독한다", response = HttpStatus.class)
	public ResponseEntity<?> subscribe(@RequestBody Subscription subscription) {
		try {
			int result = subsService.insert(subscription); // 해당 구독정보 삽입
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/subscribe")
	@ApiOperation(value = "해당 회원을 구독 취소한다.", response = HttpStatus.class)
	public ResponseEntity<?> cancelSubscribe(@RequestBody Subscription subscription) {
		try {
			int result = subsService.delete(subscription); // 해당 구독정보 삭제
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	////////////////////////////////////////////////////////////////////////////////
	// 								매물 즐겨찾기 서비스
	////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/{id}/favorite/group")
	@ApiOperation(value = "해당 회원이 가지고 있는 즐겨찾기 그룹을 보낸다.", response = FavoriteGroup.class)
	public ResponseEntity<?> getGroup(@PathVariable String id) {
		try {
			List<FavoriteGroup> result = favorService.selectGroup(id);
			return new ResponseEntity<List<FavoriteGroup>>(result, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	
	@PostMapping("/favorite")
	@ApiOperation(value = "해당 매물을 북마크에 추가한다.", response = HttpStatus.class)
	public ResponseEntity<?> addFavorite(@RequestBody Favorite favorite) {
		try {
			int result = favorService.insert(favorite); // 해당 매물 즐겨찾기 추가
			favorService.favoriteGroupUpdate(favorite.getFavoritegroup());
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/favorite")
	@ApiOperation(value = "즐겨찾기 글을 보낸다.", response = FavoriteGroup.class)
	public ResponseEntity<?> getFavoriteGroup(@RequestParam("favoritegroup") String favoritegroup) {
		try {
			System.out.println(favoritegroup);
			List<Favorite> result = favorService.selectFavorite(favoritegroup);
			return new ResponseEntity<List<Favorite>>(result, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/{id}/favorite")
	@ApiOperation(value = "해당 매물을 북마크에서 삭제한다.", response = HttpStatus.class)
	public ResponseEntity<?> deleteFavorite(@RequestBody Favorite favorite) {
		try {
			int result = favorService.delete(favorite.getNo()); // 해당 매물 즐겨찾기 삭제
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/favorite/group")
	@ApiOperation(value = "해당 그룹을 북마크에 추가한다.", response = HttpStatus.class)
	public ResponseEntity<?> addGroup(@RequestBody FavoriteGroup favoriteGroup) {
		try {
			int result = favorService.insertGroup(favoriteGroup); // 해당 즐겨찾기 그룹 추가
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/{id}/favorite/group")
	@ApiOperation(value = "해당 그룹의 이름을 수정한다.", response = HttpStatus.class)
	public ResponseEntity<?> modifyGroup(@RequestBody FavoriteGroup favoriteGroup) {
		try {
			int result = favorService.modifyGroup(favoriteGroup); // 해당 즐겨찾기 그룹 이름 변경
			System.out.println(favoriteGroup.getGroupName()+" "+favoriteGroup.getGroupNo());
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/{id}/favorite/group")
	@ApiOperation(value = "해당 그룹을 북마크에서 삭제한다.", response = User.class)
	public ResponseEntity<?> deleteGroup(@RequestBody FavoriteGroup favoriteGroup) {
		try {
			int result = favorService.deleteGroup(favoriteGroup.getGroupNo()); // 해당 구독정보가 있는지 확인
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

//	
//	@GetMapping("/{id}/mypage/qna")
//	@ApiOperation(value = "해당 회원이 올린 문의글을 조회한다.", response = User.class)
//	public ResponseEntity<?> (@RequestBody Favorite favorite) {
//		try {
//			int result = favorService.delete(favorite.getFavoriteNo()); //해당 구독정보가 있는지 확인
//			return new ResponseEntity<Integer>(result, HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//	
//	@GetMapping("/{id}/mypage/sales")
//	@ApiOperation(value = "해당 회원이 등록한 매물을 조회한다.", response = User.class)
//	public ResponseEntity<?> deleteFavoriteGroup(@RequestBody Favorite favorite) {
//		try {
//			int result = favorService.delete(favorite.getFavoriteNo()); //해당 구독정보가 있는지 확인
//			return new ResponseEntity<Integer>(result, HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
