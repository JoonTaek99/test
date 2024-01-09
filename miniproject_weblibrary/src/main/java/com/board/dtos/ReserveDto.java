package com.board.dtos;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "reserveDto")
public class ReserveDto {

	private int seq;
	private String booktitle;
	private String bookauthor;
	private String bookpublisher;
	private String reserver;
	private Date resdate;
	
	public ReserveDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReserveDto(int seq, String booktitle, String bookauthor, String bookpublisher, String reserver,
			Date resdate) {
		super();
		this.seq = seq;
		this.booktitle = booktitle;
		this.bookauthor = bookauthor;
		this.bookpublisher = bookpublisher;
		this.reserver = reserver;
		this.resdate = resdate;
	}

	@Override
	public String toString() {
		return "ReserveDto [seq=" + seq + ", booktitle=" + booktitle + ", bookauthor=" + bookauthor + ", bookpublisher="
				+ bookpublisher + ", reserver=" + reserver + ", resdate=" + resdate + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public String getBookauthor() {
		return bookauthor;
	}

	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}

	public String getBookpublisher() {
		return bookpublisher;
	}

	public void setBookpublisher(String bookpublisher) {
		this.bookpublisher = bookpublisher;
	}

	public String getReserver() {
		return reserver;
	}

	public void setReserver(String reserver) {
		this.reserver = reserver;
	}

	public Date getResdate() {
		return resdate;
	}

	public void setResdate(Date resdate) {
		this.resdate = resdate;
	}

	
	
}
