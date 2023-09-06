package view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URI;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/configuracao.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Sobre.class.getResource("/img/mit-icon.png")));
		lblNewLabel.setBounds(248, 99, 128, 111);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Assistência Técnica de Celulares");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(56, 1, 302, 26);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Autora: Poliana");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(40, 73, 119, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sob a licença MIT");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(258, 221, 153, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Cidade: São Paulo");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(40, 221, 119, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Versão Beta: 1.0");
		lblNewLabel_6.setBounds(142, 23, 93, 14);
		getContentPane().add(lblNewLabel_6);
		
		JButton btnGithub = new JButton("");
		btnGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGithub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://github.com/polianacaroline/sistemaOS/tree/main/src/view");
			}
		});
		btnGithub.setContentAreaFilled(false);
		btnGithub.setIcon(new ImageIcon(Sobre.class.getResource("/img/gitttt.png")));
		btnGithub.setBounds(50, 99, 89, 74);
		getContentPane().add(btnGithub);

	}  //fim do construtor
	
	private void link(String site) {
	Desktop desktop = Desktop.getDesktop();
	try {
		URI uri = new URI(site);
		desktop.browse(uri);
	} catch (Exception e) {
		System.out.println(e);
		
		
		
	}
										
	}
}
