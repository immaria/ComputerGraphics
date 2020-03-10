package com.antusheva;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Animation extends JPanel implements ActionListener {
    Timer timer;
    // Для анімації масштабування
    private double scale = 1;
    private double delta = 0.01;
    private double deltaX = 1;

    private int radius = 200;
    private int radiusExtention = 100;
    // Для анімації повороту
    private double angle = 0;
    // Для анімації руху
    private double tx = 0;
    private double ty = 6;
    private static int maxWidth;
    private static int maxHeight;

    public Animation() {
    // Таймер генеруватиме подію що 10 мс
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        // Встановлюємо кольори
        g2d.setBackground(new Color(217, 112, 213));
        g2d.setColor(Color.YELLOW);
        g2d.clearRect(0, 0, maxWidth+1, maxHeight+1);
        g2d.translate(maxWidth/3, maxHeight/3);

        // Draw the main Frame.
        g2d.setColor(new Color(246, 232, 255));
        BasicStroke bs2 = new BasicStroke(10, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs2);
        g2d.drawRect(
                -(radius + radiusExtention+ 25),
                -(radius + radiusExtention -25),
                (radius + radiusExtention+15)*2,
                (radius + radiusExtention+50)*2
        );

        BasicStroke bs3 = new BasicStroke(5, BasicStroke.JOIN_MITER, BasicStroke.JOIN_MITER);
        g2d.setStroke(bs3);
        g2d.drawRect(
                -(radius + radiusExtention- 725),
                -(radius + radiusExtention - 150),
                (radius + radiusExtention+60),
                (radius + radiusExtention-80)
        );
        g2d.drawString("ANIMATION", 550, -100);
        g2d.drawString("1. Motion in a circle counter-clockwise\n", 450, -70);
        g2d.drawString("\n5. Rotate around the center of the image clockwise", 450, -50);
        g2d.drawString("\nFrame line type - \n" +
                "JOIN_BEVEL", 450, -20);

//             //Завдання №1.2: Фігура, побудована по точкам ламана()
//        double points[][] = {
//                { 0, 85 }, { 85, 85 },
//                { 200, 85 }, { 150, 125 }, { 160, 190 },
//        };
//        GeneralPath plane = new GeneralPath();
//        plane.moveTo(points[0][0], points[0][1]);
//        for (int k = 1; k < points.length; k++)
//            plane.lineTo(points[k][0], points[k][1]);
//        plane.closePath();
//       // g2d.setColor(new Color(222, 143, 241));
//        g2d.fill(plane);


        // Встановлюємо параметри рендерингу
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.translate(tx, ty);

        // Перетворення для анімації повороту. Якщо не задати 2 та 3 параметри –поворот відбудеться відносно центру координат
        g2d.rotate(angle, -20, 77.5);


        GradientPaint gp1 = new GradientPaint(
                25, 105,
                new Color(255, 39, 39),
                60, 5,
                new Color(253, 183, 183),
                true
        );
        GradientPaint gp2 = new GradientPaint(
                25, 105,
                new Color(2, 99, 16),
                60, 5,
                new Color(79, 144, 87),
                true
        );
        GradientPaint gp3 = new GradientPaint(
                25, 105,
                new Color(0, 67, 255),
                60, 5,
                new Color(171, 185, 246),
                true
        );
        GradientPaint gp4 = new GradientPaint(
                25, 105,
                new Color(226, 255, 0),
                60, 5,
                new Color(247, 255, 198),
                true
        );
        g2d.setPaint(gp2);
        //g2d.setColor(new Color(40, 138, 39));
        g2d.fillRect(-90, -25, 180, 50);

        // Draw the right elipse
      //  g2d.setPaint(gp4);
        g2d.setColor(new Color(255, 255, 0));
        g2d.fillOval(40, -25, 50, 200);

        // Draw the lower rectangle
        // g2d.setColor(new Color(254, 15, 0));
        g2d.setPaint(gp1);
        g2d.fillRect(-90, 125, 180, 50);

        // Draw the left elipse
         g2d.setColor(new Color(15, 3, 255));
       // g2d.setPaint(gp3);
        g2d.fillOval(-90, -25, 50, 200);

        // Draw the left figure
        // g2d.setColor(new Color(40, 138, 39));
        g2d.setPaint(gp2);
        g2d.fillRect(-90, -25, 50, 50);

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("LR №2, Var №1");
        frame.add(new Animation());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Dimension size = (frame.getSize());
        Insets insets = frame.getInsets();
        maxWidth = (size.width - insets.left - insets.right - 1);
        maxHeight = (size.height - insets.top - insets.bottom - 1);
    }

    // Цей метод буде викликано щоразу, як спрацює таймер
    public void actionPerformed(ActionEvent e) {
        if ( scale < 0.01 ) {
            delta = -delta;
        } else if (scale > 0.99) {
            delta = -delta;
        }
        // movement
        double radiusInSquare = Math.pow(radius, 2);
        if (tx <= 0 && ty < 0){
            tx -= deltaX;
            ty = (-1) * Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx > 0 && ty <= 0){
            tx -= deltaX;
            ty = (-1) * Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx >= 0 && ty > 0){
            tx += deltaX;
            ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx < 0 && ty >= 0){
            tx += deltaX;
            ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }

        scale += delta;
        angle += 0.01;
        repaint();
    }
}