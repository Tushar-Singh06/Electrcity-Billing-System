
package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class SignUp extends JFrame implements ActionListener{
    JButton back,create;
    Choice accountType;
    JTextField meter,username,name,password;
    SignUp(){
    
       
        
        setBounds(450,150,700,400);
        getContentPane().setBackground(Color.WHITE);
        
        setLayout(null);
        
        JPanel panel=new JPanel();
        panel.setBounds(30,30,650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY,2),"Create-Account",TitledBorder.LEADING,TitledBorder.TOP,null,Color.DARK_GRAY));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(Color.DARK_GRAY);
        add(panel);
        
        JLabel heading=new JLabel("Create Account as");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(heading);
        
        accountType=new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(260,50,150,20);
        panel.add(accountType);
        
        JLabel lbmeter=new JLabel("Meter Number");
        lbmeter.setBounds(100,90,140,20);
        lbmeter.setForeground(Color.GRAY);
        lbmeter.setFont(new Font("Tahoma",Font.BOLD,14));
        lbmeter.setVisible(false);
        panel.add(lbmeter);
        
        
        
        meter=new JTextField();
        meter.setBounds(260,90,150,20);
        meter.setVisible(false);
        panel.add(meter);
        
        
        
         JLabel lbusername=new JLabel("Username");
        lbusername.setBounds(100,130,140,20);
        lbusername.setForeground(Color.GRAY);
        lbusername.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lbusername);
        
        username=new JTextField();
        username.setBounds(260,130,150,20);
        panel.add(username);
        
         JLabel lbname=new JLabel("Name");
        lbname.setBounds(100,170,140,20);
        lbname.setForeground(Color.GRAY);
        lbname.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lbname);
        
        name=new JTextField();
        name.setBounds(260,170,150,20);
        panel.add(name);
        
        meter.addFocusListener(new FocusListener(){
            
            public void focusGained(FocusEvent fe){
            
            
            }
            
            public void focusLost(FocusEvent fe){
               try{
                Conn c=new Conn();
                ResultSet rs=c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"'");
                while(rs.next()){
                   name.setText(rs.getString("name"));
                }
               
               } catch(Exception e){
               
               e.printStackTrace();
               
               }
            
            
            
            }
        
        });
        
          JLabel lbpassword=new JLabel("Password");
        lbpassword.setBounds(100,210,140,20);
        lbpassword.setForeground(Color.GRAY);
        lbpassword.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lbpassword);
        
        password=new JTextField();
        password.setBounds(260,210,150,20);
        panel.add(password);
        
        accountType.addItemListener(new ItemListener(){
               public void itemStateChanged(ItemEvent ae){
                 String user=accountType.getSelectedItem();
                 if(user.equals("Customer")){
                    lbmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                 } else {
                     lbmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                 
                 
                 }
               
               }
        
        });
        
        create=new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setBounds(140,260,120,25);
        create.addActionListener(this);
        panel.add(create);
        
        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(300,260,120,25);
        back.addActionListener(this);
        panel.add(back);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2=i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(415,30,250,250);
        panel.add(image);
        
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
          setVisible(false);
          new Login();
        }else if(ae.getSource()==create){
            String atype=accountType.getSelectedItem();
            String susername=username.getText();
            String sname=name.getText();
            String spassword=password.getText();
            String smeter=meter.getText();
            
            try{
               
                Conn c=new Conn();
                String query=null;
                if(atype.equals("Admin")){
                 query ="insert into login values('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+atype+"')";
                } else{
                
                  query ="update login set username = '"+susername+"', password = '"+spassword+"',user = '"+atype+"' where meter_no = '"+smeter+"'";
                }
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null,"Account created successfully");
                setVisible(false);
                new Login();
                
            } catch(Exception e){
              e.printStackTrace();
            }
        
        }
    }
    
    public static void main(String[] args){
       
        new SignUp();
    }
    
}
