package com.cityproperties.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Entity
@Table(name = "C_CLIENT")
public class Client implements Serializable {

	@Id
	@SequenceGenerator(name = "clientSeq", sequenceName="CLIENT_SEQUENCE", allocationSize = 1, initialValue= 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientSeq")
	@Column(name = "CLIENT_ID")
	private Long clientId;

	@Column(name = "F_NAME")
	private String firstName;

	@Column(name = "L_NAME")
	private String lastName;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "IS_SUPER")
	@Type(type="yes_no")
	private Boolean zuper = false;

	@Column(name = "IS_ACTIVE")
	@Type(type="yes_no")
	private Boolean active = false;

	@OneToMany(mappedBy = "client")
	private Set<BusinessAssociate> businessAssociates = 
			new HashSet<BusinessAssociate>(0);

	@OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
	private ClientPrivilege clientPrivilege = new ClientPrivilege(this, false,
			false, false, false);

	public Client() {/* Default constructor for hibernate */}

	public Client(Long clientId, String firstName, String lastName,
			String username, String password, Boolean zuper, Boolean active) {
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.zuper = zuper;
		this.active = active;
	}

	public Client(Long clientId, String firstName, String lastName,
			String username, String password, Boolean zuper, Boolean active,
			Set<BusinessAssociate> businessAssociates,
			ClientPrivilege clientPrivilege) {
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.zuper = zuper;
		this.active = active;
		this.businessAssociates = businessAssociates;
		this.clientPrivilege = clientPrivilege;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getZuper() {
		return zuper;
	}

	public void setZuper(Boolean zuper) {
		this.zuper = zuper;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<BusinessAssociate> getBusinessAssociates() {
		return this.businessAssociates;
	}

	public void setBusinessAssociates(Set<BusinessAssociate> businessAssociates) {
		this.businessAssociates = businessAssociates;
	}

	public ClientPrivilege getClientPrivilege() {
		return this.clientPrivilege;
	}

	public void setClientPrivilege(ClientPrivilege clientPrivilege) {
		this.clientPrivilege = clientPrivilege;
	}

}
