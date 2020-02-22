package practica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class PanelInformes extends JPanel {
	private JTextField textField;
	private JFileChooser fileChooser;
	private Connection con;
	private Component panel_desktop;
	private String ruta;
	private JFileChooser chooser;

	/**
	 * Creacion del panel para la generacion de informes
	 */
	public PanelInformes() {
		setLayout(new BorderLayout(0, 0));

		JLabel lblInformes = new JLabel("INFORMES");
		lblInformes.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblInformes, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel label = new JLabel("");
		label.setBounds(0, 1, 143, 28);
		panel.add(label);

		JLabel label_4 = new JLabel("");
		label_4.setBounds(153, 1, 143, 28);
		panel.add(label_4);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(306, 1, 143, 28);
		panel.add(label_3);

		JLabel lblnumeroFactura = new JLabel("Numero factura:");
		lblnumeroFactura.setBounds(21, 40, 143, 28);
		panel.add(lblnumeroFactura);

		textField = new JTextField();
		textField.setBounds(114, 40, 43, 28);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnGenInforme = new JButton("Generar Informe");
		btnGenInforme.setBounds(284, 224, 143, 28);
		btnGenInforme.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

				crearInforme();
			}

		});
		panel.add(btnGenInforme);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(0, 257, 143, 28);
		panel.add(label_1);

	}

	@SuppressWarnings("deprecation")
	/**
	 * Metodo para generar la factura del cliente
	 */
	private void crearInforme() {
		chooser = new JFileChooser();
		
		if (chooser.showSaveDialog(this) == chooser.APPROVE_OPTION) {
			ruta = chooser.getSelectedFile().getAbsolutePath();
			
			try {
				con = Conexion.getConnection();
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("idJava", textField.getText());
				
				JasperReport jr = (JasperReport) JRLoader.loadObject(PanelInformes.class.getResource("/informes/InformeFactura.jasper"));

				JasperReport subreport = (JasperReport) JRLoader.loadObject(PanelInformes.class.getResource("/informes/ClienteFactura.jasper"));

				parametros.put("subreport", subreport);
				
				JasperPrint print = JasperFillManager.fillReport(jr, parametros, con);
				JasperViewer.viewReport(print);
				JasperExportManager.exportReportToPdfFile(print,  ruta);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

}
