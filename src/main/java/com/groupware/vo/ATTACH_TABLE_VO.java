package com.groupware.vo;

public class ATTACH_TABLE_VO {
	String ATTACH_PK;
	String ATTACH_LIST_PK;
	String ATTACH_SIZE;
	String ATTACH_NAME;
	String ATTACH_ROUTE;

	public String getATTACH_PK() {
		return ATTACH_PK;
	}

	public void setATTACH_PK(String aTTACH_PK) {
		ATTACH_PK = aTTACH_PK;
	}

	public String getATTACH_LIST_PK() {
		return ATTACH_LIST_PK;
	}

	public void setATTACH_LIST_PK(String aTTACH_LIST_PK) {
		ATTACH_LIST_PK = aTTACH_LIST_PK;
	}

	public String getATTACH_SIZE() {
		return ATTACH_SIZE;
	}

	public void setATTACH_SIZE(String aTTACH_SIZE) {
		ATTACH_SIZE = aTTACH_SIZE;
	}

	public String getATTACH_NAME() {
		return ATTACH_NAME;
	}

	public void setATTACH_NAME(String aTTACH_NAME) {
		ATTACH_NAME = aTTACH_NAME;
	}

	public String getATTACH_ROUTE() {
		return ATTACH_ROUTE;
	}

	public void setATTACH_ROUTE(String aTTACH_ROUTE) {
		ATTACH_ROUTE = aTTACH_ROUTE;
	}

	@Override
	public String toString() {
		return "ATTACH_TABLE_VO [ATTACH_PK=" + ATTACH_PK + ", ATTACH_LIST_PK=" + ATTACH_LIST_PK + ", ATTACH_SIZE="
				+ ATTACH_SIZE + ", ATTACH_NAME=" + ATTACH_NAME + ", ATTACH_ROUTE=" + ATTACH_ROUTE + "]";
	}

}
