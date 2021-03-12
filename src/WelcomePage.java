import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage implements ActionListener {

    //skapar Gui
    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel();
    JButton administrativUser = new JButton("Admin");
    JButton kundUser = new JButton("Kund användare");


    WelcomePage(){


        // Gui inställningar
        welcomeLabel.setBounds(0,0,500,35);
        welcomeLabel.setFont(new Font(null,Font.PLAIN,25));
        welcomeLabel.setText("Välkommen Välj användningsområde");
        administrativUser.setBounds(225,200,200,25);
        kundUser.setBounds(225,250,200,25);

        administrativUser.addActionListener(this);
        kundUser.addActionListener(this);

        frame.add(administrativUser);
        frame.add(kundUser);
        frame.add(welcomeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);



    }

    // ActionListener för att ta dig vidare till antingen Admin sidan eller låntagar sidan.

    @Override
    public void actionPerformed(ActionEvent e) {

      if (e.getSource()==administrativUser){
          frame.dispose();

       //   Admin admin = new Admin();
          new Admin();


      }else if (e.getSource()==kundUser){
          Låntagare gui = new Låntagare();

      }

    }
}
