package com.cityproperties.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.cityproperties.dao.ClientDAO;
import com.cityproperties.domain.Client;

@RunWith(MockitoJUnitRunner.class)
public class LoginActionTest {
	
	private Client client;
	private ClientDAO clientDao;
	private LoginAction action = new LoginAction();
	
	@Before
	public void setup() {
		if (clientDao == null) {
			clientDao = mock(ClientDAO.class);
			action.setClientDao(clientDao);
		}
		
	}
	
//	@Test(expected=Exception.class)
//	@Test
//	public void login_throws_exception() throws Exception {
//		
//		client = mock(Client.class);
//
//		when(clientDao.loadByUsernameAndPassword("", "")).thenReturn(client);
//		
//		String result = action.login();
//		
//		assertEquals("success", result);
//		
//	}

	@Test
	public void login() throws Exception {
		
		client = mock(Client.class);

		when(clientDao.loadByUsernameAndPassword(anyString(), anyString())).thenReturn(client);
		
		String result = action.execute();
		
		assertEquals("success", result);
		
	}
	
	@After
	public void tearDown() throws Exception {
		if (clientDao != null) {
			reset(clientDao);
		}
	}

}
