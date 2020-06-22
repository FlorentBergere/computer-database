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
	
	public int getOffset() {
		return currentPage * nbEntryPerPage;
	}
	
	public int getNbEntryPerPage () {
		return nbEntryPerPage;
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
