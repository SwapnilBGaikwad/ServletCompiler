import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class FrontEndCompiler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontEndCompiler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.sendRedirect("/OnlineCompiler/CodeNInput.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String program = request.getParameter("userName");
		String input = request.getParameter("input");
		FileWriter file = new FileWriter("Solution.java");
		file.write(program);
		file.close();
		FileWriter fileInput = new FileWriter("input.txt");
		fileInput.write(input);
		fileInput.close();
		//JOptionPane.showMessageDialog(null, "File read.");
		response.setContentType("text/html");
		try {
			Process prc = Runtime.getRuntime().exec("S:/p.bat");
			prc.waitFor();
			//JOptionPane.showMessageDialog(null, "Exeuted");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		String ans ="";
		char send[] = new char[10000];
		try (FileReader fileReader = new FileReader("result.txt")) {
			while (true) {
				int no = fileReader.read(send);
				if(no<0)
					break;
				ans += new String(send);
			}
		}
		catch (Exception e) {
			
		}
		PrintWriter out =  response.getWriter();
		out.print("<h2> Result : "+ans+"</h2>");
	}
}
