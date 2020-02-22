package practica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class PFactura extends JPanel {
	private JTextField textFieldCliente;
	private JLabel lbCliente;
	private Connection con;
	private PreparedStatement pt;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Creacion del Panel para mostrar las Facturas por Cliente
	 */
	public PFactura() {
		setToolTipText("");
		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		lbCliente = new JLabel("Selecciona un Cliente:");
		panel.add(lbCliente);

		textFieldCliente = new JTextField();
		panel.add(textFieldCliente);
		textFieldCliente.setColumns(10);

		JButton btnGenerarFactura = new JButton("Generar Factura");
		panel.add(btnGenerarFactura);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnGenerarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarFacturas();
			}
		});

	}
	/**
	 * Metodo para generar la lista de facturas por cliente
	 */
	public void generarFacturas() {
		try {
			con = Conexion.getConnection();

			eliminar();
			String sql = "SELECT * FROM invoice WHERE customerid = ?";

			pt = con.prepareStatement(sql);

			pt.setString(1, textFieldCliente.getText().toString());

			ResultSet rs = pt.executeQuery();

			
			String[] tableColumnsName = { "ID", "CUSTOMERID", "TOTAL" };
			DefaultTableModel aModel = (DefaultTableModel) table.getModel();
			aModel.setColumnIdentifiers(tableColumnsName);

			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int colNo = rsmd.getColumnCount();
			while (rs.next()) {
				Object[] objects = new Object[colNo];
				for (int i = 0; i < colNo; i++) {
					objects[i] = rs.getObject(i + 1);
				}
				aModel.addRow(objects);
			}
			table.setModel(aModel);
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Metodo para limpiar la tabla cuando haces una busqueda a la BBDD
	 */
	 public void eliminar(){
	        DefaultTableModel tb = (DefaultTableModel) table.getModel();
	        int a = table.getRowCount()-1;
	        for (int i = a; i >= 0; i--) {          
	        tb.removeRow(tb.getRowCount()-1);
	        }
	    }


}
