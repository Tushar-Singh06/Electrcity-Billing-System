
package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JButton login,signup,cancel;
    JTextField username,password;
    Choice loginin;
    
    Login(){
    
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lusername=new JLabel("Username");
        lusername.setBounds(300, 20,100, 20);
        add(lusername);
        
            username=new JTextField();
            username.setBounds(400,20,150,20);
            add(username);
        
        JLabel lpassword=new JLabel("Password");
        lpassword.setBounds(300, 60,100, 20);
        add(lpassword);
        
            password=new JTextField();
            password.setBounds(400,60,150,20);
            add(password);
        
        JLabel llogininas=new JLabel("Login in as");
        llogininas.setBounds(300, 100,100, 20);
        add(llogininas);
        
            loginin=new Choice();
            loginin.add("Admin");
            loginin.add("Customer");
            loginin.setBounds(400,100,150,20);
            add(loginin);
            
             ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icon/Login.png"));
            Image i5=i4.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
            
             login=new JButton("Login",new ImageIcon(i5));
            login.setBounds(330, 160, 100, 20);
            login.addActionListener(this);
            add(login);
           
             ImageIcon i6=new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
            Image i7=i6.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
            
             cancel=new JButton("Cancel",new ImageIcon(i7));
            cancel.setBounds(450, 160, 100, 20);
            cancel.addActionListener(this);
            add(cancel);
            
            
             ImageIcon i8=new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
            Image i9=i8.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
            
             signup=new JButton("Sign Up",new ImageIcon(i9));
            signup.setBounds(380, 200, 100, 20);
            signup.addActionListener(this);
            add(signup);
            
            ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
            Image i2=i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
            ImageIcon i3=new ImageIcon(i2);
            JLabel image=new JLabel(i3);
            image.setBounds(0,0,250,250);
            add(image);
            
        setSize(640,300);
        setLocation(400,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae){
    
       if(ae.getSource()==login){
           String susername=username.getText();
           String spassword=password.getText();
           String user=loginin.getSelectedItem();
           
           try{
               Conn c=new Conn();
               String query="select * from login where username='"+susername+"' and password='"+spassword+"'and user='"+user+"'";
           
               ResultSet rs=c.s.executeQuery(query);
               if(rs.next()){
                   String meter=rs.getString("meter_no");
                   setVisible(false);
                   new Project(user,meter);
               
               }else{
                 JOptionPane.showMessageDialog(null,"Invalid Login");
                 username.setText("");
                 password.setText("");
               }
           } catch(Exception e){
             e.printStackTrace();
           }
       }else if(ae.getSource()==cancel){
           setVisible(false);
       }else if(ae.getSource()==signup){
           
           setVisible(false);
           new SignUp();
       }
    }
    
    
    public static void main(String[] args){
    
        new Login();
    }
    
}
