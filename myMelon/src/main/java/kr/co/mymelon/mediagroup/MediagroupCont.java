package kr.co.mymelon.mediagroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.Utility;

@Controller
public class MediagroupCont {

	//자동으로 생성된 객체를 서로 연결시 선언.
	//스프링 컨테이너(서버)에 이미 객체가 생성되어 있으므로
	//별도로 new MediagroupDAO() 하지 않아도 된다
	@Autowired
	private MediagroupDAO dao;
	
	public MediagroupCont() {
		System.out.println("---MediagroupCont() 객체 생성됨...");
	}//MediagroupCont() end
	
	
	//미디어그룹 쓰기 페이지 호출
	//http://localhost:9090/mymelon/mediagroup/create.do
	@RequestMapping(value = "/mediagroup/create.do", method = RequestMethod.GET)
	public String createForm() {
		return "mediagroup/createForm";
	}//createForm() end
	
	
	@RequestMapping(value = "/mediagroup/create.do", method = RequestMethod.POST)
	public ModelAndView createProc(@ModelAttribute MediagroupDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/msgView");
		
		int cnt=dao.create(dto);
		if(cnt==0) {
			String msg  ="<p>미디어 그룹 등록 실패</p>";
			String img  ="<img src='../images/fail.png'>";
			String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
			mav.addObject("msg1",  msg);
			mav.addObject("img",   img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
			
		}else {
			String msg  ="<p>미디어 그룹 등록 성공</p>";
			String img  ="<img src='../images/sound.png'>";
			String link1="<input type='button' value='계속등록' onclick='location.href=\"create.do\"'>";
			String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
			mav.addObject("msg1",  msg);
			mav.addObject("img",   img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		}//if end
		return mav;
	}//createProc() end
	
	
	@RequestMapping("mediagroup/list.do")
	public ModelAndView list() {
	    ModelAndView mav=new ModelAndView();
	    mav.setViewName("mediagroup/list");
	    //request공간에 자료 올리기
	    mav.addObject("root", Utility.getRoot());
	    mav.addObject("list", dao.list());
	    return mav;
	}//list() end
	

	@RequestMapping(value="/mediagroup/update.do", method=RequestMethod.GET)
	public ModelAndView updateForm(MediagroupDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/updateForm");
	    mav.addObject("root", Utility.getRoot());
	    mav.addObject("dto", dao.read(dto));
	    return mav;
	}//updateForm() end	
	
	
	
	@RequestMapping(value="/mediagroup/update.do", method=RequestMethod.POST)
	public ModelAndView updateProc(MediagroupDTO dto) {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("mediagroup/msgView");
	    int cnt = dao.update(dto);
	    if(cnt == 0) {
	      mav.addObject("msg1",  "<p>미디어 그룹 수정 실패</p>");
	      mav.addObject("img",   "<p><img src='../images/fail.png'></p>");
	      mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
	      mav.addObject("link2", "<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>");
	    } else {
	      mav.addObject("msg1",  "<p>미디어 그룹 수정 성공</p>");
	      mav.addObject("img",   "<p><img src='../images/sound.png'></p>");
	      mav.addObject("link1", "<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>");    
	    }//if end
	    return mav;
	}//updateProc() end
	
	
	@RequestMapping(value="/mediagroup/delete.do", method=RequestMethod.GET)
	public ModelAndView deleteForm(@ModelAttribute MediagroupDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/deleteForm");
	    mav.addObject("root", Utility.getRoot());
	    mav.addObject("dto", dto);
	    return mav;
	}//deleteForm() end	
	
	
	@RequestMapping(value="/mediagroup/delete.do", method=RequestMethod.POST)
	public ModelAndView deleteProc(MediagroupDTO dto) {
	    ModelAndView mav=new ModelAndView();
	    mav.setViewName("mediagroup/msgView");
	    int cnt=dao.delete(dto);
	    if(cnt==0) {
	      mav.addObject("msg1", "<p>미디어 그룹 삭제 실패!!</p>");
	      mav.addObject("img",  "<img src='../images/fail.png'>");
	      mav.addObject("link1","<input type='button' value='다시시도' onclick='javascript:history.back()'>");
	      mav.addObject("link2","<input type='button' value='그룹목록' onclick='location.href=\"./list.do\"'>");      
	    }else {
	      mav.addObject("msg1", "<p>미디어 그룹 삭제 성공~~</p>");
	      mav.addObject("img",  "<img src='../images/sound.png'>");
	      mav.addObject("link1","<input type='button' value='그룹목록' onclick='location.href=\"./list.do\"'>");      
	    }//if end
	    return mav;
	}//deleteProc() end
	
	
}//class end
