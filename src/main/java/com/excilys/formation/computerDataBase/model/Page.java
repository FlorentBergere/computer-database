package com.excilys.formation.computerDataBase.model;

public class Page {
	@SuppressWarnings("unused")
	private int nbEntry;
	private int nbEntryPerPage;
	private int nbPage;
	private int currentPage;
	
	public Page(int nbEntry) {
		this.nbEntry = nbEntry;
		this.nbEntryPerPage = 20;
		this.nbPage = nbEntry / nbEntryPerPage;
		this.currentPage = 0;
	}
	
	public Page(int nbEntry,int nbEntryPerPage) {
		this.nbEntry = nbEntry;
		this.nbEntryPerPage = nbEntryPerPage;
		this.nbPage = nbEntry / nbEntryPerPage;
		this.currentPage = 0;
	}
	
	public int getOffset() {
		return currentPage * nbEntryPerPage;
	}
	
	public int getNbEntryPerPage () {
		return nbEntryPerPage;
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
	
}
