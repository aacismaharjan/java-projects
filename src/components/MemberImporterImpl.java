package components;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MemberImporterImpl implements MemberImporter {
	public ArrayList< Member > importMembers( File inputFile ) throws IOException {
		ArrayList<Member> members = new ArrayList<Member>(); 
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		
		String line = reader.readLine();
		String[] record;
		
		while(line != null && line.trim() != "") {
			record = line.split("\t");
			
			Member m = new Member();
			m.setId(record[0].trim());
			m.setLastName(record[1].trim());
			m.setFirstName(record[2].trim());
			m.setAddress(record[3].trim());
			m.setCity(record[4].trim());
			m.setState(record[5].trim());
			m.setZip(record[6].trim());
			
			line = reader.readLine();
			members.add(m);
		}
		reader.close();
		return members;
	}

}
