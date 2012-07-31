package com.cityproperties.web;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.cityproperties.dao.ClientDAO;
import com.cityproperties.domain.Client;
import com.cityproperties.domain.ClientPrivilege;
import com.cityproperties.util.Constants;
import com.cityproperties.util.encrypt.Encrypter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

public class ClientValidateAction
        extends ActionSupport
        implements SessionAware, Preparable {

    // Fields
    private Long clientId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean zuper;
    private boolean active;
    private boolean view;
    private boolean insert;
    private boolean update;
    private boolean delete;

    // Session
    private Map<String, Object> session;
    private Client client;
    private List<Client> clients;

    // DI via Spring
    @Autowired
    private ClientDAO clientDao;

    public void setClientDao(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }

    @SuppressWarnings("unchecked")
    public void prepare() throws Exception {
        if (session.containsKey(Constants.MODEL_CLIENT) ) {
            client = (Client) session.get(Constants.MODEL_CLIENT);
            setClientId(client.getClientId());
            setFirstName(client.getFirstName());
            setLastName(client.getLastName());
            setUsername(client.getUsername());
            setPassword("******");
            setActive(client.getActive());
            setView(client.getClientPrivilege().getView());
            setInsert(client.getClientPrivilege().getInsert());
            setUpdate(client.getClientPrivilege().getUpdate());
            setDelete(client.getClientPrivilege().getDelete());
        }

        if (session.containsKey(Constants.CLIENTS)) {
            clients = (List<Client>) session.get(Constants.CLIENTS);
        }
    }

    public String execute() {
        if (clientId != null) {
            client = (Client) session.get(Constants.MODEL_CLIENT);
        } else {
            client = new Client();
        }

        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setUsername(username);
        client.setActive(active);

        // If new client is issued
        if (clientId == null) {
            // Encrypt password if new Client
            client.setPassword(Encrypter.encrypt(password));

            client.setClientPrivilege(
                    new ClientPrivilege(client, view, insert, update, delete));
        } else {
            ClientPrivilege privilege = client.getClientPrivilege();
            privilege.setView(view);
            privilege.setInsert(insert);
            privilege.setUpdate(update);
            privilege.setDelete(delete);

            client.setClientPrivilege(privilege);
        }

        session.put(Constants.MODEL_CLIENT, client);

        return SUCCESS;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @RequiredStringValidator(message="First name is required.")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @RequiredStringValidator(message="Last name is required.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @RequiredStringValidator(message="Username is required.")
    @EmailValidator(message="Username is not a valid email.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @RequiredStringValidator(message="Password is required.")
    @StringLengthFieldValidator(minLength="5", maxLength="10", message="Password must be from 5 to 10 characters.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isZuper() {
        return zuper;
    }

    public void setZuper(boolean zuper) {
        this.zuper = zuper;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
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
