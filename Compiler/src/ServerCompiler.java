import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;


public class ServerCompiler {

	public String execute() {
		String output = "";
		Process prc1;
		try {
			prc1 = Runtime.getRuntime().exec("S:\\java.bat");
			prc1.waitFor();
			
			DataInputStream reader = new DataInputStream(new FileInputStream("result.out"));
			while(true) {
				@SuppressWarnings("deprecation")
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				else {
					output += line;
				}
			}
			reader.close();
		} catch (IOException | InterruptedException e) {
			output = "";
		}
		
		return output;
	}

	public String compile(int programLang) {
		if(programLang == 1) {
			JOptionPane.showMessageDialog(null, "Compiling");
			try {
				return  javaCompile();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	private String javaCompile() throws IOException, InterruptedException {
		
		Process prc = Runtime.getRuntime().exec("S:\\Compile.bat");
		prc.waitFor();
		
		File compileResult = new File("CompilerOutput.out");
		if(compileResult.length() > 0) {
			String compile = "";
							
			DataInputStream error = new DataInputStream(new FileInputStream("CompileOutput.out"));
			try   {
					while (true) {
						@SuppressWarnings("deprecation")
						String temp = error.readLine() ;
						if (temp != null )
								temp +=  "<br>";
						else 
							break;
						compile  +=  temp;
					}
					
			}
			catch (EOFException  e) {
					error.close();
			}
			return compile;
		}
		else {
			return null;
		}

	}
}
