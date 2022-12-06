package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/updateBoard")
public class updateBoardController {
	
	@Autowired
	private BoardDAO dao;

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void form(Model model, int no) {
		model.addAttribute("b", dao.findByNo(no));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(BoardVO b, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		String path = request.getServletContext().getRealPath("images");
		String oldFname = b.getFname();	// hidden으로 가져온 fname을 oldFname에 담는다.
		String fname = "";
		MultipartFile uploadFile = b.getUploadFile();
		fname = uploadFile.getOriginalFilename();
		if(fname != null && !fname.equals("")) {
			b.setFname(fname);
		}
		int re = dao.update(b);
		if(re <= 0) {
			mav.addObject("msg", "수정실패");
			mav.setViewName("error");
		}else {
			if(fname != null && !fname.equals("")) {
				try {
					byte []data = uploadFile.getBytes();
					FileOutputStream fos = new FileOutputStream(path + "/" + fname);
					fos.write(data);
					fos.close();
				} catch (Exception e) {
					System.out.println("예외발생:"+e.getMessage());
				}
				if(oldFname != null && !oldFname.equals("")) {
					File file = new File(path+"/"+oldFname);
					file.delete();
				}
			}
		}
		return mav;
	}
}
