package GUI;

import AnimationEngine.BlinkAnimator;
import AudioEngine.AudioEngine;
import AudioEngine.FunctionCallback;
import AudioEngine.PlaybackMode;
import Media.EAudio;
import Media.EFont;
import Media.Media;
import Painter.Scaler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;


/**
 * The Mainmenu of the game
 */
public class MainMenu extends JFrame {
    
    private StartGamePanel startpanel;
    
    public static void main(String[] args) {
        System.setProperty("prism.allowhidpi", "false");
        System.setProperty("sun.java2d.uiScale", "1");
        
        new MainMenu();
    }
    
    /**
     * Initializes the MainMenu frame.
     */
    public MainMenu() {
        super();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int size = Math.max(screenSize.height, screenSize.width);
        Scaler.setNewsize(size);
    
        
        initComponents();
        adjustSizes();
        
        AudioEngine.play(EAudio.ost, PlaybackMode.loop, null);
    
        startpanel = new StartGamePanel(this.getWidth(),  this);
        
        MouseListener a = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource().equals(exit_button)) {
                    BlinkAnimator blinkAnimator = new BlinkAnimator(exit_button,80,true);
                    blinkAnimator.start();
                    AudioEngine.play(EAudio.button_click, PlaybackMode.regular, new FunctionCallback() {
                        @Override
                        public void callback(){
                            blinkAnimator.stop();
                            System.exit(0);
                        }
                    });
                } else if (e.getSource().equals(play_button)) {
                    BlinkAnimator blinkAnimator = new BlinkAnimator(play_button,80,true);
                    blinkAnimator.start();
                    AudioEngine.play(EAudio.button_click, PlaybackMode.regular, new FunctionCallback() {
                        @Override
                        public void callback() {
                            blinkAnimator.stop();
                            showNewGamepanel();
                        }
                    });
                } else if (e.getSource().equals(github_button)) {
                    BlinkAnimator blinkAnimator = new BlinkAnimator(github_button,80,true);
                    blinkAnimator.start();
                    AudioEngine.play(EAudio.button_click, PlaybackMode.regular, new FunctionCallback() {
                        @Override
                        public void callback() {
                            blinkAnimator.stop();
                            try{
                                openWebpage(new URI("https://github.com/AlbertCerfeda/SwingPacman"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                
                }
            }
        };
        github_button.addMouseListener(a);
        exit_button.addMouseListener(a);
        play_button.addMouseListener(a);
        
        setVisible(true);
        
    }
    
    /**
     * Opens a webpage in the Systems default web browser.
     * @param uri
     */
    public void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public void showMainPanel() {
        remove(startpanel);
        add(main_panel);
        repaint();
    }
    
    public void showNewGamepanel() {
        remove(main_panel);
        add(startpanel);
        repaint();
    }
    
    
    
    public void adjustSizes() {
        setSize(Scaler.scale(getWidth()), Scaler.scale(getHeight()));
        int width = this.getWidth();
        int height = this.getHeight();
        Media.rescaleMedia(Scaler.getScale_factor());
        
        main_panel.setSize(width, height);
        title.setBounds(Scaler.scale(title.getX()), Scaler.scale(title.getY()), Scaler.scale(title.getWidth()),Scaler.scale(title.getHeight()));
        title.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, Scaler.scale(title.getFont().getSize())));
        
        play_button.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(play_button.getFont().getSize())));
        play_button.setBounds(Scaler.scale(play_button.getX()), Scaler.scale(play_button.getY()), Scaler.scale(play_button.getWidth()),Scaler.scale(play_button.getHeight()));
        play_button.setBorder(BorderFactory.createLineBorder(Color.yellow, Scaler.scale(3)));
        
        exit_button.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(exit_button.getFont().getSize())));
        exit_button.setBounds(Scaler.scale(exit_button.getX()), Scaler.scale(exit_button.getY()), Scaler.scale(exit_button.getWidth()),Scaler.scale(exit_button.getHeight()));
        exit_button.setBorder(BorderFactory.createLineBorder(Color.yellow, Scaler.scale(3)));
        
        github_button.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(github_button.getFont().getSize())));
        github_button.setBounds(Scaler.scale(github_button.getX()), Scaler.scale(github_button.getY()), Scaler.scale(github_button.getWidth()),Scaler.scale(github_button.getHeight()));
        github_button.setBorder(BorderFactory.createLineBorder(Color.yellow, Scaler.scale(3)));
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        main_panel = new JPanel();
        title = new JLabel();
        github_button = new JButton();
        play_button = new JButton();
        exit_button = new JButton();

        //======== this ========
        setTitle("SwingPacman");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setMinimumSize(null);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== main_panel ========
        {
            main_panel.setBackground(new Color(0, 0, 27));
            main_panel.setPreferredSize(null);
            main_panel.setMaximumSize(null);
            main_panel.setMinimumSize(null);
            main_panel.setLayout(null);

            //---- title ----
            title.setText("SwingPacman");
            title.setFont(new Font(Font.SANS_SERIF, title.getFont().getStyle(), title.getFont().getSize() + 32));
            title.setForeground(Color.yellow);
            title.setMaximumSize(null);
            title.setMinimumSize(null);
            title.setPreferredSize(null);
            main_panel.add(title);
            title.setBounds(55, 55, 355, title.getPreferredSize().height);

            //---- github_button ----
            github_button.setText("GitHub");
            github_button.setBackground(new Color(0, 0, 0, 0));
            github_button.setForeground(new Color(204, 0, 204));
            github_button.setFont(github_button.getFont().deriveFont(github_button.getFont().getStyle() | Font.BOLD));
            github_button.setBorder(new LineBorder(Color.yellow, 2, true));
            github_button.setContentAreaFilled(false);
            main_panel.add(github_button);
            github_button.setBounds(305, 345, 87, 45);

            //---- play_button ----
            play_button.setText("PLAY");
            play_button.setBackground(new Color(0, 0, 0, 0));
            play_button.setBorder(new LineBorder(Color.yellow, 2, true));
            play_button.setFont(play_button.getFont().deriveFont(play_button.getFont().getStyle() | Font.BOLD, play_button.getFont().getSize() + 6f));
            play_button.setForeground(new Color(255, 0, 204));
            play_button.setContentAreaFilled(false);
            play_button.setPreferredSize(null);
            play_button.setMaximumSize(null);
            play_button.setMinimumSize(null);
            main_panel.add(play_button);
            play_button.setBounds(100, 160, 205, 45);

            //---- exit_button ----
            exit_button.setText("EXIT");
            exit_button.setBackground(new Color(0, 0, 0, 0));
            exit_button.setForeground(new Color(255, 0, 204));
            exit_button.setFont(exit_button.getFont().deriveFont(exit_button.getFont().getStyle() | Font.BOLD, exit_button.getFont().getSize() + 6f));
            exit_button.setBorder(new LineBorder(Color.yellow, 2, true));
            exit_button.setContentAreaFilled(false);
            exit_button.setPreferredSize(null);
            exit_button.setMaximumSize(null);
            exit_button.setMinimumSize(null);
            main_panel.add(exit_button);
            exit_button.setBounds(100, 220, 205, 45);
        }
        contentPane.add(main_panel);
        main_panel.setBounds(0, 0, 400, 400);

        contentPane.setPreferredSize(new Dimension(400, 425));
        setSize(400, 425);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel main_panel;
    private JLabel title;
    private JButton github_button;
    private JButton play_button;
    private JButton exit_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}