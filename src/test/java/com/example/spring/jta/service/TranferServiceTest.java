package com.example.spring.jta.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.jta.bank.exceptions.NotEnouthFoundsException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TranferServiceTest {

	@Autowired
	private TransferService transferService;
	
	@Test(expected=NotEnouthFoundsException.class)
	public void testTransfer() {
		Integer fromAccountId = 1010;
		Integer toAccountId = 1020;
		Float amount = 1000f;
		transferService.transfer(fromAccountId, toAccountId, amount);
	}
}
