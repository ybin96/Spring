package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.HashMap;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
//import javax.servlet.http.HttpSession;



@Controller
public class GoodsController {
	
	int pageSIZE = 4;
	int totalRecord = 0;
	int totalPage = 1;
	
	@Autowired
	private GoodsDAO dao;
	
	public void setDao(GoodsDAO dao) {
		this.dao = dao;
	}

	@GetMapping("/listGoods")
	public void list(
			Model model, 
			String column,
			String keyword,
			HttpSession session,   
			@RequestParam(value = "pageNUM", defaultValue = "1") int pageNUM ) {
		
		//세션에 저장한 칼럼을 읽어오고 싶어요
		String session_column = null;

		
		if(session.getAttribute("session_column") != null) {
			session_column = (String)session.getAttribute("session_column");
		}
		
		//파라메터로 전달되는(다시 정렬하는) 값을 갖고 오고 싶어요
		if(column != null && !column.equals("")) {
			session_column = column;
		}
			
		
		//언제 세션에 저장된 검색어를 갖고 오느냐?
		//새로운 검색어가 없고, 세션에 저장된 검색어가 있을때에
		if(session.getAttribute("keyword") != null &&( keyword == null || keyword.equals("") )) {
			keyword = (String)session.getAttribute("keyword");
		}
		
		
		System.out.println("column:"+column);
		System.out.println("keyword:"+keyword);
		
		System.out.println("pageNUM:"+pageNUM);
		totalRecord = dao.getTotalRecord(keyword);
		totalPage = (int)Math.ceil( totalRecord / (double)pageSIZE);
								
		int start = (pageNUM-1)*pageSIZE+1;	
	  	int end = start + pageSIZE - 1;
		
	  	HashMap<String, Object> map = new HashMap<String, Object>();
	  	map.put("start", start);
	  	map.put("end", end);
	  	map.put("column", session_column);
	  	map.put("keyword", keyword);
	  	
		model.addAttribute("totalPage", totalPage) ;
		model.addAttribute("list", dao.findAll(map));
		
		session.setAttribute("session_column", session_column);
		session.setAttribute("keyword", keyword);
	}
	
	@GetMapping("/insertGoods")
	public void insertForm() {		
	}
	
	@PostMapping("/insertGoods")
	public ModelAndView insertSubmit(GoodsVO g, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		MultipartFile uploadFile = g.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		
		String path = request.getServletContext().getRealPath("images");
		System.out.println("path:"+path);
		
		try {
			byte[]data = uploadFile.getBytes();
			FileOutputStream fos = new FileOutputStream(path +"/" + fname);
			fos.write(data);
			fos.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		g.setFname(fname);
		int re = dao.insert(g);
		if(re <= 0) {
			mav.addObject("msg", "상품 등록에 실패하였습니다.");
			mav.setViewName("error"); 
		}
		return mav;
	}
	
	
	@GetMapping("/detailGoods")
	public void detail(Model model, int no) {
		model.addAttribute("g", dao.findByNo(no));
	}
	
	@GetMapping("/deleteGoods")
	public ModelAndView delete(int no) {
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		int re = dao.delete(no);
		if(re <= 0) {
			mav.addObject("msg", "상품삭제에 실패하였습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
}























