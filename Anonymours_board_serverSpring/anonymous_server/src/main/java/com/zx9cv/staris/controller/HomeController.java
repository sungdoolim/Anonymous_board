package com.zx9cv.staris.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zx9cv.staris.dao.memoDAO;
import com.zx9cv.staris.dao.userDAO;
import com.zx9cv.staris.vo.memoVO;
import com.zx9cv.staris.vo.userVO;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {
	
	@Autowired
	memoDAO memodao;
	@Autowired
	userDAO userdao;
	
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
	
	@RequestMapping("/delmemo.do")
	public @ResponseBody String delmemo(memoVO m) {
		//�̰� �ȴ�!!!!
		System.out.println("delete...."+m.getId()+m.getWriter());
		//m.setId(idnum);
		memodao.delOne(m);
		
		
		
		System.out.println("del complete");
		return null;
	}
	
	@RequestMapping("/saveContent.do")
	public @ResponseBody String saveContent(String title,String writer,String content) {
		
		System.out.println(title+writer+content);
		memoVO m = new memoVO();
		m.setContent(content);
		m.setTitle(title);
		m.setWriter(writer);
		
		m.setId(userdao.getIndex(writer)+1);
		
		m.toString();
		memodao.insertMemo(m);
		
		
		return null;
		
	}
	@RequestMapping(value="/updatememo.do",produces ="application/json; charset=utf8")
	public @ResponseBody String modifymemo(memoVO m) {
		System.out.println("modify");
		System.out.println(m.toString());
		memodao.updateMemo(m);
		
		
		return null;
	}
	
	@RequestMapping(value="/anonymousRegister.do",produces ="application/json; charset=utf8")
	public @ResponseBody String Register(String userId,String userPw, String userName,userVO user) {
		System.out.println(userId+userPw+userName); 
		JSONObject jsonMain = new JSONObject(); // json 
		if (userdao.check(userId)) {
			jsonMain.put("userstate","false");
			
		}else {
			jsonMain.put("userstate","true");
			user.setId(userId);
			user.setName(userName);
			user.setPw(userPw);
			
			
			userdao.register(user);
			
		}

		return jsonMain.toJSONString();
	}
	@RequestMapping(value="/anonymousLogin.do",produces ="application/json; charset=utf8")
	public @ResponseBody String Login(String userId, String userPw) {
		System.out.println(userId+userPw);
		 JSONObject jsonMain = new JSONObject(); // json 


				
		if (userdao.check(userId,userPw)) {
			 jsonMain.put("userstate","true");
			
			
		}
		else {
			 jsonMain.put("userstate","false");
				
		}
		
		return jsonMain.toJSONString();
	}
	@RequestMapping(value="/callMemoList.do" ,produces = "application/json; charset=utf8")
	public @ResponseBody String callList() {
		
		
		List<memoVO>alist= memodao.allList();
		int al=alist.size();
		for(int i=0;i<al;i++) {
			System.out.println(alist.get(i).getTitle());
		}
		JSONObject jsonMain = new JSONObject(); 
		//List<memoVO> items = new ArrayList<memoVO>();
		JSONArray jArray = new JSONArray();
		JSONObject row=null;
        for(int i=0; i<al; i++){
        	
             row= new JSONObject();
             row.put("title", alist.get(i).getTitle());
             row.put("content", alist.get(i).getContent());
             row.put("writer", alist.get(i).getWriter());
             row.put("id",alist.get(i).getId());
             row.put("datetime",alist.get(i).getDatetime());
             
             
            // json��ü.put("������",��)
//            row.put("f", dto.getSid());
//            row.put("l", dto.getSname());
            // �迭�� �߰�
            // json�迭.add(�ε���,json��ü)
            jArray.add(row);
        }
        // json��ü�� �迭�� ����
        jsonMain.put("sendData", jArray);
        return jsonMain.toJSONString();
    	
		//return null;
		
	}
	
	@RequestMapping("/androidtest.do")// ����Ʈ�� ������
	public @ResponseBody String andr(String id,String pw) {
		System.out.println("�����ǿ��������ǿ���");
		System.out.println(id+","+pw);
        // json-simple ���̺귯�� �߰� �ʿ�(JSON ��ü ����)
        JSONObject jsonMain = new JSONObject(); // json ��ü
        // {������:��, ������:��}
        // {sendData:[{������:��},{������:��},...]}
        List<memoVO> items = new ArrayList<memoVO>();
        JSONArray jArray = new JSONArray(); // json�迭
        	for(int i=0;i<10;i++) {
    		memoVO vo=new memoVO();
//			vo.setSid(""+i);
//			vo.setSname("sejong");
			items.add(vo);
		}    	
        for(int i=0; i<items.size(); i++){
        	memoVO dto = items.get(i);
            JSONObject row = new JSONObject();
            // json��ü.put("������",��)
//            row.put("f", dto.getSid());
//            row.put("l", dto.getSname());
            // �迭�� �߰�
            // json�迭.add(�ε���,json��ü)
            jArray.add(i,row);
        }
        // json��ü�� �迭�� ����
        jsonMain.put("sendData", jArray);
        return jsonMain.toJSONString();
	}
	@RequestMapping(value= "/andtest.do",produces = "application/json; charset=utf8")// ���� ���� ������
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
	@RequestMapping("/testupload.do")
	public String testupload() {
		return "uploadfile";
	}
	
	@RequestMapping("/uploadFile")
	public String uploadFile(RedirectAttributes rttr,HttpServletRequest request, @RequestParam("imgFile") MultipartFile imgFile , Model model) {
		String savePath="C:\\Users\\bohee\\source\\staris";
		System.out.println("uploadFile");
		String originalFilename = imgFile.getOriginalFilename(); // fileName.jpg
	    String onlyFileName = originalFilename.substring(0, originalFilename.indexOf(".")); // fileName
	    String extension = originalFilename.substring(originalFilename.indexOf(".")); // .jpg
	 
	    String rename = onlyFileName + extension; // fileName_20150721-14-07-50.jpg
	    String fullPath = savePath + "\\" + rename;
	    
	    
		File Folder = new File(savePath);	

		// �ش� ���丮�� ������� ���丮�� �����մϴ�.
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //���� �����մϴ�.
			    System.out.println("������ �����Ǿ����ϴ�.");
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
	         }else {
			System.out.println("�̹� ������ �����Ǿ� �ֽ��ϴ�.");
		}
		
	    if (!imgFile.isEmpty()) {
	        try {
	            byte[] bytes = imgFile.getBytes();
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullPath)));
	            stream.write(bytes);
	            stream.close();
	           // bldao.fileio(blvo);
	            
	            
	            model.addAttribute("resultMsg", "������ ���ε� ����!");
	        } catch (Exception e) {
	            model.addAttribute("resultMsg", "������ ���ε��ϴ� ���� �����߽��ϴ�.");
	        }
	    } else {
	        model.addAttribute("resultMsg", "���ε��� ������ �������ֽñ� �ٶ��ϴ�.");
	    }
		
		return "redirect:/updown";
	}
	
	@RequestMapping("/downloadFile")
	public String downloadFile(RedirectAttributes rttr,HttpServletRequest request,HttpServletResponse response,String fname) throws UnsupportedEncodingException {
String fileName = request.getParameter("filename");
		// ���� �̸��� �����ؾ���

		// �� ��� ��������
		String saveDir = "C:\\Users\\bohee\\source\\staris";
		
		File file = new File(saveDir + "\\"+fname);

		response.setContentType("application/octet-stream");
		String filename = new String(fname.getBytes("UTF-8"), "8859_1");
			
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			try {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

				
				try {
					BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
					int data;
					while((data=bis.read()) != -1){
						bos.write(data);
						bos.flush();
					}

					// 8] ��Ʈ�� �ݱ�
					bis.close();
					bos.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 6] ���������� ������ ��� ��Ʈ�� ���� 
		//	rttr.addFlashAttribute("msg","SUCCESS"); 
	
		//return "redirect:/updown";
		return "/bank/blistall";
		
		
	}
	@RequestMapping("/updown")
	public String updown(Model m) {
		//List<String>fname=bldao.selectallfile();
		List<String>fname=new ArrayList<String>();
		fname.add("resources\\img\\1.png");
		fname.add("resources\\img\\2.jpg");
		fname.add("resources\\img\\3.jpg");
		fname.add("resources\\img\\4.jpg");
		m.addAttribute("bl", fname);

		return "filelist";
	}
	
	
}
