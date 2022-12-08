package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@RestController
@Setter
public class GoodsController {

	@Autowired
	private GoodsDAO dao;
	
	@RequestMapping("/listGoods")
	public List<GoodsVO> list(){
		return dao.listGoods();
	}
	
	@RequestMapping("/detailGoods")
	public GoodsVO detail(int no) {
		return dao.findByNo(no);
	}
	
	@RequestMapping("/insertGoods")
	public int insert(GoodsVO g, HttpServletRequest request) {
		MultipartFile uploadFile = g.getUploadFile();
		String fname = "";
		fname = uploadFile.getOriginalFilename();
		String path = request.getServletContext().getRealPath("images");
		System.out.println("path:"+path);
		if(fname != null && !fname.equals("")) {
			try {
				byte []data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
				fos.write(data);
				fos.close();
			} catch (Exception e) {
				System.out.println("예외처리:"+e.getMessage());
			}
		}else {
			fname = "";
		}
		g.setFname(fname);
		
		return dao.insertGoods(g);
	}
}
