import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// skapar gui

public class Anställdared {
    JButton b3 = new JButton("Updatera");
    JFrame frame = new JFrame("Redigering av Anställda");
    JTextField txt = new JTextField("Type here");
    JTextArea txta = new JTextArea();
    JTextField userIdField = new JTextField();
    JPasswordField userPwField = new JPasswordField();
    JButton b1 = new JButton("Reset");
    JLabel label = new JLabel("Id & Lösenord för att kuna söka.");
    JPanel  JP = new JPanel(new GridLayout(2,1));
    String name;
    JButton b2 = new JButton("Sök Namn ");


    JTextField id = new JTextField("Uppdarera personen med Id");
    JTextField namn = new JTextField("Uppdarera Name");
    JTextField adress = new JTextField("Uppdarera adress");
    JTextField ort = new JTextField("Uppdarera ort");
    JTextField telefonnummer1 = new JTextField("telefonnummer 1: ");
    JTextField telefonnummer2 = new JTextField("telefonnummer 2: ");
    JTextField telefonnummer3 = new JTextField("telefonnummer 3: ");
    JTextField månadslön = new JTextField("månadslön");
    JTextField semesterdagar = new JTextField("semesterdagar");

    JPanel uppdatera = new JPanel(new GridLayout(9,1));




// Gui inställningar
    Anställdared(){

        uppdatera.setBounds(20,400,200,300);


        uppdatera.add(id);
        uppdatera.add(namn);
        uppdatera.add(adress);
        uppdatera.add(ort);
        uppdatera.add(telefonnummer1);
        uppdatera.add(telefonnummer2);
        uppdatera.add(telefonnummer3);
        uppdatera.add(semesterdagar);
        uppdatera.add(månadslön);
        frame.add(uppdatera);


        var ref = new Object() {
            String answer = "";
        };


        name = "-1";

        frame.setSize(700,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userIdField.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        userIdField.setBounds(20, 30, 200, 25);
        userPwField.setBounds(20, 60, 200, 25);

        txt.setBounds(100,90,300,25);
        txta.setBounds(35,120,500,220);

        b1.setBounds(20,90,75,25);
        b2.setBounds(400,90,100,25);
        b3.setBounds(50,360,110,25);
        label.setBounds(20,10,200,25);
        txta.setBackground(Color.lightGray);


        JP.add(userIdField);
        JP.add(userPwField);
        frame.add(b2);
        frame.add(JP);
        frame.add(label);
        frame.add(b1);
        frame.add(txt);
        frame.add(txta);
        frame.add(userIdField);
        frame.add(userPwField);
        frame.setVisible(true);
        frame.add(b3);


// Skapar strängar för alla Textfields vi använder för uppdatering.

        ActionListener m = x-> {
            String input9;
            String input8;
            String input7;
            String input6;
            String input5;
            String input4;
            String input3;
            String input2;
            String input = "-1";
            // Sql kod flr att uppdatera anställd.
            String update = " Update  anställda set Namn = ?, Adress = ?, Ort = ?, TelefonNummer1 = ?, TelefonNummer2 = ?, TelefonNummer3 = ?, Månadslön = ?, SemesterdagarKvar = ? where AnställningsID = ?";


            if (input.equals("-1")){
                input = getValue();
                if(checkInput(input)){
                    try {
                        String Id = userIdField.getText();
                        String Pw = userPwField.getText();
                        // lägger till vad som skrivits i textfälten till strängarna så vi kan sätta ut i preparedstatements vilken som tillgör vilken
                        input = namn.getText();
                        input2 = id.getText();
                        input3 = adress.getText();
                        input4 = ort.getText();
                        input5 = telefonnummer1.getText();
                        input6 = telefonnummer2.getText();
                        input7 = telefonnummer3.getText();
                        input8 = månadslön.getText();
                        input9 = semesterdagar.getText();


                        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/slutupg", Id, Pw);
                        PreparedStatement pstm = connect.prepareStatement(update);
                        pstm.setString(  1,input);
                        pstm.setInt(9, Integer.parseInt(input2));
                        pstm.setString(2,input3);
                        pstm.setString(3,input4);
                        pstm.setString(4,input5);
                        pstm.setString(5,input6);
                        pstm.setString(6,input7);
                        pstm.setString(7,input8);
                        pstm.setString(8,input9);
                        System.out.println(pstm);
                        pstm.executeUpdate();


                        connect.close();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                this.name = txt.getText();
            }
            set(ref.answer);


        };
        b3.addActionListener(m);

        String q1 = " Select * from anställda where Namn = ? ";

        ActionListener n =  x->{

            String input = "-1";

            if (input.equals("-1")){
                input = getValue();
                if(checkInput(input)){
                    try {
                        String Id = userIdField.getText();
                        String Pw = userPwField.getText();

                        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/slutupg", Id, Pw);
                        PreparedStatement pstm = connect.prepareStatement(q1);
                        pstm.setString(1,input);
                        ResultSet rs =pstm.executeQuery();

                        while (rs.next())
                            ref.answer = ref.answer + "\n" + (rs.getInt(1)
                                    + "  " + rs.getString(2)
                                    + "  " + rs.getString(3)
                                    + "  " + rs.getString(4)
                                    + " " + rs.getString(5)
                                    + " " + rs.getString(6)
                                    + " " + rs.getString(7)
                                    + " " + rs.getString(8)
                                    + " " + rs.getString(9)
                            );

                        connect.close();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                this.name = txt.getText();
            }
             set(ref.answer);
        };

            b2.addActionListener(n);


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
