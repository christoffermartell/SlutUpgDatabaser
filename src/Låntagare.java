import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Låntagare  {

    // skapar Gui

    JFrame frame = new JFrame("Låntagare Användare");
    JTextField txt = new JTextField("type",1);
    JTextArea txta = new JTextArea();
    JTextField userIdField = new JTextField();
    JPasswordField userPwField = new JPasswordField();
    JButton b1 = new JButton("Reset");
    JLabel label = new JLabel("Id & Lösenord för att kuna söka.");
    JPanel  JP = new JPanel(new GridLayout(2,1));
    String name;
    JButton b2 = new JButton("Sök efter Bok");
    JButton b3 = new JButton("Sök Tidsskrift");

    public Låntagare(){

        // implementerades med hjälp av intelliJ, inte riktigt säker på varför det behövdes.
        var ref = new Object() {
            String answer = "";
        };

        // Querys med sql kod för preparedStatement.
        String q0 = "Select * from Bok_information where Titel = ? ;";
        String q1 = "Select * from tidsskrifter where Titel = ?";



        name = "-1";

        // Gui inställningar
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        userIdField.setVisible(true);
        userIdField.setBounds(300, 100, 200, 25);
        userPwField.setBounds(300, 125, 200, 25);
        txt.setBounds(10,280,400,25);
        txta.setBounds(10,300,500,25);
        txta.setSize(760,300);
        b1.setBounds(410,280,75,25);
        label.setBounds(300,50,200,25);
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


        ActionListener a = e-> {txta.setText("");
         txt.setText("");
        };
        b1.addActionListener(a);

        b2.setBounds(500,250,130,50);
        frame.add(b2);
        b3.setBounds(630,250,120,50);
        frame.add(b3);

        // Actionlistener som Kontrollerar att att värdet är enligt regex koden i metoden Checkinput
        // sendan skapar vi Stringar för att kontrollera att det är rätt user.
        // Kopplar upp oss med sql och använder sedan preparedStatement för säkerhetsskäl, sedan skriver vi ut resultaten.

        ActionListener n = e->{
            String input = "-1";
            if (input.equals("-1")) {
                input = getValue();
                if (checkInput(input)) {
                    try {
                        String Id = userIdField.getText();
                        String Pw = userPwField.getText();

                        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/slutupg", Id, Pw);
                        PreparedStatement pstm = connect.prepareStatement(q1 );

                        pstm.setString(1, input);


                        ResultSet rs = pstm.executeQuery();

                        while (rs.next())
                            ref.answer = ref.answer + "\n" + (rs.getInt(1)
                                    + "  " + rs.getString(2)
                                    + "  " + rs.getString(3)
                                    + "  " + rs.getString(4));
                        connect.close();

                    } catch (Exception l) {
                        System.out.println(l);

                    }
                }

                this.name = txt.getText();
            }
            set(ref.answer);



        };

        b3.addActionListener(n);

        // Actionlistener som Kontrollerar att att värdet är enligt regex koden i metoden Checkinput
        // sendan skapar vi Stringar för att kontrollera att det är rätt user.
        // Kopplar upp oss med sql och använder sedan preparedStatement för säkerhetsskäl, sedan skriver vi ut resultaten.

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
                            ref.answer = ref.answer + "\n" + (rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + " " + rs.getString(5));
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
    // kod för att kontrollera vad user skriver in.
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