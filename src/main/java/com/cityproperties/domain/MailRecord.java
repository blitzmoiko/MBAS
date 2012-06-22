package com.cityproperties.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "C_MAIL_RECORD")
public class MailRecord implements Serializable {
	//TODO Generate Java Doc
	
	@Id
	@SequenceGenerator(name = "mrSeq", sequenceName=" MAIL_RECORD_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mrSeq")
	@Column(name = "M_RECORD_ID")
	private Integer mailRecordId;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "CLIENT_ID")
	private Client client;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "M_TYPE_ID")
	private MailType mailType;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "BA_ID")
	private BusinessAssociate businessAssociate;
	
	@Column(name = "COMMENTS")	
	private String comments;
	
	@Column(name = "SENT_DATE")	
	@Type(type="timestamp")
	private Date sentDate;

	public MailRecord() {/* Default constructor for hibernate */}

	public MailRecord(Integer mailRecordId, Client client, MailType mailType,
			BusinessAssociate businessAssociate, Date sentDate) {
		this.mailRecordId = mailRecordId;
		this.client = client;
		this.mailType = mailType;
		this.businessAssociate = businessAssociate;
		this.sentDate = sentDate;
	}

	public MailRecord(Integer mailRecordId, Client client, MailType mailType,
			BusinessAssociate businessAssociate, String comments, Date sentDate) {
		this.mailRecordId = mailRecordId;
		this.client = client;
		this.mailType = mailType;
		this.businessAssociate = businessAssociate;
		this.comments = comments;
		this.sentDate = sentDate;
	}

	public Integer getMailRecordId() {
		return mailRecordId;
	}

	public void setMailRecordId(Integer mailRecordId) {
		this.mailRecordId = mailRecordId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public MailType getMailType() {
		return mailType;
	}

	public void setMailType(MailType mailType) {
		this.mailType = mailType;
	}

	public BusinessAssociate getBusinessAssociate() {
		return businessAssociate;
	}

	public void setBusinessAssociate(BusinessAssociate businessAssociate) {
		this.businessAssociate = businessAssociate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

}
