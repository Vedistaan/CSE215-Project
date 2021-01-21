import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

class ChessClock {
    int click=0;
    int time1 = 600000, second1 = 0, minute1 = 10, hour1 = 0;
    int time2 = 600000, second2 = 0, minute2 = 10, hour2 = 0;
    private static int xOffset = 0, yOffset = 0;

    String seconds1=String.format("%02d",second1);
    String minutes1=String.format("%02d",minute1);
    String hours1=String.format("%02d",hour1);

    String seconds2=String.format("%02d",second2);
    String minutes2=String.format("%02d",minute2);
    String hours2=String.format("%02d",hour2);
    
    String[] fish = {"Fischer", "1", "2", "3", "5"};
    JComboBox<String> fish_bar = new JComboBox<>(fish);

    JToggleButton start1 = new JToggleButton(hours1+":"+minutes1+":"+seconds1);
    JButton pause = new JButton(new ImageIcon("F:\\CSE\\CSE215\\cse\\src\\pause.png"));
    JToggleButton start2 = new JToggleButton(hours2 + ":" + minutes2 + ":" + seconds2);
    JButton close = new JButton(new ImageIcon("F:\\CSE\\CSE215\\cse\\src\\close.png"));
    JButton settings = new JButton(new ImageIcon("F:\\CSE\\CSE215\\cse\\src\\settings.png"));
    JToggleButton music = new JToggleButton("Sound");
    JButton reset = new JButton(new ImageIcon("F:\\CSE\\CSE215\\cse\\src\\reset.jpg"));
    JFrame frame = new JFrame();

    Timer timer1 = new Timer(1000, ev -> {
        if(music.isSelected()) {
            playSound();
        }
        if(time1>0) {
            time1-=1000;
            hour1 = (time1/3600000);
            minute1 = (time1/60000) % 60;
            second1 = (time1/1000) % 60;
            seconds1 = String.format("%02d", second1);
            minutes1 = String.format("%02d", minute1);
            hours1 = String.format("%02d", hour1);
            start1.setText(hours1 + ":" + minutes1 + ":" + seconds1);
        }
        else{
            start1.setText("Time Up");
            start2.setText("Winner");
        }
    });
    Timer timer2 = new Timer(1000, ev -> {
        if(music.isSelected()) {
            playSound();
        }
        if(time2>0) {
            time2-=1000;
            hour2 = (time2/3600000);
            minute2 = (time2/60000) % 60;
            second2 = (time2/1000) % 60;
            seconds2 = String.format("%02d", second2);
            minutes2 = String.format("%02d", minute2);
            hours2 = String.format("%02d", hour2);
            start2.setText(hours2 + ":" + minutes2 + ":" + seconds2);
        }
        else{
            start1.setText("Winner");
            start2.setText("Time Up");
        }
    });
    
    public void fischer1(){
        if(time1>0) {
            if (fish_bar.getSelectedItem() == "1") {
            second1+=1;
            time1+=1000;
            }
            else if (fish_bar.getSelectedItem() == "2") {
            second1+=2;
            time1+=1000*2;
            }
            else if (fish_bar.getSelectedItem() == "3") {
            second1+=3;
            time1+=1000*3;
            }
            else if (fish_bar.getSelectedItem() == "5") {
            second1+=5;
            time1+=1000*5;
            }
            hour1 = (time1/3600000);
            minute1 = (time1/60000) % 60;
            second1 = (time1/1000) % 60;
            seconds1 = String.format("%02d", second1);
            minutes1 = String.format("%02d", minute1);
            hours1 = String.format("%02d", hour1);
            start1.setText(hours1+":"+minutes1+":"+seconds1);
        }
    }
    
