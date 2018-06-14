package com.groupware.vo;

public class ATTACH_LIST_TABLE_VO {
	String ATTACH_LIST_PK;
	String MAIL_PK;
	String ATTACH_SUM_SIZE;
	String ATTACH_SUM_COUNT;

	public String getATTACH_LIST_PK() {
		return ATTACH_LIST_PK;
	}

	public void setATTACH_LIST_PK(String aTTACH_LIST_PK) {
		ATTACH_LIST_PK = aTTACH_LIST_PK;
	}

	public String getMAIL_PK() {
		return MAIL_PK;
	}

	public void setMAIL_PK(String mAIL_PK) {
		MAIL_PK = mAIL_PK;
	}

	public String getATTACH_SUM_SIZE() {
		return ATTACH_SUM_SIZE;
	}

	public void setATTACH_SUM_SIZE(String aTTACH_SUM_SIZE) {
		ATTACH_SUM_SIZE = aTTACH_SUM_SIZE;
	}

	public String getATTACH_SUM_COUNT() {
		return ATTACH_SUM_COUNT;
	}

	public void setATTACH_SUM_COUNT(String aTTACH_SUM_COUNT) {
		ATTACH_SUM_COUNT = aTTACH_SUM_COUNT;
	}

	@Override
	public String toString() {
		return "ATTACH_LIST_TABLE_VO [ATTACH_LIST_PK=" + ATTACH_LIST_PK + ", MAIL_PK=" + MAIL_PK + ", ATTACH_SUM_SIZE="
				+ ATTACH_SUM_SIZE + ", ATTACH_SUM_COUNT=" + ATTACH_SUM_COUNT + "]";
	}

}
