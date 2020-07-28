package com.excilys.mapper;

import org.springframework.stereotype.Component;

import com.excilys.model.Computer;

@Component
public class ComputerMapper {

	public static String parseAtribute (String atribute){
		String result = null;
		
		if (atribute == null) {
			result = Computer.atributes.ID.getAtribute();
		}
		else {
			switch (atribute) {
				case "computerName":
					result = Computer.atributes.NAME.getAtribute();
					break;
		
				default:
					result = Computer.atributes.ID.getAtribute();
					break;
			}	
		}

		return result;
	}

}
