import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import mu.*;


public class DeleteFrame extends JFrame
{
JPanel jp1,jp2;
JButton delete,back;
JLabel l1,l2;
JTextField t1,t2;

public DeleteFrame()
{
super("Delete Employee");
setSize(500,150);
setResizable(false);

jp1=new JPanel();
jp1.setLayout(new FlowLayout(FlowLayout.CENTER,15,25));
delete=new JButton("Delete");
back=new JButton("Back");
l1=new JLabel("ID:");
t1=new JTextField(10);
jp1.add(l1);
jp1.add(t1);
add(jp1);

jp2=new JPanel();
jp2.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
jp2.add(delete);
jp2.add(back);
add(jp2,BorderLayout.SOUTH);
setLocationRelativeTo(null);
setVisible(true);
//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent we)
{
HomeFrame h=new HomeFrame();
dispose();
}});

back.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
HomeFrame a1=new HomeFrame();
dispose();
}
});

delete.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
String id=t1.getText();
if(id.length()==0)
{
Sound2.failure();
JOptionPane.showMessageDialog(new JDialog(),"Please enter id");
return;
}
int id1=0;
try{
	id1=Integer.parseInt(id);
}
catch(NumberFormatException ex)
{
	Sound2.failure();
	JOptionPane.showMessageDialog(new JDialog(),"id should be integer");
	return;
}
DatabaseHandler s=new DatabaseHandler();
s.delete(Integer.parseInt(id));
t1.setText("");
}
});
}
}