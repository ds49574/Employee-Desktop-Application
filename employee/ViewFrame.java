import java.awt.event.*;
import javax.swing.*;

public class ViewFrame extends JFrame
{
JTextArea ta;
JScrollPane sp1;
JTable stf;
//JPanel p1;
public ViewFrame()
{

super("View All Employees");
setSize(500,150);
setResizable(false);
//p1=new JPanel();
//ta=new JTextArea(10,10);
//ta.setEditable(false);
//sp1=new JScrollPane(ta);
//sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//add(sp1);
try{
DatabaseHandler q=new DatabaseHandler();
//String s=q.query();
stf=q.query();
//stf.setFont(new Font("Comic Sans MS", 20));
//ta.setText(s);
//System.out.println("Table is returned");
//p1.add(stf);
//stf.setEditable(false);
sp1=new JScrollPane(stf);
sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
add(sp1);
setLocationRelativeTo(null);
setVisible(true);
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent we)
{
HomeFrame h=new HomeFrame();
dispose();
}});

}
catch(Exception e)
{
e.printStackTrace();	
}
}
}