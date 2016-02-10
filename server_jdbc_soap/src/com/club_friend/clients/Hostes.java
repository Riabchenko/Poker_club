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
public interface Hostes{
	
	@WebMethod String getHelloWorldAsString(String name);
	@WebMethod String new_client (String new_client_last_name,String new_client_first_name,String new_client_by_father,String him_friend,String n_date,String new_client_telephone,String new_client_email)throws SQLException;
	@WebMethod  String new_client_photo (String s64,String telephone);
	@WebMethod String[][] review_ (String cl,String last_name, String first_name,String telephone,String email); 
//====================================================================	
//методы для редактирования анкеты
	@WebMethod String edit_photo (String cl,String s64);
	@WebMethod String edit_birthday (String cl,String s_date);
	@WebMethod String edit_telephone (String new_telephone, String telephone);
	@WebMethod String edit_email (String email,String new_email);
	@WebMethod String edit_friend (String cl,String new_id_friend);
	@WebMethod String edit_ (String cl , String new_last_name,String new_first_name,String new_by_father);
	@WebMethod String new_telephone (String cl,String plus_telephone);
	@WebMethod String new_email (String cl ,String plus_email);
	@WebMethod String delete_telephone(String delete_telephone);
	@WebMethod String delete_email(String delete_email);
}