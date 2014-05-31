import java.text.DateFormat;
import java.util.*;
/**
 * This class is an auxiliary class which defines some functions related to the operation
 * of time.
 * @author royal (luoyi.zxx@gmail.com)
 */
public class DateTest {
	final static int MAXDAY = 30;
	private String[] dateTermList = new String[MAXDAY];
	private Calendar[] dateList = new Calendar[MAXDAY];
	private Calendar currentDate = new GregorianCalendar();
	private DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,Locale.US);
	//Date of 2011-XX-XX form
	private String[] newDateForm = new String[MAXDAY];
/**
 * The default constructor
 */
	public DateTest(){
		for(int i=0;i<MAXDAY;i++){
			dateList[i] = new GregorianCalendar();
			dateList[i].set(Calendar.DAY_OF_YEAR,currentDate.get(Calendar.DAY_OF_YEAR)+i+1);
			dateTermList[i] = df.format(dateList[i].getTime());
		}
	}
/**
 * To acquire a list of date in the form of X/X/XX or XX/XX/XX.
 * @return a list of date no more than the defined boundary value MAXDAY
 */
	public String[] getDateTermList() {
		return dateTermList;
	}
/**
 * Set method used to define dateTermList
 * @param dateTermList The list of date term defined as dateTermList
 */
	public void setDateTermList(String[] dateTermList) {
		this.dateTermList = dateTermList;
	}
/**
 * It changes the date form to 20XX/XX/XX
 * @return the list of date in the form of 20XX/XX/XX
 */
	public String[] changeDateForm(){
		for(int i=0;i<MAXDAY;i++){

			if(dateTermList[i].length()==6){  //  X/X/XX
				newDateForm[i] = "20"+ dateTermList[i].charAt(4)+ dateTermList[i].charAt(5)+"-0"+ dateTermList[i].charAt(0)+"-0"+ dateTermList[i].charAt(2);
			}
			else if(dateTermList[i].length()==7){   //  X/XX/XX OR XX/X/XX
				if(dateTermList[i].charAt(1)=='/'){  // X/XX/XX
					newDateForm[i] = "20"+ dateTermList[i].charAt(5)+ dateTermList[i].charAt(6)+"-0"+ dateTermList[i].charAt(0)+"-"+ dateTermList[i].charAt(2)+ dateTermList[i].charAt(3);
				}
				else{   // XX/X/XX
					newDateForm[i] = "20"+ dateTermList[i].charAt(5)+ dateTermList[i].charAt(6)+"-"+ dateTermList[i].charAt(0)+ dateTermList[i].charAt(1)+"-0"+ dateTermList[i].charAt(3);
				}
			}
			else{   // XX/XX/XX
				newDateForm[i] = "20"+ dateTermList[i].charAt(6)+ dateTermList[i].charAt(7)+"-"+ dateTermList[i].charAt(0)+ dateTermList[i].charAt(1)+"-"+ dateTermList[i].charAt(3)+ dateTermList[i].charAt(4);
			}
		}
		return newDateForm;
	}
}
