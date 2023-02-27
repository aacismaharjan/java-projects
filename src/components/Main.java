package components;

import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class Main extends MemberFileConverter {

	protected MemberExporterImpl getMemberExporter( ) {
		return new MemberExporterImpl();
	}

	protected MemberImporterImpl getMemberImporter( ) {
		return new MemberImporterImpl();
	}

	protected EligibilityImporterImpl getEligibilityImporter( ) {
		return new EligibilityImporterImpl();
	}

	protected ArrayList< Member > getValidMembersToExport( ArrayList< Member > members, ArrayList< EligibilityInfo > eligibilityInfo ) {
		ArrayList<Member> membersTemp = new ArrayList<Member>();
		
		for(Member member: members) {
			for(EligibilityInfo info: eligibilityInfo) {
				if(info.getId().equals(member.getId()) && info.isMembershipValid()) {
					membersTemp.add(member);
				}
			}
		}
		
		return membersTemp;
	}

	protected HashMap< String, ArrayList< Member >> splitMembersByState( ArrayList< Member > validMembers ) {
		HashMap<String, ArrayList<Member>> members = new HashMap<String, ArrayList<Member>>();
		
		for(Member member: validMembers) {
			if(members.containsKey(member.getState())) {
				ArrayList<Member> previousMembersOfState = members.get(member.getState());
				previousMembersOfState.add(member);
				
				members.put(member.getState(), previousMembersOfState);
			}else {
				ArrayList<Member> newMembersOfState = new ArrayList<Member>();
				newMembersOfState.add(member);
				members.put(member.getState(), newMembersOfState);
			}
		}
		
		return members;
	}

	protected File getExportFile( String outputFilePath, String state, String outputFileName ) {
		String fileName = state +  outputFileName + ".csv";
		String fullPath = outputFilePath.trim() + fileName.trim();
		File file = new File(fullPath);
		System.out.println(fullPath);
		return file;
	}
	
	public void writeMember(Member member, BufferedWriter writer) throws IOException {
		writer.write(member.toFormattedString());
		writer.close();
	}
	
	public void writeEligibilityInfo(EligibilityInfo info, BufferedWriter writer) throws IOException {
		writer.write(info.toFormattedString());
		writer.close();
	}
	
	public static void main(String[] args) {		
		Main m = new Main();
		
		try {
			m.convert(new File("./members.txt"), new File("./EligibilityMembers.txt"), "./", "_outputFile");
		}catch(Exception err) {
			err.printStackTrace();
		}
	}
}
