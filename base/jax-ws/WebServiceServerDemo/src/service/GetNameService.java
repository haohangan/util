package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface GetNameService {

	@WebMethod(action = "getName", operationName = "getName")
	String getName(@WebParam(name = "name") Integer name);

	@WebMethod(action = "getRandomName", operationName = "getRandomName")
	String getName();
}
