package br.com.caelum.camel;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaJmsInOut {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addComponent("activemq", ActiveMQComponent.activeMQComponent("tcp://localhost:61616"));
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				// As mensagens desta fila possuem o cabeçalho de Reply To.
				// Assim elas são redirecionadas automaticamente para a fila expecificada no Reply To.
				// Mesmo que eu salve em um arquivo ou envie para outro lugar esta mensagem.
				from("activemq:queue:pedidos.req")
				.log("${body}")
				.setHeader(Exchange.FILE_NAME, constant("mensagem.txt"))
				.to("file:saida");
			}
		});
		context.start();
		
		Thread.sleep(5000);
		
		context.stop();
	}	
}
