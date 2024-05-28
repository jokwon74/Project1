package GUI;

import java.time.LocalDateTime;

public class EmployeeVO {
	
	private int EMP_ID        ;
	private String EMP_NAME      ;
	private String EMP_BIRTH	 ;
	private String EMAIL         ;
	private String PHONE         ;
	private String DEPT_CODE     ;
	private String JOB_CODE      ;
	private String HIRE_DATE     ;
	

	
	public int getEMP_ID() {
		return EMP_ID;
	}
	public void setEMP_ID(int eMP_ID) {
		EMP_ID = eMP_ID;
	}
	public String getEMP_NAME() {
		return EMP_NAME;
	}
	public void setEMP_NAME(String eMP_NAME) {
		EMP_NAME = eMP_NAME;
	}
	public String getEMP_BIRTH() {
		return EMP_BIRTH;
	}
	public void setEMP_BIRTH(String eMP_BIRTH) {
		EMP_BIRTH = eMP_BIRTH;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getDEPT_CODE() {
		return DEPT_CODE;
	}
	public void setDEPT_CODE(String dEPT_CODE) {
		DEPT_CODE = dEPT_CODE;
	}
	public String getJOB_CODE() {
		return JOB_CODE;
	}
	public void setJOB_CODE(String jOB_CODE) {
		JOB_CODE = jOB_CODE;
	}

	public String getHIRE_DATE() {
		return HIRE_DATE;
	}
	public void setHIRE_DATE(String hIRE_DATE) {
		HIRE_DATE = hIRE_DATE;
	}
	public EmployeeVO() {
			
	}
	public EmployeeVO(int eMP_ID, String eMP_NAME, String eMP_BIRTH, String eMAIL, String pHONE, String dEPT_CODE,
			String jOB_CODE, String hIRE_DATE) {
		EMP_ID = eMP_ID;
		EMP_NAME = eMP_NAME;
		EMP_BIRTH = eMP_BIRTH;
		EMAIL = eMAIL;
		PHONE = pHONE;
		DEPT_CODE = dEPT_CODE;
		JOB_CODE = jOB_CODE;
		HIRE_DATE = hIRE_DATE;
	}
	@Override
	public String toString() {
		return "EmployeeVO [EMP_ID=" + EMP_ID + ", EMP_NAME=" + EMP_NAME + ", EMP_BIRTH=" + EMP_BIRTH + ", EMAIL="
				+ EMAIL + ", PHONE=" + PHONE + ", DEPT_CODE=" + DEPT_CODE + ", JOB_CODE=" + JOB_CODE + ", HIRE_DATE="
				+ HIRE_DATE + "]";
	}

	
	
	
	
}
	

