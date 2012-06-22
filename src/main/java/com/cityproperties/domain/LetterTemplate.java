package com.cityproperties.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "C_LETTER_TEMPLATE")
public class LetterTemplate implements Serializable {
	//TODO Generate Java Doc
	
	@Id
	@SequenceGenerator(name = "ltSeq", sequenceName="LETTER_TEMPLATE_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ltSeq")
	@Column(name = "L_CONTENT_ID")
	private Long letterTemplateId;
	
	@Column(name = "TEMPLATE")		
	private Blob template;
	
	@OneToMany(mappedBy = "letterTemplate")
	private Set<MailType> mailTypes = new HashSet<MailType>(0);

	public LetterTemplate() {/* Default constructor for hibernate */}

	public LetterTemplate(Long letterTemplateId, Blob template) {
		this.letterTemplateId = letterTemplateId;
		this.template = template;
	}

	public LetterTemplate(Long letterTemplateId, Blob template,
			Set<MailType> mailTypes) {
		this.letterTemplateId = letterTemplateId;
		this.template = template;
		this.mailTypes = mailTypes;
	}
	
	/**
	 * @return the letterTemplateId
	 */
	public Long getLetterTemplateId() {
		return letterTemplateId;
	}

	/**
	 * @param letterTemplateId the letterTemplateId to set
	 */
	public void setLetterTemplateId(Long letterTemplateId) {
		this.letterTemplateId = letterTemplateId;
	}

	/**
	 * @return the template
	 */
	public Blob getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(Blob template) {
		this.template = template;
	}

	/**
	 * @return
	 */
	public Set<MailType> getMailTypes() {
		return this.mailTypes;
	}

	/**
	 * @param mailTypes
	 */
	public void setMailTypes(Set<MailType> mailTypes) {
		this.mailTypes = mailTypes;
	}
	
}
