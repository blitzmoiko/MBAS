package com.cityproperties.domain;

import java.io.Serializable;
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
@Table(name = "C_LETTER_CONTENT")
public class LetterContent implements Serializable {
	//TODO Generate Java Doc
	
	@Id
	@SequenceGenerator(name = "lcSeq", sequenceName="LETTER_CONTENT_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lcSeq")
	@Column(name = "L_CONTENT_ID")
	private Long letterContentId;
	
	@Column(name = "CONTENT")	
	private String content;
	
	@OneToMany(mappedBy = "letterContent")
	private Set<MailType> mailTypes = new HashSet<MailType>(0);

	public LetterContent() {/* Default constructor for hibernate */}

	public LetterContent(Long letterContentId, String content) {
		this.letterContentId = letterContentId;
		this.content = content;
	}

	public LetterContent(Long letterContentId, String content,
			Set<MailType> mailTypes) {
		this.letterContentId = letterContentId;
		this.content = content;
		this.mailTypes = mailTypes;
	}

	public Long getLetterContentId() {
		return letterContentId;
	}

	public void setLetterContentId(Long letterContentId) {
		this.letterContentId = letterContentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<MailType> getMailTypes() {
		return this.mailTypes;
	}

	public void setMailTypes(Set<MailType> mailTypes) {
		this.mailTypes = mailTypes;
	}

}
