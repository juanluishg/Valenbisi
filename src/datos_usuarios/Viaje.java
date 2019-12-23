package datos_usuarios;


public class Viaje {
	private String tipo;
	private Estacion origen;
	private Estacion destino;
	private String mail;
	private Estacion estacionCercana;
	
	public Viaje(String tipo, Estacion origen, Estacion destino, String mail) {
		tipo = this.tipo;
		origen = this.origen;
		destino = this.destino;
		mail = this.mail;
		estacionCercana = estacionCercana(destino);
	}
	
	private Estacion estacionCercana(Estacion estacion) {
		Estacion estacion1 = Estacion.getEstacionById(estacion.getId()+1);
		if(estacion1.getLibres()>0) return estacion1;
		else {
			Estacion estacion2 = Estacion.getEstacionById(estacion.getId()-1);
			if(estacion2.getLibres()>0) return estacion2;
		}
		return estacion;
	}
	
	public Estacion getEstacionCercana() { return estacionCercana;}

	@Override
	public String toString() {
		return tipo + ";" + origen.getNombre() + ";" + destino.getNombre() + ";" + mail;
	}
	
	
}
