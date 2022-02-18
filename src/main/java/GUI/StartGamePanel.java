package GUI;

import AnimationEngine.BlinkAnimator;
import AudioEngine.AudioEngine;
import AudioEngine.FunctionCallback;
import AudioEngine.PlaybackMode;
import Game.Game;
import Media.EAudio;
import Media.EFont;
import Media.Media;
import Painter.Scaler;
import Settings.EParam;
import Settings.Settings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * The panel responsible for setting the pacmans lives and starting the game.
 */
public class StartGamePanel extends JPanel {
    
    private MainMenu menu;
    
    /**
     * Initializes the StartGamePanel panel.
     * @param size the size of the panel
     * @param mainmenu the mainmenu
     */
    public StartGamePanel(int size, MainMenu mainmenu) {
        super();
        initComponents();
        adjustSizes(size);
        
        this.menu = mainmenu;
        
        start_game_button.addActionListener((e)->{
            BlinkAnimator blink =  new BlinkAnimator(start_game_button, 80, true);
            blink.start();
            AudioEngine.play(EAudio.button_click, PlaybackMode.regular, new FunctionCallback() {
                @Override
                public void callback() {
                    blink.stop();
                    startGame();
                }
            });
        });
        back_button.addActionListener((e)->{
            BlinkAnimator blink =  new BlinkAnimator(back_button, 80, true);
            blink.start();
            AudioEngine.play(EAudio.button_click, PlaybackMode.regular, new FunctionCallback() {
                @Override
                public void callback(){
                    blink.stop();
                    menu.showMainPanel();
                }
            });
        });
        
        
        repaint();
    }
    
    /**
     * Starts the game
     */
    public void startGame() {
        Settings.set(EParam.pacman_starting_lives, getLives_spinner().getValue());
        AudioEngine.stop(EAudio.ost);
        new Game();
    }
    
