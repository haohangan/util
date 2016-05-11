package dc.cininput.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

public class CwCheckAccountVO implements Serializable {

	private String CCA_ID;

	private String CCA_CINEMA_CODE;

	private String CCA_FILM_CODE;

	private String CCM_MONTH;

	private String CCA_RCRE_USER_ID;

	private Date CCA_RCRE_DATE;

	private String LOC_NAME;

	private String FLM_FILM_TITLE;

	private Date CCA_START_DATE;

	private Date CCA_END_DATE;

	private String START_DATE;

	private String END_DATE;

	private int CCA_SHOWS;

	private int CCA_AUDIENCEQUANTITY;

	private double CCA_EARN;

	private String CCM_SUBMIT_FLAG;

	private Double CCA_EARN_LV;

	private Double CCA_WENT;

	private Double FILM_FUNDS_PAYABLE;

	private Double CCA_MGR_WENT;
	private Double CCA_MGR_PAY;

	private Double CCA_PAY;

	private String[] START_DATE_S;

	private String[] END_DATE_S;

	private int[] CCA_SHOWS_S;

	private int[] CCA_AUDIENCEQUANTITY_S;

	private double[] CCA_EARN_S;

	public String getCCA_ID() {
		return CCA_ID;
	}

	public void setCCA_ID(String cCA_ID) {
		CCA_ID = cCA_ID;
	}

	public String getCCA_CINEMA_CODE() {
		return CCA_CINEMA_CODE;
	}

	public void setCCA_CINEMA_CODE(String cCA_CINEMA_CODE) {
		CCA_CINEMA_CODE = cCA_CINEMA_CODE;
	}

	public String getCCA_FILM_CODE() {
		return CCA_FILM_CODE;
	}

	public void setCCA_FILM_CODE(String cCA_FILM_CODE) {
		CCA_FILM_CODE = cCA_FILM_CODE;
	}

	public String getCCM_MONTH() {
		return CCM_MONTH;
	}

	public void setCCM_MONTH(String cCM_MONTH) {
		CCM_MONTH = cCM_MONTH;
	}

	public String getLOC_NAME() {
		return LOC_NAME;
	}

	public void setLOC_NAME(String lOC_NAME) {
		LOC_NAME = lOC_NAME;
	}

	public String getFLM_FILM_TITLE() {
		return FLM_FILM_TITLE;
	}

	public void setFLM_FILM_TITLE(String fLM_FILM_TITLE) {
		FLM_FILM_TITLE = fLM_FILM_TITLE;
	}

	public Date getCCA_START_DATE() {
		return CCA_START_DATE;
	}

	public void setCCA_START_DATE(Date cCA_START_DATE) {
		CCA_START_DATE = cCA_START_DATE;
	}

	public Date getCCA_END_DATE() {
		return CCA_END_DATE;
	}

	public void setCCA_END_DATE(Date cCA_END_DATE) {
		CCA_END_DATE = cCA_END_DATE;
	}

	public int getCCA_SHOWS() {
		return CCA_SHOWS;
	}

	public void setCCA_SHOWS(int cCA_SHOWS) {
		CCA_SHOWS = cCA_SHOWS;
	}

	public String getCCA_RCRE_USER_ID() {
		return CCA_RCRE_USER_ID;
	}

	public void setCCA_RCRE_USER_ID(String cCA_RCRE_USER_ID) {
		CCA_RCRE_USER_ID = cCA_RCRE_USER_ID;
	}

	public Date getCCA_RCRE_DATE() {
		return CCA_RCRE_DATE;
	}

	public void setCCA_RCRE_DATE(Date cCA_RCRE_DATE) {
		CCA_RCRE_DATE = cCA_RCRE_DATE;
	}

	public int getCCA_AUDIENCEQUANTITY() {
		return CCA_AUDIENCEQUANTITY;
	}

	public void setCCA_AUDIENCEQUANTITY(int cCA_AUDIENCEQUANTITY) {
		CCA_AUDIENCEQUANTITY = cCA_AUDIENCEQUANTITY;
	}

	public double getCCA_EARN() {
		return CCA_EARN;
	}

	public void setCCA_EARN(double cCA_EARN) {
		CCA_EARN = cCA_EARN;
	}

	public String getCCM_SUBMIT_FLAG() {
		return CCM_SUBMIT_FLAG;
	}

	public void setCCM_SUBMIT_FLAG(String cCM_SUBMIT_FLAG) {
		CCM_SUBMIT_FLAG = cCM_SUBMIT_FLAG;
	}

	public Double getCCA_EARN_LV() {
		return CCA_EARN_LV;
	}

	public void setCCA_EARN_LV(Double cCA_EARN_LV) {
		CCA_EARN_LV = cCA_EARN_LV;
	}

	public Double getCCA_WENT() {
		return CCA_WENT;
	}

	public void setCCA_WENT(Double cCA_WENT) {
		CCA_WENT = cCA_WENT;
	}

	public Double getFILM_FUNDS_PAYABLE() {
		return FILM_FUNDS_PAYABLE;
	}

	public void setFILM_FUNDS_PAYABLE(Double fILM_FUNDS_PAYABLE) {
		FILM_FUNDS_PAYABLE = fILM_FUNDS_PAYABLE;
	}

	public String getSTART_DATE() {
		return START_DATE;
	}

