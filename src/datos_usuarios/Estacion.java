package datos_usuarios;

import java.sql.*;

public class Estacion {
	private int id;
	private String nombre;
	private String longitud;
	private String latitud;
	private int libres;
	private int bicis;
	
	public Estacion(int id, String nombre, String longitud, String latitud, int libres, int bicis) {
		this.id = id;
		this.nombre = nombre;
		this.longitud = longitud;
		this.latitud = latitud;
		this.libres = libres;
		this.bicis = bicis;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}
	
	public static Estacion getEstacionById(int id) {
		try {
			Connection conexion = DriverManager.getConnection(
					   "jdbc:mysql://IAP-070-2018.dsic.cloud:3306/iap",
					   "root",
					   "");
			String consulta = "SELECT * FROM valenbisi WHERE id="+id;
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				String longitud = rs.getString("longitud");
				String latitud = rs.getString("latitud");
				String libres = rs.getString("libres");
				String bicis = rs.getString("bicis");
				
				return new Estacion(id, nombre, longitud, latitud, Integer.parseInt(libres), Integer.parseInt(bicis));
			}
			rs.close();
			stmt.close();
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
		
		
		
		
	}
	
	public int getLibres() {
		return libres;
	}
	
}
