package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;

public class Login extends JFrame {

//objeto JDBC
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
//objeto tela principal
	// acessar tela principal
	Principal principal = new Principal();
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/2203549_admin_avatar_human_login_user_icon.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				// ativação da janela
				status();
			}
		});
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 295);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Login = new JLabel("Login");
		Login.setFont(new Font("Tahoma", Font.BOLD, 12));
		Login.setBounds(23, 25, 46, 14);
		contentPane.add(Login);

		txtLogin = new JTextField();
		txtLogin.setBounds(70, 22, 297, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel Senha = new JLabel("Senha");
		Senha.setFont(new Font("Tahoma", Font.BOLD, 12));
		Senha.setBounds(23, 80, 46, 14);
		contentPane.add(Senha);

		JButton btnAcessar = new JButton("");
		btnAcessar.setIcon(new ImageIcon(Login.class.getResource("/img/510861_find_magnifying glass_search_zoom_icon (1).png")));
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnAcessar.setBounds(129, 130, 89, 41);
		contentPane.add(btnAcessar);
		getRootPane().setDefaultButton(btnAcessar);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(70, 77, 297, 20);
		contentPane.add(txtSenha);
		
		lblStatus = new JLabel("New label");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(321, 179, 46, 48);
		contentPane.add(lblStatus);
		
		
	}// fim do construtor

	/**
	 * metodo para autenticar um usuario
	 */
	private void logar() {
		// validacao
		String capturaSenha = new String(txtSenha.getPassword());
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0) {

			JOptionPane.showMessageDialog(null, "Preencha a senha");
			txtSenha.requestFocus();
		} else {
			// logica principal
			String read = "select * from usuarios where login=? and senha=md5(?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				rs = pst.executeQuery();
				if (rs.next()) {
					//logar -> acessar a tela principal
					//capturar o perfil do usuario
					//System.out.println(rs.getString(5));apoio a logica
					//tratameno do perfil do usuario
					
					String perfil = rs.getString(5);
					if(perfil.equals("admin")) {
					
					principal.setVisible(true);
					principal.lblUsuario.setText(rs.getString(2));
					//habilitar os botoes
					principal.btnRelatorio.setEnabled(true);
					principal.btnUsuarios.setEnabled(true);
					principal.panelRodape.setBackground(Color.RED);
					//fechar a tela de login
					this.dispose();
					// logar
					principal.setVisible(true);
					// fechar a tela de login
					
				} else {
					principal.setVisible(true);
					principal.lblUsuario.setText(rs.getString(2));
					this.dispose();
				}
				} else {
					
					JOptionPane.showMessageDialog(null, "usuario e/ou senha inválido(s)");
					con.close();
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
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
}


//fim do codigo
