package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;

public class Servicos extends JDialog {
	private static final PdfWriter writer = null;
	private JTextField txtOS;
	private JTextField txtData;
	private JTextField txtEquipamento;
	private JTextField txtDefeito;
	private JTextField txtValor;
	private JTextField txtID;
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private JTextField txtCliente;
	private JScrollPane scrollPaneCli;
	private JList listCli;
	private Font contentFont;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servicos dialog = new Servicos();
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
	public Servicos() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPaneCli.setVisible(false);
			}
		});
		setModal(true);
		setBounds(100, 100, 568, 394);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("OS");
		lblNewLabel.setBounds(25, 22, 46, 14);
		getContentPane().add(lblNewLabel);

		txtOS = new JTextField();
		txtOS.setEditable(false);
		txtOS.setBounds(93, 19, 134, 20);
		getContentPane().add(txtOS);
		txtOS.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data/Hora");
		lblNewLabel_1.setBounds(25, 65, 58, 14);
		getContentPane().add(lblNewLabel_1);

		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setBounds(93, 62, 134, 20);
		getContentPane().add(txtData);
		txtData.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Equipamento");
		lblNewLabel_2.setBounds(11, 119, 89, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Defeito");
		lblNewLabel_3.setBounds(25, 169, 46, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Valor");
		lblNewLabel_4.setBounds(25, 218, 46, 14);
		getContentPane().add(lblNewLabel_4);

		txtEquipamento = new JTextField();
		txtEquipamento.setBounds(93, 116, 241, 20);
		getContentPane().add(txtEquipamento);
		txtEquipamento.setColumns(10);

		txtDefeito = new JTextField();
		txtDefeito.setBounds(93, 166, 241, 20);
		getContentPane().add(txtDefeito);
		txtDefeito.setColumns(10);

		txtValor = new JTextField();
		txtValor.setBounds(93, 215, 183, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setIcon(
				new ImageIcon(Servicos.class.getResource("/img/510919_find_magnifying glass_search_zoom_icon.png")));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setBounds(241, 18, 95, 34);
		getContentPane().add(btnBuscar);

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();

			}
		});
		btnAdicionar
				.setIcon(new ImageIcon(Servicos.class.getResource("/img/4781840_+_add_circle_create_expand_icon.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBounds(11, 276, 89, 34);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarOS();
			}
		});
		btnEditar.setIcon(
				new ImageIcon(Servicos.class.getResource("/img/4831013_adit_create_pen_pencil_write_icon.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(110, 276, 89, 34);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Excluir();
			}
		});
		btnExcluir.setIcon(new ImageIcon(Servicos.class.getResource("/img/4115238_delete_trash_icon (1).png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBounds(221, 276, 89, 34);
		getContentPane().add(btnExcluir);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		panel.setBounds(0, 321, 552, 34);
		getContentPane().add(panel);

		JButton btnLimpar = new JButton("");
		btnLimpar.setToolTipText("Limpar");
		btnLimpar.setIcon(new ImageIcon(Servicos.class.getResource("/img/9110490_rubber_icon.png")));
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(320, 276, 89, 34);
		getContentPane().add(btnLimpar);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(372, 11, 170, 94);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		scrollPaneCli = new JScrollPane();
		scrollPaneCli.setVisible(false);
		scrollPaneCli.setBounds(10, 39, 150, 44);
		panel_1.add(scrollPaneCli);

		listCli = new JList();
		listCli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BuscarClientesLista();

			}
		});
		scrollPaneCli.setRowHeaderView(listCli);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(45, 63, 86, 20);
		panel_1.add(txtID);
		txtID.setColumns(10);

		txtCliente = new JTextField();
		txtCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarClientes();
			}
		});
		txtCliente.setBounds(10, 21, 150, 20);
		panel_1.add(txtCliente);
		txtCliente.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("ID");
		lblNewLabel_5.setBounds(10, 66, 25, 14);
		panel_1.add(lblNewLabel_5);

		JButton btnOS = new JButton("");
		btnOS.setToolTipText("Imprimir");
		btnOS.setIcon(new ImageIcon(Servicos.class.getResource("/img/printer.png")));
		btnOS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirOS();
			}
		});
		btnOS.setBounds(432, 276, 89, 34);
		getContentPane().add(btnOS);

	}

	private void buscar() {
		// captura do numero da OS(sem caixa de TEXTO)
		String numOS = JOptionPane.showInputDialog("Numero da OS");

		// System.out.println("Teste do botão buscar");

		// Criar uma variável com a query (instrução do banco)

		// Tratamento de exceções
		String read = "select * from servicos where os = ?";
		try {

			// abrir a conexão
			con = dao.conectar();
			// preparar a execucão da query( instrução sql - CRUD Read)
			pst = con.prepareStatement(read);
			pst.setString(1, numOS);
			// o parâmetro 1 substitui a ? pelo conteúdo da caixa de texto
			// executar a query e buscar o resultado
			rs = pst.executeQuery();
			// uso da estrutura if else para verificar se existe o contato
			// rs.next() -> se existir um contato no banco
			if (rs.next()) {
				// preencher as caixas de texto com as informações

				txtOS.setText(rs.getString(1)); // 1º Campo da Tabela ID
				txtData.setText(rs.getString(2)); // 3º Campo da Tabela Login
				txtEquipamento.setText(rs.getString(3)); // 4º Campo da Tabela Senha
				txtDefeito.setText(rs.getString(4));
				txtValor.setText(rs.getString(5));
				txtID.setText(rs.getString(6));
				// btn.setEnabled(true);
				// btnApagar.setEnabled(true);

			} else {
				// System.out.println("Contaos não cadastrados");
				JOptionPane.showMessageDialog(null, "OS inexistente");
				// btnAdicionar.setEnabled(true);

			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void adicionar() {
		// System.out.println("teste");
		// if (txtOS.getText().isEmpty()) {
		// JOptionPane.showMessageDialog(null, "Preencha a OS");
		// txtOS.requestFocus();

		if (txtEquipamento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Equipamento");
			txtEquipamento.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o  Defeito");
			txtDefeito.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Valor ");
			txtValor.requestFocus();
		} else {

			// logica principal
			// CRUD Create
			String create = "insert into servicos(equipamento,defeito,valor,id) values (?,?,?,?)";
			// tratamento de excecoes
			try {
				// abrir a conexao
				con = dao.conectar();
				// preparar a execucao da query(instrucao sql - CRUD create)
				pst = con.prepareStatement(create);

				pst.setString(1, txtEquipamento.getText());
				pst.setString(2, txtDefeito.getText());
				pst.setString(3, txtValor.getText());
				pst.setString(4, txtID.getText());

				// executar a query(instrucao sql (CRUD - Create)
				pst.executeUpdate();
				// confirmar
				JOptionPane.showMessageDialog(null, "OS adicionada");
				// lpar campos
				limpar();
				// fechar a conexao
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void editarOS() {
		// System.out.println("Teste do botão");
		// validacao dos campos obrigatorio
		if (txtOS.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a OS");
			txtOS.requestFocus();
		} else if (txtEquipamento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Equipamento");
			txtEquipamento.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o  Defeito");
			txtDefeito.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Valor ");
			txtValor.requestFocus();
		} else {
			// logica principal
			// CRUD -UPDATE
		}
		String update = "update servicos set equipamento=?,defeito=?,valor=? where id=?";
		// tratamento de excecoes

		try {
			con = dao.conectar();
			pst = con.prepareStatement(update);
			// preparar a query (instrucao sql)
			pst.setString(1, txtEquipamento.getText());
			pst.setString(2, txtDefeito.getText());
			pst.setString(3, txtValor.getText());
			pst.setString(4, txtID.getText());

			// execuatra a query
			pst.executeUpdate();
			// confirmar para o usuario
			JOptionPane.showMessageDialog(null, "Dados da OS editados com sucesso!!");
			// limpar campos
			limpar();
			// fechar Campos
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void limpar() {
		txtID.setText(null);
		txtData.setText(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtValor.setText(null);
		txtCliente.setText(null);

	}

	private void listarClientes() {
		// System.out.println("Teste");
		// a linha cria um objeto usando como referencia um vetor dinamico, este objeto
		// ira temporiamaente amrazenar
		DefaultListModel<String> modelo = new DefaultListModel<>();
		// setar o modelo do vetor da lista
		listCli.setModel(modelo);
		// QUERY (instrucao sql)
		String readLista = "select * from clientes where nome like'" + txtCliente.getText() + "%'" + "order by nome";

		try {
			// abrir a conexao
			con = dao.conectar();
			pst = con.prepareStatement(readLista);
			// preparar a query e trazer o resultado para a lista
			rs = pst.executeQuery();
			// uso do while para trazer os usuarios qaunto existir
			while (rs.next()) {
				// mostra a lista de usuarios
				scrollPaneCli.setVisible(true);
				// adicionar os usuarios no vetor
				modelo.addElement(rs.getString(2));
				// esconder o scrollpane se nehuma letra for digitada
				if (txtCliente.getText().isEmpty()) {
					scrollPaneCli.setVisible(false);
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
		int linha = listCli.getSelectedIndex();
		if (linha >= 0) {
			// query
			// limit (0,1) > seleciona o indice de 0 1 usuario
			String readListaClientes = "select * from clientes where nome like'" + txtCliente.getText() + "%'"
					+ "order by nome limit " + (linha) + " , 1";
			try {
				// abrirconexao
				con = dao.conectar();
				pst = con.prepareStatement(readListaClientes);
				// executar e obter o resultado
				rs = pst.executeQuery();
				if (rs.next()) {
					// se existir um usuario esconder a lista
					scrollPaneCli.setVisible(false);

					// setar os campos
					txtID.setText(rs.getString(1));
					txtCliente.setText(rs.getString(2));

				} else {
					JOptionPane.showMessageDialog(null, "Cliente Inexistente");

				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			scrollPaneCli.setVisible(false);
		}
		// captura o indice da linha
	}

	private void Excluir() {
		// System.out.println("teste d botão excluir");
//fim do método excluircontato
		// validacao de exclusao
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusao desta OS?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// crud - delete
			String delete = "delete from servicos where os=?";
			// tratamento de excecoes
			try {
				// abrir conexao
				con = dao.conectar();
				// preparar a query (instrucao sql)
				pst = con.prepareStatement(delete);
				// susbtituir a ? pelo id do contato
				pst.setString(1, txtOS.getText());
				// executar a query
				pst.executeUpdate();
				// limparCampos
				limpar();
				// exibir uma mensagem ao usuario
				JOptionPane.showMessageDialog(null, "OS excluida");
				// fechar a conxão
				con.close();
			} catch (Exception e) {
				System.out.println(e);

			}

		}
	}

	/**
	 * Impressão da OS
	 */
	private void imprimirOS() {

		Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

		// instanciar objeto para usar os métodos da biblioteca
		Document document = new Document();
		// documento pdf
		if (txtEquipamento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo do equipamento");
			txtEquipamento.requestFocus();

		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo defeito");
			txtDefeito.requestFocus();

		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo valor");

			txtValor.requestFocus();

		} else {
			try {

				// criar um documento em branco (pdf) de nome clientes.pdf
				PdfWriter.getInstance(document, new FileOutputStream("os.pdf"));
				// abrir o documento (formatar e inserir o conteúdo)
				document.open();
				String readOS = "select * from servicos inner join clientes on servicos.id = clientes.id where os =?";
				// conexão com o banco
				try {
					// abrir a conexão
					con = dao.conectar();
					// preparar a execução da query (instrução sql)
					pst = con.prepareStatement(readOS);
					pst.setString(1, txtOS.getText());
					// executar a query
					rs = pst.executeQuery();

					// se existir a OS
					if (rs.next()) {
						// imprimir imagens


												
						
						
						PdfPTable table = new PdfPTable(14); // 2 colunas

						PdfPCell col2 = new PdfPCell(new Paragraph("Equipamento: "));
						PdfPCell col3 = new PdfPCell(new Paragraph("Defeito: "));
						PdfPCell col4 = new PdfPCell(new Paragraph("Valor"));
						PdfPCell col5 = new PdfPCell(new Paragraph("Nome: "));
						PdfPCell col6 = new PdfPCell(new Paragraph("Fone: "));
						PdfPCell col7 = new PdfPCell(new Paragraph("Email: "));
						
						Paragraph header = new Paragraph("Orçamento", FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD));
						header.setAlignment(Element.ALIGN_CENTER);
						document.add(header);
						
						
						
						Paragraph OS = new Paragraph("DoctorCell-MA");
						OS.setAlignment(Element.ALIGN_CENTER);
						document.add(OS);
						Paragraph desc2 = new Paragraph("Assistência Técnica em tablets e celulares",
								FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD));
								desc2.setAlignment(Element.ALIGN_LEFT);
								document.add(desc2);
						
						
						Paragraph desc = new Paragraph("Endereço: Avenida Esperança, 2275 centro, São joão do Sóter-MA \nCelular(99) 9.8489-4627 \nEmail:doctorcell@gmail.com");

						desc.setAlignment(Element.ALIGN_LEFT);
						document.add(desc);
						
						
						
						
						
						Paragraph os14 = new Paragraph("Nome: " + rs.getString(8));
						os14.setAlignment(Element.ALIGN_LEFT);
						document.add(os14);
						
						Paragraph os16 = new Paragraph("Celular: " + rs.getString(17));
						os16.setAlignment(Element.ALIGN_LEFT);
						document.add(os16);
						
						Paragraph os17 = new Paragraph("Email: " + rs.getString(18));
						os17.setAlignment(Element.ALIGN_LEFT);
						document.add(os17);
						
						
						Paragraph os19 = new Paragraph("\n____________________________________________");
						os19.setAlignment(Element.ALIGN_LEFT);
						document.add(os19);
						



						

						Paragraph os11 = new Paragraph("Tipo de Equipamento: " + rs.getString(3));
						os11.setAlignment(Element.ALIGN_LEFT);
						document.add(os11);
			
					
						Paragraph os12 = new Paragraph("Defeito do Aparelho: " + rs.getString(4));
						os12.setAlignment(Element.ALIGN_LEFT);
						document.add(os12);

					
						
						Paragraph os13 = new Paragraph("Valor Total: " + rs.getString(5));
						os13.setAlignment(Element.ALIGN_LEFT);
						document.add(os13);

						
						
									
						
						Image logo = Image.getInstance(Servicos.class.getResource("/img/assis.png"));
						logo.scaleToFit(200, 200);
						logo.setAlignment(Element.ALIGN_CENTER);
						document.add(logo);
						document.add(Chunk.NEWLINE);
						
						
						
						
						
						
						Paragraph data = new Paragraph("Data da emissão da OS: " + rs.getString(2));
						data.setAlignment(Element.ALIGN_CENTER);
						document.add(data);
						
						
						
											}
					// fechar a conexão com o banco
					con.close();
				} catch (Exception e) {
					System.out.println(e);

				}
			} catch (Exception e) {
				System.out.println(e);
			}
			// fechar o documento (pronto para "impressão" (exibir o pdf))
			document.close();
			// Abrir o desktop do sistema operacional e usar o leitor padrão
			// de pdf para exibir o documento
			try {
				Desktop.getDesktop().open(new File("os.pdf"));
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	


	 
}
