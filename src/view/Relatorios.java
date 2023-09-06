package view;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;

public class Relatorios extends JDialog {
	// objeto jdbc
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	 
	public static void main(String[] args) {
		try {
			Relatorios dialog = new Relatorios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	 
	public Relatorios() {
		setTitle("Relatorio");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JButton btnClientes = new JButton("");
		btnClientes.setToolTipText("Cliente");
		btnClientes.setIcon(new ImageIcon(
				Relatorios.class.getResource("/img/49338_business man_client_consultant_male_man_icon.png")));
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnClientes.setBounds(46, 54, 89, 65);
		getContentPane().add(btnClientes);

		JButton btnServicos = new JButton("");
		btnServicos.setToolTipText("Relatorio");
		btnServicos.setIcon(new ImageIcon(
				Relatorios.class.getResource("/img/7106324_graph_document_data_infographic_element_icon.png")));
		btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatoriosServicos();
			}
		});
		btnServicos.setBounds(306, 54, 89, 65);
		getContentPane().add(btnServicos);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		panel.setBounds(0, 231, 434, 30);
		getContentPane().add(panel);

		JButton btnEstoque = new JButton("");
		btnEstoque.setIcon(new ImageIcon(
				Relatorios.class.getResource("/img/6843036_courier_deliver_delivery_package_product_icon.png")));
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				relatorioEstoque();

			}
		});
		btnEstoque.setBounds(180, 54, 89, 65);
		getContentPane().add(btnEstoque);
	}// fim do construtor

	private void relatorioClientes() {
		// instanciar um objeto para construir a página pdf
		Document document = new Document();
		// configurar como A4 e modo paisagem
		// document.setPageSize(PageSize.A4.rotate());
		// gerar o documento pdf
		try {
			// criar um documento em branco (pdf) de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			// abrir o documento (formatar e inserir o conteúdo)
			document.open();
			// adicionar a data atual
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			// adicionar um páragrafo
			document.add(new Paragraph("Clientes:"));
			document.add(new Paragraph(" ")); 
		
			String readClientes = "select nome,fone,email from clientes order by nome";
			try {
			
				con = dao.conectar();
				
				pst = con.prepareStatement(readClientes);
			
				rs = pst.executeQuery();
			
				PdfPTable tabela = new PdfPTable(3); // (2) número de colunas
				
				PdfPCell col1 = new PdfPCell(new Paragraph("Cliente"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				while (rs.next()) {
					// popular a tabela
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));

				}
				// adicionar a tabela ao documento pdf
				document.add(tabela);
				// fechar a conexão com o banco
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	
		document.close();
		
		
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void relatoriosServicos() {
		// instanciar um objeto para construir a página pdf
		Document document = new Document();
		// configurar como A4 e modo paisagem
		document.setPageSize(PageSize.A4.rotate());
		// gerar o documento pdf
		try {
			// criar um documento em branco (pdf) de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("Servicos.pdf"));
			// abrir o documento (formatar e inserir o conteúdo)
			document.open();
			// adicionar a data atual
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			// adicionar um páragrafo
			document.add(new Paragraph("Ordem de Serviço:"));
			document.add(new Paragraph(" ")); // pular uma linha
			// ----------------------------------------------------------
			// query (instrução sql para gerar o relatório de clientes)
			String readServicos = "select os,nome,dataOS,equipamento,defeito,valor from servicos inner join clientes on servicos.id = clientes.id order by os ";
			try {
				// abrir a conexão com o banco
				con = dao.conectar();
				// preparar a query (executar a instrução sql)
				pst = con.prepareStatement(readServicos);
				// obter o resultado (trazer do banco de dados)
				rs = pst.executeQuery();
				// atenção uso do while para trazer todos os clientes
				// Criar uma tabela de duas colunas usando o framework(itextPDF)
				PdfPTable tabela = new PdfPTable(6); // (2) número de colunas
				// Criar o cabeçalho da tabela
				PdfPCell col1 = new PdfPCell(new Paragraph("OS"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Nome"));
				PdfPCell col3 = new PdfPCell(new Paragraph("DataOS"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Tipo de Equipamento"));
				PdfPCell col5 = new PdfPCell(new Paragraph("Defeito Relatado"));
				PdfPCell col6 = new PdfPCell(new Paragraph("Valor Total"));

				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				tabela.addCell(col5);
				tabela.addCell(col6);

				while (rs.next()) {
					// popular a tabela
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
					tabela.addCell(rs.getString(6));

				}
				// adicionar a tabela ao documento pdf
				document.add(tabela);
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
			Desktop.getDesktop().open(new File("Servicos.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	private void relatorioEstoque() {

		// instanciar um objeto para construir a página pdf

		Document document = new Document();

		// configurar como A4 e modo paisagem

		// document.setPageSize(PageSize.A4.rotate());

		// gerar o documento pdf

		try {

			// criar um documento em branco (pdf) de nome clientes.pdf

			PdfWriter.getInstance(document, new FileOutputStream("estoque.pdf"));

			// abrir o documento (formatar e inserir o conteúdo)

			document.open();

			// adicionar a data atual

			Date dataRelatorio = new Date();

			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

			document.add(new Paragraph(formatador.format(dataRelatorio)));

			// adicionar um páragrafo

			document.add(new Paragraph("Estoque:"));

			document.add(new Paragraph(" ")); // pular uma linha

			// ----------------------------------------------------------

			// query (instrução sql para gerar o relatório de clientes)

			String readClientes = "select codigo as código, nome as produto, date_format(dataval, '%d/%m/%Y') as validade, estoque, estoquemin as estóque_mínimo \r\n"

					+ "from produtos where estoque < estoquemin";

			try {

				// abrir a conexão com o banco

				con = dao.conectar();

				// preparar a query (executar a instrução sql)

				pst = con.prepareStatement(readClientes);

				// obter o resultado (trazer do banco de dados)

				rs = pst.executeQuery();

				// atenção uso do while para trazer todos os clientes

				// Criar uma tabela de duas colunas usando o framework(itextPDF)

				PdfPTable tabela = new PdfPTable(5); // (2) número de colunas

				// Criar o cabeçalho da tabela

				PdfPCell col1 = new PdfPCell(new Paragraph("código: "));
				PdfPCell col2 = new PdfPCell(new Paragraph("produto: "));
				PdfPCell col3 = new PdfPCell(new Paragraph("validade: "));
				PdfPCell col4 = new PdfPCell(new Paragraph("estoque: "));
				PdfPCell col5 = new PdfPCell(new Paragraph("estoque mínimo: "));

				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				tabela.addCell(col5);
				while (rs.next()) {

					// popular a tabela

					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));

				}

				// adicionar a tabela ao documento pdf

				document.add(tabela);

				// adicionar um páragrafo

				document.add(new Paragraph("Validade:"));
				document.add(new Paragraph(" "));
				String read = "select codigo as código, nome as produto, date_format(dataval, '%d/%m/%Y') as validade\r\n"

						+ "from produtos where dataval < dataent";

				pst = con.prepareStatement(read);
				rs = pst.executeQuery();
				PdfPTable tabela2 = new PdfPTable(3);
				PdfPCell col6 = new PdfPCell(new Paragraph("código: "));
				PdfPCell col7 = new PdfPCell(new Paragraph("produto: "));
				PdfPCell col8 = new PdfPCell(new Paragraph("validade: "));

				tabela2.addCell(col6);
				tabela2.addCell(col7);
				tabela2.addCell(col8);

				while (rs.next()) {

					tabela2.addCell(rs.getString(1));
					tabela2.addCell(rs.getString(2));
					tabela2.addCell(rs.getString(3));

				}

				document.add(tabela2);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {

			Desktop.getDesktop().open(new File("estoque.pdf"));
		} catch (Exception e) {
			System.out.println(e);

		}

	}

}
