package com.cityproperties.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "C_MAIL_TYPE")
public class MailType implements Serializable {
	//TODO Generate Java Doc
	
	@Id
	@SequenceGenerator(name = "mtSeq", sequenceName="MAIL_TYPE_SEQUENCE", allocationSize = 1, initialValue= 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mtSeq")
	@Column(name = "M_TYPE_ID")
	private Long mailTypeId;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "L_CONTENT_ID")
	private LetterContent letterContent;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name = "L_TEMPLATE_ID")
	private LetterTemplate letterTemplate;
	
	@Column(name = "TYPE_NAME")		
	private String description;
	
	@Column(name = "REVISION_DATE")		
	private Date revisionDate;
	
	@OneToMany(mappedBy = "mailType")	
	private Set<MailRecord> mailRecords = new HashSet<MailRecord>(0);

	public MailType() {/* Default constructor for hibernate */}

	/**
	 * @param mailTypeId
	 * @param letterContent
	 * @param letterTemplate
	 * @param description
	 * @param revisionDate
	 */
	public MailType(Long mailTypeId, LetterContent letterContent,
			LetterTemplate letterTemplate, String description, Date revisionDate) {
		this.mailTypeId = mailTypeId;
		this.letterContent = letterContent;
		this.letterTemplate = letterTemplate;
		this.description = description;
		this.revisionDate = revisionDate;
	}

	/**
	 * @param mailTypeId
	 * @param letterContent
	 * @param letterTemplate
	 * @param description
	 * @param revisionDate
	 * @param mailRecords
	 */
	public MailType(Long mailTypeId, LetterContent letterContent,
			LetterTemplate letterTemplate, String description, Date revisionDate,
			Set<MailRecord> mailRecords) {
		this.mailTypeId = mailTypeId;
		this.letterContent = letterContent;
		this.letterTemplate = letterTemplate;
		this.description = description;
		this.revisionDate = revisionDate;
		this.mailRecords = mailRecords;
	}

	/**
	 * @return the mailTypeId
	 */
	public Long getMailTypeId() {
		return mailTypeId;
	}

	/**
	 * @param mailTypeId the mailTypeId to set
	 */
	public void setMailTypeId(Long mailTypeId) {
		this.mailTypeId = mailTypeId;
	}

	/**
	 * @return the letterContent
	 */
	public LetterContent getLetterContent() {
		return letterContent;
	}

	/**
	 * @param letterContent the letterContent to set
	 */
	public void setLetterContent(LetterContent letterContent) {
		this.letterContent = letterContent;
	}

	/**
	 * @return the letterTemplate
	 */
	public LetterTemplate getLetterTemplate() {
		return letterTemplate;
	}

	/**
	 * @param letterTemplate the letterTemplate to set
	 */
	public void setLetterTemplate(LetterTemplate letterTemplate) {
		this.letterTemplate = letterTemplate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the revisionDate
	 */
	public Date getRevisionDate() {
		return revisionDate;
	}

	/**
	 * @param revisionDate the revisionDate to set
	 */
	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}

	/**
	 * @return
	 */
	public Set<MailRecord> getMailRecords() {
		return this.mailRecords;
	}

	/**
	 * @param mailRecords
	 */
	public void setMailRecords(Set<MailRecord> mailRecords) {
		this.mailRecords = mailRecords;
	}
	
}
