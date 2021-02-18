package com.zx9cv.staris.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx9cv.staris.dao.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	@RequestMapping("/androidtest.do")// 리스트를 보내기
	public @ResponseBody String andr(String id,String pw) {
		System.out.println("오오ㅗ오오오오ㅗ오오");
		System.out.println(id+","+pw);
        // json-simple 라이브러리 추가 필요(JSON 객체 생성)
        JSONObject jsonMain = new JSONObject(); // json 객체
        // {변수명:값, 변수명:값}
        // {sendData:[{변수명:값},{변수명:값},...]}
        List<UserVO> items = new ArrayList<UserVO>();
        JSONArray jArray = new JSONArray(); // json배열
        	for(int i=0;i<10;i++) {
    		UserVO vo=new UserVO();
			vo.setSid(""+i);
			vo.setSname("sejong");
			items.add(vo);
		}    	
        for(int i=0; i<items.size(); i++){
        	UserVO dto = items.get(i);
            JSONObject row = new JSONObject();
            // json객체.put("변수명",값)
            row.put("f", dto.getSid());
            row.put("l", dto.getSname());
            // 배열에 추가
            // json배열.add(인덱스,json객체)
            jArray.add(i,row);
        }
        // json객체에 배열을 넣음
        jsonMain.put("sendData", jArray);
        return jsonMain.toJSONString();
	}
	@RequestMapping("/andtest.do")// 단일 값들 보내기
	public @ResponseBody String andtest(String id,String pw, HttpServletResponse response,HttpServletRequest request ) throws IOException {
		System.out.println("andtest");
		HttpSession session=request.getSession();
		String bid= (String)session.getAttribute("bid");
		String name= (String)session.getAttribute("bname");
		
		System.out.println(bid);
		System.out.println(name);
			 JSONObject jsonMain = new JSONObject(); // json 
		//	 String pwd="Abdeok odielswo dj244 d";
			 jsonMain.put("Bpw", name);
			 jsonMain.put("Bid",bid);
			 jsonMain.put("test","test");
			// System.out.println(pwd);		
				return jsonMain.toJSONString();
		//	return jsonMain;
	}
	
	
}
