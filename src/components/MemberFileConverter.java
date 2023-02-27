package components;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MemberFileConverter {
	/*
	 * NOTE: Do not modify this class
	 */
	protected abstract MemberExporterImpl getMemberExporter( );

	protected abstract MemberImporterImpl getMemberImporter( );

	protected abstract EligibilityImporterImpl getEligibilityImporter( );

	protected abstract ArrayList< Member > getValidMembersToExport( ArrayList< Member > members, ArrayList< EligibilityInfo > eligibilityInfo );

	protected abstract HashMap< String, ArrayList< Member >> splitMembersByState( ArrayList< Member > validMembers );

	protected abstract File getExportFile( String outputFilePath, String state, String outputFileName );

	public void convert( File inputMemberFile, File inputEligibilityFile, String outputFilePath, String outputFileName ) throws Exception {

		/*
		 * Read :
		 */
		MemberImporter memberImporter = getMemberImporter( );
		ArrayList< Member > membersFromFile = memberImporter.importMembers( inputMemberFile );

		EligibilityImporter eligibilityImporter = getEligibilityImporter( );
		ArrayList< EligibilityInfo > eligibilityInfo = eligibilityImporter.importEligibilities( inputEligibilityFile );

		/*
		 * Filter :
		 */

		ArrayList< Member > validMembers = getValidMembersToExport( membersFromFile, eligibilityInfo );
		HashMap< String, ArrayList< Member >> membersFilteredByState = splitMembersByState( validMembers );

		/*
		 * Export :
		 */

		MemberExporterImpl exporter = getMemberExporter( );
		for ( HashMap.Entry< String, ArrayList< Member >> map: membersFilteredByState.entrySet( ) ) {
			String state = map.getKey();
			ArrayList< Member > membersPerState = map.getValue( );

			File outputFile = getExportFile( outputFilePath, state, outputFileName );
			writeValidMembers( outputFile, exporter, membersPerState );
		}

	}

	private void writeValidMembers( File outputFile, MemberExporterImpl exporter, ArrayList< Member > validMembers ) throws IOException {
		Writer writer = new FileWriter( outputFile );

		for ( Member member: validMembers ) {
			exporter.writeMember( member, writer );
		}

		writer.flush( );
		writer.close( );
	}

}
