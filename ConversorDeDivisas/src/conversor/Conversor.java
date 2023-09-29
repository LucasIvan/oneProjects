package conversor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Conversor {

	private JFrame frame;
	private JTextField txt;
	private JButton btn;
	private JComboBox cmb;
	private JLabel lbl;
	
	public enum Moneda {
		pesos_dolar,
		pesos_euro,
		pesos_libra,
		dolar_pesos,
		euro_pesos,
		libra_pesos
	}
	
	public double dolar = 350.03;
	public double euro = 369.70;
	public double libra = 427.13;
	
	public double inputValor = 0.00;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversor window = new Conversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Conversor() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(55, 55, 55));
		frame.setBounds(100, 100, 289, 243);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setBackground(new Color(192, 192, 192));
		txt.setBounds(10, 53, 106, 20);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox();
		cmb.setBackground(new Color(192, 192, 192));
		cmb.setModel(new DefaultComboBoxModel(Moneda.values()));
		cmb.setBounds(155, 52, 106, 22);
		frame.getContentPane().add(cmb);
		
		btn = new JButton("Convertir");
		btn.setBackground(new Color(192, 192, 192));
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				convertir();
			}
		});
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn.setBounds(92, 91, 89, 23);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("0.00");
		lbl.setForeground(new Color(255, 255, 255));
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBounds(10, 125, 251, 42);
		frame.getContentPane().add(lbl);
	}
	
	public void convertir() {
		if (validar(txt.getText())) {
			Moneda moneda = (Moneda) cmb.getSelectedItem();
			switch (moneda) {
			case pesos_dolar -> pesosAMoneda(dolar);
			case pesos_euro -> pesosAMoneda(euro);
			case pesos_libra -> pesosAMoneda(libra);
			case dolar_pesos -> monedaAPesos(dolar);
			case euro_pesos -> monedaAPesos(euro);
			case libra_pesos -> monedaAPesos(libra);
			default ->
			throw new IllegalArgumentException("Unexpected value: " + moneda);
			}
		}
		
	}
	
	public void pesosAMoneda(double moneda) {
		double resultado = inputValor / moneda;
		lbl.setText(redondear(resultado));
	}
	
	public void monedaAPesos(double moneda) {
		double resultado = inputValor * moneda;
		lbl.setText(redondear(resultado));
	}
	
	public String redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	public Boolean validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if (x > 0) inputValor = x;
			return true;
		} catch (NumberFormatException e) {
			lbl.setText("Valor inválido");
			return false;
		}
	}
}
