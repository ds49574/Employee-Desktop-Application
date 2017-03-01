import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import mu.*;
public class HomeFrame extends JFrame
{
JPanel jp;
JButton add,modify,delete,view;
public HomeFrame()
{
super("Employee Records Management");
setSize(500,150);
setResizable(false);
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
jp=new JPanel();
jp.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
add=new JButton("Add");
modify=new JButton("Modify");
delete=new JButton("Delete");
view=new JButton("View");
jp.add(add);
jp.add(modify);
jp.add(delete);
jp.add(view);
add(jp);
setLocationRelativeTo(null);
setVisible(true);
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
{
int output=JOptionPane.showConfirmDialog(null,"Do You Want To Exit");
if(output==JOptionPane.YES_OPTION)
{
 System.exit(1);
}}});
add.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
AddFrame a=new AddFrame();
dispose();
}});
modify.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
ModifyFrame a=new ModifyFrame();
dispose();
}});
delete.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
DeleteFrame a=new DeleteFrame();
dispose();
}});
view.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
ViewFrame a=new ViewFrame();
dispose();
}});
}
public static void main(String args[])
{
HomeFrame h=new HomeFrame();
}
}

class DatabaseHandler
{
static Connection con;
public static void getConnection()
{
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog(),"error");
}
}






public void insert(String id,String name)
{
int id1;
String expression="^[a-zA-Z\\s]+";
try
{
getConnection();
String q="insert into employee values(?,?)";
PreparedStatement pst=con.prepareStatement(q);
try
{
id1=Integer.parseInt(id);
}
catch(Exception e)
{
Sound2.failure();	
JOptionPane.showMessageDialog(new JDialog(),"ID Must Be A Number");
return;
}
if(id1<0)
{
	Sound2.failure();
JOptionPane.showMessageDialog(new JDialog(),"ID Must Be Positive");
return;
}
if(!name.matches(expression))
{
JOptionPane.showMessageDialog(new JDialog(),"Please Enter Proper Name");
return;
}
pst.setInt(1,id1);
pst.setString(2,name);
int i=pst.executeUpdate();
Sound1.success();
JOptionPane.showMessageDialog(new JDialog(),"1 Record Added");
}
catch(Exception e)
{
Sound2.failure();
JOptionPane.showMessageDialog(new JDialog(),"Record Already Exists");
}
}






public JTable query()
{
	String col[]={"ID","Roll.No"};
	String rows[][];
	StringBuffer sb=new StringBuffer();
	JTable stuff=null;
try
{
getConnection();
String q="select * from employee order by id";
Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
ResultSet rs=st.executeQuery(q);
rs.last();
int ro=rs.getRow();
rs.beforeFirst();
rows=new String[ro][2];
//sb.append("ID:"+"\t"+"NAME:"+"\n");
//System.out.println(ro);
int i=0;
while(i<ro&&rs.next())
{
//sb.append(rs.getString(1)+"\t"+rs.getString(2)+"\n");
rows[i][0]=rs.getString(1);
rows[i][1]=rs.getString(2);
i++;
}


rs.close();
stuff=new JTable(rows,col);
stuff.setModel(new DefaultTableModel(rows,col){
	public boolean isCellEditable(int row, int column) {
        return false;
    }
});
stuff.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog()," "+e);
System.out.println(e);
}
return stuff;
}






public void modify(String im,String nam)
{
int i;
String expression="^[a-zA-Z\\s]+";
try
{
//Sound2.failure();
getConnection();
try
{
i=Integer.parseInt(im);
}
catch(Exception e)
{
Sound2.failure();
JOptionPane.showMessageDialog(new JDialog(),"ID Must Be A Number");
return;
}
if(!nam.matches(expression))
{
Sound2.failure();
JOptionPane.showMessageDialog(new JDialog(),"Please Enter Proper Name");
return;
}
PreparedStatement stmt=con.prepareStatement("Update employee SET name=? WHERE id=?");
stmt.setInt(2,i);
stmt.setString(1,nam);
int i12=stmt.executeUpdate();
Sound1.success();
JOptionPane.showMessageDialog(new JDialog(),i12+" Record Modified");
}
catch(Exception e)
{
Sound2.failure();
JOptionPane.showMessageDialog(new JDialog()," "+"Record Does Not Exists");
}
}








/*
public void modify(int i,String nam)
{
try
{
getConnection();
PreparedStatement stmt=con.prepareStatement("Update employee SET name=? WHERE id=?");
stmt.setInt(2,i);
stmt.setString(1,nam);
int i12=stmt.executeUpdate();
Sound1.success();
JOptionPane.showMessageDialog(new JDialog(),i12+" Record Modified");
}
catch(Exception e)
{
Sound2.failure();
JOptionPane.showMessageDialog(new JDialog()," "+"Record Does Not Exists");
}
}*/









public void delete(int i)
{
try
{
getConnection();
PreparedStatement stmt=con.prepareStatement("delete from employee WHERE id=?");
stmt.setInt(1,i);
int i11=stmt.executeUpdate();
if(i11==0)
Sound2.failure();
else
Sound1.success();
JOptionPane.showMessageDialog(new JDialog(),i11+" Record Deleted");
}
catch(Exception e)
{
Sound2.failure();
JOptionPane.showMessageDialog(new JDialog()," "+"Record Does Not Exists");
}
}
}