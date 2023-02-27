package utils;
import components.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;

public class CreateEligibilityInfo {
	public static void main(String[] args) {
		EligibilityInfo info = new EligibilityInfo();
		info.setId("0007ABCD");
		info.setEffectiveFrom(LocalDate.of(122, 05, 01));
		info.setEffectiveTo(LocalDate.of(123, 05, 01));		
		
		Main main = new Main();
		try {
			main.writeEligibilityInfo(info, new BufferedWriter(new FileWriter("./EligibilityMembers.txt", true)));
		}catch (Exception err){
			err.printStackTrace();
		}
	}
}
