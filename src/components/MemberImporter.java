package components;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface MemberImporter {
	public ArrayList< Member > importMembers( File inputFile ) throws Exception;
}
