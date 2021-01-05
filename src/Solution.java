import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class ChessClock {
    int time1 = 600000, second1 = 0, minute1 = 10, hour1 = 0;
    int time2 = 600000, second2 = 0, minute2 = 10, hour2 = 0;
    private static int xOffset = 0, yOffset = 0;

    String seconds1=String.format("%02d",second1);
    String minutes1=String.format("%02d",minute1);
    String hours1=String.format("%02d",hour1);

    String seconds2=String.format("%02d",second2);
    String minutes2=String.format("%02d",minute2);
    String hours2=String.format("%02d",hour2);

    JToggleButton start1 = new JToggleButton(hours1+":"+minutes1+":"+seconds1);
    JButton pause = new JButton(new ImageIcon("F:\\CSE\\CSE215\\cse\\src\\pause.png"));
    JToggleButton start2 = new JToggleButton(hours2 + ":" + minutes2 + ":" + seconds2);
    JButton close = new JButton(new ImageIcon("F:\\CSE\\CSE215\\cse\\src\\close.png"));
    JButton settings = new JButton(new ImageIcon("F:\\CSE\\CSE215\\cse\\src\\settings.png"));
    JButton reset = new JButton(new ImageIcon("F:\\CSE\\CSE215\\cse\\src\\reset.jpg"));
    JFrame frame = new JFrame();

    Timer timer1 = new Timer(1000, ev -> {
        time1-=1000;
        hour1 = (time1/3600000);
        minute1 = (time1/60000) % 60;
        second1 = (time1/1000) % 60;
        seconds1 = String.format("%02d", second1);
        minutes1 = String.format("%02d", minute1);
        hours1 = String.format("%02d", hour1);
        if(time1>=0) {
            start1.setText(hours1+":"+minutes1+":"+seconds1);
        }
    });
    Timer timer2 = new Timer(1000, ev -> {
        time2-=1000;
        hour2 = (time2/3600000);
        minute2 = (time2/60000) % 60;
        second2 = (time2/1000) % 60;
        seconds2 = String.format("%02d", second2);
        minutes2 = String.format("%02d", minute2);
        hours2 = String.format("%02d", hour2);
        if(time1>=0) {
            start2.setText(hours2+":"+minutes2+":"+seconds2);
        }
    });

    public ChessClock(){
        start1.addActionListener(e -> {
            timer1.start();
            timer2.stop();
            start2.setSelected(false);
        });
        start2.addActionListener(e -> {
            timer1.stop();
            timer2.start();
            start1.setSelected(false);
        });
        pause.addActionListener(e -> {
            timer1.stop();
            timer2.stop();
            start1.setSelected(false);
            start2.setSelected(false);
        });
        close.addActionListener(e -> {
            System.exit(0);
        });
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
            String[] fish = {"Fischer", "1", "2", "3", "5"};

            JComboBox hour_bar = new JComboBox(hour);
            if(hour_bar.getSelectedItem() == "1") {
                hour1 = hour2 = 1;
                time1 = 3600000;
            }
            else if(hour_bar.getSelectedItem() == "2") {
                hour1 = hour2 = 2;
                time1 = time2 = 3600000 * 2;
            }
            else if(hour_bar.getSelectedItem() == "5") {
                hour1 = hour2 = 5;
                time1 = time2 = 3600000 * 4;
            }
            JComboBox min_bar = new JComboBox(min);
            if(min_bar.getSelectedItem() == "1") {
                minute1 = minute2 = 1;
                if(hour1 != 0) {
                    time1 += 60000;
                    time2 += 60000;
                }
                else {
                    time1 = time2 = 60000;
                }
            }
            else if(min_bar.getSelectedItem() == "3") {
                minute1 = minute2 = 3;
                if(hour1 != 0) {
                    time1 += 60000 * 3;
                    time2 += 60000 * 3;
                }
                else {
                    time1 = time2 = 60000 * 3;
                }
            }
            else if(min_bar.getSelectedItem() == "5") {
                minute1 = minute2 = 5;
                if(hour1 != 0) {
                    time1 += 60000 * 5;
                    time2 += 60000 * 5;
                }
                else {
                    time1 = time2 = 60000 * 5;
                }
            }
            else if(min_bar.getSelectedItem() == "10") {
                minute1 = minute2 = 10;
                if(hour1 != 0) {
                    time1 += 60000 * 10;
                    time2 += 60000 * 10;
                }
                else {
                    time1 = time2 = 60000 * 10;
                }
            }
            else if(min_bar.getSelectedItem() == "15") {
                minute1 = minute2 = 15;
                if(hour1 != 0) {
                    time1 += 60000 * 15;
                    time2 += 60000 * 15;
                }
                else {
                    time1 = time2 = 60000 * 15;
                }
            }
            else if(min_bar.getSelectedItem() == "30") {
                minute1 = minute2 = 30;
                if(hour1 != 0) {
                    time1 += 60000 * 30;
                    time2 += 60000 * 30;
                }
                else {
                    time1 = time2 = 60000 * 30;
                }
            }
            JComboBox fish_bar = new JComboBox(fish);
            JButton done = new JButton("Done");


            hour_bar.setBounds(150, 75,100,30);
            min_bar.setBounds(150, 125, 100,30);
            fish_bar.setBounds(150, 175, 100, 30);
            done.setBounds(165, 275, 70,20);

            done.addActionListener(ev -> {
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

        start2.setBounds(70, 380, 250, 110);
        start2.setFont(new Font("San Francisco", Font.PLAIN, 20));
        start2.setFocusable(false);

        pause.setBounds(170, 280, 50, 50);

        close.setBounds(350, 30, 20, 20);

        settings.setBounds(90, 280, 50, 50);

        reset.setBounds(250, 280, 50, 50);
    }
}
public class Solution {
    public static void main(String[] args) {
        ChessClock s = new ChessClock();
    }
}