	public void setSTART_DATE(String sTART_DATE) {
		START_DATE = sTART_DATE;
	}

	public String getEND_DATE() {
		return END_DATE;
	}

	public void setEND_DATE(String eND_DATE) {
		END_DATE = eND_DATE;
	}

	public Double getCCA_MGR_WENT() {
		return CCA_MGR_WENT;
	}

	public void setCCA_MGR_WENT(Double cCA_MGR_WENT) {
		CCA_MGR_WENT = cCA_MGR_WENT;
	}

	public Double getCCA_MGR_PAY() {
		return CCA_MGR_PAY;
	}

	public void setCCA_MGR_PAY(Double cCA_MGR_PAY) {
		CCA_MGR_PAY = cCA_MGR_PAY;
	}

	public Double getCCA_PAY() {
		return CCA_PAY;
	}

	public void setCCA_PAY(Double cCA_PAY) {
		CCA_PAY = cCA_PAY;
	}

	public String[] getSTART_DATE_S() {
		return START_DATE_S;
	}

	public void setSTART_DATE_S(String[] sTART_DATE_S) {
		START_DATE_S = sTART_DATE_S;
	}

	public String[] getEND_DATE_S() {
		return END_DATE_S;
	}

	public void setEND_DATE_S(String[] eND_DATE_S) {
		END_DATE_S = eND_DATE_S;
	}

	public int[] getCCA_SHOWS_S() {
		return CCA_SHOWS_S;
	}

	public void setCCA_SHOWS_S(int[] cCA_SHOWS_S) {
		CCA_SHOWS_S = cCA_SHOWS_S;
	}

	public int[] getCCA_AUDIENCEQUANTITY_S() {
		return CCA_AUDIENCEQUANTITY_S;
	}

	public void setCCA_AUDIENCEQUANTITY_S(int[] cCA_AUDIENCEQUANTITY_S) {
		CCA_AUDIENCEQUANTITY_S = cCA_AUDIENCEQUANTITY_S;
	}

	public double[] getCCA_EARN_S() {
		return CCA_EARN_S;
	}

	public void setCCA_EARN_S(double[] cCA_EARN_S) {
		CCA_EARN_S = cCA_EARN_S;
	}

	@Override
	public int hashCode() {
		return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
				.append(CCA_CINEMA_CODE).append(CCA_FILM_CODE)
				.append(CCA_START_DATE).append(CCA_END_DATE).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		CwCheckAccountVO rhs = (CwCheckAccountVO) obj;
		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(CCA_CINEMA_CODE, rhs.getCCA_CINEMA_CODE())
				.append(CCA_FILM_CODE, rhs.getCCA_FILM_CODE())
				.append(CCA_START_DATE, rhs.getCCA_START_DATE())
				.append(CCA_END_DATE, rhs.getCCA_END_DATE()).isEquals();
	}

	@Override
	public String toString() {
		return "CwCheckAccountVO [CCA_CINEMA_CODE=" + CCA_CINEMA_CODE
				+ ", CCA_FILM_CODE=" + CCA_FILM_CODE + ", CCA_START_DATE="
				+ CCA_START_DATE + ", CCA_END_DATE=" + CCA_END_DATE + "]";
	}

	public static void main(String[] args) {
		Set<CwCheckAccountVO> set = new HashSet<CwCheckAccountVO>();
		Date date1 = new Date();

		CwCheckAccountVO vo1 = new CwCheckAccountVO();
		vo1.setCCA_CINEMA_CODE("C1");
		vo1.setCCA_FILM_CODE("F1");
		vo1.setCCA_START_DATE(date1);
		vo1.setCCA_END_DATE(date1);

		CwCheckAccountVO vo2 = new CwCheckAccountVO();
		vo2.setCCA_CINEMA_CODE("C1");
		vo2.setCCA_FILM_CODE("F2");
		vo2.setCCA_START_DATE(date1);
		vo2.setCCA_END_DATE(date1);
		// System.out.println(vo1.hashCode());
		// System.out.println(vo1.hashCode());
		// System.out.println(vo2.hashCode());

		// set.add(vo2);
		// set.add(vo2);
		// System.out.println(set.size());

		List<CwCheckAccountVO> list = new ArrayList<CwCheckAccountVO>();
		list.add(vo1);
		list.add(vo2);
		list.add(vo1);

		for (CwCheckAccountVO vo : list) {
			System.out.println(vo);
		}
		System.out.println("================================");
		sort(list);
		for (CwCheckAccountVO vo : list) {
			System.out.println(vo);
		}
	}

	/**
	 * 测试
	 * 
	 * @param list
	 */
	private static void sort(List<CwCheckAccountVO> list) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		Comparator mycmp = ComparableComparator.getInstance();
//		mycmp = ComparatorUtils.nullLowComparator(mycmp);// 允许null
		mycmp = ComparatorUtils.reversedComparator(mycmp);// 逆序
		ArrayList<BeanComparator> sortFields = new ArrayList<BeanComparator>();
		sortFields.add(new BeanComparator("CCA_FILM_CODE", mycmp));
		sortFields.add(new BeanComparator("CCA_START_DATE"));
		sortFields.add(new BeanComparator("CCA_END_DATE"));
		ComparatorChain chain = new ComparatorChain(sortFields);

		Collections.sort(list, chain);
	}
}
