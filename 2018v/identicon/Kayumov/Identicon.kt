import java.awt.Color
import java.io.*
import java.util.Random


    private const val W = 140
    private const val H = 140

    private const val FACE_X = 70
    private const val FACE_Y = 70
    private const val FACE_RX = 50
    private const val FACE_RY = 60

    private const val EYE_Y = 60
    private const val EYE_RX = 12
    private const val EYE_RY = 12

    private const val NOSE_BRIDGE = 20

    private const val MAX_HAPPINESS = 30

    private val faceColors = ArrayList<Color>()
    private val eyeColors = ArrayList<Color>()
    private val eyeBrowColors = ArrayList<Color>()
    private val lipsColors = ArrayList<Color>()

    fun init() {
        var reader = BufferedReader(FileReader("face_colors.in"))
        reader.readLines().forEach {
            faceColors.add(getRGB(it))
        }
        reader.close()

        reader = BufferedReader(FileReader("lips_colors.in"))
        reader.readLines().forEach {
            lipsColors.add(getRGB(it))
        }
        reader.close()

        reader = BufferedReader(FileReader("eye_colors.in"))
        reader.readLines().forEach {
            eyeColors.add(getRGB(it))
        }
        reader.close()

        eyeBrowColors.add(Color.BLACK)
        eyeBrowColors.add(Color.DARK_GRAY)
    }

    class SVGImage(val name: String, width: Int, height: Int) {
        private var image = StringBuilder("<svg width=\"$width\" height=\"$height\">\n")

        fun drawEllipse(x: Int, y: Int, rx: Int, ry: Int, color: Color) {
            image.append("<ellipse cx = \"$x\" cy = \"$y\" rx=\"$rx\" ry=\"$ry\" " +
                    "style=\"fill:rgb(${color.red},${color.green},${color.blue})\" stroke:black; stroke-width:1 />\n")
        }

        fun drawQuadraticPath(x: Int, y: Int, x1: Int, y1: Int, x2: Int, y2: Int, color: Color, strokeWidth: Int) {
            image.append("<path d=\"M $x,$y Q $x1, $y1 $x2,$y2\" stroke=\"rgb(${color.red},${color.green},${color.blue})\" " +
                    "stroke-width=\"$strokeWidth\" fill=\"none\" />")
        }

        fun drawLine(x1: Int, y1: Int, x2: Int, y2: Int, color: Color, strokeWidth: Int) {
            image.append("<line x1=\"$x1\" y1=\"$y1\" x2=\"$x2\" y2=\"$y2\" " +
                    "stroke=\"rgb(${color.red},${color.green},${color.blue}\" stroke-width=\"$strokeWidth\" />")
        }

        fun drawRect(x: Int, y: Int, width: Int, height: Int, color: Color) {
            image.append("<rect x = \"$x\" y = \"$y\" width=\"$width\" height=\"$height\" " +
                    "style=\"fill:rgb(${color.red},${color.green},${color.blue})\" />\n")
        }

        override fun toString(): String {
            image.append("</svg>")
            return image.toString()
        }
    }

    private fun getRGB(rgb: String): Color {
        val ret = IntArray(3)
        for (i in 0..2) {
            ret[i] = Integer.parseInt(rgb.substring(i * 2, i * 2 + 2), 16)
        }
        return Color(ret[0], ret[1], ret[2])
    }

    fun main(args: Array<String>) {
        init()
        if (args.isEmpty()) {
            println("No input")
            return
        }
        val names = args[0].split(" ")
        //val names = Array(100, { it.toString() })
        val images = Array(names.size, { generateImage(names[it]) })
        //saveHTML(images)
        for (image in images) {
            println(image.name)
            println(image.toString())
            println()
        }
    }

    private fun generateImage(s: String): SVGImage {
        var image = SVGImage(s, W, H)
        val random = Random(s.hashCode().toLong())

        // Draw face
        image.drawEllipse(FACE_X, FACE_Y, FACE_RX + random.nextInt(13), FACE_RY + random.nextInt(13), faceColors[random.nextInt(faceColors.size)])

        // Draw eyes
        val eyeColor = eyeColors[random.nextInt(eyeColors.size)]
        val eyeBrowColor = eyeBrowColors[random.nextInt(eyeBrowColors.size)]
        val eyeBrowWidth = random.nextInt(5) + 1

        image = drawEye(random, image, true, eyeColor, eyeBrowColor, eyeBrowWidth)
        image = drawEye(random, image, false, eyeColor, eyeBrowColor, eyeBrowWidth)

        // Draw mouth
        val happiness = ((random.nextDouble() * 2 - 1) * MAX_HAPPINESS).toInt()
        val mouthWidth = random.nextInt(4) + 2
        image.drawQuadraticPath(40,100, 70,100 + happiness, 100,100, lipsColors[random.nextInt(lipsColors.size)], mouthWidth)

        // Draw nose
        //image.drawLine(70, 55, 60, 80, Color.BLACK, 2)
        //image.drawLine(60, 80, 80, 80, Color.BLACK, 2)
        return image
    }

    private fun drawEye(random: Random, image: SVGImage, left: Boolean, eyeColor: Color, eyeBrowColor: Color, eyeBrowWidth: Int): SVGImage {
        val eyedX = ((random.nextDouble() * 2 - 1) * 6).toInt()
        val eyedY = ((random.nextDouble() * 2 - 1) * 6).toInt()
        val eyeX = if (left) FACE_X - NOSE_BRIDGE + eyedX else FACE_X + NOSE_BRIDGE + eyedX
        val eyeY = EYE_Y + eyedY
        val eyeRx = (EYE_RX * (1.25 - random.nextDouble() / 2)).toInt()
        val eyeRy = (EYE_RY * (1.25 - random.nextDouble() / 2)).toInt()
        val sheenRx = minOf(eyeRx, eyeRy) / 2
        val sheenRy = minOf(eyeRx, eyeRy) / 2
        val browCurve = (random.nextDouble() * 7).toInt()
        image.drawEllipse(eyeX, eyeY, eyeRx, eyeRy, Color.white)
        image.drawEllipse(eyeX, eyeY, sheenRx, sheenRy, eyeColor)
        image.drawQuadraticPath(eyeX - 15, eyeY - eyeRy, eyeX, eyeY - eyeRy - browCurve,
                eyeX + 15, eyeY - eyeRy, eyeBrowColor, eyeBrowWidth)
        return image
    }

    private fun saveHTML(images: Array<SVGImage>) {
        try {
            val out = PrintWriter(File("image.html"))
            out.println("<html>")
            out.println("<body>")
            images.forEach {
                out.println("<p>${it.name}</p>")
                out.println(it.toString())
            }
            out.println("</body>")
            out.println("</html>")
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }