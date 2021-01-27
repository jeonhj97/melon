package kr.co.mymelon.media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.mymelon.mediagroup.MediagroupDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

@Component // 자신만의 객체사용 not @Controller
public class MediaDAO {

	@Autowired
	DBOpen dbopen;

	@Autowired
	DBClose dbclose;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sql = null;
	ArrayList<MediaDTO> list = null;

	public MediaDAO() {
		System.out.println("---MediaDAO()객체 생성됨...");
	}// MediaDAO() end

	public ArrayList<MediaDTO> list(MediaDTO dto) {// 목록
		ArrayList<MediaDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT mediano, title, rdate, poster, filename, filesize, mview, mediagroupno ");
			sql.append(" FROM media ");
			sql.append(" WHERE mview='Y' and mediagroupno=? ");
			sql.append(" ORDER BY mediano desc ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getMediagroupno());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<MediaDTO>();
				do {
					dto = new MediaDTO();
					dto.setMediano(rs.getInt("mediano"));
					dto.setTitle(rs.getString("title"));
					dto.setRdate(rs.getString("rdate"));// date로 지정해도 String으로 받음
					dto.setPoster(rs.getString("poster"));
					dto.setFilename(rs.getString("filename"));
					dto.setFilesize(rs.getLong("filesize"));
					dto.setMview(rs.getString("mview"));
					dto.setMediagroupno(rs.getInt("mediagroupno"));
					list.add(dto);
				} while (rs.next());
			} else {
				list = null;
			} // if end

		} catch (Exception e) {
			System.out.println("목록실패:" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end

		return list;
	}// list()end

	public int create(MediaDTO dto) {// 쓰기
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" INSERT INTO media(mediano, title, rdate, poster, filename, filesize, mediagroupno) ");
			sql.append(" VALUES(media_seq.nextval, ?, sysdate, ?,?,?,?) ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getPoster());
			pstmt.setString(3, dto.getFilename());
			pstmt.setLong(4, dto.getFilesize());
			pstmt.setInt(5, dto.getMediagroupno());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("등록실패:" + e);
		} finally {
			DBClose.close(con, pstmt);
		}
		return cnt;
	}// list()end

	public MediaDTO read(int mediano) {//상세보기
	    MediaDTO dto = null;
	    try {
	      con = dbopen.getConnection();
	      sql = new StringBuilder();
	      sql.append(" SELECT mediano, title, rdate, poster, filename, filesize, mview, mediagroupno");
	      sql.append(" FROM media");
	      sql.append(" WHERE mediano = ?"); 
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setInt(1, mediano);
	      rs = pstmt.executeQuery();
	      if(rs.next()) {
	        dto = new MediaDTO();
	        dto.setMediano(rs.getInt("mediano"));
	        dto.setTitle(rs.getString("title"));
	        dto.setRdate(rs.getString("rdate"));
	        dto.setPoster(rs.getString("poster"));
	        dto.setFilename(rs.getString("filename"));
	        dto.setFilesize(rs.getLong("filesize"));
	        dto.setMview(rs.getString("mview"));
	        dto.setMediagroupno(rs.getInt("mediagroupno"));
	      }//if end

	    } catch (Exception e) {
	        System.out.println("상세보기실패"+e);
	    } finally {
	        DBClose.close(con, pstmt, rs);
	    }//end
	    return dto;
	}//read() end
	
	public int update(MediaDTO dto) {//상세보기
	    int cnt=0;
	    try {
	      con = dbopen.getConnection();
	      sql = new StringBuilder();
	      sql.append(" UPDATE media ");
	      sql.append(" SET title=?, poster=?, filename=?, filesize=? ");
	      sql.append(" WHERE mediano = ?"); 
	      pstmt = con.prepareStatement(sql.toString());
	      
	      pstmt.setString(1, dto.getTitle());
	      pstmt.setString(2, dto.getPoster());
	      pstmt.setString(3, dto.getFilename());
	      pstmt.setLong(4, dto.getFilesize());
	      pstmt.setInt(5, dto.getMediano());
	      
	      cnt= pstmt.executeUpdate();
	    
	    } catch (Exception e) {
	        System.out.println("수정실패"+e);
	    } finally {
	        DBClose.close(con, pstmt);
	    }//end
	    return cnt;
	}//read() end
	
	public int delete(int mediano){
	    int cnt = 0;
	    try{
	      con = dbopen.getConnection();
	      sql = new StringBuilder();
	      sql.append(" DELETE FROM media");
	      sql.append(" WHERE mediano = ?");
	      pstmt = con.prepareStatement(sql.toString());
	      pstmt.setInt(1, mediano);
	      cnt = pstmt.executeUpdate();
	    }catch(Exception e){
	      System.out.println("삭제실패: "+e);
	    }finally{
	      DBClose.close(con, pstmt);
	    }//end
	    return cnt;
	}//delete() end
	
	
	

}// class end
