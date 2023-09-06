package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import utils.Validador;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Cliente extends JDialog {
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private JTextField txtBairro;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtCpf;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JTextField txtID;

	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnLimpar;
	private JButton btnBuscar;
	private JTextField txtComplemento;
	private JTextField txtNumero;
	private JTextField txtCep;
	private JTextField txtCidade;
	private JComboBox cboUF;
	private JList listUsers;
	private JScrollPane scrollPaneUsers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente dialog = new Cliente();
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
	public Cliente() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPaneUsers.setVisible(false);
			}
		});
		getContentPane().setBackground(new Color(192, 192, 192));
		getContentPane().setForeground(new Color(0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Cliente.class.getResource("/img/1243707_login_register_signup_access_icon.png")));
		setTitle("CADASTRO ");
		setBounds(100, 100, 588, 469);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 24, 46, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 60, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 116, 46, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("CPF");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(336, 116, 46, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Endereço");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(10, 141, 60, 14);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Telefone");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(10, 166, 60, 14);
		getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Bairro");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(336, 166, 46, 14);
		getContentPane().add(lblNewLabel_6);

		txtBairro = new JTextField();
		txtBairro.setBounds(387, 163, 138, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);
		txtBairro.setDocument(new Validador(50));

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listaClientes();

			}
		});
		txtNome.setBounds(66, 57, 159, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador(50));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(66, 113, 213, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setDocument(new Validador(50));

		txtCpf = new JTextField();
		txtCpf.setBounds(387, 110, 138, 20);
		getContentPane().add(txtCpf);
		txtCpf.setColumns(10);
		txtCpf.setDocument(new Validador(11));
		txtEndereco = new JTextField();
		txtEndereco.setBounds(66, 138, 459, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);
		txtEndereco.setDocument(new Validador(100));

		txtTelefone = new JTextField();
		txtTelefone.setBounds(66, 163, 213, 20);
		getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);
		txtTelefone.setDocument(new Validador(16));
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(66, 21, 94, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("CADASTRO CLIENTE");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setToolTipText("");
		lblNewLabel_7.setBounds(169, 21, 225, 26);
		getContentPane().add(lblNewLabel_7);

		JButton btnAdicionar_1 = new JButton("");
		btnAdicionar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();

			}
		});
		btnAdicionar_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar_1.setToolTipText("Adicionar");
		btnAdicionar_1.setIcon(new ImageIcon(Cliente.class.getResource("/img/66907_user_add_icon.png")));
		btnAdicionar_1.setBounds(173, 307, 89, 41);
		getContentPane().add(btnAdicionar_1);

		JButton EditarClientes = new JButton("");
		EditarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarClientes();  
			
			}
		});
		EditarClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		EditarClientes.setToolTipText("Editar");
		EditarClientes.setIcon(new ImageIcon(Cliente.class.getResource("/img/66910_edit_user_icon.png")));
		EditarClientes.setBounds(10, 307, 89, 41);
		getContentPane().add(EditarClientes);

		JButton btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setIcon(new ImageIcon(Cliente.class.getResource("/img/66898_trash_delete_icon.png")));
		btnExcluir.setBounds(316, 307, 89, 41);
		getContentPane().add(btnExcluir);

		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();

			}
		});
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setToolTipText("Limpar");
		btnLimpar.setIcon(new ImageIcon(Cliente.class.getResource("/img/9110490_rubber_icon.png")));
		btnLimpar.setBounds(441, 307, 89, 41);
		getContentPane().add(btnLimpar);

		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumero.setBounds(321, 194, 56, 14);
		getContentPane().add(lblNumero);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblComplemento.setBounds(10, 194, 114, 14);
		getContentPane().add(lblComplemento);

		JLabel lblUF = new JLabel("UF");
		lblUF.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUF.setBounds(10, 232, 80, 14);
		getContentPane().add(lblUF);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(100, 194, 204, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		txtNumero = new JTextField();
		txtNumero.setBounds(387, 194, 138, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCidade.setBounds(100, 257, 60, 14);
		getContentPane().add(lblCidade);

		txtCep = new JTextField();
		txtCep.setBounds(150, 230, 143, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);

		JLabel lblCEP = new JLabel("CEP");
		lblCEP.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCEP.setBounds(110, 232, 60, 14);
		getContentPane().add(lblCEP);

		txtCidade = new JTextField();
		txtCidade.setBounds(150, 255, 143, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		cboUF = new JComboBox();
		cboUF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUF.setBounds(43, 229, 56, 22);
		getContentPane().add(cboUF);

		JButton btnBuscarCep = new JButton("buscar CEP");
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnBuscarCep.setBounds(296, 229, 109, 23);
		getContentPane().add(btnBuscarCep);

		scrollPaneUsers = new JScrollPane();
		scrollPaneUsers.setVisible(false);
		scrollPaneUsers.setBounds(66, 72, 159, 30);
		getContentPane().add(scrollPaneUsers);

		listUsers = new JList();
		listUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BuscarClientesLista();
			}
		});
		scrollPaneUsers.setViewportView(listUsers);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		panel.setBounds(0, 398, 593, 41);
		getContentPane().add(panel);

	}
		

