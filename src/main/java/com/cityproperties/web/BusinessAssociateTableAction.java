package com.cityproperties.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cityproperties.dao.BusinessAssociateDAO;
import com.cityproperties.domain.BusinessAssociate;
import com.cityproperties.util.Constants;
import com.opensymphony.xwork2.ActionSupport;

public class BusinessAssociateTableAction 
		extends ActionSupport 
		implements SessionAware {

	// Result List
	private List<BusinessAssociate> gridModel;

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
	private List<BusinessAssociate> businessAssociates;

	// DI via Spring
	BusinessAssociateDAO businessAssociateDao;

	@SuppressWarnings("unchecked")
	public String execute() {
		Object list = session.get(Constants.LETTER_CONTENTS);
		if (list != null) {
			businessAssociates = (List<BusinessAssociate>) list;
		} else {
			businessAssociates = businessAssociateDao.findAll();
		}

		if (getSord() != null && getSord().equalsIgnoreCase("asc")) {
			Collections.sort(businessAssociates);
		}
		if (getSord() != null && getSord().equalsIgnoreCase("desc")) {
			Collections.sort(businessAssociates, null);
			Collections.reverse(businessAssociates);
		}

		setRecord(businessAssociates.size());

		int to = (getRows() * getPage());
		int from = to - getRows();

		if (to > getRecord()) {
			to = getRecord();
		}

		if (loadonce) {
			setGridModel(businessAssociates);
		} else {
			if (searchString != null && searchOper != null) {
				int id = Integer.parseInt(searchString);
				
				if (searchOper.equalsIgnoreCase("eq")) {
					List<BusinessAssociate> cList = new ArrayList<BusinessAssociate>();
					cList.add(businessAssociateDao.find(id));
					setGridModel(cList);
				} else if (searchOper.equalsIgnoreCase("ne")) {
					setGridModel(businessAssociateDao.findNotById(businessAssociates, id, from, to));
				} else if (searchOper.equalsIgnoreCase("lt")) {
					setGridModel(businessAssociateDao.findLesserAsId(businessAssociates, id, from, to));
				} else if (searchOper.equalsIgnoreCase("gt")) {
					setGridModel(businessAssociateDao.findGreaterAsId(businessAssociates, id, from, to));
				}
			} else {
				setGridModel(businessAssociateDao.getLetterContentsByIds(businessAssociates, from, to));
			}
		}

		setTotal((int) Math.ceil((double) getRecord() / (double) getRows()));

		session.put(Constants.LETTER_CONTENTS, businessAssociates);

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

	public List<BusinessAssociate> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<BusinessAssociate> gridModel) {
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
    
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setBusinessAssociateDao(BusinessAssociateDAO businessAssociateDao) {
		this.businessAssociateDao = businessAssociateDao;
	}

}