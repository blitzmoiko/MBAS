package com.cityproperties.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "C_CLIENT_PRIVILEGE")
public class ClientPrivilege implements Serializable {

	@Id
	@Column(name = "CLIENT_ID", unique = true, nullable = false)
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "client"))
	private Long clientId;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Client client;

	@Column(name = "HAS_VIEW")
	@Type(type="yes_no")
	private Boolean view;

	@Column(name = "HAS_UPDATE")
	@Type(type="yes_no")
	private Boolean insert;

	@Column(name = "HAS_DELETE")
	@Type(type="yes_no")
	private Boolean update;

	@Column(name = "HAS_INSERT")
	@Type(type="yes_no")
	private Boolean delete;

	public ClientPrivilege() {/* Default constructor for hibernate */}

	public ClientPrivilege(Client client, Boolean view, Boolean insert,
			Boolean update, Boolean delete) {
		this.client = client;
		this.view = view;
		this.insert = insert;
		this.update = update;
		this.delete = delete;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Boolean getView() {
		return view;
	}

	public void setView(Boolean view) {
		this.view = view;
	}

	public Boolean getInsert() {
		return insert;
	}

	public void setInsert(Boolean insert) {
		this.insert = insert;
	}

	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

}
