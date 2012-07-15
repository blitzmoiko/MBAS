package com.cityproperties.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "C_BA")
public class BusinessAssociate 
		implements Serializable, Comparable<BusinessAssociate> {
	
	@Id
	@SequenceGenerator(name = "baSeq", sequenceName="BA_SEQUENCE", allocationSize = 1, initialValue= 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "baSeq")
	@Column(name = "BA_ID")
	private Long businessAssociateId;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "CLIENT_ID")
	private Client client;
	
	@Column(name = "F_NAME")	
	private String firstName;
	
	@Column(name = "M_NAME")	
	private String middleName;
	
	@Column(name = "L_NAME")	
	private String lastName;
	
	@Column(name = "SEX")	
	private String sex;
	
	@Column(name = "EMAIL")	
	private String email;
	
	@Column(name = "HOME_PHONE")	
	private String homePhone;
	
	@Column(name = "WORK_PHONE")	
	private String workPhone;
	
	@Column(name = "MOBILE_PHONE")	
	private String mobilePhone;
	
	@Column(name = "BIRTH_DATE")	
	private Date birthDate;
	
	@Column(name = "WEDDING_DATE")	
	private Date anniversaryDate;
	
	@Column(name = "IS_SUPPLIER")
	@Type(type="yes_no")
	private Boolean supplier;
	
	@OneToMany(mappedBy = "businessAssociate", fetch=FetchType.EAGER)
	private Set<MailRecord> mailRecords = new HashSet<MailRecord>(0);

	public BusinessAssociate() {/* Default constructor for hibernate */}

	public BusinessAssociate(Long businessAssociateId, Client client, String firstName,
			String lastName, String sex, String email, Boolean supplier) {
		this.businessAssociateId = businessAssociateId;
		this.client = client;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.email = email;
		this.supplier = supplier;
	}

	public BusinessAssociate(Long businessAssociateId, Client client, String firstName,
			String middleName, String lastName, String sex, String email,
			String homePhone, String workPhone, String mobilePhone,
			Date birthDate, Date anniversaryDate, Boolean supplier,
			Set<MailRecord> mailRecords) {
		this.businessAssociateId = businessAssociateId;
		this.client = client;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.sex = sex;
		this.email = email;
		this.homePhone = homePhone;
		this.workPhone = workPhone;
		this.mobilePhone = mobilePhone;
		this.birthDate = birthDate;
		this.anniversaryDate = anniversaryDate;
		this.supplier = supplier;
		this.mailRecords = mailRecords;
	}

	public int compareTo(BusinessAssociate o) {
	    final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;
	    
	    if ( this == o ) return EQUAL;
	    
		if (!this.firstName.equalsIgnoreCase(o.firstName))
			return this.firstName.compareTo(o.firstName);
		if (!this.lastName.equalsIgnoreCase(o.lastName))
			return this.lastName.compareTo(o.lastName);
		if (!this.sex.equalsIgnoreCase(o.sex))
			return this.sex.compareTo(o.sex);
		if (!this.email.equalsIgnoreCase(o.email))
			return this.email.compareTo(o.email);
		
		if (!this.supplier && o.supplier) return BEFORE;
		if (this.supplier && !o.supplier) return AFTER;
		
		return this.birthDate.compareTo(o.birthDate);
	}

	public Long getBusinessAssociateId() {
		return businessAssociateId;
	}

	public void setBusinessAssociateId(Long businessAssociateId) {
		this.businessAssociateId = businessAssociateId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getAnniversaryDate() {
		return anniversaryDate;
	}

	public void setAnniversaryDate(Date anniversaryDate) {
		this.anniversaryDate = anniversaryDate;
	}

	public Boolean getSupplier() {
		return supplier;
	}

	public void setSupplier(Boolean supplier) {
		this.supplier = supplier;
	}

	public Set<MailRecord> getMailRecords() {
		return mailRecords;
	}

	public void setMailRecords(Set<MailRecord> mailRecords) {
		this.mailRecords = mailRecords;
	}

}
