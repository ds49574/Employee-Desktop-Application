import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import mu.*;
//import mu.*;

public class ModifyFrame extends JFrame
{
JPanel jp1,jp2;
JButton modify,back;
JLabel l1,l2;
JTextField t1,t2;

public ModifyFrame()
{
super("Modify Employee");
setSize(500,150);
setResizable(false);

jp1=new JPanel();
jp1.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
modify=new JButton("Modify");
back=new JButton("Back");
l1=new JLabel("ID:");
l2=new JLabel("New Name:");
t1=new JTextField(5);
t2=new JTextField(10);
jp1.add(l1);
jp1.add(t1);
jp1.add(l2);
jp1.add(t2);
add(jp1);

jp2=new JPanel();
jp2.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
jp2.add(modify);
jp2.add(back);
add(jp2,BorderLayout.SOUTH);
setLocationRelativeTo(null);
setVisible(true);
setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent we)
{
HomeFrame h1=new HomeFrame();
dispose();
}
});

back.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
HomeFrame a1=new HomeFrame();
dispose();
}
});

/*modify.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
String id=t1.getText();
String name=t2.getText();
int id1=0;
if(id.length()==0)
{
Sound2.failure();
JOptionPane.showMessageDialog(new JDialog(),"Please enter id");
return;
}
try
{
id1=Integer.parseInt(id);
}
catch(NumberFormatException e)
{
	Sound2.failure();
JOptionPane.showMessageDialog(new JDialog(),"ID Must Be A Number");
return;
}
DatabaseHandler s=new DatabaseHandler();
s.modify(id1,name);
t1.setText("");
t2.setText("");
}
});
*/



modify.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
String id=t1.getText();
String name=t2.getText();
if(id.length()==0)
{
Sound2.failure();
JOptionPane.showMessageDialog(new JDialog(),"Please enter id");
return;
}
DatabaseHandler s=new DatabaseHandler();
s.modify(id,name);
t1.setText("");
t2.setText("");
}
});





}
}
