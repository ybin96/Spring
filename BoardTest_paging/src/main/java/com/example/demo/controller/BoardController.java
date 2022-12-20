package com.example.demo.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class BoardController {
	
	public int pageSize = 5;
	public int totalRecord = 0;
	public int totalPage = 1;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("/board/insert/{no}")
	public ModelAndView insertForm( @PathVariable int no) {
		ModelAndView mav = new ModelAndView("/board/insert");
		mav.addObject("no", no);
		return mav;
	}
	
	@PostMapping("/board/insert")
	public ModelAndView insertSubmit(Board b, HttpServletRequest request) {
		
		//일단 새글이라고 봅니다.
		int no = boardService.getNextNo();
		int b_ref = no;
		int b_step = 0;
		int b_level = 0;
		
		//만약에 답글이라면
		int pno = b.getNo();   //부모글번호 ==> 0:새글, 0!= 답글
		if(pno != 0) {
			Board p = boardService.findById(pno);
			b_ref = p.getB_ref();
			b_step = p.getB_step();
			b_level = p.getB_level();
			boardService.updateStep(b_ref, b_step);
			b_step++;
			b_level++;
		}
		
		b.setNo(no);
		b.setB_ref(b_ref);
		b.setB_step(b_step);
		b.setB_level(b_level);
				
		// 파일 업로드
		MultipartFile uploadFile = b.getUploadFile();
		String fname = null;
		fname = uploadFile.getOriginalFilename();
		
		String path = request.getServletContext().getRealPath("images");
		System.out.println("path:"+path);
		if(fname != null && !fname.equals("")) {
			try {
				/*
				 * byte []data = uploadFile.getBytes(); FileOutputStream fos = new
				 * FileOutputStream(path+"/"+fname); fos.write(data); fos.close();
				 */
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}else {
			fname="";
		}
		
		b.setFname(fname);
		
		String ip = request.getRemoteAddr();
		b.setIp(ip);
		ModelAndView mav = new ModelAndView("redirect:/board/list/1");	
		boardService.insert(b);
		return mav;
	}
	
	@GetMapping("/board/list/{pageNUM}")
	public ModelAndView list(Model model, @PathVariable int pageNUM) {
		ModelAndView mav = new ModelAndView("/board/list");
		totalRecord = (int)dao.count();
		totalPage = (int)Math.ceil((double)totalRecord/pageSize);
		model.addAttribute("total",totalPage);	
		
		int start = (pageNUM-1)*pageSize+1;
		int end = start + pageSize -1;
		
		//HashMap<String, Object> map = new HashMap<String,Object>();
		//map.put("start", start);
		//map.put("end", end);
		//model.addAttribute("list", boardService.findAll());
		//model.addAttribute("list",boardService.findAllByOrderByB_refDescOrderByB_stepAsc());
		model.addAttribute("list",boardService.selectAll(start,end));
		return mav;
	}
	
	
	@GetMapping("/board/detail/{no}")
	public ModelAndView detail(@PathVariable int no) {
		ArrayList<String> imgList = new ArrayList<String>();
		imgList.add(".jpg");
		imgList.add(".png");
		imgList.add(".gif");
		
		ModelAndView mav = new ModelAndView("/board/detail");
		mav.addObject("b", boardService.findById(no));
		return mav;
	}
	
	@GetMapping("/board/delete/{no}")
	public ModelAndView deleteForm(@PathVariable int no) {
		ModelAndView mav = new ModelAndView("/board/delete");
		mav.addObject("no",no);
		return mav;
	}
	
	@PostMapping("/board/delete")
	public ModelAndView deleteSubmit(int no, String pwd, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/board/list/1");
		String path = request.getServletContext().getRealPath("/images");
		String oldFname = boardService.findById(no).getFname();
		int re = boardService.delete(no,pwd);
		if(re == 1) {
			try {
				File file = new File(path+"/"+oldFname);
				file.delete();
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		return mav;
	}
	
	@GetMapping("/board/update/{no}")
	public ModelAndView updateForm(@PathVariable int no) {
		ModelAndView mav = new ModelAndView("/board/update");
		mav.addObject("b",boardService.getOne(no));
		return mav;
	}
	
	@PostMapping("/board/update")
	public ModelAndView updateSubmit(Board b, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/board/list/1");
		String path = request.getServletContext().getRealPath("/images");
		String oldFname = b.getFname(); // 원래 파일이름(view에 hidden으로 가져온다
		String fname = null;	// 수정된 파일이름
		MultipartFile uploadFile = b.getUploadFile();
		fname = uploadFile.getOriginalFilename();
		if(fname != null && !fname.equals("")) {
			try {
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
				b.setFname(fname);
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		
		int re = boardService.update(b);
		if(re == 1) {
			if(fname !=null && !fname.equals("") && oldFname != null && !oldFname.equals("")) {
				try {
					File file = new File(path+"/"+oldFname);
					file.delete();
				} catch (Exception e) {
					System.out.println("예외발생:"+e.getMessage());
				}
			}
		}else {
			mav.addObject("msg","게시물 삭제 실패");
			mav.setViewName("/error");
		}
		
		return mav;
	}
}


















