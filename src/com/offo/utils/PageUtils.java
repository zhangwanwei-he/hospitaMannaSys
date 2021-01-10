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
	//����ǰҳ�� ��ֵ
	private  void initCurrentPage(String currentPage) {
		if(currentPage==null) {
			this.currentPage=1;
		}else {
			this.currentPage=Integer.valueOf(currentPage);
		}
	}
	//��ÿ��ҳ�ĵ�һ����¼�ĳ�ʼ�±긳ֵ
	private void initStartIndex() {
		this.startIndex=this.pageSize*(this.currentPage-1);
	}
	//����ҳ������ֵ
	private void initTotalPages() {
		this.totalPages=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
	}
	//��һҳ��ֵ
	private void initPrePage() {
		if(currentPage==1) {
			this.prePage=1;
		}else {
			this.prePage=this.currentPage-1;
		}
	}
	//��һҳ��ֵ	
	private  void initNextPage() {
		if(currentPage==totalPages) {
			this.nextPage=totalPages;
		}else {
			this.nextPage=currentPage+1;
		}
	}
	private int pageSize;//ҳ����
	private int currentPage;//��ǰҳ��
	private int totalCount;//��¼����
	private int startIndex;//ÿҳ��һ����¼�ĳ�ʼ�±�
	private int prePage;//��һҳ
	private int nextPage;//��һҳ
	private int totalPages;//��ҳ����
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
