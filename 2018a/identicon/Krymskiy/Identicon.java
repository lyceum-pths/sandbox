import java.io.*;
import java.security.*;
import java.util.*;


public class Identicon {
	public static String genIdenticon(String s) {
		byte[] bite = new byte[2*s.length()];
		for(int i = 0; i < s.length(); i++){
			bite[2*i] = (byte) ((int)(s.charAt(i))/256);
			bite[2*i+1] = (byte) ((int)(s.charAt(i))%256);
			
		}
		Random genByName = new SecureRandom(bite);
		
		
		int wide = 400;
		int high = 400;
		String result = "";
		//print the initialisation
		result+=(" <svg width = \""+wide+"\" height=\""+high +"\">");
			//<polygon 
			//points="60,25 140,25 160,60 80,60  " 
			//style="fill: yellow; fill-opacity:0.5; stroke: black;" /> 
			/*
			 * <html>
<body>

<h1>My first SVG</h1>

<svg width="100" height="100">
			 */
			int nov = genByName.nextInt(30)+4;
			result+=(" <polygon points = ");
			result+=" \"";
			for(int j = 0; j < nov; j++){
				result+=(genByName.nextInt(wide)+","+genByName.nextInt(high)+" ");
			}
			result+=(" \"");
			String full = genByName.nextBoolean() ? "nonzero" : "evenodd" ;
			int resclr = genByName.nextInt(512);
			String repres = Integer.toOctalString(resclr);
			resclr = 2*Integer.parseInt(repres,16);
			repres = Integer.toHexString(resclr);
			while(repres.length()<3) {
				repres = '0'+repres;
			}
			result+=(" style=\"fill-rule:"+full+"; fill: #"+repres.charAt(0)+'0'+repres.charAt(1)+'0'+repres.charAt(2)+'0'+"; fill-opacity:1; stroke: black;\" />");
		
		result+=(" </svg>");
		return result;
	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("name.txt"));
		PrintWriter out = new PrintWriter(new File("output.html"));
		out.println("<html><body>");
		out.println("<h1>The identicon</h1>");
		while(in.hasNextLine()) {
			String s = in.nextLine();
			out.println(genIdenticon(s));
		}
		in.close();
		
		//print the initialisation
		
		
		out.println("</body></html>");
		
		out.close();

	}

}
