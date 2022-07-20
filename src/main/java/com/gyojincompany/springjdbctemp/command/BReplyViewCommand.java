package com.gyojincompany.springjdbctemp.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.gyojincompany.springjdbctemp.dao.BDao;
import com.gyojincompany.springjdbctemp.dto.BDto;

public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bid = request.getParameter("bid");
		//System.out.println("게시판번호확인:" + bid);
		
		BDao dao = new BDao();
		BDto dto = dao.replyView(bid);
		
		model.addAttribute("dto", dto);
	}

}
