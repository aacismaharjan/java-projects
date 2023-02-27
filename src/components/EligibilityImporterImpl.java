package components;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class EligibilityImporterImpl implements EligibilityImporter {

	protected static DateFormat	DATE_FORMAT		= new SimpleDateFormat( "yyyy-MM-dd" );
	protected static String PIPE_DELIMITER	= "\\|";

	@Override
	public ArrayList<EligibilityInfo> importEligibilities(File eligibilityFile ) throws IOException {
		ArrayList<EligibilityInfo> eligibilityInfos = new ArrayList<EligibilityInfo>(); 
		BufferedReader reader = new BufferedReader(new FileReader(eligibilityFile));
		
		String line = reader.readLine();
		String[] record;
		
		while(line != null && line != "") {
			record = line.split("\t");
			if(record.length == 0) break;
			
			EligibilityInfo info = new EligibilityInfo();
			info.setId(record[0].trim());
			info.setEffectiveFrom(LocalDate.parse(record[1].replace("/", "-").trim()));
			info.setEffectiveTo(LocalDate.parse(record[2].replace("/", "-").trim()));;
			
			line = reader.readLine();
			eligibilityInfos.add(info);
		}
		reader.close();
		return eligibilityInfos;
	}

}
