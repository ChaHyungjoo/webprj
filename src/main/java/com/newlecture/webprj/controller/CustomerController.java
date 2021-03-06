package com.newlecture.webprj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecture.webprj.dao.NoticeDao;
import com.newlecture.webprj.dao.jdbc.JdbcNoticeDao;
import com.newlecture.webprj.entity.NoticeView;

@Controller
public class CustomerController {
	
	@Autowired		//컨테이너에 담긴 객체를 자동 참조
	private NoticeDao noticeDao;
	
	@RequestMapping("/customer/notice")
	public String notice(
			@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="") String query,
			Model model){
		
		System.out.println(page);
		System.out.println(query);
		
		
		List<NoticeView> list = noticeDao.getList(page, query);
		
		model.addAttribute("list", list);
		
		
		return "/WEB-INF/views/customer/notice/list.jsp";
	}
	@RequestMapping("/customer/notice/{id}")
	public String noticeDetail(@PathVariable("id") String id, Model model){
		
		NoticeView notice = noticeDao.get(id);
		
		model.addAttribute("notice", notice);
		
		return "/WEB-INF/views/customer/notice/detail.jsp";
	}
	
	
}
