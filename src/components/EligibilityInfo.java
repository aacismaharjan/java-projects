package components;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class EligibilityInfo {

	private String	memberId;
	private LocalDate	effectiveFrom;
	private LocalDate	effectiveTo;
	
	SimpleDateFormat formatter;
	
	public EligibilityInfo() {
		this.formatter = new SimpleDateFormat("yyyy/MM/dd");
	}

	public String getId( ) {
		return memberId;
	}

	public void setId( String memberId ) {
		this.memberId = memberId;
	}

	public LocalDate getEffectiveFrom( ) {
		return effectiveFrom;
	}

	public void setEffectiveFrom( LocalDate effectiveFrom ) {
		this.effectiveFrom = effectiveFrom;
	}

	public LocalDate getEffectiveTo( ) {
		return effectiveTo;
	}

	public void setEffectiveTo( LocalDate effectiveTo ) {
		this.effectiveTo = effectiveTo;
	}
	
	public boolean isMembershipValid() {
		return this.effectiveTo.isAfter(LocalDate.now());
	}
	
	public String toFormattedString() {
		return String.format("%12s\t%25s\t%25s\n", memberId, formatter.format(effectiveFrom), formatter.format(effectiveTo));
	}
	
	public String toCSVString( ) {
		return String.format("%s,%s,%s\n", memberId, formatter.format(effectiveFrom), formatter.format(effectiveTo));
	}
}
