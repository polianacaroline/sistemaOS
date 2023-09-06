package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.DAO;
import utils.Validador;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	DAO dao = new DAO();

	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private JButton btnEditar;
	private JButton btnAdicionar;
	private JButton btnLimpar;
	private JButton btnApagar;
	private JButton btnBuscar;
	private AbstractButton btnPesquisar;
	private JComponent listaUsuarios;
	private JLabel lblNewLabel;
	private JComboBox cboPerfil;
	private JCheckBox chckSenha;
	private JScrollPane scrollPaneUsers;
	private JList listUsers;
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
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
	public Usuarios() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				scrollPaneUsers.setVisible(false);
				txtNome.setText(null);
				
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/configuracao.png")));
		setTitle("Usuarios");
		setModal(true);
		setBounds(100, 100, 523, 303);
		getContentPane().setLayout(null);

		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Dialog", Font.BOLD, 12));
		lblID.setBounds(10, 13, 46, 14);
		getContentPane().add(lblID);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNome.setBounds(10, 64, 46, 14);
		getContentPane().add(lblNome);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(75, 11, 118, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		txtNome = new JTextField();
		txtNome.addMouseListener(new MouseAdapter() {
			@Override
			
			
			
			public void mouseClicked(MouseEvent e) {
			}
		});
		txtNome.setBorder(null);
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {// solta a tecla
				listaUsuario();
			}
		});
		txtNome.setBounds(66, 62, 157, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador(20));

		JLabel lblLogin = new JLabel("login");
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 12));
		lblLogin.setBounds(10, 131, 46, 14);
		getContentPane().add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		txtLogin.setBounds(66, 129, 157, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		txtLogin.setDocument(new Validador(10));

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSenha.setBounds(10, 167, 46, 14);
		getContentPane().add(lblSenha);

		btnLimpar = new JButton("");
		btnLimpar.setOpaque(false);
		btnLimpar.setBorderPainted(false);
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/9110490_rubber_icon.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();

			}
		});
		btnLimpar.setToolTipText("Apagar");
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setBounds(87, 181, 52, 42);
		getContentPane().add(btnLimpar);

		txtSenha = new JPasswordField();
		txtSenha.setBackground(UIManager.getColor("Button.highlight"));
		txtSenha.setEnabled(false);
		txtSenha.setBorder(null);
		txtSenha.setBounds(66, 160, 157, 20);
		getContentPane().add(txtSenha);
		txtSenha.setDocument(new Validador(200));

		btnAdicionar = new JButton("");
		btnAdicionar.setToolTipText("Adicionar novo usuario");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setEnabled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/216490_add_user_icon.png")));
		btnAdicionar.setBounds(191, 191, 32, 32);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.setToolTipText("Editar");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setEnabled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setContentAreaFilled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				if (chckSenha.isSelected()) {
					editarContato();
				
					
				} else {
				
					
					editarContatoExcetoSenha();
				}
				
			}
		});
		btnEditar.setBorder(null);
		btnEditar.setIcon(
				new ImageIcon(Usuarios.class.getResource("/img/4831013_adit_create_pen_pencil_write_icon.png")));
		btnEditar.setBounds(149, 191, 32, 32);
		getContentPane().add(btnEditar);

		btnApagar = new JButton("");
		btnApagar.setEnabled(false);
		btnApagar.setToolTipText("Excluir usuario");
		btnApagar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnApagar.setBorder(null);
		btnApagar.setContentAreaFilled(false);
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContato();
			}
		});
		btnApagar.setIcon(
				new ImageIcon(Usuarios.class.getResource("/img/1564505_close_delete_exit_remove_icon (1).png")));
		btnApagar.setBounds(28, 192, 89, 31);
		getContentPane().add(btnApagar);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 228, 620, 42);
		getContentPane().add(panel);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				read();
			}
		});
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setIcon(
				new ImageIcon(Usuarios.class.getResource("/img/510919_find_magnifying glass_search_zoom_icon.png")));
		btnPesquisar.setBounds(213, 0, 40, 41);
		getContentPane().add(btnPesquisar);
		getRootPane().setDefaultButton(btnPesquisar);
				
				lblNewLabel = new JLabel("Perfil");
				lblNewLabel.setBounds(376, 14, 46, 14);
				getContentPane().add(lblNewLabel);
				
				cboPerfil = new JComboBox();
				cboPerfil.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
					}
				});
				cboPerfil.setModel(new DefaultComboBoxModel(new String[] {" ", "admin", "users"}));
				cboPerfil.setBounds(432, 10, 61, 22);
				getContentPane().add(cboPerfil);
				
				chckSenha = new JCheckBox("AlterarSenha");
				chckSenha.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (chckSenha.isSelected()) {
							txtSenha.setText(null);
							txtSenha.requestFocus();
							txtSenha.setBackground(Color.pink);
							txtSenha.setEnabled(true);
							
							
							
						} else {
							txtSenha.setBackground(Color.WHITE);
							txtSenha.setEnabled(false);
						}
						
						
						
						
					}
				});
				chckSenha.setBounds(383, 191, 118, 23);
				getContentPane().add(chckSenha);
				
				scrollPaneUsers = new JScrollPane();
				scrollPaneUsers.setBounds(66, 80, 157, 32);
				getContentPane().add(scrollPaneUsers);
				
				listUsers = new JList();
				listUsers.setBorder(null);
				listUsers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						buscarUsuarioLista();
					}
				});
				scrollPaneUsers.setViewportView(listUsers);

	}// fim construtor

	private void limparCampos() {
		txtID.setText(null);
		txtNome.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		txtSenha.setBackground(Color.WHITE);
		
		btnApagar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnAdicionar.setEnabled(false);
		btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		txtSenha.setEnabled(false);
		cboPerfil.setSelectedItem("");
		chckSenha.setSelected(false);
		
	
		//clicar na tela ele limpa
	}

	/**
	 * PRA VER SE FUNCIONA método para adicionar um novo contato E SO UM TESTE
	 */

	private void read() {

		// System.out.println("Teste do botão buscar");

		// Criar uma variável com a query (instrução do banco)

		// Tratamento de exceções
		String read = "select * from usuarios where login = ?";
		try {
			// abrir a conexão
			con = dao.conectar();
			// preparar a execucão da query( instrução sql - CRUD Read)

			// o parâmetro 1 substitui a ? pelo conteúdo da caixa de texto
			pst = con.prepareStatement(read);
			pst.setString(1, txtLogin.getText());
			// executar a query e buscar o resultado
			rs = pst.executeQuery();
			// uso da estrutura if else para verificar se existe o contato
			// rs.next() -> se existir um contato no banco
			if (rs.next()) {
				// preencher as caixas de texto com as informações

				txtID.setText(rs.getString(1)); // 1º Campo da Tabela ID
				txtNome.setText(rs.getString(2)); // 2º Campo da Tabela Nome
				txtLogin.setText(rs.getString(3)); // 3º Campo da Tabela Login
				txtSenha.setText(rs.getString(4)); // 4º Campo da Tabela Senha
				cboPerfil.setSelectedItem(rs.getString(5));
				btnEditar.setEnabled(true);
				btnApagar.setEnabled(true);
                
			} else {
				// System.out.println("Contaos não cadastrados");
				JOptionPane.showMessageDialog(null, "Usuario inexistente");
				btnAdicionar.setEnabled(true);

			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}

	}

	private void adicionar() {
		String capturaSenha = new String(txtSenha.getPassword());

		// System.out.println("teste");
		// systen.out.println("teste")
		// validacao de campos obrigatorios

		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome ");
			txtNome.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login");
			txtLogin.requestFocus();
			

		} else if (capturaSenha.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha");
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals(" ")) {
			JOptionPane.showMessageDialog(null, "Preencha o Perfil do usuario ");
			cboPerfil.requestFocus();
		} else {
			// logica principal
			// CRUD Create
			String create = "insert into usuarios(nome,login,senha,perfil) values (?,?,md5(?),?)";
			// tratamento de excecoes
			try {
				// abrir a conexao
				con = dao.conectar();
				// preparar a execucao da query(instrucao sql - CRUD create)
				pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				// executar a query(instrucao sql (CRUD - Create)
				pst.executeUpdate();
				// confirmar
				JOptionPane.showMessageDialog(null, "usuario adicionado");
				// limpar campos
				limparCampos();
				// fechar a conexao
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Usuário não adicionado.\nEste login já está sendo utilizado.");
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
		}
			}
		}
	

	private void editarContato() {
		
		String capturaSenha = new String(txtSenha.getPassword());
		

		// System.out.println("Teste do botão");
		// validacao dos campos obrigatorio
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do usuario");
			txtNome.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usuario");
			txtLogin.requestFocus();

		} else if (cboPerfil.getSelectedItem().equals(" ")) {
			JOptionPane.showMessageDialog(null, "Preencha o Perfil do usuario ");
			cboPerfil.requestFocus();
		} else {
			// logica principal
			// CRUD -UPDATE
			String update = "update usuarios set nome=?,login=?,senha=md5(?), perfil=? where id=?";
			// tratamento de excecoes
			try {
				// abrir conexão
				con = dao.conectar();
				// preparar a query (instrucao sql)
				pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtID.getText());
				
				// execuatra a query
				pst.executeUpdate();
				// confirmar para o usuario
				JOptionPane.showMessageDialog(null, "Dados do usuarios editados com sucesso!!");
				// limpar campos
				limparCampos();
				// fechar Campos
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void editarContatoExcetoSenha() {
		
		String capturaSenha = new String(txtSenha.getPassword());
		

		// System.out.println("Teste do botão");
		// validacao dos campos obrigatorio
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do usuario");
			txtNome.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usuario");
			txtLogin.requestFocus();

		} else if (capturaSenha.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o perfil do usuario ");
			txtSenha.requestFocus();
		} else {
			// logica principal
			// CRUD -UPDATE
			String update2 = "update usuarios set nome=?,login=?,perfil=? where id=?";
			// tratamento de excecoes
			try {
				// abrir conexão
				con = dao.conectar();
				// preparar a query (instrucao sql)
				pst = con.prepareStatement(update2);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtID.getText());
				
				// execuatra a query
				pst.executeUpdate();
				// confirmar para o usuario
				JOptionPane.showMessageDialog(null, "Dados do usuarios editados com sucesso!!");
				// limpar campos
				limparCampos();
				// fechar Campos
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void excluirContato() {
		// System.out.println("teste d botão excluir");
		// fim do método excluircontato
		// validacao de exclusao
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusao deste usuario?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// crud - delete
			String delete = "delete from usuarios where id=?";
			// tratamento de excecoes
			try {
				// abrir conexao
				con = dao.conectar();
				// preparar a query (instrucao sql)
				pst = con.prepareStatement(delete);
				// susbtituir a ? pelo id do contato
				pst.setString(1, txtID.getText());
				// executar a query
				pst.executeUpdate();
				// limparCampos
				limparCampos();
				// exibir uma mensagem ao usuario
				JOptionPane.showMessageDialog(null, "Usuario excluido");
				// fechar a conxão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// metodo usado para listausuarios na lista
	private void listaUsuario() {
		
		// System.out.println("Teste");
		// a linha cria um objeto usando como referencia um vetor dinamico, este objeto
		// ira temporiamaente amrazenar
		DefaultListModel<String> modelo = new DefaultListModel<>();
		
	listUsers.setModel(modelo);
		// QUERY (instrucao sql)
		String readLista = "select * from usuarios where nome like'" + txtNome.getText() + "%'" + "order by nome";

		try {
			// abrir a conexao
			con = dao.conectar();
			pst = con.prepareStatement(readLista);
			// preparar a query e trazer o resultado para a lista
			rs = pst.executeQuery();
			// uso do while para trazer os usuarios qaunto existir
			while (rs.next()) {
				// mostra a lista de usuarios
			
				// adicionar os usuarios no vetor
				modelo.addElement(rs.getString(2));
				// esconder o scrollpane se nehuma letra for digitada
				
				
			 if (txtNome.getText().isEmpty()) {
			scrollPaneUsers.setVisible(false);
				
			}
			}
		} catch (Exception e) {
			System.out.println(e);
		
		}

	}

	private void buscarUsuarioLista() {

	 

	        // System.out.println("teste");

	 

	        // variavel que captuar o indice da linha da lista

	 

	        int linha = listUsers.getSelectedIndex();	

	        if (linha >= 0) {	 
	            // String readBuscaLista=
	 

	            // Query (instrução sql)
	 
	            // limite " , 1" -> selecionar o indice 0 e 1 usuario da lista
 

	            String readBuscaLista = "select *from usuarios where nome like '" + txtNome.getText() + "%'"
	                    + "order by nome limit " + (linha) + " ,1";
	 
	            try {	 
	                con = dao.conectar();
	                pst = con.prepareStatement(readBuscaLista);
	                rs = pst.executeQuery();	 
	                if (rs.next()) {
	 

	                	scrollPaneUsers.setVisible(false);	                    
	                    txtID.setText(rs.getString(1));
	                    txtNome.setText(rs.getString(2));
	                    txtLogin.setText(rs.getString(3));
	                    txtSenha.setText(rs.getString(4));
	                    cboPerfil.setSelectedItem(rs.getString(5));
	                    btnEditar.setEnabled(true);
	                    btnApagar.setEnabled(true);
	                    
	                    txtSenha.setBackground(Color.green);
	                    txtSenha.setEnabled(true);
	                    
	                    


	                } else {	
	                    // System.out.println("Contatos não cadastrados");	 
	                    JOptionPane.showMessageDialog(null, "Usuario inexistente");	 
	                }

	                con.close();
	 
	            } catch (Exception e) {
	 
	            }
	 
	        } else {
	 
	            scrollPaneUsers.setVisible(false);
	            
	        }

	    }

	}


