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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "C_LETTER_CONTENT")
public class LetterContent 
		implements Serializable, Comparable<LetterContent> {
	
	@Id
	@SequenceGenerator(name = "lcSeq", sequenceName="LETTER_CONTENT_SEQUENCE", allocationSize = 1, initialValue= 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lcSeq")
	@Column(name = "L_CONTENT_ID")
	private Long letterContentId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CONTENT")	
	private String content;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "letterContent")
	private Set<MailType> mailTypes = new HashSet<MailType>(0);

	public LetterContent() {/* Default constructor for hibernate */}

	public LetterContent(Long letterContentId, String name, String content) {
		this.letterContentId = letterContentId;
		this.name = name;
		this.content = content;
	}

	public LetterContent(Long letterContentId, String content,
			String name, Set<MailType> mailTypes) {
		this.letterContentId = letterContentId;
		this.name = name;
		this.content = content;
		this.mailTypes = mailTypes;
	}
	
	public int compareTo(LetterContent o) {
		 return name.compareTo(o.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
