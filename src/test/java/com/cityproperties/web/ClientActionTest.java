package com.cityproperties.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cityproperties.dao.ClientDAO;
import com.cityproperties.domain.Client;
import com.opensymphony.xwork2.ActionContext;

public class ClientActionTest {

	private Client client;
	private ClientDAO clientDao;
	private ClientAction action = new ClientAction();

	@Before
	public void setUp() throws Exception {
		if (clientDao == null) {
			clientDao = mock(ClientDAO.class);
			action.setClientDao(clientDao);
		}
	}

	@Test
	public void saveOrUpdate() {
		
		client = mock(Client.class);
		
		action.setClient(client);
		
		String result = action.saveOrUpdate();
		
		assertEquals("success", result);

		verify(clientDao).save(client);
		
	}

	@Test
	public void list() {

		String result = action.list();
		
		assertEquals("success", result);
		
		verify(clientDao).findAll();
		
	}

	@Test
	public void delete() {
		
		client = mock(Client.class);
		
		action.setClient(client);
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		ActionContext context = mock(ActionContext.class);
		ActionContext.setContext(context);
		
		when(ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST)).thenReturn(request);
		when(request.getParameter("id")).thenReturn("1");
		when(clientDao.find(anyLong())).thenReturn(client);
		
		String result = action.delete();
		
		assertEquals("success", result);
		
		verify(clientDao).find(anyLong());
		
		verify(clientDao).remove(client);
		
	}
	
	@Test
	public void edit() {
		
		client = mock(Client.class);
		
		action.setClient(client);
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		ActionContext context = mock(ActionContext.class);
		ActionContext.setContext(context);
		
		when(ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST)).thenReturn(request);
		when(request.getParameter("id")).thenReturn("1");
		when(clientDao.find(anyLong())).thenReturn(client);
		
		String result = action.edit();
		
		assertEquals("success", result);
		
		verify(clientDao).find(anyLong());
		
	}

	@After
	public void tearDown() throws Exception {
		if (clientDao != null) {
			reset(clientDao);
		}
	}

}
