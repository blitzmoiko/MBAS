package com.cityproperties.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.cityproperties.dao.ClientDAO;
import com.cityproperties.domain.Client;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class ClientAction
        extends ActionSupport
        implements SessionAware, Preparable {

    // Session
    private Map<String, Object> session;
    private Client client;
    private List<Client> clients;

    //DI via Spring
    @Autowired
    private ClientDAO clientDao;

    public void setClientDao(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }

    @SuppressWarnings("unchecked")
    public void prepare() {
        if (session.containsKey(Constants.MODEL_CLIENT)) {
            client = (Client) session.get(Constants.MODEL_CLIENT);
        }

        if (session.containsKey(Constants.CLIENTS)) {
            clients = (List<Client>) session.get(Constants.CLIENTS);
        }
    }

    public String execute() {
        return SUCCESS;
    }

    /**
     * To save or update client.
     * @return String
     */
    public String saveOrUpdate() {
        clientDao.save(client);
        session.remove(Constants.MODEL_CLIENT);
        return SUCCESS;
    }

    /**
     * To list all clients.
     * @return String
     */
    public String list() {
        if (session.containsKey(Constants.MODEL_CLIENT)) {
            session.remove(Constants.MODEL_CLIENT);
        }

        if (session.containsKey(Constants.CLIENTS)) {
            session.remove(Constants.CLIENTS);
        }

        clients = clientDao.findAll();
        session.put(Constants.CLIENTS, clients);
        return SUCCESS;
    }

    /**
     * To delete a client.
     * @return String
     */
    public String delete() {
        HttpServletRequest request = ServletActionContext.getRequest();
        clientDao.removeById(Long.parseLong(request.getParameter("id")));
        return SUCCESS;
    }

    /**
     * To list a single client by Id.
     * @return String
     */
    public String edit() {
        HttpServletRequest request = ServletActionContext.getRequest();
        client = clientDao.find(Long.parseLong(request.getParameter("id")));
        session.put(Constants.MODEL_CLIENT, client);
        return SUCCESS;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
