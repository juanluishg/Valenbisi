package datos_abiertos;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import get_datos.HTTP;

public class Conector {
	private final static String NOMBRE_COLA = "raw";
	private final static String TOPIC_CSV = "csv";
	private final static String TOPIC_JSON = "geoJson";
	public static void main(String [] args) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(NOMBRE_COLA, false, false, false, null);
			channel.exchangeDeclare(NOMBRE_COLA, BuiltinExchangeType.TOPIC);			
			HTTP datos_csv = new HTTP("http://mapas.valencia.es/lanzadera/opendata/Valenbisi/CSV");
			HTTP datos_json = new HTTP("http://mapas.valencia.es/lanzadera/opendata/Valenbisi/JSON");
			
			String csv = datos_csv.connect();
			String json = datos_json.connect();
			
			channel.basicPublish(NOMBRE_COLA, TOPIC_CSV, null, csv.getBytes());
			channel.basicPublish(NOMBRE_COLA, TOPIC_JSON, null, json.getBytes());
			
			channel.close();
			connection.close();
				
		}catch(Exception e) { e.getStackTrace();}
	}
}
