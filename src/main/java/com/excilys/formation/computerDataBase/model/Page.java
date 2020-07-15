package com.excilys.formation.computerDataBase.model;

import java.util.ArrayList;
import java.util.List;

public class Page {
	@SuppressWarnings("unused")
	private int nbEntry;
	private int pageLength;
	private int nbPage;
	private int currentPage;
	
	private String attributeToOrder;
	private String search;
	private String currentOrder = Page.order.ASCENDING.getOrder();
	
	public enum order {
		ASCENDING("ASC"),
		DESCENDING("DSC");
		
		private final String order;
		private order (String order) {
		this.order = order;
		} 
		public String getOrder() {
			return this.order;
		}
	}
	
	
	public Page(int nbEntry) {
		this.nbEntry = nbEntry;
		this.pageLength = 20;
		this.nbPage = nbEntry / pageLength;
		this.currentPage = 0;
	}
	
	public Page(int nbEntry,int pageLength) {
		this.nbEntry = nbEntry;
		this.pageLength = pageLength;
		this.nbPage = nbEntry / pageLength;
		this.currentPage = 0;
	}
	
	public int getOffset() {
		return currentPage * pageLength;
	}
	
	public int getNbEntryPerPage () {
		return pageLength;
	}
	
	public void goTo (int pageNumber) {
		if(pageNumber > nbPage) {
			currentPage = nbPage;
		}else if (pageNumber < 0) {
			currentPage = 0;
		}else {
			currentPage = pageNumber;
		}
	}
	
	public int getNbPage () {
		return nbPage;
	}
	
	
	/**
	 * Permet d'avoir un liste de page avant et après la page courante.
	 * @return 
	 */
	public List<Integer> getListPage(){
		List<Integer> result = new ArrayList<Integer>();
		if (this.currentPage > 2 && this.nbPage - this.currentPage > 2) {
			for(int i = this.currentPage-3; i <= this.currentPage + 3; i++) {
				result.add(i);
			}
		}else if (this.currentPage <= 2 && this.nbPage - this.currentPage <= 2){
			for(int i = 0; i < this.nbPage ; i++) {
				result.add(i);
			}
		}else if (this.currentPage <= 2 ) {
			for(int i = 0; i <= this.currentPage + 3; i++) {
				result.add(i);
			}
		}else if (this.nbPage - this.currentPage <= 2) {
			for(int i = this.currentPage-3; i <= this.nbPage; i++) {
				result.add(i);
			}
		}
		return result;
	}
	
	public boolean next () {
		if (this.currentPage < this.nbPage) {
			this.currentPage++;
			return true;
		}else {
			return false;
		}
	}
	
	public boolean previous () {
		if (this.currentPage > 0) {
			this.currentPage--;
			return true;
		}else {
			return false;
		}
	}
	
	public void setAttributeToOrder(String attributeToOrder) {
		this.attributeToOrder = attributeToOrder;
	}
	
	public String getAttributeToOrder() {
		return this.attributeToOrder;
	}

	public String getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(String currentOrder) {
		this.currentOrder = currentOrder;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	public void setPageLength (int pageLength) {
		this.pageLength = pageLength;
		this.nbPage = nbEntry / pageLength;
		if (currentPage * pageLength > nbEntry) {
			this.currentPage = this.nbPage;
		}

	}
	
}
