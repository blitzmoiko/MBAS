package com.cityproperties.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "C_LETTER_TEMPLATE")
public class LetterTemplate
		implements Serializable, Comparable<LetterTemplate> {

	@Id
	@SequenceGenerator(name = "ltSeq", sequenceName = "LETTER_TEMPLATE_SEQUENCE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ltSeq")
	@Column(name = "L_TEMPLATE_ID")
	private Long letterTemplateId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "TEMPLATE")
	@Lob
	private byte[] template;

	@Column(name = "TEMPLATE_THUMB")
	@Lob
	private byte[] thumbnail;

	@Column(name = "CONTENT_TYPE")
	private String contentType;

	@OneToMany(fetch=FetchType.EAGER, mappedBy = "letterTemplate")
	private Set<MailType> mailTypes = new HashSet<MailType>(0);

	public LetterTemplate() {/* Default constructor for hibernate */
	}

	public LetterTemplate(Long letterTemplateId, String name, byte[] template,  byte[] thumbnail, String contentType) {
		this.letterTemplateId = letterTemplateId;
		this.name = name;
		this.template = template;
		this.thumbnail = thumbnail;
		this.contentType = contentType;
	}

	public LetterTemplate(Long letterTemplateId, String name, byte[] template, byte[] thumbnail, String contentType,
			Set<MailType> mailTypes) {
		this.letterTemplateId = letterTemplateId;
		this.name = name;
		this.template = template;
		this.thumbnail = thumbnail;
		this.contentType = contentType;
		this.mailTypes = mailTypes;
	}

	public int compareTo(LetterTemplate o) {
		 return name.compareTo(o.name);
	}

	public Long getLetterTemplateId() {
		return letterTemplateId;
	}

	public void setLetterTemplateId(Long letterTemplateId) {
		this.letterTemplateId = letterTemplateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getTemplate() {
		return template;
	}

	public void setTemplate(byte[] template) {
		this.template = template;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Set<MailType> getMailTypes() {
		return this.mailTypes;
	}

	public void setMailTypes(Set<MailType> mailTypes) {
		this.mailTypes = mailTypes;
	}

}
