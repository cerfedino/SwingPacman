package Painter.HUD;

import Entities.Sprite;
import Media.*;
import Settings.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LivesJPanel extends JPanel {
    
    private int lives = 0;
    private JLabel label;
    public LivesJPanel(){
        super();
        label = new JLabel("LIVES:");
        label.setFont(new Font("Sans Serif", Font.BOLD, (int) Settings.get(EParam.label_size)));
        label.setForeground(((Color)Settings.get(EParam.label_color)));
        add(label);
        
        setOpaque(false);
        updateLives(0);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        removeAll();
        add(label);
        label.setLocation(0,0);
        
        int offset = label.getWidth();
        int width = Media.getImg(EImage.live).getWidth();
        for (int i = 1; i<= lives; i++) {
            Sprite s = new Sprite(offset+(i*width), getHeight()/2, EImage.live);
            add(s);
        }
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void updateLives(int lives) {
        this.lives = lives;
        int newWidth = label.getWidth()
                        + ((lives+1)*Media.getImg(EImage.live).getWidth());
        int newHeight = Math.max(label.getHeight(), Media.getImg(EImage.live).getHeight());
        
        setBounds(getX(), getY(),newWidth, newHeight);
    }
}