    public void fischer2(){
        if(time2>0) {
            if (fish_bar.getSelectedItem() == "1") {
            second2+=1;
            time2+=1000;
            }
            else if (fish_bar.getSelectedItem() == "2") {
            second2+=2;
            time2+=1000*2;
            }
            else if (fish_bar.getSelectedItem() == "3") {
            second2+=3;
            time2+=1000*3;
            }
            else if (fish_bar.getSelectedItem() == "5") {
            second2+=5;
            time2+=1000*5;
            }
            hour2 = (time2/3600000);
            minute2 = (time2/60000) % 60;
            second2 = (time2/1000) % 60;
            seconds2 = String.format("%02d", second2);
            minutes2 = String.format("%02d", minute2);
            hours2 = String.format("%02d", hour2);
            start2.setText(hours2+":"+minutes2+":"+seconds2);
        }
    }

    public ChessClock(){
        frame.getContentPane().setBackground(Color.lightGray);
        start1.addActionListener(e -> {
            if(music.isSelected()) {
                playSound();
            }
            timer1.stop();
            if(click!=0) {
                fischer1();
            }
            timer2.start();
            start2.setSelected(false);
            click++;
        });
        start2.addActionListener(e -> {
            timer2.stop();
            fischer2();
            timer1.start();
            start1.setSelected(false);
        });
        pause.addActionListener(e -> {
            timer1.stop();
            timer2.stop();
            start1.setSelected(false);
            start2.setSelected(false);
        });
        close.addActionListener(e -> System.exit(0));
        settings.addActionListener(e -> {
            JFrame setting_window = new JFrame();
            setting_window.setTitle("Clock Configuration");
            setting_window.setResizable(false);
            setting_window.setBounds(500, 200, 400, 400);
            setting_window.setResizable(false);
            setting_window.setLayout(null);
            setting_window.setUndecorated(true);
            frame.setOpacity(0.5F);


            String[] hour = {"Hours", "1", "2", "5"};
            String[] min = {"Minutes", "1", "3", "5", "10", "15", "30"};

            JComboBox<String> hour_bar = new JComboBox<>(hour);
            JComboBox<String> min_bar = new JComboBox<>(min);
            JButton done = new JButton("Done");


            hour_bar.setBounds(150, 75,100,30);
            min_bar.setBounds(150, 125, 100,30);
            fish_bar.setBounds(150, 175, 100, 30);
            done.setBounds(165, 275, 70,20);

            done.addActionListener(ev -> {
                click=0;
                
                if(hour_bar.getSelectedItem() == "1") {
                    hour1=hour2=1;
                    time1=time2=3600000;
                }
                else if(hour_bar.getSelectedItem() == "2") {
                    hour1=hour2=2;
                    time1=time2=3600000*2;
                }
                else if(hour_bar.getSelectedItem() == "5") {
                    hour1=hour2=5;
                    time1=time2=3600000*5;
                }
                if (min_bar.getSelectedItem() == "1") {
                    minute1 = minute2 = 1;
                    if(hour1 != 0 && hour2 != 0) {
                        time1 += 60000;
                        time2 += 60000;
                    }
                    else {
                        time1 = time2 = 60000;
                    }
                }
                else if(min_bar.getSelectedItem() == "3") {
                    minute1 = minute2 = 3;
                    if(hour1 != 0 && hour2 != 0) {
                        time1 += 60000 * 3;
                        time2 += 60000 * 3;
                    }
                    else {
                        time1 = time2 = 60000 * 3;
                    }
                }
                else if(min_bar.getSelectedItem() == "5") {
                    minute1 = minute2 = 5;
                    if(hour1 != 0 && hour2 != 0) {
                        time1 += 60000 * 5;
                        time2 += 60000 * 5;
                    }
                    else {
                        time1 = time2 = 60000 * 5;
                    }
                }
                else if(min_bar.getSelectedItem() == "10") {
                    minute1 = minute2 = 10;
                    if(hour1 != 0 && hour2 != 0) {
                        time1 += 60000 * 10;
                        time2 += 60000 * 10;
                    }
                    else {
                        time1 = time2 = 60000 * 10;
                    }
                }
                else if(min_bar.getSelectedItem() == "15") {
                    minute1 = minute2 = 15;
                    if(hour1 != 0 && hour2 != 0) {
                        time1 += 60000 * 15;
                        time2 += 60000 * 15;
                    }
                    else {
                        time1 = time2 = 60000 * 15;
                    }
                }
                else if(min_bar.getSelectedItem() == "30") {
                    minute1 = minute2 = 30;
                    if(hour1 != 0 && hour2 != 0) {
                        time1 += 60000 * 30;
                        time2 += 60000 * 30;
                    }
                    else {
                        time1 = time2 = 60000 * 30;
                    }
                }
                
                String seconds1=String.format("%02d",second1);
                String minutes1=String.format("%02d",minute1);
                String hours1=String.format("%02d",hour1);

                String seconds2=String.format("%02d",second2);
                String minutes2=String.format("%02d",minute2);
                String hours2=String.format("%02d",hour2);

                start1.setText(hours1+":"+minutes1+":"+seconds1);
                start2.setText(hours2+":"+minutes2+":"+seconds2);

                frame.setOpacity(1);
                setting_window.dispose();
            });

            setting_window.add(hour_bar);
            setting_window.add(min_bar);
            setting_window.add(fish_bar);
            setting_window.add(done);

            setting_window.setVisible(true);
        });
        reset.addActionListener(e -> {
            click=0;
            
            time1 = 600000; second1 = 0; minute1 = 10; hour1 = 0;
            time2 = 600000; second2 = 0; minute2 = 10; hour2 = 0;

            String seconds1=String.format("%02d",second1);
            String minutes1=String.format("%02d",minute1);
            String hours1=String.format("%02d",hour1);

            String seconds2=String.format("%02d",second2);
            String minutes2=String.format("%02d",minute2);
            String hours2=String.format("%02d",hour2);

            start1.setText(hours1+":"+minutes1+":"+seconds1);
            start2.setText(hours2+":"+minutes2+":"+seconds2);
        });

        frame.add(music);
        frame.add(start1);
        frame.add(pause);
        frame.add(start2);
        frame.add(close);
        frame.add(settings);
        frame.add(reset);

        frame.setUndecorated(true);
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                xOffset = e.getX();
                yOffset = e.getY();
            }
        });
        frame.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                frame.setLocation(evt.getXOnScreen() - xOffset, evt.getYOnScreen() - yOffset);
            }
        });

        frame.setTitle("Chess Clock");
        frame.setBounds(500, 100, 400, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);

        start1.setBounds(70, 120, 250, 110);
        start1.setFont(new Font("San Francisco", Font.PLAIN, 20));
        start1.setFocusable(false);
        start1.setBackground(Color.white);
        start1.setOpaque(true);
        start1.setBorder(BorderFactory.createEmptyBorder());

        start2.setBounds(70, 380, 250, 110);
        start2.setFont(new Font("San Francisco", Font.PLAIN, 20));
        start2.setFocusable(false);
        start2.setBackground(Color.white);
        start2.setOpaque(true);
        start2.setBorder(BorderFactory.createEmptyBorder());

        pause.setBounds(170, 280, 50, 50);
        pause.setBorder(BorderFactory.createEmptyBorder());

        close.setBounds(350, 30, 20, 20);
        close.setBorder(BorderFactory.createEmptyBorder());
        close.setBackground(Color.white);
        close.setOpaque(true);

        settings.setBounds(90, 280, 50, 50);
        settings.setBorder(BorderFactory.createEmptyBorder());

        reset.setBounds(250, 280, 50, 50);
        reset.setBorder(BorderFactory.createEmptyBorder());

        music.setBounds(240, 550, 70, 20);
    }
    public void playSound()
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\Vedistaan Windrunner\\Documents\\GitHub\\CSE215-Project\\src\\tick.wav"));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }
}
public class Solution {
    public static void main(String[] args) {
        ChessClock s = new ChessClock();
    }
}
