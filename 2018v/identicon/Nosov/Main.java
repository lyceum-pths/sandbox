import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static String randomString(){
		String symbols = "qwerty";
        StringBuilder randString = new StringBuilder();
        int count = (int)(Math.random()*100 + 1);
        for(int i=0;i<count;i++){
          randString.append(symbols.charAt((int)(Math.random()*symbols.length())));
        }
        return(randString.toString());
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("stdout.svg")));
		for(int j = 0; j < 1; j++){
		String s = args[0];
		int p = 1;
		int sum = 0;
		for(int i = 0; i < s.length(); i++){
			if(Character.getNumericValue(s.charAt(i)) > 0){
				p *= Character.getNumericValue(s.charAt(i));
			}
			if(((i+1) % 3 == 0)||(i+1 == s.length())){
				for(;p > 1048575;){
					p -= 1048575;
				}
				if(p > 0){
					sum += p;
				}else{
					sum -= p;
				}		 		
				if(sum > 1048575){
					sum -= 1048575;
				}
				p = 1;
			}
		}
		String value = Integer.toBinaryString(sum);	
		System.out.println(value);
		for(int h = 0; value.length() != 20; h++){
			if(h % 3 == 0){
				if(h % 2 == 0){
					value = "0" + value;
				}else{
					value = "1" + value;
				}
			}else{
				if(h % 2 == 0){
					value = "1" + value;
				}else{
					value = "0" + value;
				}
			}
		}
		out.write("<db>");
		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + "\n");
		out.write("<svg version = \"1.1\"" + "\n");
		out.write("baseProfile=\"full\"");
		out.write("height = \"100px\"  width = \"100px\">");
		if(value.charAt(0) == '0'){
			out.write("<rect x=\"0\" y=\"0\" width=\"100\" height=\"100\" fill=\"rgb(50, 50, 50)\" stroke=\"rgb(50, 50, 50)\"/>");
		}else{
			out.write("<rect x=\"0\" y=\"0\" width=\"100\" height=\"100\" fill=\"rgb(205, 205, 205)\" stroke=\"rgb(205, 205, 205)\"/>");
		}
		String color1 = "";
		String color2 = "";
		if(value.charAt(1) == '0'){
			if(value.charAt(2) == '0'){
				if(value.charAt(3) == '0'){
					if(value.charAt(4) == '0'){
						color1="rgb(176, 26, 26)";
						color2="rgb(231, 81, 81)";
					}else{
						color1="rgb(177, 27, 154)";
						color2="rgb(232, 82, 209)";
					}
				}else{
					if(value.charAt(4) == '0'){
						color1="rgb(122, 27, 176)";
						color2="rgb(177, 82, 232)";
					}else{
						color1="rgb(27, 32, 176)";
						color2="rgb(82, 87, 232)";
					}
				}
			}else{
				if(value.charAt(3) == '0'){
					if(value.charAt(4) == '0'){
						color1="rgb(27, 173, 176)";
						color2="rgb(82, 229, 232)";
					}else{
						color1="rgb(62, 176, 27)";
						color2="rgb(117, 232, 82)";
					}
				}else{
					if(value.charAt(4) == '0'){
						color1="rgb(171, 176, 27)";
						color2="rgb(227, 232, 82)";
					}else{
						color1="rgb(177, 95, 27)";
						color2="rgb(232, 150, 82)";
					}
				}
			}
		}else{
			if(value.charAt(2) == '0'){
				if(value.charAt(3) == '0'){
					if(value.charAt(4) == '0'){
						color1="rgb(176, 26, 26)";
						color2="rgb(231, 81, 81)";
					}else{
						color1="rgb(177, 27, 154)";
						color2="rgb(232, 82, 209)";
					}
				}else{
					if(value.charAt(4) == '0'){
						color1="rgb(122, 27, 176)";
						color2="rgb(177, 82, 232)";
					}else{
						color1="rgb(27, 32, 176)";
						color2="rgb(82, 87, 232)";
					}
				}
			}else{
				if(value.charAt(3) == '0'){
					if(value.charAt(4) == '0'){
						color2="rgb(27, 173, 176)";
						color1="rgb(82, 229, 232)";
					}else{
						color2="rgb(62, 176, 27)";
						color1="rgb(117, 232, 82)";
					}
				}else{
					if(value.charAt(4) == '0'){
						color2="rgb(171, 176, 27)";
						color1="rgb(227, 232, 82)";
					}else{
						color2="rgb(177, 95, 27)";
						color1="rgb(232, 150, 82)";
					}
				}
			}
		}
		if(value.charAt(5) == '0'){
			out.write("<rect x=\"3\" y=\"3\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color1 +"\"/>");
		}
		if(value.charAt(6) == '0'){
			out.write("<rect x=\"23\" y=\"23\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color1 +"\"/>");
		}
		if(value.charAt(7) == '0'){
			out.write("<rect x=\"43\" y=\"43\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color2 +"\" stroke-width=\"3px\"/>");
		}
		if(value.charAt(8) == '0'){
			out.write("<rect x=\"63\" y=\"63\" width=\"14\" height=\"14\" fill=\""+ color2 +"\" stroke=\""+ color2 +"\"/>");
		}
		if(value.charAt(9) == '0'){
			out.write("<rect x=\"83\" y=\"83\" width=\"14\" height=\"14\" fill=\""+ color2 +"\" stroke=\""+ color2 +"\"/>");
		}
		if(value.charAt(10) == '0'){
			out.write("<rect x=\"23\" y=\"63\" width=\"14\" height=\"14\" fill=\""+ color2 +"\" stroke=\""+ color1 +"\" stroke-width=\"3px\"/>");
			out.write("<rect x=\"63\" y=\"23\" width=\"14\" height=\"14\" fill=\""+ color2 +"\" stroke=\""+ color1 +"\" stroke-width=\"3px\"/>");
		}
		if(value.charAt(11) == '0'){
			out.write("<rect x=\"3\" y=\"83\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color2 +"\" stroke-width=\"3px\"/>");
			out.write("<rect x=\"83\" y=\"3\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color2 +"\" stroke-width=\"3px\"/>");
		}
		if(value.charAt(12) == '0'){
			out.write("<rect x=\"3\" y=\"23\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color1 +"\"/>");
			out.write("<rect x=\"23\" y=\"3\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color1 +"\"/>");
		}
		if(value.charAt(13) == '0'){
			out.write("<rect x=\"3\" y=\"43\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color1 +"\"/>");
			out.write("<rect x=\"43\" y=\"3\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color1 +"\"/>");
		}
		if(value.charAt(14) == '0'){
			out.write("<rect x=\"63\" y=\"3\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color1 +"\"/>");
			out.write("<rect x=\"3\" y=\"63\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color1 +"\"/>");
		}
		if(value.charAt(15) == '0'){
			out.write("<rect x=\"23\" y=\"83\" width=\"14\" height=\"14\" fill=\""+ color2 +"\" stroke=\""+ color2 +"\"/>");
			out.write("<rect x=\"83\" y=\"23\" width=\"14\" height=\"14\" fill=\""+ color2 +"\" stroke=\""+ color2 +"\"/>");
		}
		if(value.charAt(16) == '0'){
			out.write("<rect x=\"43\" y=\"63\" width=\"14\" height=\"14\" fill=\""+ color2 +"\" stroke=\""+ color2 +"\"/>");
			out.write("<rect x=\"63\" y=\"43\" width=\"14\" height=\"14\" fill=\""+ color2 +"\" stroke=\""+ color2 +"\"/>");
		}
		if(value.charAt(17) == '0'){
			out.write("<rect x=\"43\" y=\"83\" width=\"14\" height=\"14\" fill=\""+ color2 +"\" stroke=\""+ color2 +"\"/>");
			out.write("<rect x=\"83\" y=\"43\" width=\"14\" height=\"14\" fill=\""+ color2 +"\" stroke=\""+ color2 +"\"/>");
		}
		if(value.charAt(18) == '0'){
			out.write("<rect x=\"63\" y=\"83\" width=\"14\" height=\"14\" fill=\""+ color2 +"\" stroke=\""+ color2 +"\"/>");
			out.write("<rect x=\"83\" y=\"63\" width=\"14\" height=\"14\" fill=\""+ color2 +"\" stroke=\""+ color2 +"\"/>");
		}
		if(value.charAt(19) == '0'){
			out.write("<rect x=\"23\" y=\"43\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color1 +"\"/>");
			out.write("<rect x=\"43\" y=\"23\" width=\"14\" height=\"14\" fill=\""+ color1 +"\" stroke=\""+ color1 +"\"/>");
		}
		out.write("</db>");
	}
		out.close();
	}
	
}
