
package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class BoardController {
	
	public int pageSIZE = 5;
	public int totalRecord = 0;
	public int totalPage = 1;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@GetMapping("/board/insert/{no}")
	public ModelAndView insertForm( @PathVariable int no) {
		ModelAndView mav = new ModelAndView("/board/insert");
		mav.addObject("no", no);
		return mav;
	}
	
	@PostMapping("/board/insert")
	public ModelAndView insertSubmit(Board b, HttpServletRequest request) {
		
		//파일에 대한 처리
		String path = request.getServletContext().getRealPath("/images");
		System.out.println("path:"+path);
		String fname = null;
		MultipartFile uploadFile = b.getUploadFile();
		fname = uploadFile.getOriginalFilename();
		if(fname != null && !fname.equals("")) {
			try {
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}else {
			fname = "";
		}
		
		b.setFname(fname);
		
		
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
				
		String ip = request.getRemoteAddr();
		b.setIp(ip);
		ModelAndView mav = new ModelAndView("redirect:/board/list/1/all");	
		boardService.insert(b);
		return mav;
	}
	
	@GetMapping("/board/list/{pageNUM}/{id}")
	public ModelAndView list(HttpSession session, Model model, @PathVariable int pageNUM, @PathVariable String id) {
		//로그인한 회원의 상태유지
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		String login_id = user.getUsername();		
		session.setAttribute("user", memberDAO.findById(login_id).get());
		
		System.out.println("pageNUM:"+pageNUM);
		System.out.println("id:"+id);
		ModelAndView mav  = new ModelAndView("/board/list");
		
		if(id.equals("all")) {
			totalRecord = (int)boardDAO.count();
		}else {
			totalRecord = boardDAO.countById(id);
		}
				
		totalPage =(int) Math.ceil((double)totalRecord/pageSIZE);
		
		int start = (pageNUM-1)*pageSIZE+1;
		int end = start+pageSIZE-1;
		
		System.out.println("start:"+start);
		System.out.println("end:"+end);
	
		if(id.equals("all")) {
			model.addAttribute("list", boardService.selectAll(start, end));
		}else {
			model.addAttribute("list", boardService.selectAllById(start, end, id));
		}	
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("id", id);
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
		mav.addObject("imgList", imgList);
		return mav;
	}
	
	@GetMapping("/board/delete/{no}")
	public ModelAndView deleteForm(@PathVariable int no) {
		ModelAndView mav = new ModelAndView("/board/delete");
		mav.addObject("no", no);
		return mav;
	}
	
	
	@PostMapping("/board/delete")
	public ModelAndView deleteSubmit(int no,String pwd , HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/images");
		String oldFname = boardService.findById(no).getFname();
		ModelAndView mav = new ModelAndView("redirect:/board/list/1/all");
		int re = boardService.deleteBoard(no,pwd);
		if(re > 0) {
			try {
				File file = new File(path +"/" + oldFname);
				file.delete();
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		return mav;
	}
	
	
	@GetMapping("/board/update/{no}")
	public ModelAndView updateForm(@PathVariable int no) {
		ModelAndView mav = new ModelAndView("/board/update");
		mav.addObject("b", boardService.findById(no));
		return mav;
	}
	
	
	@PostMapping("/board/update")
	public ModelAndView updateSubmit(Board b, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/board/list/1/all");
		String oldFname= b.getFname();
		String path = request.getServletContext().getRealPath("/images");
		String fname = null;
		MultipartFile uploadFile = b.getUploadFile();
		fname = uploadFile.getOriginalFilename();
		if(fname != null && !fname.equals("")) {
			try {
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
				b.setFname(fname);
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		
		int re = boardService.updateBoard(b);
		if(re > 0) {
			if(fname != null && !fname.equals("") && oldFname != null && !oldFname.equals("")) {
				try {
					File file = new File(path + "/" + oldFname);
					file.delete();
				}catch (Exception e) {
					System.out.println("예외발생:"+e.getMessage());
				}
			}
		}else {
			mav.addObject("msg", "게시물 수정에 실패하였습니다.");
			mav.setViewName("/error");
		}
		
		return mav;
	}
	
}


















