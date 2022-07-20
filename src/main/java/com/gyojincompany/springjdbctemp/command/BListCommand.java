package com.gyojincompany.springjdbctemp.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.gyojincompany.springjdbctemp.dao.BDao;
import com.gyojincompany.springjdbctemp.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		
		model.addAttribute("list", dtos);
		
	}

}
