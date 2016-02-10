package com.club_friend.clients;

import java.sql.SQLException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

	//Service Endpoint Interface
	@WebService

	@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
	
	public interface Game{

	@WebMethod String table_in_game(String table)throws SQLException;
	@WebMethod String table_out_game(String table)throws SQLException;
	@WebMethod String status_game(String cl,String table, String new_status)throws SQLException;
	
}
