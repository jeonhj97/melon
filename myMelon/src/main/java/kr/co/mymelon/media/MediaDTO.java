package kr.co.mymelon.media;

import org.springframework.web.multipart.MultipartFile;

public class MediaDTO {

	
	  private int mediano;
	  private String title;
	  private String poster;
	  private String filename;
	  private long filesize;
	  private String mview;
	  private String rdate;
	  private int mediagroupno;
	  
//-------------------------------------------------
	  //createForm.jsp참조
	  
	  
	  //1)스프링 파일 객체 멤버 변수 선언
	  //<input type="file" id="filename" name="filenameMF">
	  private MultipartFile filenameMF; //myweb 이클립스에서는 라이브러리로 다운받아와서 파일첨부를 사용할수있지만
	  									//spring에서는 MultipartFile이란 함수를 사용하면 이걸 getter setter
	  									//처럼 사용 해서 파일 첨부기능 을 사용할수있다.
	  //<input type="file" id="poster" name="posterMF>
	  private MultipartFile posterMF;
	  
	  //2)getter와 setter작성
	  public MultipartFile getFilenameMF() {
			return filenameMF;
		}
		public void setFilenameMF(MultipartFile filenameMF) {
			this.filenameMF = filenameMF;
		}
		public MultipartFile getPosterMF() {
			return posterMF;
		}
		public void setPosterMF(MultipartFile posterMF) {
			this.posterMF = posterMF;
		}
		
		//3)servlet-context.xml에 스프링빈 등록(이렇게해야 사용가능)
		
		
		
//-------------------------------------------------
	  
	public int getMediano() {
		return mediano;
	}
	public void setMediano(int mediano) {
		this.mediano = mediano;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getMview() {
		return mview;
	}
	public void setMview(String mview) {
		this.mview = mview;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public int getMediagroupno() {
		return mediagroupno;
	}
	public void setMediagroupno(int mediagroupno) {
		this.mediagroupno = mediagroupno;
	}
	@Override
	public String toString() {
		return "MediaCont [mediano=" + mediano + ", title=" + title + ", poster=" + poster + ", filename=" + filename
				+ ", filesize=" + filesize + ", mview=" + mview + ", rdate=" + rdate + ", mediagroupno=" + mediagroupno
				+ "]";
	}
}//class end
