package com.cityproperties.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.dao.LetterTemplateDAO;
import com.cityproperties.domain.LetterTemplate;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;

public class LetterTemplateTableAction 
		extends ActionSupport 
		implements SessionAware {

	// Result List
	private List<LetterTemplate> gridModel;

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
	private List<LetterTemplate> letterTemplates;

	// DI via Spring
	LetterTemplateDAO letterTemplateDao;

	@SuppressWarnings("unchecked")
	public String execute() {
		Object list = session.get(Constants.LETTER_CONTENTS);
		if (list != null) {
			letterTemplates = (List<LetterTemplate>) list;
		} else {
			letterTemplates = letterTemplateDao.findAll();
		}

		if (getSord() != null && getSord().equalsIgnoreCase("asc")) {
			Collections.sort(letterTemplates);
		}
		if (getSord() != null && getSord().equalsIgnoreCase("desc")) {
			Collections.sort(letterTemplates, null);
			Collections.reverse(letterTemplates);
		}

		setRecord(letterTemplates.size());

		int to = (getRows() * getPage());
		int from = to - getRows();

		if (to > getRecord()) {
			to = getRecord();
		}

		if (loadonce) {
			setGridModel(letterTemplates);
		} else {
			if (searchString != null && searchOper != null) {
				int id = Integer.parseInt(searchString);
				
				if (searchOper.equalsIgnoreCase("eq")) {
					List<LetterTemplate> cList = new ArrayList<LetterTemplate>();
					cList.add(letterTemplateDao.find(id));
					setGridModel(cList);
				} else if (searchOper.equalsIgnoreCase("ne")) {
					setGridModel(letterTemplateDao.findNotById(letterTemplates, id, from, to));
				} else if (searchOper.equalsIgnoreCase("lt")) {
					setGridModel(letterTemplateDao.findLesserAsId(letterTemplates, id, from, to));
				} else if (searchOper.equalsIgnoreCase("gt")) {
					setGridModel(letterTemplateDao.findGreaterAsId(letterTemplates, id, from, to));
				}
			} else {
				setGridModel(letterTemplateDao.getLetterTemplatesByIds(letterTemplates, from, to));
			}
		}

		setTotal((int) Math.ceil((double) getRecord() / (double) getRows()));

		session.put(Constants.LETTER_CONTENTS, letterTemplates);

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

	public List<LetterTemplate> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<LetterTemplate> gridModel) {
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

	public void setBusinessAssociateDao(LetterTemplateDAO letterTemplateDao) {
		this.letterTemplateDao = letterTemplateDao;
	}

}