package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.User;
import com.kh.mapper.UserMapper;
/*
 * implements 로 상속받는 인터페이스 서비스는
 * 기능에 대한 목록이 작성이 되어있을 뿐이지
 * 상세한 기능에 대해서 작성이 된 것이 아님
 * 상속을 받은 클래스는 적어놓은 각각의 목록들의
 * 기능을 설정해줘야하기 때문에
 * 설정이 안된 목록이 있으면 빨간 엑스박스가 뜸
 * */
//서비스 목록 리스트 여기는 목록만 작성해주고 imple 오버라이드 해서 각 환경에 맞게 재사용
@Service //서비스 기능을 상세하게 작성해주는 공간
public class UserServiceImpl  implements UserService{
	@Autowired 
	UserMapper userMapper;
	
	@Override
	public List<User> findAll(){
		//관리자로 로그인했을 때 유저보이게하기
		return userMapper.findAll();
	}
	
	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}
	
	@Override
	public void deleteUser(int id) {
		userMapper.deleteUser(id);
	}
	
	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}
}