package com.example.spring.jta.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.jta.service.TransferService;

@RestController
public class TransferController {

	private TransferService transferService;	
	public TransferController(TransferService transferService) {
		this.transferService = transferService;
	}
		
	@PostMapping(path="/transf")
	public ResponseEntity<String> transfer(@RequestParam Integer fromAccountId
			, @RequestParam Integer toAccountId
			, @RequestParam Float amount) {
		ResponseEntity<String> result = null;
		try {
			transferService.transfer(fromAccountId, toAccountId, amount);
			result = ResponseEntity.ok().build();
		} catch (Exception e) {
			result = ResponseEntity.status(500).body(e.getMessage());
		}
		return result;		
	}	
}
