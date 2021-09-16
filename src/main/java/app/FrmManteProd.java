package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Categorias;
import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	JComboBox cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTextField txtEstado;
	private JTable tblSalida;
	DefaultTableModel modelo = new DefaultTableModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(326, 135, 89, 23);
		contentPane.add(btnRegistrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		tblSalida = new JTable();
		tblSalida.setModel(modelo);
		modelo.addColumn("Codigo");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Stock");
		modelo.addColumn("Precio");
		modelo.addColumn("Categoria");
		modelo.addColumn("Estado");
		scrollPane.setViewportView(tblSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id. Producto :");
		lblNewLabel.setBounds(10, 14, 102, 14);
		contentPane.add(lblNewLabel);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 138, 86, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa :");
		lblCategora.setBounds(10, 142, 102, 14);
		contentPane.add(lblCategora);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(122, 42, 86, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre :");
		lblNewLabel_1.setBounds(10, 45, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		txtStock = new JTextField();
		txtStock.setBounds(122, 73, 86, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(122, 104, 86, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Stock :");
		lblNewLabel_2.setBounds(10, 76, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Precio :");
		lblNewLabel_3.setBounds(10, 107, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Estado :");
		lblNewLabel_4.setBounds(252, 14, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(326, 11, 86, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrar();
			}
		});
		btnLimpiar.setBounds(326, 103, 89, 23);
		contentPane.add(btnLimpiar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		btnEliminar.setBounds(326, 72, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(326, 41, 89, 23);
		contentPane.add(btnBuscar);
		llenaCombo();
	}
	void borrar(){
		txtCodigo.setText("");
		cboCategorias.setSelectedIndex(0);
		txtDescripcion.setText("");
		txtEstado.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
	}
	
	/*public ArrayList<Categorias> mostrarCategorias(){
		ArrayList<Categorias>lista=null;
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		String sql="select c from Categorias c";
		
		List<Categorias> lstCat = em.createQuery(sql, Categorias.class).getResultList();
		lista = new ArrayList<Categorias>();
		for(Categorias c : lstCat){
			lista.add(c);
		}
		return lista;
	}
	void llenaCombo(){
		ArrayList<Categorias> lstEspec = mostrarCategorias();
		if(lstEspec==null){
			JOptionPane.showMessageDialog(this, "Combo no tiene datos");
		}else {
			cboCategorias.addItem("Seleccione");
			for(Categorias e: lstEspec){
				cboCategorias.addItem(e.getIdcategoria()+"-"+ e.getDescripcion());
			}
		}
	}*/
	
	void llenaCombo(){
		ArrayList<Categorias>lista=null;
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		String sql="select c from Categorias c";
		
		List<Categorias> lstCat = em.createQuery(sql, Categorias.class).getResultList();
		lista = new ArrayList<Categorias>();
		for(Categorias c : lstCat){
			lista.add(c);
		}
		ArrayList<Categorias> lstEspec = lista;
		if(lstEspec==null){
			JOptionPane.showMessageDialog(this, "Combo no tiene datos");
		}else {
			cboCategorias.addItem("Seleccione");
			for(Categorias e: lstEspec){
				cboCategorias.addItem(e.getIdcategoria()+"-"+ e.getDescripcion());
			}
		}
	}
	
	/*public ArrayList<Producto> mostrarProducto(){
		ArrayList<Producto>lista=null;
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		String sql="select c from Producto c";
		
		List<Producto> lstProd = em.createQuery(sql, Producto.class).getResultList();
		lista = new ArrayList<Producto>();
		for(Producto p : lstProd){
			lista.add(p);
		}
		return lista;
	}
	void listado() {
		ArrayList<Producto> lstProducto = mostrarProducto();
		if(lstProducto==null||lstProducto.size()==0){
			JOptionPane.showMessageDialog(this, "Lista Vacia");
		}else {
			modelo.setRowCount(0);
			for (Producto p: lstProducto ) {
				Object aDatos[] = {p.getIdprod(),p.getDescripcion(),p.getStock(),p.getPrecio(),p.getIdcategoria(),p.getEstado()};
			modelo.addRow(aDatos);
			}		
		}
	}*/
	
	void listado(){
		ArrayList<Producto>lista=null;
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		String sql="select c from Producto c";
		
		List<Producto> lstProd = em.createQuery(sql, Producto.class).getResultList();
		lista = new ArrayList<Producto>();
		for(Producto p : lstProd){
			lista.add(p);
		}
		ArrayList<Producto> lstProducto = lista;
		if(lstProducto==null||lstProducto.size()==0){
			JOptionPane.showMessageDialog(this, "Lista Vacia");
		}else {
			modelo.setRowCount(0);
			for (Producto p: lstProducto ) {
				Object aDatos[] = {p.getIdprod(),p.getDescripcion(),p.getStock(),p.getPrecio(),p.getIdcategoria(),p.getEstado()};
			modelo.addRow(aDatos);
			}		
		}
	}
	
	void registrar(){
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		EntityManager em = fabrica.createEntityManager();

		Producto p = new Producto();
		p.setIdprod(leerCodigo());
		p.setDescripcion(leerDesc());
		p.setStock(leerStock());
		p.setPrecio(leerPrecio());
		p.setIdcategoria(leerCat());
		p.setEstado(leerEstado());
		
		em.getTransaction().begin();
		em.merge(p); 
		em.getTransaction().commit();
		System.out.println("Registro OK");
		em.close();
		
		JOptionPane.showMessageDialog(this, "Producto " + p.getIdprod() + " registrado");
	}
	
	void eliminar(){
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		Producto u = em.find(Producto.class, leerCodigo());//devuelve el objeto usuario segun la PK
		
		if(u==null){
			System.out.println("codigo NO existe");
		}
		else{
			em.getTransaction().begin();
			em.remove(u); //para eliminar
			em.getTransaction().commit();
			System.out.println("Eliminacion OK");
		}
	}
	
	void buscar(){
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		Producto p = em.find(Producto.class, leerCodigo());
		txtDescripcion.setText(p.getDescripcion());
		txtEstado.setText(p.getEstado()+"");
		txtPrecio.setText(p.getPrecio()+"");
		txtStock.setText(p.getStock()+"");
		cboCategorias.setSelectedIndex(p.getIdcategoria());
	}
	
	private int leerCat() {
		if(cboCategorias.getSelectedIndex()==0){
			JOptionPane.showMessageDialog(this, "Categoria no puede ser vacio");
			return 0;
		}
		return cboCategorias.getSelectedIndex();
	}
	private String leerCodigo() {
		if(txtCodigo.getText().length()==0){
			JOptionPane.showMessageDialog(this, "Codigo no puede ser vacio");
			return null;
		}
		return txtCodigo.getText();
	}
	private String leerDesc() {
		if(txtDescripcion.getText().length()==0){
			JOptionPane.showMessageDialog(this, "Descripcion no puede ser vacio");
			return null;
		}
		return txtDescripcion.getText();
	}
	private int leerStock() {
		if(txtStock.getText().length()==0){
			JOptionPane.showMessageDialog(this, "Stock no puede ser vacio");
			return 0;
		}
		return Integer.parseInt(txtStock.getText());
	}
	private double leerPrecio() {
		if(txtPrecio.getText().length()==0){
			JOptionPane.showMessageDialog(this, "Precio no puede ser vacio");
			return 0;
		}
		return Double.parseDouble(txtStock.getText());
	}
	private int leerEstado() {
		if(txtEstado.getText().length()==0){
			JOptionPane.showMessageDialog(this, "Estado no puede ser vacio");
			return 0;
		}
		return Integer.parseInt(txtEstado.getText());
	}
}

