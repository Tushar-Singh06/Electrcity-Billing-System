

package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class MeterInfo extends JFrame implements ActionListener{
    JTextField tfname,tfaddress,tfstate,tfcity,tfemail,tfphone;
    JButton next,cancel;
    JLabel lbmeter;
    Choice meterlocation,metertype,phasecode,billtype;
    String meternumber;
    MeterInfo(String meternumber){
       this.meternumber=meternumber;
        setSize(700,500);
        setLocation(400,200);
        
        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        JLabel heading=new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        p.add(heading);
        
        JLabel lbname=new JLabel("Meter Number");
        lbname.setBounds(100,80,100,20);
        p.add(lbname);
        
        
        JLabel lbmeternumber=new JLabel(meternumber);
        lbmeternumber.setBounds(240,80,100,20);
        p.add(lbmeternumber);
        
        
        
        JLabel lbmeterno=new JLabel("Meter Location");
        lbmeterno.setBounds(100,120,100,20);
        p.add(lbmeterno);
        
        meterlocation=new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240,120,200,20);
        p.add(meterlocation);
        
     
        
        
        JLabel lbmetertype=new JLabel("Meter Type");
        lbmetertype.setBounds(100,160,100,20);
        p.add(lbmetertype);
        
        metertype=new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240,160,200,20);
        p.add(metertype);
        
        
         JLabel lbphasecode=new JLabel("Phase Code");
        lbphasecode.setBounds(100,200,100,20);
        p.add(lbphasecode);
        
         phasecode=new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        
        phasecode.setBounds(240,200,200,20);
        p.add(phasecode);
        
        
         JLabel lbbilltype=new JLabel("Bill Type");
        lbbilltype.setBounds(100,240,100,20);
        p.add(lbbilltype);
        
        billtype=new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(240,240,200,20);
        p.add(billtype);
        
         JLabel lbdays=new JLabel("Days");
        lbdays.setBounds(100,280,100,20);
        p.add(lbdays);
        
        JLabel days=new JLabel("30 Days");
        days.setBounds(240,280,200,20);
        p.add(days);
        
        
         JLabel lbdefault=new JLabel("Note:");
        lbdefault.setBounds(100,320,100,20);
        p.add(lbdefault);
        
        JLabel ldefault=new JLabel("By Default Bill is calculated for 30 days");
        ldefault.setBounds(240,320,500,20);
        p.add(ldefault);
      
        
        
        next=new JButton("Submit");
        next.setBounds(220,390,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
         
        
        setLayout(new BorderLayout());
        
        add(p,"Center");
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2=i1.getImage().getScaledInstance(150,300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image,"West");
        
        getContentPane().setBackground(Color.WHITE);
                
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
     
    }
    
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==next){
           String meter=meternumber;
           String location=meterlocation.getSelectedItem();
           String type=metertype.getSelectedItem();
           String code=phasecode.getSelectedItem();
           String typebill=billtype.getSelectedItem();
           String days="30";
           
           
           String query="insert into meter_info values('"+meter+"','"+location+"','"+type+"','"+code+"','"+typebill+"','"+days+"')";
           
           try{
               
               Conn c=new Conn();
               c.s.executeUpdate(query);
              
               
               JOptionPane.showMessageDialog(null,"Meter Information Added Successfully");
               setVisible(false);
               
               
           
           
           } catch(Exception e){
              e.printStackTrace();
           }
        
        }else{
           setVisible(false);
        }
        
        
    }
    
    public static void main(String[] args){
    
       new MeterInfo("");
    }
    
}

