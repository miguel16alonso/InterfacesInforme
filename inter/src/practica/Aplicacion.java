package practica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
/**
*
* @see <a href="http://docs.oracle.com/javase/7/docs/technotes/tools/windows/javadoc.html">Javadoc</a>
* @see <a href="https://docs.oracle.com/javase/8/docs/api/">Javadoc JDK</a>
* @author miguel
* @version 1.0
*
*/
public class Aplicacion extends JFrame {

	private JPanel card;
	private JMenuItem mntmGenerarFactura;
	private JMenuItem mntmFacturasDelCliente;
	private JMenuItem mntmClientes;
	private JMenu mnConsulta;
	private JMenuBar menuBar;
	private JPanel panelPrincipal;

	/**
	 *	Inicio de la Aplicacion
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacion frame = new Aplicacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor que crea el JFrame
	 */
	public Aplicacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 650, 650);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnConsulta = new JMenu("Consulta");
        menuBar.add(mnConsulta);
        
        card = new JPanel();
        card.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(card);
        card.setLayout(new CardLayout(0, 0));

        panelPrincipal = new JPanel();
        card.add(panelPrincipal, "porDefecto");
        panelPrincipal.setLayout(null);
        
        JLabel lblBienvenidoAlGenerador = new JLabel("BIENVENIDO AL GENERADOR DE FACTURAS POR CLIENTE");
        lblBienvenidoAlGenerador.setBounds(131, 28, 394, 33);
        panelPrincipal.add(lblBienvenidoAlGenerador);
        
        
        

        mntmClientes = new JMenuItem("Clientes");
        mntmClientes.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	PCliente clientes = new PCliente();
                	card.add(clientes,"clientes");
                	itemStateChanged("clientes");
                }
        });
        mnConsulta.add(mntmClientes);

        mntmFacturasDelCliente = new JMenuItem("Facturas del cliente");
        mntmFacturasDelCliente.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	PFactura facturas = new PFactura();
                	card.add(facturas,"facturas");
                	itemStateChanged("facturas");
                }
        });
        mnConsulta.add(mntmFacturasDelCliente);

        mntmGenerarFactura = new JMenuItem("Generar factura");
        mntmGenerarFactura.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	PanelInformes info = new PanelInformes();
                	card.add(info,"final");
                	itemStateChanged("final");
                }
        });
        mnConsulta.add(mntmGenerarFactura);
       

	}
	
    /**
     * Se invoca cuando el usuario ha seleccionado o deseleccionado un elemento
     * @params parametro del CardLayout
     */
	public void itemStateChanged(String evt) {
		CardLayout cl = (CardLayout)(card.getLayout());
		cl.show(card, evt);
	}
}
