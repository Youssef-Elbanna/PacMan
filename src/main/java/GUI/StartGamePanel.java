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

        if(getDiff_spinner2().getValue()=="EASY"){
            System.out.println(getDiff_spinner2().getValue());
            Settings.set(EParam.ghost_speed,3.00);
        }else if(getDiff_spinner2().getValue()=="MEDIUM"){
            Settings.set(EParam.ghost_speed,4.00);
        }else if(getDiff_spinner2().getValue()=="HARD"){
            Settings.set(EParam.ghost_speed,5.00);
        }

        if(getMAP_spinner3().getValue()=="MAP 1"){
            Settings.set(EParam.line_color,Color.BLUE);
        } else if(getMAP_spinner3().getValue()=="MAP 2"){
            Settings.set(EParam.line_color,Color.RED);
            Settings.set(EParam.background_color,Color.BLACK);
            Settings.set(EParam.path_width,15);
            Settings.set(EParam.line_thickness,15);
        }
        else if(getMAP_spinner3().getValue()=="MAP 3"){
            Settings.set(EParam.line_color,Color.GREEN);
            Settings.set(EParam.background_color,Color.BLACK);
            Settings.set(EParam.path_width,8);
            Settings.set(EParam.line_thickness,8);
        }

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

        Difficulty.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(Difficulty.getFont().getSize())));
        Difficulty.setBounds(Scaler.scale(Difficulty.getX()), Scaler.scale(Difficulty.getY()), Scaler.scale(Difficulty.getWidth()), Scaler.scale(Difficulty.getHeight()));

        Map.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(Map.getFont().getSize())));
        Map.setBounds(Scaler.scale(Map.getX()), Scaler.scale(Map.getY()), Scaler.scale(Map.getWidth()), Scaler.scale(Map.getHeight()));


        lives_spinner.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(lives_spinner.getFont().getSize())));
        lives_spinner.setBounds(Scaler.scale(lives_spinner.getX()), Scaler.scale(lives_spinner.getY()), Scaler.scale(lives_spinner.getWidth()), Scaler.scale(lives_spinner.getHeight()));
        lives_spinner.setBorder(BorderFactory.createLineBorder(Color.yellow, Scaler.scale(3)));
        lives_spinner.getEditor().getComponent(0).setForeground(Color.magenta);
        lives_spinner.getEditor().getComponent(0).setBackground(new Color(0, 0, 27));
        lives_spinner.getEditor().getComponent(0);
        lives_spinner.setOpaque(true);

        Diff_spinner2.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(Diff_spinner2.getFont().getSize())));
        Diff_spinner2.setBounds(Scaler.scale(Diff_spinner2.getX()), Scaler.scale(Diff_spinner2.getY()), Scaler.scale(Diff_spinner2.getWidth()), Scaler.scale(Diff_spinner2.getHeight()));
        Diff_spinner2.setBorder(BorderFactory.createLineBorder(Color.yellow, Scaler.scale(3)));
        Diff_spinner2.getEditor().getComponent(0).setForeground(Color.magenta);
        Diff_spinner2.getEditor().getComponent(0).setBackground(new Color(0, 0, 27));
        Diff_spinner2.getEditor().getComponent(0);
        Diff_spinner2.setOpaque(true);

        MAP_spinner3.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(MAP_spinner3.getFont().getSize())));
        MAP_spinner3.setBounds(Scaler.scale(MAP_spinner3.getX()), Scaler.scale(MAP_spinner3.getY()), Scaler.scale(MAP_spinner3.getWidth()), Scaler.scale(MAP_spinner3.getHeight()));
        MAP_spinner3.setBorder(BorderFactory.createLineBorder(Color.yellow, Scaler.scale(3)));
        MAP_spinner3.getEditor().getComponent(0).setForeground(Color.magenta);
        MAP_spinner3.getEditor().getComponent(0).setBackground(new Color(0, 0, 27));
        MAP_spinner3.getEditor().getComponent(0);
        MAP_spinner3.setOpaque(true);
    
        back_button.setFont(Media.getFont(EFont.regular).deriveFont(Font.PLAIN, (int) Scaler.scale(back_button.getFont().getSize())));
        back_button.setBounds(Scaler.scale(back_button.getX()), Scaler.scale(back_button.getY()), Scaler.scale(back_button.getWidth()), Scaler.scale(back_button.getHeight()));
        back_button.setBorder(BorderFactory.createLineBorder(Color.yellow, Scaler.scale(2)));
        back_button.setOpaque(true);
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Youssef Elbanna
        starting_liv_label = new JLabel();
        start_game_button = new JButton();
        newgame_title = new JLabel();
        back_button = new JButton();
        lives_spinner = new JSpinner();
        Difficulty = new JLabel();
        Map = new JLabel();
        label1 = new JLabel();
        Diff_spinner2 = new JSpinner();
        MAP_spinner3 = new JSpinner();

        //======== this ========
        setMaximumSize(null);
        setMinimumSize(null);
        setPreferredSize(null);
        setBackground(new Color(0xaa000000, true));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
        . EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax
        . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,
        12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans
        . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .
        getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(null);

        //---- starting_liv_label ----
        starting_liv_label.setText("Starting Lives ");
        starting_liv_label.setForeground(new Color(0xff9900));
        starting_liv_label.setFont(starting_liv_label.getFont().deriveFont(starting_liv_label.getFont().getStyle() | Font.BOLD, starting_liv_label.getFont().getSize() + 7f));
        starting_liv_label.setHorizontalAlignment(SwingConstants.RIGHT);
        add(starting_liv_label);
        starting_liv_label.setBounds(0, 140, 205, 31);

        //---- start_game_button ----
        start_game_button.setText("START GAME");
        start_game_button.setBackground(new Color(0x00001b));
        start_game_button.setBorder(new LineBorder(new Color(0x3366ff), 2, true));
        start_game_button.setFont(start_game_button.getFont().deriveFont(start_game_button.getFont().getStyle() | Font.BOLD, start_game_button.getFont().getSize() + 7f));
        start_game_button.setForeground(new Color(0xff9900));
        start_game_button.setContentAreaFilled(false);
        add(start_game_button);
        start_game_button.setBounds(35, 285, 325, 55);

        //---- newgame_title ----
        newgame_title.setText("NEW GAME");
        newgame_title.setFont(new Font(Font.SANS_SERIF, newgame_title.getFont().getStyle(), newgame_title.getFont().getSize() + 32));
        newgame_title.setForeground(new Color(0xff9900));
        newgame_title.setMaximumSize(null);
        newgame_title.setMinimumSize(null);
        newgame_title.setPreferredSize(null);
        newgame_title.setHorizontalAlignment(SwingConstants.CENTER);
        add(newgame_title);
        newgame_title.setBounds(0, 20, 395, newgame_title.getPreferredSize().height);

        //---- back_button ----
        back_button.setText(" \ud83d\udd19 back");
        back_button.setBackground(new Color(0x00001b));
        back_button.setBorder(new LineBorder(new Color(0x3366ff), 2, true));
        back_button.setFont(back_button.getFont().deriveFont(back_button.getFont().getStyle() | Font.BOLD, back_button.getFont().getSize() + 1f));
        back_button.setForeground(new Color(0xff9900));
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
        lives_spinner.setBackground(new Color(0x00001b));
        add(lives_spinner);
        lives_spinner.setBounds(225, 140, 100, 30);

        //---- Difficulty ----
        Difficulty.setText("Difficulty");
        Difficulty.setForeground(new Color(0xff9900));
        Difficulty.setFont(Difficulty.getFont().deriveFont(Difficulty.getFont().getStyle() | Font.BOLD, Difficulty.getFont().getSize() + 7f));
        Difficulty.setHorizontalAlignment(SwingConstants.RIGHT);
        add(Difficulty);
        Difficulty.setBounds(-5, 180, 205, 31);

        //---- Map ----
        Map.setText("MAP");
        Map.setForeground(new Color(0xff9900));
        Map.setFont(Map.getFont().deriveFont(Map.getFont().getStyle() | Font.BOLD, Map.getFont().getSize() + 7f));
        Map.setHorizontalAlignment(SwingConstants.RIGHT);
        add(Map);
        Map.setBounds(-5, 220, 205, 31);

        //---- label1 ----
        label1.setText("________________________________");
        add(label1);
        label1.setBounds(new Rectangle(new Point(105, 75), label1.getPreferredSize()));

        //---- Diff_spinner2 ----
        Diff_spinner2.setModel(new SpinnerListModel(new String[] {"EASY", "MEDIUM", "HARD"}));
        Diff_spinner2.setForeground(Color.black);
        Diff_spinner2.setMaximumSize(null);
        Diff_spinner2.setMinimumSize(null);
        Diff_spinner2.setPreferredSize(null);
        Diff_spinner2.setFont(Diff_spinner2.getFont().deriveFont(Diff_spinner2.getFont().getStyle() | Font.BOLD, Diff_spinner2.getFont().getSize() + 4f));
        Diff_spinner2.setBackground(new Color(0x00001b));
        add(Diff_spinner2);
        Diff_spinner2.setBounds(225, 180, 100, 30);

        //---- MAP_spinner3 ----
        MAP_spinner3.setModel(new SpinnerListModel(new String[] {"MAP 1", "MAP 2", "MAP 3"}));
        MAP_spinner3.setForeground(Color.black);
        MAP_spinner3.setMaximumSize(null);
        MAP_spinner3.setMinimumSize(null);
        MAP_spinner3.setPreferredSize(null);
        MAP_spinner3.setBackground(new Color(0x00001b));
        MAP_spinner3.setFont(MAP_spinner3.getFont().deriveFont(MAP_spinner3.getFont().getSize() + 5f));
        add(MAP_spinner3);
        MAP_spinner3.setBounds(225, 225, 100, 30);

        setPreferredSize(new Dimension(400, 400));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Youssef Elbanna
    private JLabel starting_liv_label;
    private JButton start_game_button;
    private JLabel newgame_title;
    private JButton back_button;
    private JSpinner lives_spinner;
    private JLabel Difficulty;
    private JLabel Map;
    private JLabel label1;
    private JSpinner Diff_spinner2;
    private JSpinner MAP_spinner3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    
    //setters and getters
    public MainMenu getMenu(){
        return menu;
    }
    
    public JLabel getStarting_liv_label(){
        return starting_liv_label;
    }
    
    public JSpinner getLives_spinner(){
        return lives_spinner;
    }
    public JSpinner getDiff_spinner2(){
    return Diff_spinner2;
    }
    public JSpinner getMAP_spinner3(){
        return MAP_spinner3;
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