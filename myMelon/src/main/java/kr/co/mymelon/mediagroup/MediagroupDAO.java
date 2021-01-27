package kr.co.mymelon.mediagroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.utility.DBClose;
import net.utility.DBOpen;

@Component		
public class MediagroupDAO {
	
	//@Component어노테이션으로 자동 생성된 객체를 사용하려면
	//객체간 서로 연결이 되어야 한다
	@Autowired
	private DBOpen dbopen;
	
	@Autowired
	private DBClose dbclose;
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	StringBuilder sql=null;
	ArrayList<MediagroupDTO> list=null;		
	
	public MediagroupDAO() {
		System.out.println("---MediagroupDAO()객체 생성됨...");
	}//MediagroupDAO() end
	
	
	public int create(MediagroupDTO dto) {
	    int cnt = 0; 
	    try{
	      con = dbopen.getConnection();
	      sql = new StringBuilder();  
	      sql.append(" INSERT INTO mediagroup(mediagroupno, title)");
	      sql.append(" VALUES( mediagroup_seq.nextval, ? )");
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setString(1, dto.getTitle());
	      cnt = pstmt.executeUpdate();
	    }catch(Exception e){
	      System.out.println("미디어그룹등록실패:" + e);
	    }finally{
	      DBClose.close(con, pstmt);
	    }
	    return cnt;
	}//create() end
	
	
	public ArrayList<MediagroupDTO> list(){
	    try{
	        con=dbopen.getConnection();
	        sql=new StringBuilder();     
	        sql.append(" SELECT mediagroupno, title");
	        sql.append(" FROM mediagroup");
	        sql.append(" ORDER BY mediagroupno DESC");
	        
	        pstmt=con.prepareStatement(sql.toString());
	        rs=pstmt.executeQuery();
	        if(rs.next()){
	          list=new ArrayList<MediagroupDTO>();
	          do{
	            MediagroupDTO dto=new MediagroupDTO();
	            dto.setMediagroupno(rs.getInt("mediagroupno"));
	            dto.setTitle(rs.getString("title"));
	            list.add(dto);
	          }while(rs.next());
	        }else{
	          list=null;
	        }//if end
	        
	      }catch(Exception e) {
	        System.out.println("미디어그룹목록실패: "+e);
	      }finally{
	        DBClose.close(con, pstmt, rs);
	      }    
	      return list;
	}//list() end
	
	
    public MediagroupDTO read(MediagroupDTO dto){
	    try{
	      con = dbopen.getConnection();
	      sql = new StringBuilder();
	      sql.append(" SELECT mediagroupno, title");
	      sql.append(" FROM mediagroup");
	      sql.append(" WHERE mediagroupno = ?");
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setInt(1, dto.getMediagroupno());
	      rs = pstmt.executeQuery();
	      if(rs.next()){
	        dto.setMediagroupno(rs.getInt("mediagroupno"));
	        dto.setTitle(rs.getString("title"));
	      }else {
	        dto = null;
	      }//if end
	    }catch(Exception e){
	      System.out.println("미디어그룹 상세보기 실패: "+e);
	    }finally{
	      DBClose.close(con, pstmt, rs);
	    }//end
	    return dto;
	}//read() end	
	
	
	public int update(MediagroupDTO dto){
	    int cnt = 0;
	    try{
	      con = dbopen.getConnection();
	      sql = new StringBuilder();
	      sql.append(" UPDATE mediagroup");
	      sql.append(" SET title = ?");
	      sql.append(" WHERE mediagroupno = ?");
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setString(1, dto.getTitle());
	      pstmt.setInt(2, dto.getMediagroupno());
	      cnt = pstmt.executeUpdate();

	    }catch(Exception e){
	      System.out.println("미디어그룹 수정실패: "+e);
	    }finally{
	      DBClose.close(con, pstmt);
	    }//end
	    return cnt;
	}//update() end
	
	
	public int delete(MediagroupDTO dto){
	    int cnt = 0;
	    try{
	      con = dbopen.getConnection();
	      sql = new StringBuilder();
	      sql.append(" DELETE FROM mediagroup");
	      sql.append(" WHERE mediagroupno = ?");
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setInt(1, dto.getMediagroupno());
	      cnt = pstmt.executeUpdate();
	    }catch(Exception e){
	      System.out.println("미디어그룹 삭제실패: "+e);
	    }finally{
	      DBClose.close(con, pstmt);
	    }//end
	    return cnt;
	}//delete() end
	
}//class end
