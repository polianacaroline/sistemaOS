package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

public class Principal extends JFrame {
	// Instanciar objetos JDBC
	private Connection con;
	DAO dao = new DAO();
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblStatus;
	private JLabel lblData;
	//mudou de "private"para "public"
	//estes componentes serao alteradas pela tela de login 
	
	public JLabel lblUsuario;
	public JButton btnUsuarios;
	public JButton btnRelatorio;
	public JPanel panelRodape;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/configuracao.png")));
		setTitle("Assistencia Técnica de Celulares");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowActivated(WindowEvent e) {
					// ativação da janela
					status();
					setarData();
				
				}
			});
		
					setBounds(100, 100, 732, 479);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnUsuarios = new JButton("");
		btnUsuarios.setEnabled(false);
		btnUsuarios.setBackground(new Color(234, 240, 253));
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setToolTipText("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
				
			}
		});
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/172626_user_male_icon.png")));
		btnUsuarios.setBounds(10, 52, 128, 128);
		contentPane.add(btnUsuarios);
		
		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setContentAreaFilled(false);
		btnSobre.setBorder(null);
		btnSobre.setBorderPainted(false);
		btnSobre.setIcon(new ImageIcon(Principal.class.getResource("/img/4470663_description_about_app_web_info_icon.png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBounds(668, 0, 48, 48);
		contentPane.add(btnSobre);
		
		panelRodape = new JPanel();
		panelRodape.setBackground(new Color(0, 0, 160));
		panelRodape.setBounds(-5, 394, 721, 52);
		contentPane.add(panelRodape);
		panelRodape.setLayout(null);
		
		lblData = new JLabel("New label");
		lblData.setBounds(307, 11, 529, 20);
		panelRodape.add(lblData);
		lblData.setForeground(Color.WHITE);
		lblData.setToolTipText("DATA");
		lblData.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel usuarioss = new JLabel("Usuario:");
		usuarioss.setForeground(new Color(255, 255, 255));
		usuarioss.setBounds(22, 14, 97, 14);
		panelRodape.add(usuarioss);
		usuarioss.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblUsuario = new JLabel("");
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(131, 16, 154, 14);
		panelRodape.add(lblUsuario);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(658, 343, 48, 48);
		contentPane.add(lblStatus);
		lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));
		lblStatus.setToolTipText("Conexão");
		
		JLabel lblMenu = new JLabel("Usuarios");
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMenu.setBounds(21, 27, 72, 14);
		contentPane.add(lblMenu);
		
		JButton btnClientes = new JButton("");
		btnClientes.setBackground(new Color(234, 240, 253));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				cliente.setVisible(true);
				
			}
		});
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setIcon(new ImageIcon(Principal.class.getResource("/img/171447_users_group_icon.png")));
		btnClientes.setToolTipText("Clientes");
		btnClientes.setBounds(162, 52, 128, 128);
		contentPane.add(btnClientes);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblClientes.setBounds(177, 27, 72, 14);
		contentPane.add(lblClientes);
		
		JButton btnServicos = new JButton("");
		btnServicos.setBackground(new Color(234, 240, 253));
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servicos servicos= new Servicos();
				servicos.setVisible(true);
			}
			
		});
		btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos.setIcon(new ImageIcon(Principal.class.getResource("/img/configuracao.png")));
		btnServicos.setToolTipText("Serviços");
		btnServicos.setBounds(10, 255, 128, 128);
		contentPane.add(btnServicos);
		
		JLabel lblMenu_3 = new JLabel("Relatórios");
		lblMenu_3.setToolTipText("Relatórios");
		lblMenu_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMenu_3.setBounds(201, 230, 72, 14);
		contentPane.add(lblMenu_3);
		
		btnRelatorio = new JButton("");
		btnRelatorio.setEnabled(false);
		btnRelatorio.setBackground(new Color(234, 240, 253));
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clicar na tyela principal
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		btnRelatorio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorio.setIcon(new ImageIcon(Principal.class.getResource("/img/2931174_clipboard_copy_paste_analysis_report_icon.png")));
		btnRelatorio.setToolTipText("Relatórios");
		btnRelatorio.setBounds(162, 255, 129, 129);
		contentPane.add(btnRelatorio);
		
		JLabel lblMenu_3_1 = new JLabel("Serviços");
		lblMenu_3_1.setToolTipText("Serviços");
		lblMenu_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMenu_3_1.setBounds(32, 233, 72, 14);
		contentPane.add(lblMenu_3_1);
		
		JLabel lblNewLabel = new JLabel("DoctorCell");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setForeground(new Color(0, 0, 160));
		lblNewLabel.setBounds(534, 231, 237, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Principal.class.getResource("/img/configuracao.png")));
		lblNewLabel_1.setBounds(560, 175, 60, 64);
		contentPane.add(lblNewLabel_1);
		
		JButton btnClientes_1 = new JButton("");
		btnClientes_1.setIcon(new ImageIcon(Principal.class.getResource("/img/fornecedor-128x128.png")));
		btnClientes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Fornecedores fornecedores = new Fornecedores();
				fornecedores.setVisible(true);
				
				
			}
		});
		btnClientes_1.setToolTipText("Clientes");
		btnClientes_1.setBackground(new Color(234, 240, 253));
		btnClientes_1.setBounds(333, 255, 139, 128);
		contentPane.add(btnClientes_1);
		
		JButton btnClientes_1_1 = new JButton("");
		btnClientes_1_1.setIcon(new ImageIcon(Principal.class.getResource("/img/6843036_courier_deliver_delivery_package_product_icon.png")));
		btnClientes_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produtos produtos = new Produtos();
				produtos.setVisible(true);
				
			}
		});
		btnClientes_1_1.setToolTipText("Clientes");
		btnClientes_1_1.setBackground(new Color(234, 240, 253));
		btnClientes_1_1.setBounds(333, 52, 128, 128);
		contentPane.add(btnClientes_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Produtos");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(369, 28, 72, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fornecedores");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(348, 230, 102, 14);
		contentPane.add(lblNewLabel_3);
	

	}
	/**
	 * Método responsável por exibir o status da conexão
	 * 
	 */

	private void status() {
		try {
			// abrir a conexão
			con = dao.conectar();
			if (con == null) {
				// System.out.println("Erro de conexão");
				lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));
			} else {
				// System.out.println("Banco conectado");
				lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/img/dbon.png")));
			}
			// Nunca esquecer de fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}

	}//Fim do método status

	/**
	 * Método responsável por setar a data no rodape
	 */
	private void setarData() {
		Date data = new Date();
		//CRIAR OBJETO PARA FORMATAR A DATA
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		//alterar o texto da label pela data atual formataada
		lblData.setText(formatador.format(data));
		setLocationRelativeTo(null);
	}
}
