import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;




public class LoginPage implements ActionListener {

// skapar Gui Komponenter
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIdField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("UserID");
    JLabel userPWLabel = new JLabel("Password");
    JLabel messageLabel = new JLabel();



    LoginPage() {

       // Ger komponenterna deras positioner
        userIDLabel.setBounds(50, 100, 75, 25);
        userPWLabel.setBounds(50, 150, 75, 25);
        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));

        userIdField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);

        resetButton.setBounds(125, 200, 100, 25);
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);

        loginButton.setBounds(225, 200, 100, 25);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);


        // lägger till komponenterna  till själva Gui rutan
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(userIDLabel);
        frame.add(userPWLabel);
        frame.add(messageLabel);
        frame.add(userIdField);
        frame.add(userPasswordField);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

          userIdField.setText("låntagare");
          userPasswordField.setText("hejhej123!");

    }


      // ActionListener för vad som ska ske när man klickar på knapparna
    @Override
    public void actionPerformed(ActionEvent e) {

        // Sätter att det inte ska vara någon text i textfieldsen
        if (e.getSource()==resetButton){
            userIdField.setText("");
            userPasswordField.setText("");
        }
        //Skapar 2 strings med inloggningsinforationen användaren skriver in och kopplar upp till sql.
        // samt en instans av klassen Welcomepage.
        if (e.getSource()==loginButton){
            try {
                String userName = userIdField.getText() ;
                String pass = userPasswordField.getText() ;
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/slutupg",userName,pass);

                con.close();
                messageLabel.setForeground(Color.green);
                messageLabel.setText("Login Succesfull!");
                frame.dispose();
                WelcomePage welcome = new WelcomePage();
                userName = "";
                pass = "";

            } catch (Exception a) {
                System.out.println(a);
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Wrong password");

            }


        }



    }

}
