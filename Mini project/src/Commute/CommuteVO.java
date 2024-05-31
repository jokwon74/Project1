package Commute;

import java.util.Date;

public class CommuteVO {
	
    private int EMP_ID;
    private Date STARTTIME;
    private Date ENDTIME;
    private boolean BUSINESS_TRIP;
    private boolean LEAVE;
    
	public CommuteVO() {}
    
	public CommuteVO(int eMP_ID, Date sTARTTIME, Date eNDTIME, boolean bUSINESS_TRIP, boolean lEAVE) {
		EMP_ID = eMP_ID;
		STARTTIME = sTARTTIME;
		ENDTIME = eNDTIME;
		BUSINESS_TRIP = bUSINESS_TRIP;
		LEAVE = lEAVE;
	}

	public int getEMP_ID() {
		return EMP_ID;
	}

	public void setEMP_ID(int eMP_ID) {
		EMP_ID = eMP_ID;
	}

	public Date getSTARTTIME() {
		return STARTTIME;
	}

	public void setSTARTTIME(Date sTARTTIME) {
		STARTTIME = sTARTTIME;
	}

	public Date getENDTIME() {
		return ENDTIME;
	}

	public void setENDTIME(Date eNDTIME) {
		ENDTIME = eNDTIME;
	}

	public boolean getBUSINESS_TRIP() {
		return BUSINESS_TRIP;
	}

	public void setBUSINESS_TRIP(boolean bUSINESS_TRIP) {
		BUSINESS_TRIP = bUSINESS_TRIP;
	}

	public boolean getLEAVE() {
		return LEAVE;
	}

	public void setLEAVE(boolean lEAVE) {
		LEAVE = lEAVE;
	}
	
	
    
    
    

}
