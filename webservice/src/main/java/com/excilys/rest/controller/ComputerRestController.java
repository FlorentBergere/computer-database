package com.excilys.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.dto.ComputerDTO;
import com.excilys.service.ComputerService;

@RestController
public class ComputerRestController {

	@Autowired
	private ComputerService computerService;

	
	@GetMapping(value = "/computer/{id}", produces = "application/json")
	public ResponseEntity<ComputerDTO> returnCompany(@PathVariable Long id) {
		ComputerDTO computerDTO = null;
		computerDTO = computerService.findById(id.intValue());
		return new ResponseEntity<ComputerDTO>(computerDTO, HttpStatus.OK);
	}

}