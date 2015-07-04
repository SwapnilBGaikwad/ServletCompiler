import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

public class ProgramReader {
	HttpServletRequest request; 
	int programLang;

	public ProgramReader(HttpServletRequest request) {
		this.request = request;
		programLang = 0;
	}

	public void readProgram() throws IOException , NullPointerException{
		programLang = 1;
		if(programLang == 1) {
			readJavaProgram();
		}
		else {
			JOptionPane.showMessageDialog(null, "Exit");
		}
	}

	private void readJavaProgram() throws IOException {
		String program = request.getParameter("program");
		FileOutputStream programFile = new FileOutputStream("Solution.java");
		programFile.write(program.getBytes());
		programFile.close();
	}

	public void readInput() throws IOException {
		String input = request.getParameter("input");
		FileOutputStream inputFile = new FileOutputStream("input.in");
		inputFile.write(input.getBytes());
		inputFile.close();
	}

	public int getProgramLang() {
		return programLang;
	}

}
