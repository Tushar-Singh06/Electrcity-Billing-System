
package electricity.billing.system;
import java.awt.*;
import javax.swing.*;
import  java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;
public class DepositDetails extends JFrame implements ActionListener {
    Choice meternumber,cmonth;
    JTable table;
    JButton search,print;
    DepositDetails(){
        
        super("Deposit Details");
    
        setSize(700,700);
        setLocation(400,100);
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);


        JLabel lbmeternumber=new JLabel("Search By Meter Number");
        lbmeternumber.setBounds(20,20,150,20);
        add(lbmeternumber);
        
        meternumber=new Choice();
        meternumber.setBounds(180,20,150,20);
        add(meternumber);
        
        
        try{
        
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from customer");
            while(rs.next()){
            
               meternumber.add(rs.getString("meter_no"));
            }
            
        
        } catch(Exception e){
            
            e.printStackTrace();
        
        }
        
        JLabel lbmonth=new JLabel("Search By Month");
        lbmonth.setBounds(400,20,150,20);
        add(lbmonth);
        
        cmonth=new Choice();
        cmonth.setBounds(550,20,100,20);
        
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);
        
        
        table=new JTable();
        
        try{
           Conn c=new Conn();
            ResultSet rs= c.s.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(Exception e){
            
            
        
           e.getStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(0,100,700,600);
        add(sp);
        

        search=new JButton("Search");
        search.setBounds(120,70,80,20);
        search.addActionListener(this);
        add(search);
        
        print=new JButton("Print");
        print.setBounds(250,70,80,20);
        print.addActionListener(this);
        add(print);
        
        

        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    } 
    
    public void actionPerformed(ActionEvent ae){
       if(ae.getSource()==search){
          String query="select * from bill where meter_no = '"+meternumber.getSelectedItem()+"' and month = '"+cmonth.getSelectedItem()+"'";
          
          try{
              
              Conn c=new Conn();
             ResultSet rs=c.s.executeQuery(query);
             table.setModel(DbUtils.resultSetToTableModel(rs));
          
          
          } catch(Exception e){
          
            e.printStackTrace();
          }
       
       }else{
       
           try{
           
              table.print();
           
           } catch(Exception e){
           
             e.printStackTrace();
           }
       
       }
    
    }
    
    
    public static void main(String[] args){
    
      new DepositDetails();
    
    }
    
}
