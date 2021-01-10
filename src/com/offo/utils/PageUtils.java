package com.offo.utils;

public class PageUtils {
	public PageUtils(int pageSize, String currentPage, int totalCount) {
		this.pageSize = pageSize;
		initCurrentPage(currentPage);
		this.totalCount = totalCount;
		initStartIndex();
		initTotalPages();
		initPrePage();
		initNextPage();
	}
	//给当前页码 赋值
	private  void initCurrentPage(String currentPage) {
		if(currentPage==null) {
			this.currentPage=1;
		}else {
			this.currentPage=Integer.valueOf(currentPage);
		}
	}
	//给每个页的第一条记录的超始下标赋值
	private void initStartIndex() {
		this.startIndex=this.pageSize*(this.currentPage-1);
	}
	//给总页码数赋值
	private void initTotalPages() {
		this.totalPages=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
	}
	//上一页赋值
	private void initPrePage() {
		if(currentPage==1) {
			this.prePage=1;
		}else {
			this.prePage=this.currentPage-1;
		}
	}
	//下一页赋值	
	private  void initNextPage() {
		if(currentPage==totalPages) {
			this.nextPage=totalPages;
		}else {
			this.nextPage=currentPage+1;
		}
	}
	private int pageSize;//页容量
	private int currentPage;//当前页码
	private int totalCount;//记录总数
	private int startIndex;//每页第一条记录的超始下标
	private int prePage;//上一页
	private int nextPage;//下一页
	private int totalPages;//总页码数
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
