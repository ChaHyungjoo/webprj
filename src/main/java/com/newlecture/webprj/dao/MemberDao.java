package com.newlecture.webprj.dao;

import com.newlecture.webprj.entity.Member;

public interface MemberDao {
	
	int insert(String id, String pwd, String name, String phone, String email);

	int insert(Member member);

	Member get(String id);
	
}
