package com.example.demo.controller;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.NewBook;

@RestController
public class NewBookController {
	
	@GetMapping("/seat")
	public String seat() {
		String url = "http://mpllc-seat.sen.go.kr/seatinfo/Seat_Info/1_count.asp";
		String seat = null;
		try {
			Document doc = Jsoup.connect(url).get();
			seat = doc.getElementsByClass("wating_f").text();
			//Elements seat = doc.getElementsByClass("wating_f");
			System.out.println(seat);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage()); 
		}
		
		return seat;
	}

	@GetMapping("/newBook")
	public ArrayList<NewBook> newBook() {
		String url = "https://www.hanbit.co.kr/store/books/new_book_list.html";
		ArrayList<NewBook> booklist = new ArrayList<NewBook>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements list = doc.getElementsByClass("book_tit");
			for(Element e:list) {
				// <a>태그를 가져온다. Element를 반한하기때문에 .get으로 0번째 값을 가져온다
				Element a = e.getElementsByTag("a").get(0);
				String title = a.text();		// 책 제목	
				String link = a.attr("href");	// 책 주소
//				System.out.println(title);
//				System.out.println(link);
//				System.out.println("----------------------------");
				booklist.add(new NewBook(title,link));
			}
			//System.out.println(doc);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage()); 
		}
		return booklist;
	}
}
