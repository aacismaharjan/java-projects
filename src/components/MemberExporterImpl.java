package components;
import java.io.IOException;
import java.io.Writer;

public class MemberExporterImpl implements MemberExporter {
	public void writeMember( Member member, Writer writer ) throws IOException {
		writer.write(member.toCSVString( ));
	}
}
