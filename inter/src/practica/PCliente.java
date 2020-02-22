package practica;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
/**
*
* @see <a href="http://docs.oracle.com/javase/7/docs/technotes/tools/windows/javadoc.html">Javadoc</a>
* @see <a href="https://docs.oracle.com/javase/8/docs/api/">Javadoc JDK</a>
* @author miguel
* @version 1.0
*
*/
@SuppressWarnings("serial")
public class PCliente extends JPanel {
	private JTable table;
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	/**
	 * Creacion del Panel de Cliente
	 */
	public PCliente() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblClientes = new JLabel("CLIENTES");
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblClientes, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		try {
			con =  Conexion.getConnection();
			if(con == null)
				System.out.println("ERROR DE CONEXION");
			
			stmt = con.createStatement();
			
			String sql = "SELECT * FROM CUSTOMER";
			
			rs = stmt.executeQuery(sql);
			
			String[] tableColumnsName = {"ID","FIRSTNAME","LASTNAME","STREET","CITY"}; 
			DefaultTableModel aModel = (DefaultTableModel) table.getModel();
			aModel.setColumnIdentifiers(tableColumnsName);
			
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int colNo = rsmd.getColumnCount();
			while(rs.next()){
				Object[] objects = new Object[colNo];
				for(int i=0;i<colNo;i++){
					objects[i]=rs.getObject(i+1);
				}
				aModel.addRow(objects);
			}
			table.setModel(aModel);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
