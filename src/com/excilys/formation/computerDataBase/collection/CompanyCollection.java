package com.excilys.formation.computerDataBase.collection;

import java.util.ArrayList;
import java.util.List;
import com.excilys.formation.computerDataBase.model.Company;

public class CompanyCollection {
	private List<Company> companys;

	public CompanyCollection (){
		this.companys = new ArrayList<Company>();
	}
	
	public void addCompany(Company c){
		this.companys.add(c);
	}
	
	@Override
	public String toString() {
		String result = new String();
		for(Company c : companys)
			result += c.toString();
		return result;
	}
}