    public void adjustSizes(int size) {
        setSize(size,size);
        int width = this.getHeight();
        int height = this.getHeight();
        
        setBounds(0,0,width, height);
        newgame_title.setBounds(Scaler.scale(newgame_title.getX()), Scaler.scale(newgame_title.getY()), Scaler.scale(newgame_title.getWidth()), Scaler.scale(newgame_title.getHeight()));
        newgame_title.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, Scaler.scale(newgame_title.getFont().getSize())));
        
        start_game_button.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(start_game_button.getFont().getSize())));
        start_game_button.setBounds(Scaler.scale(start_game_button.getX()), Scaler.scale(start_game_button.getY()), Scaler.scale(start_game_button.getWidth()), Scaler.scale(start_game_button.getHeight()));
        start_game_button.setBorder(BorderFactory.createLineBorder(Color.yellow, Scaler.scale(3)));
        start_game_button.setOpaque(true);
        
        starting_liv_label.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(starting_liv_label.getFont().getSize())));
        starting_liv_label.setBounds(Scaler.scale(starting_liv_label.getX()), Scaler.scale(starting_liv_label.getY()), Scaler.scale(starting_liv_label.getWidth()), Scaler.scale(starting_liv_label.getHeight()));
    
        lives_spinner.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(lives_spinner.getFont().getSize())));
        lives_spinner.setBounds(Scaler.scale(lives_spinner.getX()), Scaler.scale(lives_spinner.getY()), Scaler.scale(lives_spinner.getWidth()), Scaler.scale(lives_spinner.getHeight()));
        lives_spinner.setBorder(BorderFactory.createLineBorder(Color.yellow, Scaler.scale(3)));
        lives_spinner.getEditor().getComponent(0).setForeground(Color.magenta);
        lives_spinner.getEditor().getComponent(0).setBackground(new Color(0, 0, 27));
        lives_spinner.getEditor().getComponent(0);
        lives_spinner.setOpaque(true);
    
        back_button.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(back_button.getFont().getSize())));
        back_button.setBounds(Scaler.scale(back_button.getX()), Scaler.scale(back_button.getY()), Scaler.scale(back_button.getWidth()), Scaler.scale(back_button.getHeight()));
        back_button.setBorder(BorderFactory.createLineBorder(Color.yellow, Scaler.scale(2)));
        back_button.setOpaque(true);
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        starting_liv_label = new JLabel();
        start_game_button = new JButton();
        newgame_title = new JLabel();
        back_button = new JButton();
        lives_spinner = new JSpinner();

        //======== this ========
        setMaximumSize(null);
        setMinimumSize(null);
        setPreferredSize(null);
        setBackground(new Color(0, 0, 0, 170));
        setLayout(null);

        //---- starting_liv_label ----
        starting_liv_label.setText("Starting Lives ");
        starting_liv_label.setForeground(Color.magenta);
        starting_liv_label.setFont(starting_liv_label.getFont().deriveFont(starting_liv_label.getFont().getStyle() | Font.BOLD, starting_liv_label.getFont().getSize() + 7f));
        starting_liv_label.setHorizontalAlignment(SwingConstants.RIGHT);
        add(starting_liv_label);
        starting_liv_label.setBounds(0, 155, 205, 31);

        //---- start_game_button ----
        start_game_button.setText("START GAME");
        start_game_button.setBackground(new Color(0, 0, 27));
        start_game_button.setBorder(new LineBorder(Color.yellow, 2, true));
        start_game_button.setFont(start_game_button.getFont().deriveFont(start_game_button.getFont().getStyle() | Font.BOLD, start_game_button.getFont().getSize() + 7f));
        start_game_button.setForeground(new Color(255, 0, 204));
        start_game_button.setContentAreaFilled(false);
        add(start_game_button);
        start_game_button.setBounds(100, 240, 190, 55);

        //---- newgame_title ----
        newgame_title.setText("NEW GAME");
        newgame_title.setFont(new Font(Font.SANS_SERIF, newgame_title.getFont().getStyle(), newgame_title.getFont().getSize() + 32));
        newgame_title.setForeground(Color.yellow);
        newgame_title.setMaximumSize(null);
        newgame_title.setMinimumSize(null);
        newgame_title.setPreferredSize(null);
        newgame_title.setHorizontalAlignment(SwingConstants.CENTER);
        add(newgame_title);
        newgame_title.setBounds(0, 40, 395, newgame_title.getPreferredSize().height);

        //---- back_button ----
        back_button.setText("- back");
        back_button.setBackground(new Color(0, 0, 27));
        back_button.setBorder(new LineBorder(Color.yellow, 2, true));
        back_button.setFont(back_button.getFont().deriveFont(back_button.getFont().getStyle() | Font.BOLD, back_button.getFont().getSize() + 1f));
        back_button.setForeground(new Color(255, 0, 204));
        back_button.setContentAreaFilled(false);
        add(back_button);
        back_button.setBounds(15, 360, 70, 30);

        //---- lives_spinner ----
        lives_spinner.setModel(new SpinnerNumberModel(3, 1, null, 1));
        lives_spinner.setForeground(Color.black);
        lives_spinner.setMaximumSize(null);
        lives_spinner.setMinimumSize(null);
        lives_spinner.setPreferredSize(null);
        lives_spinner.setFont(lives_spinner.getFont().deriveFont(lives_spinner.getFont().getSize() + 11f));
        lives_spinner.setBackground(new Color(0, 0, 27));
        add(lives_spinner);
        lives_spinner.setBounds(225, 155, 100, 30);

        setPreferredSize(new Dimension(400, 400));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel starting_liv_label;
    private JButton start_game_button;
    private JLabel newgame_title;
    private JButton back_button;
    private JSpinner lives_spinner;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    
    
    public MainMenu getMenu(){
        return menu;
    }
    
    public JLabel getStarting_liv_label(){
        return starting_liv_label;
    }
    
    public JSpinner getLives_spinner(){
        return lives_spinner;
    }
    
    public JButton getStart_game_button(){
        return start_game_button;
    }
    
    public JLabel getNewgame_title(){
        return newgame_title;
    }
    
    public JButton getBack_button(){
        return back_button;
    }
}