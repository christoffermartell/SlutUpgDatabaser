import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin {

    JFrame frame = new JFrame("Administrativ Användare");
    JTextField txt = new JTextField("type",1);
    JTextArea txta = new JTextArea();
    JTextField userIdField = new JTextField();
    JPasswordField userPwField = new JPasswordField();
    JButton b1 = new JButton("Reset");
    JLabel label = new JLabel("Id & Lösenord för att kuna söka.");
    JPanel  JP = new JPanel(new GridLayout(2,1));
    String name;
    JButton b2 = new JButton("SÖk 2x Click");
    JButton b3 = new JButton("Anställda Redigering -->");


    Admin(){


        var ref = new Object() {
            String answer = "";
        };
        String q0 = "Select * from låntagare_information where LånadeBöcker = ? ;";



        name = "-1";

        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        userIdField.setVisible(true);
        userIdField.setBounds(500, 100, 200, 25);
        userPwField.setBounds(500, 125, 200, 25);
        txt.setBounds(10,280,400,25);
        txta.setBounds(10,300,500,25);
        txta.setSize(760,300);
        b1.setBounds(410,280,75,25);
        b3.setBounds(10,20,250,25);
        label.setBounds(500,50,200,25);
        txta.setBackground(Color.lightGray);

        System.out.println("hej");

        JP.add(userPwField);
        JP.add(userPwField);


        frame.add(JP);
        frame.add(label);
        frame.add(b1);
        frame.add(txt);
        frame.add(txta);
        frame.add(userIdField);
        frame.add(userPwField);
        frame.setVisible(true);
        frame.add(b3);


        ActionListener d = e->{
            new Anställdared();

        };

        ActionListener a = e-> {txta.setText("");
        txt.setText("");

        };

        b1.addActionListener(a);
        b3.addActionListener(d);


        b2.setBounds(500,250,130,50);
        frame.add(b2);


        ActionListener s = e->{

            String input = "-1";

                if (input.equals("-1")) {
                    input = getValue();
                    if (checkInput(input)) {
                        try {
                            String Id = userIdField.getText();
                            String Pw = userPwField.getText();

                            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/slutupg", Id, Pw);
                            PreparedStatement pstm = connect.prepareStatement(q0);
                            pstm.setString(1, input);
                           
                            ResultSet rs = pstm.executeQuery();
                            while (rs.next())
                                ref.answer = ref.answer + "\n" + (rs.getInt(1)
                                        + "  " + rs.getString(2)
                                        + "  " + rs.getString(3)
                                        + "  " + rs.getString(4)
                                        + " " + rs.getString(5)
                                        + " " + rs.getString(6)
                                        + " " + rs.getString(7)
                                        + " " + rs.getString(8));
                            connect.close();

                        } catch (Exception l) {
                            System.out.println(l);

                        }
                    }
                    input = "-1";
                    this.name = txt.getText();
                }
                set(ref.answer);

        };

        b2.addActionListener(s);


    }
    public String getValue() {
        if (this.name.equals("-1")){ return "-1"; }

        String n = name;
        name = "-1";
        //   System.out.println("Returning" +n);
        return n;
    }
    public void set(String text){
        this.txta.setText("ans: \n"+ text);

    }
    public static boolean checkInput(String input){
        Pattern p = Pattern.compile("[A-Z]([a-z]{1,25})");

        Matcher matcher = p.matcher(input);

        while (matcher.find()){
            if(matcher.group().length() != 0){
                return true;
            }

        }
        return false;

    }

}
