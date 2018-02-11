import java.util.Random;

public class Identicon {
    public static void main(String[] args) {
        String s = args[0];
        int dimensions = 100;
        Random random = new Random(s.hashCode());
        String color;
        int var1, var2, var3;
        random.nextBoolean();
        boolean orientation = random.nextBoolean();

        s = "<!DOCTYPE html><html><body>";
        s += "<svg width=\"" + dimensions+ "\" height=\"" + dimensions + "\">";

        // Фон
        color = getRandomColor(random);
        s += "<rect x=\"0\" y=\"0\" width=\"" + dimensions + "\" height=\"" + dimensions + "\" style=\"fill:rgb(" + color + ")\" />";

        //Лмнии
        color = getRandomColor(random);
        var1 = (int) (dimensions * random.nextFloat());
        var2 = (int) (dimensions * random.nextFloat());
        if (orientation) {
            s += "<line x1=\"0\" y1=\"" + var1 + "\" x2=\"" + dimensions + "\" y2=\"" + var2 + "\" style=\"stroke:rgb(" + color + ");stroke-width:" + (int)(dimensions * 0.1) + "\" />";
            s += "<line x1=\"0\" y1=\"" + (dimensions - var1) + "\" x2=\"" + dimensions + "\" y2=\"" + (dimensions - var2) + "\" style=\"stroke:rgb(" + color + ");stroke-width:" + (int)(dimensions * 0.1) + "\" />";
        } else {
            s += "<line x1=\"" + var1 + "\" y1=\"0\" x2=\"" + var2 + "\" y2=\"" + dimensions + "\" style=\"stroke:rgb(" + color + ");stroke-width:" + (int)(dimensions * 0.1) + "\" />";
            s += "<line x1=\"" + (dimensions - var1) + "\" y1=\"0\" x2=\"" + (dimensions - var2) + "\" y2=\"" + dimensions + "\" style=\"stroke:rgb(" + color + ");stroke-width:" + (int)(dimensions * 0.1) + "\" />";
        }

        //Треугольгик
        var1 = (int) ((dimensions / 2) * random.nextFloat());
        var2 = dimensions - (int) ((dimensions / 2) * random.nextFloat());
        var3 = (int) ((dimensions / 2) * 0.9 * random.nextFloat());
        color = getRandomColor(random);
        if (!orientation) {
            s += "<polygon points=\""+ var1 +"," + dimensions / 2  + " " + var2 + "," + var3 + " " + var2 + "," + (dimensions - var3) +"\" style=\"fill:rgb(" + color + ");stroke:purple;stroke-width:0\" />";
            s += "<polygon points=\""+ (dimensions - var1) +"," + dimensions / 2  + " " + (dimensions - var2) + "," + var3 + " " + (dimensions - var2) + "," + (dimensions - var3) +"\" style=\"fill:rgb(" + color + ");stroke:purple;stroke-width:0\" />";
        } else {
            s += "<polygon points=\" " + dimensions / 2  + ","+ var1 +" " + var3 + "," + var2 + " " + (dimensions - var3) + "," + var2 +"\" style=\"fill:rgb(" + color + ");stroke:purple;stroke-width:0\" />";
            s += "<polygon points=\" " + dimensions / 2  + ","+ (dimensions - var1)  +" " + var3 + "," + (dimensions - var2) + " " + (dimensions - var3) + "," + (dimensions - var2) +"\" style=\"fill:rgb(" + color + ");stroke:purple;stroke-width:0\" />";
        }

        //Круги
        color = getRandomColor(random);
        int radius = (int) (dimensions * 3 / 20 * random.nextFloat() + dimensions * 0.05);
        var1 = (int) ((dimensions - 2 * radius) * random.nextFloat()) + radius;
        var3 = (int) (((dimensions / 2) - 2 * radius) * random.nextFloat()) + radius;
        var2 = (int) ((radius - dimensions * 0.05) * random.nextFloat() + dimensions * 0.025);
        if (orientation) {
            s += "<circle cx=\"" + var1 + "\" cy=\"" + var3 + "\" r=\"" + radius + "\" stroke = rgb(" + color + ") stroke-width=\"" + var2 + "\" fill=\"black\" />";
            s += "<circle cx=\"" + var1 + "\" cy=\"" + (dimensions - var3) + "\" r=\"" + radius + "\" stroke = rgb(" + color + ") stroke-width=\"" + var2 + "\" fill=\"black\" />";
        } else {
            s += "<circle cx=\"" + var3 + "\" cy=\"" + var1 + "\" r=\"" + radius + "\" stroke = rgb(" + color + ") stroke-width=\"" + var2 + "\" fill=\"black\" />";
            s += "<circle cx=\"" + (dimensions - var3) + "\" cy=\"" + var1 + "\" r=\"" + radius + "\" stroke = rgb(" + color + ") stroke-width=\"" + var2 + "\" fill=\"black\" />";
        }
        s += "</svg></body></html>";

        System.out.println(s);
    }

    private static String getRandomColor(Random random) {
        int r = (int) (256 * random.nextFloat());
        int g = (int) (256 * random.nextFloat());
        int b = (int) (256 * random.nextFloat());

        return r + "," + g + "," +  b;
    }
}
