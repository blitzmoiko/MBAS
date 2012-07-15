package com.cityproperties.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.dao.LetterContentDAO;
import com.cityproperties.domain.LetterContent;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;

public class LetterContentTableAction 
		extends ActionSupport 
		implements SessionAware {

	// Result List
	private List<LetterContent> gridModel;

	// Get how many rows we want to have into the grid - rowNum 
	// attribute in the grid
	private Integer rows = 0;

	// Get the requested page. By default grid sets this to 1.
	private Integer page = 0;

	// Your Total Pages
	private Integer total = 0;

	// All Record
	private Integer record = 0;

	// Sorting order - asc or desc
	private String sord;

	// Get index row - i.e. user click to sort.
	private String sidx;

	// Search Field
	private String searchField;

	// The Search String
	private String searchString;

	// He Search Operation ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
	private String searchOper;
	
	// Load once operator
	private boolean loadonce;
	
	// Session
	private Map<String, Object> session;
	private List<LetterContent> letterContents;

	// DI via Spring
	LetterContentDAO letterContentDao;

	@SuppressWarnings("unchecked")
	public String execute() {
		Object list = session.get(Constants.LETTER_CONTENTS);
		if (list != null) {
			letterContents = (List<LetterContent>) list;
		} else {
			letterContents = letterContentDao.findAll();
		}

		if (getSord() != null && getSord().equalsIgnoreCase("asc")) {
			Collections.sort(letterContents);
		}
		if (getSord() != null && getSord().equalsIgnoreCase("desc")) {
			Collections.sort(letterContents, null);
			Collections.reverse(letterContents);
		}

		setRecord(letterContents.size());

		int to = (getRows() * getPage());
		int from = to - getRows();

		if (to > getRecord()) {
			to = getRecord();
		}

		if (loadonce) {
			setGridModel(letterContents);
		} else {
			if (searchString != null && searchOper != null) {
				int id = Integer.parseInt(searchString);
				
				if (searchOper.equalsIgnoreCase("eq")) {
					List<LetterContent> cList = new ArrayList<LetterContent>();
					cList.add(letterContentDao.find(id));
					setGridModel(cList);
				} else if (searchOper.equalsIgnoreCase("ne")) {
					setGridModel(letterContentDao.findNotById(letterContents, id, from, to));
				} else if (searchOper.equalsIgnoreCase("lt")) {
					setGridModel(letterContentDao.findLesserAsId(letterContents, id, from, to));
				} else if (searchOper.equalsIgnoreCase("gt")) {
					setGridModel(letterContentDao.findGreaterAsId(letterContents, id, from, to));
				}
			} else {
				setGridModel(letterContentDao.getLetterContentsByIds(letterContents, from, to));
			}
		}

		setTotal((int) Math.ceil((double) getRecord() / (double) getRows()));

		session.put(Constants.LETTER_CONTENTS, letterContents);

		return SUCCESS;
	}

	public String getJSON() {
		return execute();
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRecord() {
		return record;
	}

	public void setRecord(Integer record) {
		this.record = record;
		if (this.record > 0 && this.rows > 0) {
			this.total = (int) Math.ceil((double) this.record
					/ (double) this.rows);
		} else {
			this.total = 0;
		}
	}

	public List<LetterContent> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<LetterContent> gridModel) {
		this.gridModel = gridModel;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	
    public void setSearchField(String searchField) {
    	this.searchField = searchField;
    }

    public void setSearchString(String searchString) {
    	this.searchString = searchString;
    }

    public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}
    
	public boolean isLoadonce() {
		return loadonce;
	}

	public void setLoadonce(boolean loadonce) {
		this.loadonce = loadonce;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setBusinessAssociateDao(LetterContentDAO letterContentDao) {
		this.letterContentDao = letterContentDao;
	}

}