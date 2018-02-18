package company;

import java.util.Scanner;

public class Main {

    public static String genId(String s, int width, int height) {
        int id_width = 10;
        int id_height = 10;
        int[] hash = new int[s.length()];

        for(int i = 0; i < s.length(); ++i) {
            String s1 = s.substring(0, i + 1);
            hash[i] = s1.hashCode();
        }

        Main.Pixel pixel_foreground;
        if (s.length() >= 3) {
            pixel_foreground = new Main.Pixel(hash[s.length() - 3] % 255, hash[s.length() - 2] % 255, hash[s.length() - 1] % 255);
        } else if (s.length() == 2) {
            pixel_foreground = new Main.Pixel(hash[0] % 255, hash[1] % 255, (hash[0] + hash[1]) % 255);
        } else {
            pixel_foreground = new Main.Pixel(hash[0] % 255, (hash[0] + hash[0]) % 255, (hash[0] + hash[0] + hash[0]) % 255);
        }

        Main.Pixel pixel_background = new Main.Pixel(255, 255, 255);
        Main.Pixel[][] image_pix = new Main.Pixel[id_width][id_height];

        int scalex;
        for(int x = 0; x < id_width; ++x) {
            for(scalex = 0; scalex < id_height; ++scalex) {
                Main.Pixel p;
                if ((hash[x % s.length()] >> scalex & 1) == 0) {
                    p = pixel_foreground;
                } else {
                    p = pixel_background;
                }

                image_pix[x][scalex] = new Main.Pixel(p.R, p.G, p.B);
            }
        }

        String svg = "<svg xmlns=\"http://www.w3.org/2000/svg\" \n  " +
                "   xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n " +
                "    width=\"400\" height=\"400\">";
        scalex = width / id_width;
        int scaley = height / id_height;

        for(int x = 0; x < id_width; ++x) {
            for(int y = 0; y < id_height; ++y) {
                svg = svg + "<rect x = \"" + x * scalex + "\" y = \"" + y * scaley +
                        "\" width = \"" + scalex + "\" height = \"" + scaley +
                        "\" style = \"fill:rgb(" + image_pix[x][y].R + ", " +
                        image_pix[x][y].G + ", " + image_pix[x][y].B + ")\" />";
                svg = svg + "\n";
            }
        }

        svg = svg + "</svg>";
        return svg;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String out = genId(s, 400, 400);
        System.out.println(out);
    }

    public static class Pixel {
        public int R;
        public int G;
        public int B;

        public Pixel(int x, int y, int z) {
            this.R = x;
            this.G = y;
            this.B = z;
        }
    }
}
