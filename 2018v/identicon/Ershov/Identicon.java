import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.print.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.*;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;


public class Identicon {
	static int colour[] = new int[3];
	static String htmlPath = "/home/mikhail/eclipse-workspace/IDENT/id.html";
	
	public static byte[][] generateIdenticons(String text, int image_width, int image_height){
		int width = image_width, height = image_height;
		byte[][] identicon = new byte[width][height];
        Random rand = new Random(text.hashCode());
        colour[0] = rand.nextInt(256);
        colour[1] = rand.nextInt(256);
        colour[2] = rand.nextInt(256);
        for(int x = 0 ; x <= width / 2 ; x++) {
            for (int y = 0; y < height; y++) {
            	identicon[x][y] = (byte) rand.nextInt(2);
            	identicon[width - x - 1][y] = identicon[x][y];
            }
            
        }
        return identicon;
	}
	
	public static void openHTML() throws IOException {
		FileWriter writer = new FileWriter(htmlPath, true);
		PrintWriter print = new PrintWriter(writer);
		print.print("<!DOCTYPE html>\n" + 
					"<html>\n" + 
					"<body>\n");
		
		print.close();
	}
	
	public static void closeHTML() throws IOException {
		FileWriter writer = new FileWriter(htmlPath, true);
		PrintWriter print = new PrintWriter(writer);
		print.print("</body>\n" + 
				"</html>"); 
		print.close();
	}
	
	public static void createHTML(byte [][] image) throws IOException {
		FileWriter writer = new FileWriter(htmlPath, true);
		PrintWriter print = new PrintWriter(writer);
		print.print("<svg width=\"" + image.length * 10 + "\" >\n" );		
		for (int x = 0; x < image.length; x++) {
			for (int y = 0; y < image[0].length; y++) {
				if (image[x][y] == 1)
					print.print("<rect height = \"10\" width = \"10\" x=\"" + 10 * x + "\" y=\"" + 10 * y + "\" fill=\"rgb(" + colour[0] + ", " + colour[1] + ", " + colour[2] + ")\" />\n");
				
				else
					print.print("<rect height = \"10\" width = \"10\" x=\"" + 10 * x + "\" y=\"" + 10 * y + "\" fill=\"rgb(255, 255, 255)\" />\n");
			}
		}
		print.print("</svg>\n");
		print.close();
	}
	
	public static void main(String[] args) throws IOException {
		FileWriter writer = new FileWriter(htmlPath);
		PrintWriter print = new PrintWriter(writer);
		print.print("");
		openHTML();
		for (int i = 0; i < 100; i++) {
			String s = Integer.toString(i);
			byte [][] image = generateIdenticons(s, 6, 6);
			createHTML(image);
		}
		
		String s = "mikhail-ershov";
		byte [][] image = generateIdenticons(s, 6, 6);
		createHTML(image);
		
		closeHTML();
		print.close();
	}
}
