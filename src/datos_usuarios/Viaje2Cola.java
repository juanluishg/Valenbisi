package datos_usuarios;

import org.json.JSONObject;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import get_datos.HTTP;

public class Viaje2Cola {
	private final static String NOMBRE_COLA = "usuarios";
	private final static String TOPIC_VIAJE = "viaje";
	
	
	
	public static void main(String [] args) {
		Viaje v = new Viaje("inicio", Estacion.getEstacionById(106), Estacion.getEstacionById(110), "prueba@prueba.com");
		publicar(v);
		
	}

	private static void publicar(Viaje v) {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			try {
				Connection connection = factory.newConnection();
				Channel channel = connection.createChannel();
				channel.queueDeclare(NOMBRE_COLA, false, false, false, null);
				channel.exchangeDeclare(NOMBRE_COLA, BuiltinExchangeType.TOPIC);			
				
				String viaje = v.toString();
				
				channel.basicPublish(NOMBRE_COLA, TOPIC_VIAJE, null, viaje.getBytes());
				
				channel.close();
				connection.close();
					
			}catch(Exception e) { e.getStackTrace();}
	}

	

	
}
