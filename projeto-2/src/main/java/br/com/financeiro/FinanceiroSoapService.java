package br.com.financeiro;

import jakarta.jws.Oneway;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.Use;

@WebService(targetNamespace="http://financeiro.com.br/nota") 
@SOAPBinding(use=Use.LITERAL,style=Style.DOCUMENT,parameterStyle=ParameterStyle.BARE)
public class FinanceiroSoapService {
	
	
	public FinanceiroSoapService() {
		System.out.println("Subindo serviço SOAP: http://localhost:8080/webservices/financeiro");
	}
	
	@WebMethod @Oneway
	public void cadastraNota(@WebParam(name="nota") Nota nota) {
		
		System.out.println("Financeiro (SOAP) recebendo: "  + nota);

	}
}
