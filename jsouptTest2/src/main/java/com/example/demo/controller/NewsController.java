package com.example.demo.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.News;
import com.example.demo.vo.Result;

@RestController
public class NewsController {
	
	@GetMapping("/shop")
	public Result shop(String search, String sort) {
		System.out.println("검색어:"+search);
		String item = search;
//		String url = "https://search.shopping.naver.com/search/all?frm=NVSHATC&origQuery="+item+"&pagingIndex=1&pagingSize=40&productSet=total&query="+item+"&sort=price_asc&timestamp=&viewType=list";
		String url = "https://search.shopping.naver.com/search/all?frm=NVSHATC&origQuery="+item+"&pagingIndex=1&pagingSize=40&productSet=total&query="+item+"&sort=price_"+sort+"&timestamp=&viewType=list";
		
		int price=0;
		String link = "";
		String title = " ";
		try {
			Document doc = Jsoup.connect(url).get();
//			System.out.println(doc);
		
			Element e = doc.selectFirst("#__next > div > div.style_container__UxP6u > div.style_inner__i4gKy > div.style_content_wrap__Cdqnl > div.style_content__xWg5l > ul > div > div:nth-child(1) > li > div > div.basicList_info_area__TWvzp");
			Element a= e.getElementsByTag("a").get(0);
			Element p = e.getElementsByClass("price_num__S2p_v").get(0);
			
			title = a.attr("title");
			link = a.attr("href");
//			String price_str = p.text();
//			price_str =  price_str.substring(0, price_str.length()-1);
//			int price = Integer.parseInt(price_str);
			price = Integer.parseInt(p.text().replace(",", "").substring(0,p.text().replace(",", "").length()-1));
			
			System.out.println("상품명:"+title);
			System.out.println("url:"+link);			
			System.out.println("가격:"+price);			
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return new Result(title, link, price);
	}
	
	
	
	@GetMapping("/imgDown2")
	public String imgDown2() {
		String webtoonURL = "https://comic.naver.com/webtoon/weekday";
		
		try {
			Document doc = Jsoup.connect(webtoonURL).get();
			//#content > div.list_area.daily_all > div:nth-child(2) > div
			//#content > div.list_area.daily_all > div:nth-child(7)
			//#content > div.list_area.daily_all > div:nth-child(7)
			for(int i=1;i<=7;i++) {
				String mon = "월요일";String tue = "화요일";String wen = "수요일";
				String thr = "목요일";String fri = "금요일";String sat = "토요일";String sun = "일요일";
				Elements list = doc.select("#content > div.list_area.daily_all > div:nth-child("+i+")").get(0).getElementsByTag("img");
				for(Element e:list) {
					String img = e.attr("src");
					String title = e.attr("title");				
					System.out.println(title+" 저장");
//					System.out.println(img);
//					System.out.println("------------------------------");
					String path = "c:/webtoon";
					title = title.replace("?", "");
					title = title.replace(":", "");
					switch(i) {
						case 1: path="c:/webtoon/"+mon ;break;
						case 2: path="c:/webtoon/"+tue ;break;
						case 3: path="c:/webtoon/"+wen ;break;
						case 4: path="c:/webtoon/"+thr ;break;
						case 5: path="c:/webtoon/"+fri ;break;
						case 6: path="c:/webtoon/"+sat ;break;
						case 7: path="c:/webtoon/"+sun ;break;
					}
					FileOutputStream fos = new FileOutputStream(path+"/"+title+".jpg");
					URL url = new URL(img);
					InputStream is = url.openStream();
					FileCopyUtils.copy(is.readAllBytes(), fos);
					fos.close();
				}
			}
			
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		return "ok";
	}
	
//	@GetMapping("/imgDown")
//	public String imgDown() {
//		String imgurl = "https://shared-comic.pstatic.net/thumb/webtoon/758037/thumbnail/thumbnail_IMAG21_15cb2611-34c0-4f02-a689-41d0b1016579.jpg";
//		
//		try {
//			String path="c:/webtoon";
//			String fname = "참교육";
//			FileOutputStream fos = new FileOutputStream(path+"/"+fname+".jpg");
//			URL url = new URL(imgurl);
//			InputStream is = url.openStream();
//			FileCopyUtils.copy(is.readAllBytes(), fos);
//			fos.close();
//		} catch (Exception e) {
//			System.out.println("예외발생:"+e.getMessage());
//		}
//		
//		return "ok";
//	}
	
	@GetMapping("/news")
	public ArrayList<News> news() {
		String url = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=105&sid2=230";
		ArrayList<News> newslist = new ArrayList<News>();
		try {
				Document doc = Jsoup.connect(url).get();
				int totalPage = Integer.parseInt(doc.select("#main_content > div.paging > a:nth-last-child(1)").get(0).text());	
				String base = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid2=230&sid1=105&date=20221223&page=";
				for(int i=1; i<=totalPage;i++) {
					String url2 = base + i;
					Document doc2 = Jsoup.connect(url2).get();
					Elements list = doc2.select("#main_content > div.list_body.newsflash_body").get(0).getElementsByTag("a");
					for(Element e:list) {
							 String title = e.text();
							 String link = e.attr("href");
							 if(title != null && !title.equals("")) {
								 newslist.add(new News(title,link));
							 }
					}
				}
				//System.out.println(totalPage);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return newslist;
	}
	
//	@GetMapping("/news")
//	public ArrayList<News> news() {
//		String url = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=105&sid2=230";
//		ArrayList<News> newslist = new ArrayList<News>();
//		try {
//			Document doc = Jsoup.connect(url).get();
//			Elements list = doc.select("#main_content > div.list_body.newsflash_body").get(0).getElementsByTag("a");
//			for(Element e:list) {
//					 String title = e.text();
//					 String link = e.attr("href");
//					 if(title != null && !title.equals("")) {
//						 newslist.add(new News(title,link));
//					 }
//			}
//		} catch (Exception e) {
//			System.out.println("예외발생:"+e.getMessage());
//		}
//		return newslist;
//	}
}