//limpar
	private void limpar() {
		txtID.setText(null);
		txtNome.setText(null);
		txtCpf.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCep.setText(null);
		txtCidade.setText(null);
		txtTelefone.setText(null);
		txtEmail.setText(null);

	}

	private void adicionar() {
		// System.out.println("teste");
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do cliente");
			txtNome.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CPF do cliente");
			txtCpf.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Endereço do cliente");
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o numero do cliente ");
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha seu Bairro");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha sua Cidade");
			txtCidade.requestFocus();
		} else if (txtTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha seu Telefone");
			txtTelefone.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha seu Email");
			txtEmail.requestFocus();
		} else {
			

			// logica principal
			// CRUD Create
			String create = "insert into clientes(nome, cpf,endereco,numero,complemento,bairro,cep,cidade,uf,fone,email) values (?,?,?,?,?,?,?,?,?,?,?)";
			// tratamento de excecoes
			try {
				// abrir a conexao
				con = dao.conectar();
				// preparar a execucao da query(instrucao sql - CRUD create)
				pst = con.prepareStatement(create);

				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCpf.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtNumero.getText());

				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtCep.getText());
				pst.setString(8, txtCidade.getText());

				pst.setString(9, cboUF.getSelectedItem().toString());

				pst.setString(10, txtTelefone.getText());
				pst.setString(11, txtEmail.getText());

				// executar a query(instrucao sql (CRUD - Create)
				pst.executeUpdate();
				// confirmar
				JOptionPane.showMessageDialog(null, "Cliente adicionado");
				// lpar campos
				limpar();
				// fechar a conexao
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {

                JOptionPane.showMessageDialog(null, "Cliente não adicionado.\nEste CPF, RG ou EMAIL já está sendo utilizado.");

                txtCpf.setText(null);
                txtCpf.requestFocus();
                txtEmail.setText(null);
                txtEmail.requestFocus();

            

            } catch (Exception e2) {

                System.out.println(e2);

        }

        }

        

    }

	private void excluir() {
		// System.out.println("teste d botão excluir");
//fim do método excluircontato
		// validacao de exclusao
		int confirma = JOptionPane.showConfirmDialog(null, "cliente nao excluido?", "este cliente tem uma OS pendente!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// crud - delete
			String delete = "delete from clientes where id=?";
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
				limpar();
				// exibir uma mensagem ao usuario
				JOptionPane.showMessageDialog(null, "cliente excluido");
				// fechar a conxão
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {

                JOptionPane.showMessageDialog(null, "Cliente não excluido.\nEste cliente tem uma OS pendente.");

                txtID.setText(null);

                txtID.requestFocus();

                

            

            } catch (Exception e2) {

                System.out.println(e2);

        }

        }

        

    }
		private void editarClientes() {
			// System.out.println("Teste do botão");
			// validacao dos campos obrigatorio
			if (txtNome.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o nome do cliente");
				txtNome.requestFocus();
			} else if (txtCpf.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o CPF do cliente");
				txtCpf.requestFocus();
			} else if (txtEndereco.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Endereço do cliente");
				txtEndereco.requestFocus();
			} else if (txtNumero.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Numero do endereço ");
				txtNumero.requestFocus();
			} else if (txtComplemento.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Complemento");
				txtComplemento.requestFocus();
			} else if (txtBairro.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha seu Bairro");
				txtBairro.requestFocus();
			} else if (txtCidade.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha sua Cidade");
				txtCidade.requestFocus();
			} else if (txtTelefone.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha seu Telefone");
				txtTelefone.requestFocus();
			} else if (txtEmail.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha seu Email");
				txtEmail.requestFocus();
				// logica principal
				// CRUD -UPDATE
			} else {
				String update = "update clientes set nome=?,cpf=?,endereco=?,numero=?,complemento=?,bairro=?,cep=?,cidade=?,uf=?,fone=?,email=? where id=?";
				// tratamento de excecoes
				
				try {
					con = dao.conectar();
					pst = con.prepareStatement(update);
					// preparar a query (instrucao sql)
					pst.setString(1, txtNome.getText());
					pst.setString(2, txtCpf.getText());
					pst.setString(3, txtEndereco.getText());
					pst.setString(4, txtNumero.getText());
					pst.setString(5, txtComplemento.getText());
					pst.setString(6, txtBairro.getText());
					pst.setString(7, txtCep.getText());
					pst.setString(8, txtCidade.getText());
					pst.setString(9, cboUF.getSelectedItem().toString());
					pst.setString(10, txtTelefone.getText());
					pst.setString(11, txtEmail.getText());
					pst.setString(12, txtID.getText());
				
					// execuatra a query
					pst.executeUpdate();
					// confirmar para o usuario
					JOptionPane.showMessageDialog(null, "Dados do clientes editados com sucesso!!");
					// limpar campos
					limpar();
					// fechar Campos
					con.close();

				} catch (Exception e) {
					System.out.println(e);
				}

			}
		

	}// FIM DO CODIGO

	/**
	 * buscarCep
	 */
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUF.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						System.out.println("OK");
						// lblStatus.setIcon(new
						// javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void listaClientes() {
		// System.out.println("Teste");
		// a linha cria um objeto usando como referencia um vetor dinamico, este objeto
		// ira temporiamaente amrazenar
		DefaultListModel<String> modelo = new DefaultListModel<>();
		// setar o modelo do vetor da lista
		listUsers.setModel(modelo);
		// QUERY (instrucao sql)
		String readLista = "select * from clientes where nome like'" + txtNome.getText() + "%'" + "order by nome";

		try {
			// abrir a conexao
			con = dao.conectar();
			pst = con.prepareStatement(readLista);
			// preparar a query e trazer o resultado para a lista
			rs = pst.executeQuery();
			// uso do while para trazer os usuarios qaunto existir
			while (rs.next()) {
				// mostra a lista de usuarios
				scrollPaneUsers.setVisible(true);
				// adicionar os usuarios no vetor
				modelo.addElement(rs.getString(2));
				// esconder o scrollpane se nehuma letra for digitada
				if (txtNome.getText().isEmpty()) {
					scrollPaneUsers.setVisible(false);
				}
				// if (txtNome.getText().isEmpty()) {
				// scrollPaneUsers.setVisible(false);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void BuscarClientesLista() {
		// System.out.println("Teste");
		int linha = listUsers.getSelectedIndex();
		if (linha >= 0) {
			// query
			// limit (0,1) > seleciona o indice de 0 1 usuario
			String readListaClientes = "select * from clientes where nome like'" + txtNome.getText() + "%'"
					+ "order by nome limit " + (linha) + " , 1";
			try {
				// abrirconexao
				con = dao.conectar();
				pst = con.prepareStatement(readListaClientes);
				// executar e obter o resultado
				rs = pst.executeQuery();
				if (rs.next()) {
					// se existir um usuario esconder a lista
					scrollPaneUsers.setVisible(false);

					// setar os campos
					txtNome.setText(rs.getString(2));
					txtCpf.setText(rs.getString(3));
					txtEndereco.setText(rs.getString(4));
					txtNumero.setText(rs.getString(5));

					txtComplemento.setText(rs.getString(6));
					txtBairro.setText(rs.getString(7));
					txtCep.setText(rs.getString(8));
					txtCidade.setText(rs.getString(9));

					cboUF.setSelectedItem(rs.getString(10));

					txtTelefone.setText(rs.getString(11));
					txtEmail.setText(rs.getString(12));
					txtID.setText(rs.getString(1));

				} else {
					JOptionPane.showMessageDialog(null, "Clientes Inexistente");

				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			scrollPaneUsers.setVisible(false);
		}
		// captura o indice da linha
	}
}// FIM DO CONSTRUTOR
