package com.example.demo.controller;

import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/insertBoard")
public class InsertBoardController {
	
	@Autowired
	private BoardDAO dao;	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}


	@RequestMapping(method = RequestMethod.GET)
	public void form(@RequestParam(value="no", defaultValue = "0") int no, Model model) {		
		model.addAttribute("no", no);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(BoardVO b, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		String path = request.getServletContext().getRealPath("images");
		System.out.println("path:"+path);
		int no = dao.getNextNo();
		int b_ref= no;
		int b_step = 0;
		int b_level = 0;
		
		//답글인지 판별하는 선택문
		int p_no = b.getNo();
		BoardVO p = dao.findByNo(p_no);	// 부모의 no를 가져온다
		if(p_no != 0) {
			// b_ref는 부모글의 b_ref와 동일하게 한다
			b_ref = p.getB_ref();
			b_level = p.getB_level();
			b_step = p.getB_step();
			// 이미 달려있는 답글들의 b_step을 1씩 증가시킨다.
			dao.update(b_ref, b_step);
			// b_step과 b_level은 부모글보다 1씩 증가시킨다.
			b_level++;
			b_step++;
		}
		
		b.setNo(no);
		b.setB_ref(b_ref);
		b.setB_step(b_step);
		b.setB_level(b_level);
		
		String fname = "";
		MultipartFile uploadFile = b.getUploadFile();
		fname = uploadFile.getOriginalFilename();
		if(fname != null && !fname.equals("")) {
			try {
				byte []data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				fos.write(data);
				fos.close();
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}else {
			fname = "";
		}
		b.setFname(fname);
		int re = dao.insert(b);
		if(re <= 0) {
			mav.addObject("msg", "게시물 등록에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
}










