package components;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface EligibilityImporter {
	public ArrayList< EligibilityInfo > importEligibilities( File eligibilityFile ) throws IOException;
}
