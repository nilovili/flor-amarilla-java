import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class FlowerApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flower Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new FlowerPanel());
        frame.setVisible(true);
    }
}

class FlowerPanel extends JPanel {
    private final String text1 = "EN EL MUNDO DE LA PROGRAMACIÓN,";
    private final String text2 = "TU ERES MI FUNCIÓN FAVORITA";
    private int posY = 50;
    private int direction = 1;
    private int petalCount = 0;
    private int charCount1 = 0;
    private int charCount2 = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(new Color(173, 216, 230)); // Light Sky Blue
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw the stem
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(10));
        g2.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2 + 150);

        // Draw the petals
        g2.setColor(Color.YELLOW);
        for (int i = 0; i < petalCount; i++) {
            drawPetal(g2, i);
        }

        // Draw the center
        g2.setColor(Color.PINK);
        g2.fillOval(getWidth() / 2 - 35, getHeight() / 2 - 35, 70, 70);

        // Draw the text
        g2.setColor(Color.BLACK);  // Cambiar a negro
        g2.setFont(new Font("Arial", Font.BOLD, 28));  // Cambiar a negrita y un poco más grande

        int textWidth1 = g2.getFontMetrics().stringWidth(text1.substring(0, charCount1));
        int textWidth2 = g2.getFontMetrics().stringWidth(text2.substring(0, charCount2));

        g2.drawString(text1.substring(0, charCount1), (getWidth() - textWidth1) / 2, posY);
        g2.drawString(text2.substring(0, charCount2), (getWidth() - textWidth2) / 2, posY + 30);

        if (posY > 85 || posY < 35) {
            direction *= -1;
        }

        posY += direction * 2;

        // Repaint
        try {
            Thread.sleep(50);

            // continuar dibujando
            if (petalCount < 12) {
                petalCount++;
                Thread.sleep(100);
            } else {
                petalCount = 0;  // resetear petalos
            }

            // adicionar caracter a la secuencia
            if (charCount1 < text1.length()) {
                charCount1++;
                Thread.sleep(50);
            } else if (charCount2 < text2.length()) {
                charCount2++;
                Thread.sleep(50);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        repaint();
    }

    private void drawPetal(Graphics2D g2, int i) {
        double angle = i * (360.0 / 12.0);
        double radian = Math.toRadians(angle);
        double x = getWidth() / 2 + 35 * Math.cos(radian);
        double y = getHeight() / 2 + 35 * Math.sin(radian);

        GeneralPath petal = new GeneralPath();
        petal.moveTo(x, y);
        petal.curveTo(x + 70 * Math.cos(radian + Math.PI / 6), y + 70 * Math.sin(radian + Math.PI / 6),
                x + 80 * Math.cos(radian), y + 80 * Math.sin(radian),
                x + 70 * Math.cos(radian - Math.PI / 6), y + 70 * Math.sin(radian - Math.PI / 6));
        petal.closePath();

        g2.fill(petal);
    }
}