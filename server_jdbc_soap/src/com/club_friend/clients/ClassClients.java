/* MacBook-Pro-Apple:bin apple$ wsgen -keep -cp . com.club_friend.clients.ClassClients
 * 
 * MacBook-Pro-Apple:src apple$ javac Client_.java
   MacBook-Pro-Apple:src apple$ java -cp ./mysql-connector-java-5.1.34-bin.jar:. Client_ 
   
   MacBook-Pro-Apple:jdbc_first apple$ javac -cp bin src/com/club_friend/clients/ClassNew.java
   MacBook-Pro-Apple:bin apple$ java -cp ./mysql-connector-java-5.1.34-bin.jar:. com/club_friend/clients/ClassNew
   */
package com.club_friend.clients;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.Base64;
import java.util.Properties;
import javax.jws.WebService;
import javax.sql.rowset.serial.SerialBlob;

//Service Implementation
@WebService(endpointInterface = "com.club_friend.clients.Hostes")

//portName = "HelloWSPort", serviceName = "HelloWSService", 
//targetNamespace = "http://hello_webservice/", 
//endpointInterface = "hello_webservice.HelloWS")





public class ClassClients {                            //реализация интерфейса Client

	public static int id;
	private static int friend;
	private static String last_name,first_name,by_father,friend_last_name,friend_first_name,friend_by_father;
	private static Date birthday;
	static PreparedStatement ps;
	static String SQL; 
	static ResultSet rs; 
  static int him_friend_int, id_new_client, is_id_client;
	private static int rows;
	private static int is;
	private static int is_;
	private static Savepoint savepoint_n_c,savepoint_n_t,savepoint_n_e,savepoint_e_,savepoint_e_t,savepoint_e_e,savepoint_e_b,savepoint_e_f,savepoint_d_t,savepoint_d_e;
	private static float balance;
	private static String by;
	private static String telephone;
	private static String email;
	public static String status;
	public static String photo;
	private static String photo_for_android;
	private static String photo_string;
	private static byte [] bitmapdata;
	private static String ok;
	private static String sp64;
	private static String reg_error;
	private static String reg_messege;
	private static String reg_res;
	private static String reg_photo;
	private static String message;
	private static String[][] Clients;
	private static String photo_encode;
	private static String[][] anketa;
	public static String[][] telephone_arr;
	public static String[][] email_arr;
	public static String[][] fr_arr;
	public static String[][] st_arr;
	public static String[][] bal_arr;
	public static String[][] id_ma;
	public static String[][] id_many;
	private static Savepoint savepoint_e_ph;
	private static String edit_photo;
	private static String edit_dr;
	private static String edit_t;
	private static String edit_e;
	private static String edit_fr;
	private static String edit_fio;
	private static String add_t;
	private static String add_e;
	private static String del_t;
	private static String del_e;
	private static Savepoint savepoint_photo;


public static void main(String[] args) throws Exception
	{ 
/*	//добавить нового клиента
	String new_client_last_name=" Кpвa";
	String new_client_first_name="Алаа";
	String new_client_by_father="Александрович";
	String him_friend="2";
	String n_date = "1978-12-27";		  	
	String new_client_telephone = "+38877";	
	String new_client_email = "ykde@gmail.com";

	new_client (new_client_last_name,new_client_first_name,new_client_by_father,him_friend,n_date,new_client_telephone,new_client_email);
*/
	
//String telephone="123";
//id=0;
//last_name="";
//first_name="";
// email="";
// String cl="151";
//review_ (cl,last_name, first_name, telephone, email);
////new_client_photo (s64,telephone);
//	
}
//___________

public String getHelloWorldAsString(String name) {
	System.out.println("получена переменная "+name);
	return "Hello World JAX-WS " + name;
}



//*********************************************
//добавить нового клиента ( таблица Clients,Telephone,email)
public static String new_client (String new_client_last_name,String new_client_first_name,String new_client_by_father,String him_friend,String n_date,String new_client_telephone,String new_client_email) throws SQLException {
	System.out.println(" получены данные: "+new_client_last_name+" "+new_client_first_name+" "+new_client_by_father+" "+him_friend+" "+n_date+" "+new_client_telephone+" "+new_client_email);
		
	
	Connection conn = null;
 				  	
   try{					                                           //блок кода, который отслеживает ошибки
 			conn = getConnection();	  	
 			System.out.println("Got Connection.");
 			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
 			savepoint_n_c=conn.setSavepoint("savepoint_n_c");
//********************Создание и выполнение запроса****************************
 					try {
 					
 						
 						System.out.println(" получены данные: "+new_client_last_name+" "+new_client_first_name+" "+new_client_by_father+" "+him_friend+" "+n_date+" "+new_client_telephone+" "+new_client_email);
 					
 						//есть друг
 						if (him_friend.compareToIgnoreCase("") != 0){
 							System.out.println("1");
 							telephone = him_friend;						
 							id_by_telephone (telephone);//ищем существует ли друг,и находим его id
 							him_friend_int=id;
 						
 						if(id!=0){
 							System.out.println("2");
 							id=0;
 								
 							new_client_fio(conn,new_client_last_name,new_client_first_name,new_client_by_father,n_date); //создание новой записи о ФИО,ДР,дате рождения 							
 							new_client_friend(conn,id_new_client, him_friend_int);//добавляем друга для нового клиента  							
 							new_client_telephone(conn,new_client_telephone,id_new_client); //добавить номер телефона для нового клиента,если телефон существует, то новый клиент будет удален 						
 						
 						 if (is==1){
 							System.out.println("3");
 									if(new_client_email.compareToIgnoreCase("") != 0){ 	
 										System.out.println("4");
 											new_client_email(conn,is,id_new_client,new_client_telephone, new_client_email); //добавить новый email, если существует,то запись не добавится и новый клиент сотрется
 											if (is_==1){
 												System.out.println("5");
 												new_client_status(conn,is,id_new_client);	 //создание записи о статусе
 												reg_res="002";
 										 	}else{ System.out.println("6");reg_res="012";}
 									}else{System.out.println("7");
 										  new_client_status(conn,is,id_new_client);	 //создание записи о статусе
											reg_res="002";}
 						 }else{System.out.println("8");reg_res="011";}
 						}else{System.out.println("9");reg_res="001";}
 						 					
 						}else{	
 							System.out.println("10");
 							new_client_fio(conn,new_client_last_name,new_client_first_name,new_client_by_father,n_date); //создание новой записи о ФИО,ДР,дате рождения
							new_client_telephone(conn,new_client_telephone,id_new_client); //добавить номер телефона для нового клиента,если телефон существует, то новый клиент будет удален 						
	 						
						 if (is==1){
							 System.out.println("11");
									if(new_client_email.compareToIgnoreCase("") != 0){ 
										System.out.println("12");
											new_client_email(conn,is,id_new_client,new_client_telephone, new_client_email); //добавить новый email, если существует,то запись не добавится и новый клиент сотрется
											if (is_==1){
												System.out.println("13");
												new_client_status(conn,is,id_new_client);	 //создание записи о статусе
												reg_res="002";
										 	}else{System.out.println("14");reg_res="012";}
									}else{System.out.println("15");
										new_client_status(conn,is,id_new_client);	 //создание записи о статусе
										reg_res="002";}
						 }else{System.out.println("16");reg_res="011";}
				
 						}		
 						
					
 						
// 						if (is_ == 1){ 															 //is_ указывает что все методы были пройдены успешно,и в status было последнее действие
// 							System.out.println("Добавлен новый клиент "+new_client_last_name+" "+new_client_first_name+" "+new_client_by_father);  		  					  				  		 							
// 						}	  			
//*****************************************************************************			 
 					}catch(Exception e){
 						e.getStackTrace();
 						reg_res="003";
 						System.out.println("Ошибка в запросах!!!Новый клиент не добавлен!");}
   conn.commit();	
   }
   catch(SQLException se){
   	conn.rollback(savepoint_n_c);
   	reg_res="004";
   	System.out.println("Произошла ошибка!Повторите действия.");
   }
 		catch(ClassNotFoundException cnfe){
 			reg_res="005";
 			System.out.println("Where Driver?Новый клиент не добавлен!");
 		}
   	catch (Exception e) {
   		reg_res="006";
   		System.out.println("Ошибка при соединении!!!Новый клиент не добавлен!");
   		e.printStackTrace();
   	}
   finally { 
   	if (conn != null) conn.close();  
   
   }	return reg_res;		      
}
//добавить фото для клиента ( таблица Clients)
public static String new_client_photo (String s64,String telephone) throws SQLException {
	Connection conn = null;
				  	
 try{					                                           //блок кода, который отслеживает ошибки
			conn = getConnection();	  	
	//		System.out.println("Got Connection.");
			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
			savepoint_n_c=conn.setSavepoint("savepoint_n_c");
//********************Создание и выполнение запроса****************************
					try {
						id_by_telephone (telephone);
						System.out.println("id "+id);
						if(id!=0){
							new_photo(s64,conn,id);  //загружаем фото 
							System.out.println(reg_photo);
						}else{
							reg_photo="007";
						}
									
//*****************************************************************************			 
					}catch(Exception e){
						reg_photo="003";
						System.out.println("Ошибка в запросах!!!Новый клиент не добавлен!");}
 conn.commit();	
 }
 catch(SQLException se){
 	conn.rollback(savepoint_n_c);
 	reg_photo="004";
 	System.out.println("Произошла ошибка!Повторите действия.");
 }
		catch(ClassNotFoundException cnfe){
			reg_photo="005";
			System.out.println("Where Driver?Новый клиент не добавлен!");
		}
 	catch (Exception e) {
 		reg_photo="006";
 		System.out.println("Ошибка при соединении!!!Новый клиент не добавлен!");
 		e.printStackTrace();
 	}
 finally { 
 	if (conn != null) conn.close();          
 }	
 return reg_photo;
}

//*********************************************
 //методы для добавления нового пользователя
 //создание новой записи о ФИО,ДР,дате рождения
 private static int new_client_fio(Connection conn,String new_client_last_name,String new_client_first_name,String new_client_by_father,String n_date) throws SQLException {
		
	 System.out.println(" зашли в метод ");
	 java.sql.Date new_client_birthday = java.sql.Date.valueOf(n_date); 	 //меняем тип переменной с String на Date
	 System.out.println("birthday "+new_client_birthday);
	  SQL = "INSERT INTO Clients (`Last Name`, `First Name`, `By Father`,`Birthday`) VALUE (?,?,?,?)" ;
		ps = conn.prepareStatement(SQL);  //создание запроса ps
		ps.setString(1,new_client_last_name);
		ps.setString(2,new_client_first_name);
		ps.setString(3,new_client_by_father);
		ps.setDate(4, new_client_birthday);
		System.out.println(" создали запрос "+new_client_last_name+" "+new_client_first_name+" "+new_client_by_father+" "+new_client_birthday);
		
		ps.execute();			     //выполнение запроса и присвоение rs результата
		SQL=null;
		ps.close();		
		
	//	*********************
		SQL = "SELECT `id_client` FROM Clients WHERE (`Last Name`=? AND `First Name`=? AND `By Father`=? AND `Birthday`=?)" ;
		ps = conn.prepareStatement(SQL);  //создание запроса ps
		ps.setString(1,new_client_last_name);
		ps.setString(2,new_client_first_name);
		ps.setString(3,new_client_by_father);
		ps.setDate(4, new_client_birthday);

		rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата

		rs.last();
		id_new_client = rs.getInt(1);

// 		  			  		   			 

		SQL=null;
		ps.close();
		return id_new_client;
		
 }

//добавляем друга для нового клиента 
 private static void new_client_friend(Connection conn,int id_new_client, int him_friend_int) throws SQLException { 
	// him_friend_int=Integer.parseInt(him_friend);  //меняем тип переменной с String на int
	 System.out.println("Друг с id "+him_friend_int+" добавляется...");
		if (him_friend_int != 0){
			try {
					SQL = "UPDATE Clients SET `Clients_id_client`=?  WHERE `id_client`=? " ;
					ps = conn.prepareStatement(SQL);  //создание запроса ps
					ps.setInt(2,id_new_client);
					ps.setInt(1,him_friend_int);

					ps.execute();			     //выполнение запроса и присвоение rs результата

					SQL=null;
					ps.close();
					System.out.println("Друг  добавлен");
			} 
			catch (Exception e) {
				System.out.println("Друга с таким id не существует");
			}
	}
 }

 //добавить номер телефона для нового клиента,если телефон существует, то новый клиент будет удален
 private static int new_client_telephone(Connection conn,String new_client_telephone,int id_new_client) throws SQLException {	 
		is = 0;	
	 try{	
			System.out.println("Пытаемся добавить телефон");

			SQL = "SELECT `Clients_id_client` FROM Telephone WHERE `telephone` = ?" ;
			ps = conn.prepareStatement(SQL);  //создание запроса ps
			ps.setString(1,new_client_telephone);
			rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата

			rs.last();
		  rows=rs.getRow();
			rs.beforeFirst();
			System.out.println("Существующих клиентов "+rows);
			while(rs.next()){
				is_id_client=rs.getInt(1);
				System.out.println("Существующие клиенты "+is_id_client);
			}
			SQL=null;
			ps.close();
System.out.println("Новый клиент "+id_new_client);
	if (rows == 0){
		is = 1;
		String SQL1 = "INSERT INTO Telephone(`telephone`,`Clients_id_client`) VALUE (?,?)" ;
		PreparedStatement ps1 = conn.prepareStatement(SQL1);  //создание запроса ps
		ps1.setString(1,new_client_telephone);
		ps1.setInt(2,id_new_client);
		ps1.execute();			     //выполнение запроса
		System.out.println("Добавлен телефон "+new_client_telephone);
		SQL1=null;
		ps1.close();
		
	}
	else{
		is= 0;
		SQL = "DELETE FROM `Clients` WHERE `id_client`=?" ;
		ps = conn.prepareStatement(SQL);  //создание запроса ps
		ps.setInt(1,id_new_client);
		ps.execute();			     //выполнение запроса и присвоение rs результата

		SQL=null;
		ps.close();
		System.out.println("Клиент с данным номером телефона уже существует!");

		SQL = "SELECT `Last Name`, `First Name`, `By Father`, `Birthday`, `Date registration` FROM Clients WHERE `id_client` = ? ";
		ps = conn.prepareStatement(SQL);  //создание запроса ps
		ps.setInt(1,is_id_client);
		rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата
//______________________________________________________________________________
		//Вывод на экран информации				
		while (rs.next()) {
				String l_name = rs.getString(1);
				String f_name = rs.getString(2);
				String b_father = rs.getString(3);
				Date b_day = rs.getDate(4);
				Timestamp d_registration = rs.getTimestamp(5);

				System.out.println("ФИО: "+ l_name+" "+f_name+" "+b_father);	  	 
				System.out.println("Дата рождения: "+b_day);
				System.out.println("Дата регистрации: "+d_registration);	
				System.out.println();
		}		   	   
// ____________________________________________________________________________
		SQL=null;
		rs.close();
		ps.close();		  			
 }	
	}catch(Exception e){System.out.println("Такой телефон существует!!!!!!");}
	return is;
}

 //добавить новый email, если существует,то запись не добавится и новый клиент сотрется
 private static int new_client_email(Connection conn,int is, int id_new_client,String new_client_telephone,String new_client_email) throws SQLException {	 
if (is == 1){
	try {
		is_=1;
		rows=0;
		SQL = "SELECT `Clients_id_client` FROM email WHERE `email` = ?" ;
		ps = conn.prepareStatement(SQL);  //создание запроса ps
		ps.setString(1,new_client_email);
		rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата

		rs.last();
		rows=rs.getRow();
		rs.beforeFirst();
		while(rs.next()){
			is_id_client=rs.getInt(1);
		}
		SQL=null;
		ps.close();
		rs.close();
		
	
	  if (rows == 0){
			SQL = "INSERT INTO email(`email`,`Clients_id_client`) VALUE (?,?)" ;
			ps = conn.prepareStatement(SQL);  //создание запроса ps
			ps.setString(1,new_client_email);
			ps.setInt(2,id_new_client);
			ps.execute();			     //выполнение запроса и присвоение rs результата

			SQL=null;
			ps.close();
		}	
		else{
			is_ = 0;
			SQL = "DELETE FROM `Telephone` WHERE `telephone`=?" ;
			ps = conn.prepareStatement(SQL);  //создание запроса ps
			ps.setString(1,new_client_telephone);
			ps.execute();			     //выполнение запроса и присвоение rs результата
	
			SQL=null;
			ps.close();
		//********************	
			SQL = "DELETE FROM `Clients` WHERE `id_client`=?" ;
			ps = conn.prepareStatement(SQL);  //создание запроса ps
			ps.setInt(1,id_new_client);
			ps.execute();			     //выполнение запроса и присвоение rs результата
	
			SQL=null;
			ps.close();
			System.out.println("Клиент с данным email уже существует!");
    //******************
			SQL = "SELECT `Last Name`, `First Name`, `By Father`, `Birthday`, `Date registration` FROM Clients WHERE `id_client` = ? ";
			ps = conn.prepareStatement(SQL);  //создание запроса ps
			ps.setInt(1,is_id_client);
			rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата
//______________________________________________________________________________
			//Вывод на экран информации				
			while (rs.next()) {
				String l_name = rs.getString(1);
				String f_name = rs.getString(2);
				String b_father = rs.getString(3);
				Date b_day = rs.getDate(4);
				Timestamp d_registration = rs.getTimestamp(5);

				System.out.println("ФИО: "+ l_name+" "+f_name+" "+b_father);	  	 
				System.out.println("Дата рождения: "+b_day);
				System.out.println("Дата регистрации: "+d_registration);	
				System.out.println();
		
	}		
//____________________________________________________________________________
			SQL=null;
			rs.close();
			ps.close();
		}
     
	}catch(Exception e){System.out.println("Такой телефон существует!!!!!!");}
  }	
return is_;
	}
 
 //создание записи о статусе
 private static void new_client_status(Connection conn,int is,int id_new_client) throws SQLException {
	 if (is == 1){
	 String status = "free";
	SQL = "INSERT INTO Status(`Clients_id_client`,`Status`) VALUE (?,?)" ;
	ps = conn.prepareStatement(SQL);  //создание запроса ps
	ps.setInt(1,id_new_client);
	ps.setString(2,status);
	ps.execute();			     //выполнение запроса и присвоение rs результата

	SQL=null;
	ps.close();
	//System.out.println("Новому клиенту присвоен статус "+status);  		  					  				  		 		
  }
 }
 
 
//========================================================================
//загружаем фото
 private static String new_photo(String s64,Connection conn,int id) throws SQLException{

		 System.out.println("Собираемся сохранить фото "+s64);
		 System.out.println("id "+id);
			try {

					byte[] bitmapdata_ = com.sun.org.apache.xml.internal.security.utils.Base64.decode(s64);
			    System.out.println("раскодировали "+bitmapdata_.toString());
			    
  		    Blob blobValue = new SerialBlob(bitmapdata_);
  		    savepoint_photo=conn.setSavepoint("savepoint_photo");
  		    //********************************				  
			       SQL = "UPDATE Clients SET `Photo`=? WHERE id_client=?" ;
							ps = conn.prepareStatement(SQL);  //создание запроса ps														
					    ps.setBlob(1, blobValue);							
			  			ps.setInt(2,id);				
				  		
//  		    	ps.execute();
//		    	   System.out.println("выполнили");		
			        ps.executeUpdate();
			    		 System.out.println("выполнили update");	 
			    	 SQL=null;
						ps.close();	
						reg_photo="008";
			    	//********************************	
			        conn.commit();
			 }
						catch(SQLException se){
						 	conn.rollback(savepoint_photo);
						 	reg_photo="004";
						 	System.out.println("Произошла ошибка!Повторите действия.");
						 }
			catch(Exception e){
				reg_photo="009";
				e.getStackTrace();
				System.out.println("Ошибка при записи фото в базу данных");}			
		
	 return reg_photo;
	 
 }; 
  
//*****************************************************************************
//методы для определения id
//определение  id_client по ФИО, для дальнейшей работы именно с id
 public static String[][] id_by_name (String last_name,String first_name) throws SQLException {	  	
	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;
	String SQL; 

	try{					                                      //блок кода, который отслеживает ошибки
	conn = getConnection();	  	
	conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
//	String last_name_=String.valueOf(last_name);
//	String first_name_=String.valueOf(first_name);
	String last_name_=String.valueOf(last_name);
	String first_name_=String.valueOf(first_name);
	SQL = "SELECT id_client, `Last Name`,`First Name`,`By Father`, `Birthday` FROM Clients WHERE `Last Name` = ? AND `First Name` = ?";
 ps = conn.prepareStatement(SQL);
	ps.setString(1,last_name_);
	ps.setString(2, first_name_);
	rs = ps.executeQuery();			    										 //выполнение запроса
	System.out.println("----"+last_name_+" "+first_name_);
  rs.last();
	 int rows7 = rs.getRow();
	 rs.beforeFirst(); 
	 System.out.println("==="+rows7);
	 if(rows7==0){
		 id_many=new String[1][1];
		 id_many[0][0]="013"; 	
	 }
	 if(rows7>1){ 
	   	 id_many = new String[1][rows7];
	   	 int number;
	   	 for (number = 0; rs.next(); number++) {
	   		String i= rs.getString(1);
	   		String f= rs.getString(2);
	   		String im= rs.getString(3);
	   		String o= rs.getString(4);
	   		String dr= rs.getString(5);
	   		id_many [0] [number]=i+" - "+f+" "+im+" "+" "+o+" "+dr;
	   		System.out.println(id_many [0] [number]);
	   	 }	 
	 
	 } 
	 else{   
		 while (rs.next()) {																// вывод значений полей в виде строк
			 id_many=new String[1][1];
			 id_many[0][0]=rs.getString(1); 	
    }
	 }

 SQL=null;
	rs.close();
	ps.close();
	conn.commit();
	
	 }
  catch(ClassNotFoundException cnfe){
  	 id_many=new String[1][1];
		 id_many[0][0]="005";
  	System.out.println("Where Driver?");}
	 catch (Exception e) {
		 id_many=new String[1][1];
		 id_many[0][0]="003";
		  System.out.println("Ошибка запроса!!!Неверно задано ФИО");
	  	e.printStackTrace();
	}
  finally { if (conn != null) conn.close(); }
	return id_many;
}

//определение  id_client по номеру телефона, для дальнейшей работы именно с id
public static int id_by_telephone (String telephone) throws SQLException {	 
	 System.out.println("telephone "+telephone);
	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;
	String SQL; 

	try{					                                           //блок кода, который отслеживает ошибки
			conn = getConnection();	 
			System.out.println("соединение установленно");
			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
			
//********************Создание и выполнение запроса****************************
			SQL = "SELECT `Clients_id_client` FROM Telephone WHERE `telephone` = ?";
			ps = conn.prepareStatement(SQL);  													//создание запроса ps
			ps.setString(1,telephone);
			System.out.println("составили запрос");
			rs = ps.executeQuery();			  											   //выполнение запроса и присвоение rs результата
			System.out.println("выполнили запрос");
			
			rs.last();
			int id_n=rs.getRow();
			rs.beforeFirst();
			System.out.println("количество = "+id_n);
			if(id_n>0){
			while (rs.next()) {
				id = rs.getInt(1);
			}
			}else{id=0;}
//______________________________________________________________________________	 
	//System.out.println("Запрос выполнен. Ниже представлен результат: ");
	System.out.println("id = "+id+", найден по номеру телефона "+telephone);
//____________________________________________________________________________
			SQL=null;
			rs.close();
			ps.close();
			
//*****************************************************************************		  			
			conn.commit();	
		}
		catch(ClassNotFoundException cnfe){
			  id=0;
				System.out.println("Where Driver?");
    }
    catch (Exception e) {
    	   id=0;
	    	 System.out.println("Ошибка запроса!!!Неверно задан номер телефона!");
		     e.printStackTrace();
    }
    finally { 
    	 if (conn != null) conn.close(); 	
    }
   	return id;
   
 }

//определение  id_client по email, для дальнейшей работы именно с id
 public static int id_by_email(String email) throws SQLException {	  	
 	Connection conn = null;
 	PreparedStatement ps;
 	ResultSet rs;
 	String SQL; 

 	try{					                                           //блок кода, который отслеживает ошибки
 			conn = getConnection();	  	
 			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
//********************Создание и выполнение запроса****************************
 			String email_=String.valueOf(email);
 			SQL = "SELECT `Clients_id_client` FROM email WHERE `email` = ?";
 			ps = conn.prepareStatement(SQL);  													//создание запроса ps
 			ps.setString(1,email_);
 			rs = ps.executeQuery();			  											   //выполнение запроса и присвоение rs результата
 			
 			rs.last();
			int id_n=rs.getRow();
			rs.beforeFirst();
			System.out.println("количество = "+id_n+" "+email);
			if(id_n>0){
				while (rs.next()) {
 				id = rs.getInt(1); 
 				System.out.println("id = "+id+" по email"+email);
				}
			}else{id=0;}
//____________________________________________________________________________
 			SQL=null;
 			rs.close();
 			ps.close();
 			
//*****************************************************************************		  			
 			conn.commit();	
 		}
 		catch(ClassNotFoundException cnfe){
 			id=0;
 				System.out.println("Where Driver?");
     }
     catch (Exception e) {
    	   id=0;
	    	 System.out.println("Ошибка запроса!!!");
		     e.printStackTrace();
     }
     finally { 
     	 if (conn != null) conn.close(); 
     }
    	return id;
  }
  	   
//==================================================================  	  
 //просмотр сведений о клиенте по id	  
	   public static String[][] review_ (String cl,String last_name, String first_name,String telephone,String email) throws SQLException {
	  	 	Connection conn = null;
	  	 	PreparedStatement ps;
	  	 	ResultSet rs;
	  	 	String SQL; 

	  	 	try{					                                           //блок кода, который отслеживает ошибки
	  	 			conn = getConnection();	  	
	  	 			conn.setAutoCommit(false); 
	//**************************************************************  	 					  
	  	 			int control=1;
		  			if(	cl.compareToIgnoreCase("")==0){
		  				
		  					if(last_name.compareToIgnoreCase("")!=0&&first_name.compareToIgnoreCase("")!=0){
		  					System.out.println("фио"+first_name+" "+last_name);
		  					id_by_name (last_name,first_name);
		  					String q=id_many[0][0];
		  					
		  					if(q.compareToIgnoreCase("013")==0){
		  						anketa=new String [1][1];
		  						anketa[0][0]="013";
		  					}
		  					if(id_many[0].length>1){
		  						control=0;
		  						anketa=new String[2][id_many[0].length];
		  						System.out.println(id_many[0].length);
		  						anketa[0][0]="025";
		  						for(int w=0;w<id_many[0].length;w++){
		  								anketa[1][w]=id_many[0][w];
		  							System.out.println("отправляем "+(anketa[1][w]).toString());
		  						}
		  						for(int r=1;r<id_many[0].length;r++){
		  							anketa[0][r]="015";
		  						}
		  						
		  					}else{
		  						
		  						id=Integer.valueOf(id_many[0][0]);
		  						System.out.println("id="+id);}
		  				}else{
		  					if(telephone.compareToIgnoreCase("")!=0){
	  	 						id_by_telephone (telephone);		  					
		  					}else{
		  						if(email.compareToIgnoreCase("")!=0){
		  							System.out.println("зашли в email");
		  							id_by_email(email);
		  						}else{
		  							anketa = new String[1][1];
		  							anketa[0][0]="013";
		  						}
		  					}
		  				}
		  			}else{ id=Integer.valueOf(cl);}
		  			if (control == 1){
		  			if(id!=0){
		  			review_fio (id); 			 					//вывод ФИО,ДР,дата регистрации по id  
		  			if((Clients[0][0]).compareToIgnoreCase("013")!=0 && (Clients[0][0]).compareToIgnoreCase("005")!=0){
		  					review_all_telephone (id);			//вывод всех номеров телефонных по id	  			
		  					if((telephone_arr[0][0]).compareToIgnoreCase("005")!=0 && (telephone_arr[0][0]).compareToIgnoreCase("013")!=0){
		  							review_all_email(id);						//вывод всех email по id	
		  							review_friend( id);              //поиск друга,который его привел, и вывод его ФИО 	
		  							what_status (id);								//вывод статуса
		  							what_balance (id);								//вывод информации о балансе
		  		
		  							int N= Clients.length+telephone_arr.length+email_arr.length+fr_arr.length+st_arr.length+bal_arr.length+1;
		  							int wt=telephone_arr[0].length;
		  							int we=email_arr[0].length;
		  							int wf=fr_arr[0].length;
		  							int ws=st_arr[0].length;
		  							int W=ws;
		  							if(wt>=we&&wt>=wf&&wt>=ws){
		  								W=wt;		  				
		  							}
		  							if(we>=wt&&we>=wf&&we>=ws){
		  								W=we;
		  							}
		  							if(wf>=wt&&wf>=we&&wf>=ws){
		  								W=wf;
		  							}		  				
		  			
		  							anketa=new String [N][W];
		  							for(int i=0;i<Clients.length;i++){		  				
		  								anketa[i][0]=Clients[i][0];		  				
		  							}		  		  			
		  							for (int j=0;j<wt;j++){
		  								anketa[Clients.length][j]=telephone_arr[0][j];
		  							}
		  							for (int d=0;d<we;d++){
		  								anketa[Clients.length+1][d]=email_arr[0][d];
		  							}
		  							for (int b=0;b<wf;b++){
		  								anketa[Clients.length+2][b]=fr_arr[0][b];
		  							}
		  							for (int c=0;c<ws;c++){
		  								anketa[Clients.length+3][c]=st_arr[0][c];
		  							}
		  							anketa[Clients.length+4][0]=bal_arr[0][0];		  				
	  			
		  							for(int i=0;i<N;i++){
		  								for(int j=0;j<W;j++)
		  									if(anketa[i][j]==null||(anketa[i][j]).toString()==null){
		  										anketa[i][j]="015";
		  									}		
		  								} 
		  							String id_str=String.valueOf(id);
		  							anketa[Clients.length+5][0]=id_str;
		  							System.out.println("id="+id+" "+id_str);
		  					}
		  					else{
			  					anketa = new String[1][1];
			  					anketa[0][0]=telephone_arr[0][0];
			  				}
		  					
		  				}else{
		  					anketa = new String[1][1];
		  					anketa[0][0]=Clients[0][0];
		  				}
		  			}
		  			else{
		  				anketa = new String[1][1];
		  				anketa[0][0]="013";
		  			}
		  		}
//**********************************************************************
		  			conn.commit();	
	   		}
	   		catch(ClassNotFoundException cnfe){
	   			anketa = new String[1][1];
  				anketa[0][0]="005";
	   				System.out.println("Where Driver?");
	       }
	       catch (Exception e) {
	      	 anketa = new String[1][1];
	  				anketa[0][0]="003";
	  	    	 System.out.println("Ошибка запроса!!!");
	  		     e.printStackTrace();
	       }
	       finally { 
	       	 if (conn != null) conn.close(); 
	       }
	    	   

	     return anketa;
}
//================================================================== 
//методы для вывода информации о клиенте
//вывод ФИО,ДР,дата регистрации по id     
	  	private static String[][] review_fio (int id) throws SQLException  {
	  		Connection conn = null;
		  	System.out.println("id в методе= "+id);
		  	
		    try{					                                           //блок кода, который отслеживает ошибки
		  			conn = getConnection();	  	
		  			conn.setAutoCommit(false);        
	  		
	  		// ********************Создание и выполнение запроса****************************
	  		 			SQL = "SELECT `Last Name`, `First Name`, `By Father`, `Birthday`, `Date registration` ,`Photo` FROM Clients WHERE `id_client` = ? ";
	  					ps = conn.prepareStatement(SQL);  //создание запроса ps
	  		 			ps.setInt(1,id);
	  		 			ResultSet rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата
	  		//______________________________________________________________________________
	  		 //Вывод на экран информации				
	  			   rs.last();
	  			   int c=rs.getRow();
	  			   rs.beforeFirst();
	  		 			
	  			   if (c != 0){
	  		 			while (rs.next()) {
	  			  	 last_name = rs.getString(1);
	  			  
	  			  	 first_name = rs.getString(2);
	  			  	 by_father = rs.getString(3);
	  			  	 birthday = rs.getDate(4);
	  			  	  String birthday_str=birthday.toString();
	  			  	 Timestamp date_registration = rs.getTimestamp(5);
	  			  	  String date_registration_str = date_registration.toString();
	  			  	 Blob photo= rs.getBlob(6);
	  			  	 if (photo!=null){
	  			  	 		int photoLength = (int) photo.length(); 
	  			  	 		if(photoLength==0){
	  			  	 			photo_encode="";
	  			  	 		}else{
	  			  	 			byte[] photoAsBytes = photo.getBytes(1, photoLength);	  			  	   			  	 		
	  			  	 			photo_encode = com.sun.org.apache.xml.internal.security.utils.Base64.encode(photoAsBytes);
	  			  	 			}	
	  			  	 		}else{ photo_encode="";
	  			  	 	}	  	 		
	  			  	 Clients = new String [6][1];// указываем количество строк и столбцов
	  			  	 Clients [0][0]=last_name;
	  			  	 Clients [1][0]=first_name;
	  			  	 Clients [2][0]= by_father;
	  			  	 Clients [3][0]= birthday_str;
	  			  	 Clients [4][0]=date_registration_str;
	  			  	 Clients [5][0]=photo_encode;
//	  			  	 
//	  			  	 
//	  			  	 System.out.println();
//	  			  	 System.out.println("ФИО: "+ last_name+" "+first_name+" "+by_father);	  	 
//	  			  	 System.out.println("Дата рождения: "+birthday);
//	  			  	 System.out.println("Дата регистрации: "+date_registration);		
	  			   }	
	  			  }else{
	  			  	Clients=new String [1][1];
	  			  	Clients [0][0]= "013";
	  			  }
	  		//____________________________________________________________________________
	  		 	 SQL=null;
	  		    rs.close();
	  		    ps.close();		  			
	  		//********************Создание и выполнение запроса****************************
	  		    conn.commit();	
				}
			  catch(ClassNotFoundException cnfe){
			  	Clients=new String [1][1];
			  	Clients [0][0]= "005";
			  	System.out.println("Where Driver?");
			  }
			  catch (Exception e) {
			  	Clients=new String [1][1];
			  	Clients [0][0]= "013";
			  	System.out.println("Ошибка запроса!!!Ошибка при запросе вывода информации о клиенте!");
				 	e.printStackTrace();
			   }
			   finally { 
			     if (conn != null) conn.close(); 
		     }	  		    
	  		 return Clients; 
}

//вывод всех телефонных номеров  по id
	  	private static String[][] review_all_telephone (int id) throws SQLException {
	  			 
	  				Connection conn = null;
	  		  	System.out.println("id в методе= "+id);
	  		  	
	  		    try{					                                           //блок кода, который отслеживает ошибки
	  		  			conn = getConnection();	  	
	  		  			conn.setAutoCommit(false);        
	  	  		
// ********************Создание и выполнение запроса****************************	  			 
	  			 SQL = "SELECT `telephone` FROM Telephone WHERE `Clients_id_client` = ? ";
	  			 ps = conn.prepareStatement(SQL);  //создание запроса ps
	  		   ps.setInt(1,id);
	  			 rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата
	  		//______________________________________________________________________________
	  			 rs.last();
	  			 int rows = rs.getRow();
	  			 rs.beforeFirst(); 
	  				
	  			 if(rows!=0){
	  		 	 String[] t = new String[rows+1];
	  		 	 telephone_arr=new String[1][rows];
	  		   for (int number = 0; rs.next(); number++) {
	  		  	 telephone_arr[0][number]=rs.getString(1);
	  		   } 
	  		  }else{
	  		  	telephone_arr=new String [1][1];
	  		  	telephone_arr [0][0]= "013";
	  		  }
	  		//____________________________________________________________________________
	  				SQL=null;
	  				rs.close();
	  				ps.close();	  				 
	  		 
//********************Создание и выполнение запроса****************************
	  		    conn.commit();	
				}
			  catch(ClassNotFoundException cnfe){
			  	telephone_arr=new String [1][1];
			  	telephone_arr [0][0]= "005";
			  	System.out.println("Where Driver?");
			  }
			  catch (Exception e) {
			  	telephone_arr=new String [1][1];
			  	telephone_arr [0][0]= "013";
			  	System.out.println("Ошибка запроса!!!Ошибка при запросе вывода информации о клиенте!");
				 	e.printStackTrace();
			   }
			   finally { 
			     if (conn != null) conn.close(); 
		     }	  		    
	  		 return  telephone_arr; 
}
//вывод всех email по id
	  	private static String[][] review_all_email(int id) throws SQLException {
 			 
 				Connection conn = null;
 		  	System.out.println("id в методе email= "+id);
 		  	
 		    try{					                                           //блок кода, который отслеживает ошибки
 		  			conn = getConnection();	  	
 		  			conn.setAutoCommit(false);        
 	  		
//********************Создание и выполнение запроса****************************	  			 	
	  		
	  		SQL = "SELECT `email` FROM email WHERE `Clients_id_client` = ? ";
	  				ps = conn.prepareStatement(SQL);  //создание запроса ps
	  				ps.setInt(1,id);
	  			  rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата
	  		//______________________________________________________________________________
	  			  rs.last();
	  			  int rows1 = rs.getRow();
	  				rs.beforeFirst(); 

	  			  email_arr=new String[1][rows1+1];
	  			  if(rows1==0){
	  			  	email_arr[0][0]="";
	  			  }
	  				for (int number1 = 0; rs.next(); number1++) {	  				
	  					email_arr[0][number1] = rs.getString(1);
	  				}
	  		//____________________________________________________________________________
	  				SQL=null;
	  				rs.close();
	  				ps.close();	 
//********************Создание и выполнение запроса****************************
	  		    conn.commit();	
				}
			  catch(ClassNotFoundException cnfe){
			  	email_arr=new String[1][1];
			  	email_arr[0][0]="005";
			  	System.out.println("Where Driver?");
			  }
			  catch (Exception e) {
			  	email_arr=new String[1][1];
			  	email_arr[0][0]="003";
			  	System.out.println("Ошибка запроса!!!Ошибка при запросе вывода информации о клиенте!");
				 	e.printStackTrace();
			   }
			   finally { 
			     if (conn != null) conn.close(); 
		     }	  		    
	  		 return  email_arr; 
}

//поиск друга,который его привел, и вывод его ФИО
	  	private static String[][] review_friend(int id) throws SQLException{
	  			 Connection conn = null;	  		  	
	  		  	
	  		    try{					                                           //блок кода, который отслеживает ошибки
	  		  			conn = getConnection();	  	
	  		  			conn.setAutoCommit(false);        
	  	  		
	 //********************Создание и выполнение запроса****************************	  			 	
	 	  		
	  			 SQL = "SELECT `Clients_id_client` FROM Clients  WHERE `id_client` = ?";
	  				ps = conn.prepareStatement(SQL);  //создание запроса ps
	  				ps.setInt(1,id);
	  				rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата
	  			 rs.last();
	  			 int f=rs.getRow();
	  			 rs.beforeFirst();
	  			 
	  			 
	  				while (rs.next()){
	  			  	friend = rs.getInt(1);
	  		   }	
	  			  SQL=null;
	  				rs.close();
	  				ps.close();
	  			//***************************************************	
	  				System.out.println("friend "+friend);
	  				if(friend==0){
	  					fr_arr=new String [1][1];
	  					fr_arr[0][0]="015";	  					
	  				}else{
	  					
	  					SQL = "SELECT `Last Name`, `First Name`, `By Father` FROM Clients WHERE `id_client` = ? ";
	  					ps = conn.prepareStatement(SQL);  //создание запроса ps
	  					ps.setInt(1,friend);
	  					rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата
	  		//______________________________________________________________________________			  				  				  				
	  					rs.last();
	  					int fri=rs.getRow();
	  					rs.beforeFirst();
	  				if (fri!=0){
	  					fr_arr=new String [1][4];
	  					String friendStr=String.valueOf(friend);
	  					fr_arr[0][0]=friendStr;
	  					while (rs.next()) {		  						
	  			  	  fr_arr[0][1] = rs.getString(1);
	  						fr_arr[0][2] = rs.getString(2);
	  						fr_arr[0][3] = rs.getString(3);
	  						  				  				  			  	
	//  						System.out.println("ФИО друга что привел его: "+friend_last_name+" "+friend_first_name+" "+friend_by_father+". Его id "+friend);		  					
	  				  		
	  					}
	  				}else{
	  					fr_arr=new String [1][1];
	  					fr_arr [0][0]= "";
	  				}
	  		//____________________________________________________________________________
	  		  	SQL=null;
	  					rs.close();
	  					ps.close();	}	
	  		    
	  				//********************Создание и выполнение запроса****************************
		  		    conn.commit();	
					}
				  catch(ClassNotFoundException cnfe){
				  	fr_arr=new String [1][1];
				  	fr_arr [0][0]= "005";
				  	System.out.println("Where Driver?");
				  }
				  catch (Exception e) {
				  	fr_arr=new String [1][1];
				  	fr_arr [0][0]= "003";
				  	System.out.println("Ошибка запроса!!!Ошибка при запросе вывода информации о клиенте!");
					 	e.printStackTrace();
				   }
				   finally { 
				     if (conn != null) conn.close(); 
			     }	  		    
		  		 return  fr_arr; 
	}

//определяем status клиента на данный момент
	  	public static String[][] what_status (int id) throws SQLException {
	  		 Connection conn = null;	  		  	
 		  	
 		    try{					                                           //блок кода, который отслеживает ошибки
 		  			conn = getConnection();	  	
 		  			conn.setAutoCommit(false);        
 	  		
//********************Создание и выполнение запроса****************************	  			 		  		
	  	  	
	  						SQL = "SELECT `status`, `Datetime` FROM Status WHERE `Clients_id_client` = ? ";
	  						ps = conn.prepareStatement(SQL);
	  						ps.setInt(1,id);
	  						rs = ps.executeQuery();			    										 //выполнение запроса
	  						
	  						rs.last();
	  						int st=rs.getRow();
	  						rs.beforeFirst();
	  						if(st==0){
	  							st_arr=new String[1][1];
	  							st_arr [0][0]="016";
	  						}else{
	  						st_arr=new String[1][2];
	  						while (rs.next()) {																// вывод значений полей в виде строк	  							 
	  							st_arr[0][0]=rs.getString(1); 
	  							st_arr[0][1]=rs.getString(2); 	  						
	  							
	  						System.out.println("У клиента статус - "+st_arr[0][0]+".Он изменен "+st_arr[0][1]+".");					
	  						}
	  						}

	  						SQL=null;
	  						rs.close();
	  						ps.close();
	  					 
//********************Создание и выполнение запроса****************************
			  		    conn.commit();	
						}
					  catch(ClassNotFoundException cnfe){
					  	st_arr=new String [1][1];
					  	st_arr [0][0]= "005";
					  	System.out.println("Where Driver?");
					  }
					  catch (Exception e) {
					  	st_arr=new String [1][1];
					  	st_arr [0][0]= "016";
					  	System.out.println("Не найден клиент в журнале Status");
						 	e.printStackTrace();
					   }
					   finally { 
					     if (conn != null) conn.close(); 
				     }	  		    
  				 
	  				 return st_arr;
	  	}
	  		 
//вывод информации о Балансе
	  	public static String[][] what_balance (int id) throws SQLException {
	  				 Connection conn = null;	  		  	
	  	 		  	
	  	 		    try{					                                           //блок кода, который отслеживает ошибки
	  	 		  			conn = getConnection();	  	
	  	 		  			conn.setAutoCommit(false);        
	  	 	  		
//********************Создание и выполнение запроса****************************	  		
	  				
	  						SQL = "SELECT SUM(`Balance`) FROM `Balance` WHERE `Clients_id_client` = ? ";
	  						ps = conn.prepareStatement(SQL);
	  						ps.setInt(1,id);
	  						rs = ps.executeQuery();			    										 //выполнение запроса
	  			     
	  						
	  						bal_arr=new String[1][1];
	  			     
	  						while (rs.next()) {																// вывод значений полей в виде строк
	  						 balance = rs.getFloat(1); 
	  						 bal_arr[0][0]=String.valueOf(balance);
	  						System.out.println("У клиента баланс "+bal_arr[0][0]);					
	  						}
	  						 
	  						SQL=null;
	  						rs.close();
	  						ps.close();	
	  				
	  			 
//********************Создание и выполнение запроса****************************
		  		    conn.commit();	
					}
				  catch(ClassNotFoundException cnfe){
				  	bal_arr=new String[1][1];
  					bal_arr[0][0]="005";
				  	System.out.println("Where Driver?");
  				}catch (Exception e){
  					bal_arr=new String[1][1];
  					bal_arr[0][0]="017";
  					System.out.println("Не найден balance");
  					}
	  								  
				   finally { 
				     if (conn != null) conn.close(); 
			     }	  		    
				 
  				 return bal_arr;
  	}
//====================================================================	
//методы для редактирования анкеты
//edit photo	  	
	  	public static String edit_photo (String cl,String s64) throws SQLException {
	  		Connection conn = null;
	  					  	
	  	 try{					                                           //блок кода, который отслеживает ошибки
	  				conn = getConnection();	  	
	  		//		System.out.println("Got Connection.");
	  				conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
	  				savepoint_e_ph=conn.setSavepoint("savepoint_e_ph");
	  	//********************Создание и выполнение запроса****************************
	  						
	  				try {
	  							id=Integer.valueOf(cl);
	  							if(id!=0){
	  								new_photo(s64,conn,id);  //загружаем фото 
	  								edit_photo=reg_photo;
	  								System.out.println(reg_photo);
	  							}else{
	  								edit_photo="007";
	  							}
	  										
	  	//*****************************************************************************			 
	  						}catch(Exception e){
	  							edit_photo="003";
	  							System.out.println("Ошибка в запросах!!!Новый клиент не добавлен!");}
	  	 conn.commit();	
	  	 }
	  	 catch(SQLException se){
	  	 	conn.rollback(savepoint_e_ph);
	  	 	edit_photo="004";
	  	 	System.out.println("Произошла ошибка!Повторите действия.");
	  	 }
	  			catch(ClassNotFoundException cnfe){
	  				edit_photo="005";
	  				System.out.println("Where Driver?Новый клиент не добавлен!");
	  			}
	  	 	catch (Exception e) {
	  	 		edit_photo="006";
	  	 		System.out.println("Ошибка при соединении!!!Новый клиент не добавлен!");
	  	 		e.printStackTrace();
	  	 	}
	  	 finally { 
	  	 	if (conn != null) conn.close();          
	  	 }	
	  	 return edit_photo;
	  	}
	  	
	  //изменить ДР
	   public static String edit_birthday (String cl,String s_date) throws SQLException {
	  	Connection conn = null;
	  	PreparedStatement ps;
	  	String SQL; 
	    	//меняем тип переменной с String на Date
	  		java.sql.Date new_birthday = java.sql.Date.valueOf(s_date); 
	  		int id=Integer.valueOf(cl);
	  	
	    try{					                                           //блок кода, который отслеживает ошибки
	  			conn = getConnection();	  	
	  			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
	  			savepoint_e_b=conn.setSavepoint("savepoint_e_b");
	  //********************Создание и выполнение запроса****************************
	  			SQL = "UPDATE Clients SET `Birthday`=?  WHERE `id_client` = ? ";
	  			ps = conn.prepareStatement(SQL);  //создание запроса ps
	  			ps.setInt(2,id);
	  			ps.setDate(1,new_birthday);
	  			ps.execute();			     //выполнение запроса и присвоение rs результата

	      	 System.out.println("ДР обновлено. Новый ДР: "+new_birthday);

	      	SQL=null;
	  			ps.close();
	  			edit_dr="026";
	  //*****************************************************************************		  			
	  			 conn.commit();	
	  		}
	    	catch (SQLException se){
	    		edit_dr="004";
	    		conn.rollback(savepoint_e_b);
	    		System.out.println("Произошла ошибка!Повторите действия.");
	    	}
	  		catch(ClassNotFoundException cnfe){
	  			edit_dr="005";
	  	     System.out.println("Where Driver?");
	      }
	      catch (Exception e) {
	      	edit_dr="006";
	      	 System.out.println("Ошибка запроса!!!Произошла ошибка при обновлении ДР!");
	  	     e.printStackTrace();
	      }
	      finally { 
	      	 if (conn != null) conn.close(); 
	      }
	     	return edit_dr;
	   }

	  //изменить номер телефона клиента по номеру телефона  
	  	   public static String edit_telephone (String telephone, String new_telephone) throws SQLException {
	  		  	Connection conn = null;
	  		  	PreparedStatement ps;
	  		  	String SQL; 
	  		  	System.out.println("Обновление выполнено,новый телефон "+telephone+" "+new_telephone);
	  		    try{					                                           //блок кода, который отслеживает ошибки
	  		  			conn = getConnection();	  	
	  		  			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
	  		  			savepoint_e_t=conn.setSavepoint("savepoint_e_t");
	  // ********************Создание и выполнение запроса****************************
	  		  			SQL = "UPDATE Telephone SET `telephone`=?  WHERE `telephone`=? ";
	  		  			ps = conn.prepareStatement(SQL);  //создание запроса ps
	  		  			ps.setString(1,new_telephone);
	  		  			ps.setString(2,telephone);

	  		  			ps.execute();			     //выполнение запроса и присвоение rs результата

	    		  	  System.out.println("Обновление выполнено,новый телефон "+new_telephone);

	    		   	  SQL=null;
	  		  		  ps.close();	
	  		  		  edit_t="026";
	  // *****************************************************************************		  			
	  		  			conn.commit();	
	  		  		}
	  		    	catch (SQLException se){
	  		    		edit_t="004";
	  		    		conn.rollback(savepoint_e_t);
	  		    		System.out.println("Произошла ошибка!Повторите действия.");
	  		    	}
	  		  		catch(ClassNotFoundException cnfe){
	  		  			edit_t="005";
	  		  	     System.out.println("Where Driver?");
	  		      }
	  		      catch (Exception e) {
	  		      	edit_t="006";
	  			    	 System.out.println("Ошибка запроса!!!Ошибка при изменении номера телефона!");
	  				     e.printStackTrace();
	  		      }
	  		      finally { 
	  		      	 if (conn != null) conn.close(); 
	  	        }
	  		    return edit_t;
	  	     }

	  //изменить email клиента  по email
	  	   public static String edit_email (String email,String new_email) throws SQLException {
	  		  	Connection conn = null;
	  		  	PreparedStatement ps;
	  		  	String SQL; 

	  		    try{					                                           //блок кода, который отслеживает ошибки
	  		  			conn = getConnection();	  	
	  		  			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий	
	  		  			savepoint_e_e=conn.setSavepoint("savepoint_e_e");
	  // ********************Создание и выполнение запроса****************************
	  		  			SQL = "UPDATE email SET `email`=?  WHERE `email`=? ";
	  		  			ps = conn.prepareStatement(SQL);  //создание запроса ps
	  		  			ps.setString(1,new_email);
	  		  			ps.setString(2,email);

	  		  			ps.execute();			     //выполнение запроса и присвоение rs результата
	  	  	  	 
	  		  	    System.out.println("Обновление выполнено");

	  		    	  SQL=null;
	  		    	  ps.close();
	  		    	  edit_e="026";
	  // ******************************************************************************		  			
	  		  		 conn.commit();	
	  		  	 }
	  	    	catch (SQLException se){
	  	    		edit_e="004";
	  	    		conn.rollback(savepoint_e_e);
	  	    		System.out.println("Произошла ошибка!Повторите действия.");
	  	    	}
	  		  	 catch(ClassNotFoundException cnfe){
	  		  		 edit_e="005";
	  		  	     System.out.println("Where Driver?");
	  		     }
	  		     catch (Exception e) {
	  		    	 edit_e="006";
	  			    	 System.out.println("Ошибка запроса!!!Изменить email не удалось!");
	  				     e.printStackTrace();
	  		     }
	  		     finally { 
	  		      	 if (conn != null) conn.close(); 
	  	       }
	  		    return edit_e;
	  	     }

	  //изменить друга клиента  по id /VIP возможность
	  	   public static String edit_friend (String cl,String new_id_friend) throws SQLException {
	  		  	Connection conn = null;
	  		  	PreparedStatement ps;
	  		  	String SQL; 
	  		  	int new_id_friend_int=Integer.parseInt(new_id_friend);
	  		  	int id=Integer.valueOf(cl);
	  		  	try{					                                           //блок кода, который отслеживает ошибки
	  		  			conn = getConnection();	  	
	  		  			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
	  		  			savepoint_e_f=conn.setSavepoint("savepoint_e_f");
	  // ********************Создание и выполнение запроса****************************
	  		  			SQL = "SELECT `id_client` FROM Clients  WHERE `id_client` = ?";
	  	  				ps = conn.prepareStatement(SQL);  //создание запроса ps
	  	  				ps.setInt(1,new_id_friend_int);
	  	  				rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата
	  	  			 rs.last();
	  	  			 int f=rs.getRow();
	  	  			 rs.beforeFirst();
	  	  			 	
	  	  			  SQL=null;
	  	  				rs.close();
	  	  				ps.close();
	  	  			//***************************************************	
	  	  				if(f==0){
	  	  					edit_fr="028";
	  	  				}else{
	  		  			SQL = "UPDATE Clients SET `Clients_id_client`=?  WHERE `id_client`=? ";
	  		  			ps = conn.prepareStatement(SQL);  //создание запроса ps
	  		  			ps.setInt(1,new_id_friend_int);
	  		  			ps.setInt(2,id);

	  		  			ps.execute();			     //выполнение запроса и присвоение rs результата
	  	  	  	 
	   		     	  System.out.println("Обновление выполнено.id друга изменен.");

	  		  	    SQL=null;
	  		  			ps.close();
	  		  			edit_fr="026";}
	  // *****************************************************************************		  			
	  		  			conn.commit();	
	  		  		}
	  		  		catch (SQLException se){
	  		  			edit_fr="004";
	  		  			conn.rollback(savepoint_e_f);
	  		  			System.out.println("Произошла ошибка!Повторите действия.");
	  		  		}
	  		  		catch(ClassNotFoundException cnfe){
	  		  			edit_fr="005";
	  		  	     System.out.println("Where Driver?");
	  		      }
	  		      catch (Exception e) {
	  		      	edit_fr="006";
	  			    	 System.out.println("Ошибка запроса!!!id друга не удалось изменить!");
	  				     e.printStackTrace();
	  		      }
	  		      finally { 
	  		      	 if (conn != null) conn.close(); 
	  	        }
	  		    return edit_fr;
	  	     }
	  	   
	  //изменить Фамилию,Имя или Отчество по id 
	  	   public static String edit_ (String cl , String new_last_name,String new_first_name,String new_by_father) throws SQLException {
	  		  	Connection conn = null;
	  		  	PreparedStatement ps;
//	  		  //	ResultSet rs;
//	  		  	String SQL; 
//	  			 int id=Integer.valueOf(cl);
//	  		  	if (new_last_name != "null"){
//	  		  		last_name = new_last_name;
//	  		  	}
//	  		  	if (new_first_name != "null"){
//	  		  		first_name = new_first_name;
//	  		  	}
//	  		  	if (new_by_father != "null"){
//	  		  		by_father = new_by_father;
//	  		  	}

	  		  	int id=Integer.valueOf(cl);
	  		    try{					                                           //блок кода, который отслеживает ошибки
	  		  			conn = getConnection();	  	
	  		 // 			System.out.println("Got Connection.");
	  		  			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
	  		  			savepoint_e_=conn.setSavepoint("savepoint_e_");
	  //********************Создание и выполнение запроса****************************
	  		  			SQL = "UPDATE Clients SET `Last Name`=?, `First Name`=?, `By Father`=? WHERE `id_client` = ? ";
	  		  			ps = conn.prepareStatement(SQL);  //создание запроса ps
	  		  			ps.setInt(4,id);
	  		  			ps.setString(1,new_last_name);
	  		  			ps.setString(2,new_first_name);
	  		  			ps.setString(3,new_by_father);
	  		  			ps.execute();			     //выполнение запроса и присвоение rs результата

	  		     	 System.out.println("Обновление выполнено");

	  		  	    SQL=null;
	  		  			ps.close();
	  		  			edit_fio="026";
	  //*****************************************************************************		  			
	  		  			conn.commit();	
	  		  		}
	  		    	catch(SQLException se){
	  		    		edit_fio="004";
	  		    		conn.rollback(savepoint_e_);
	  		    		System.out.println("Произошла ошибка!Повторите действия.");
	  		    	}
	  		  		catch(ClassNotFoundException cnfe){
	  		  			edit_fio="005";
	  		  	     System.out.println("Where Driver?");
	  		      }
	  		      catch (Exception e) {
	  		      	edit_fio="006";
	  			    	 System.out.println("Ошибка запроса в изменении ФИО!!!");
	  				     e.printStackTrace();
	  		      }
	  		      finally { 
	  		      	 if (conn != null) conn.close(); 
	  	        }
	  	       	return edit_fio;
	  	     }

	  //__________________________________________ 	   
	  //добавить номер телефона клиента по его id 
	  	   public static String new_telephone (String cl,String plus_telephone) throws SQLException {
	  		  	Connection conn = null;
	  		  	PreparedStatement ps;
	  		  	String SQL; 
	  		  	int id=Integer.valueOf(cl);
	  		    try{					                                           //блок кода, который отслеживает ошибки
	  		  			conn = getConnection();	  	
	  		  			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
	  		  			savepoint_n_t=conn.setSavepoint("savepoint_n_t");
	  // ********************Создание и выполнение запроса****************************
	  		  			System.out.println("Пытаемся добавить телефон");

	  		  			SQL = "SELECT `Clients_id_client` FROM Telephone WHERE `telephone` = ?" ;
	  		  			ps = conn.prepareStatement(SQL);  //создание запроса ps
	  		  			ps.setString(1,plus_telephone);
	  		  			rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата

	  		  			rs.last();
	  		  		  rows=rs.getRow();
	  		  			rs.beforeFirst();
	  		  			System.out.println("Существующих клиентов "+rows);
	  		  		
//	  		  			while(rs.next()){
//	  		  				is_id_client=rs.getInt(1);
//	  		  				System.out.println("Существующие клиенты "+is_id_client);
//	  		  			}
	  		  			SQL=null;
	  		  			ps.close();
	  		  System.out.println("Новый клиент "+id_new_client);
	  		  	if (rows == 0){

	  		  			SQL = "INSERT INTO Telephone(`telephone`,`Clients_id_client`) VALUE (?,?)" ;
	  		  			ps = conn.prepareStatement(SQL);  //создание запроса ps
	  		  			ps.setString(1,plus_telephone);
	  		  			ps.setInt(2,id);

	  		  			ps.execute();			     //выполнение запроса и присвоение rs результата

	      		  	System.out.println("Добавлен еще один номер телефона для id "+id);
	   	 	   	   
	  		  			SQL=null;
	  		  			ps.close();
	  		  			add_t="026";
	  		  			}else{add_t="011";}
	  // *****************************************************************************		  			
	  		  			conn.commit();	
	  		  		}
	  		    	catch (SQLException se){
	  		    		add_t="004";
	  		    		conn.rollback(savepoint_n_t);
	  		    		System.out.println("Произошла ошибка!Повторите действия.");
	  		    	}
	  		  		catch(ClassNotFoundException cnfe){
	  		  			add_t="005";
	  		  	     System.out.println("Where Driver?");
	  		      }
	  		      catch (Exception e) {
	  		      	add_t="006";
	  			    	 System.out.println("Ошибка запроса!!!Номер телефона не добавлен!");
	  				     e.printStackTrace();
	  		      }
	  		      finally { 
	  		      	 if (conn != null) conn.close(); 
	  	        }
	  		    return add_t;
	  	     }

	  //добавить email клиента по его id 
	  	   public static String new_email (String cl ,String plus_email) throws SQLException {
	  		  	Connection conn = null;
	  		  	PreparedStatement ps;
	  		  //	ResultSet rs;
	  		  	String SQL; 
	  		  	int id=Integer.valueOf(cl);
	  		  	
	  		    try{					                                           //блок кода, который отслеживает ошибки
	  		  			conn = getConnection();	  	
	  		 // 			System.out.println("Got Connection.");
	  		  			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
	  		  			savepoint_n_e=conn.setSavepoint("savepoint_n_e");
	  // ********************Создание и выполнение запроса****************************

	  		  			SQL = "SELECT `Clients_id_client` FROM email WHERE `email` = ?" ;
	  		  			ps = conn.prepareStatement(SQL);  //создание запроса ps
	  		  			ps.setString(1,plus_email);
	  		  			rs = ps.executeQuery();			     //выполнение запроса и присвоение rs результата

	  		  			rs.last();
	  		  		  rows=rs.getRow();
	  		  			rs.beforeFirst();
	  		  			System.out.println("Существующих клиентов "+rows);
	  		  		
//	  		  			while(rs.next()){
//	  		  				is_id_client=rs.getInt(1);
//	  		  				System.out.println("Существующие клиенты "+is_id_client);
//	  		  			}
	  		  			SQL=null;
	  		  			ps.close();
	  		  	if (rows == 0){
  		  			
	  		  			SQL = "INSERT INTO email(`email`,`Clients_id_client`) VALUE (?,?)" ;
	  		  			ps = conn.prepareStatement(SQL);  //создание запроса ps
	  		  			ps.setString(1,plus_email);
	  		  			ps.setInt(2,id);

	  		  			ps.execute();			     //выполнение запроса и присвоение rs результата

	  		  			System.out.println("Добавлен еще один email для "+last_name+" "+first_name+" "+by_father);
	   	 	   	   
	  		  			SQL=null;
	  		  			ps.close();		
	  		  			add_e="026";
	  		  			}else{add_e="012";}
	  		  	
	  // *****************************************************************************		  			
	  		  			conn.commit();	
	  		  		}
	  		    	catch (SQLException se){
	  		    		add_e="004";
	  		    		conn.rollback(savepoint_n_e);
	  		    		System.out.println("Произошла ошибка!Повторите действия.");
	  		    	}
	  		  		catch(ClassNotFoundException cnfe){
	  		  			add_e="005";
	  		  	     System.out.println("Where Driver?");
	  		      }
	  		      catch (Exception e) {
	  		      	add_e="006";
	  			    	 System.out.println("Ошибка запроса!!!");
	  				     e.printStackTrace();
	  		      }
	  		      finally { 
	  		      	 if (conn != null) conn.close(); 
	  	        }
	  		    return add_e;
	  	     }
	  //_________________________________________	 
	  //удалить номер телефона	   
	       public static String delete_telephone(String delete_telephone) throws SQLException {
	  	  		Connection conn = null;
	  		  	PreparedStatement ps;
	  		  	String SQL; 

	  		  	
	  		    try{					                                           //блок кода, который отслеживает ошибки
	  		  			conn = getConnection();	  	
	  		  			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
	  		  			savepoint_d_t=conn.setSavepoint("savepoint_d_t");
	  // ********************Создание и выполнение запроса****************************
	  		  					try {
	  		  							SQL = "DELETE FROM `Telephone` WHERE `telephone`=?" ;
	  		  							ps = conn.prepareStatement(SQL);  //создание запроса ps
	  		  							ps.setString(1,delete_telephone);
	  		  							ps.execute();			     //выполнение запроса и присвоение rs результата
	  		
	  		  							SQL=null;
	  		  							ps.close();
	  		  							System.out.println("Телефон удалили!");
	  		  							del_t="026";
	  			 
	  		  					}catch(Exception e){
	  		  						del_t="027";
	  		  						System.out.println("Телефон не удалось удалить!");}
	  // *****************************************************************************		  					
	  		  					conn.commit();	
	  		    }
	  		    catch (SQLException se){
	  		    	del_t="004";
	  		    	conn.rollback(savepoint_d_t);
	  		    	System.out.println("Произошла ошибка!Повторите действия.");
	  		    }
	  		    catch(ClassNotFoundException cnfe){
	  		    	del_t="005";
	  		    	System.out.println("Where Driver?");
	  		    }
	  		    catch (Exception e) {
	  		    	del_t="006";
	  		    	System.out.println("Ошибка соединения!!!");
	  		    	e.printStackTrace();
	  		    }
	  		    finally { 
	  		    	if (conn != null) conn.close();          
	  		    }		      

	  				return del_t;

	  	   }
	  	   
	  //удалить email	
	       public static String delete_email(String delete_email) throws SQLException {
	  	  		Connection conn = null;
	  		  	PreparedStatement ps;
	  		  	String SQL; 
	  		  	
	  		    try{					                                           //блок кода, который отслеживает ошибки
	  		  			conn = getConnection();	  	
	  		  			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
	  		  			savepoint_d_e=conn.setSavepoint("savepoint_d_e");
	  // ********************Создание и выполнение запроса****************************
	    					try {					
	    						SQL = "DELETE FROM `email` WHERE `email`=?" ;
	    						ps = conn.prepareStatement(SQL);  //создание запроса ps
	    						ps.setString(1,delete_email);
	    						ps.execute();			     //выполнение запроса и присвоение rs результата
	  		
	    						SQL=null;
	    						ps.close();
	    						System.out.println("email удален!");	
	    						del_e="026";
	    					}catch(Exception e){
	    						del_e="027";
	    						System.out.println("email не удалось удалить!");}
	  //*****************************************************************************		  					
	    					conn.commit();	
	  		    }
	  		    catch (SQLException se){
	  		    	del_e="004";
	  		    	conn.rollback(savepoint_d_e);
	  		    	System.out.println("Произошла ошибка!Повторите действия.");
	  		    }
	  		    catch(ClassNotFoundException cnfe){
	  		    	del_e="005";
	  		    	System.out.println("Where Driver?");
	  		    }
	  		    catch (Exception e) {
	  		    	del_e="006";
	  		    	System.out.println("Ошибка соединения!!!");
	  		    	e.printStackTrace();
	  		    }
	  		    finally { 
	  		    	if (conn != null) conn.close();          
	  		    }		      

	  	  	 return del_e;
	  	   }
//=================================================================	  	
//====================================================================	  		 

//загружаем фото c file
	  		 private static String review_photo(Connection conn,int id){
	  			
	  					try {
	  								System.out.println("зашли в метод");
	  						//********************************
	  						 SQL = "SELECT `Photo` FROM Clients WHERE id_client=?" ;
	  							ps = conn.prepareStatement(SQL);  //создание запроса ps
	  							ps.setInt(1,id);				
	  								System.out.println("составили запрос");
	  							rs = ps.executeQuery();
	  								System.out.println("выполнили запрос");
	  							while(rs.next()){
	  								byte [] b = rs.getBytes(1);//1???
	  								//byte[] b = {1,1};
	  									System.out.println("результат "+b);
	  								//____
	  								File image = new File("/Users/apple/Desktop/add_image-1.png");
	  									System.out.println("создан файл "+image);
	  									
	  					      FileOutputStream fos = new FileOutputStream(image);
	  					      	System.out.println("создан выходной поток "+fos);
	  					      byte[] buffer = new byte[1];
	  					      InputStream is = rs.getBinaryStream(1);
	  					      while (is.read(buffer) > 0) {
	  					        fos.write(b);
	  					      }
	  					      	System.out.println("выходной поток "+is);
	  					      fos.close();						
	  								//___
	  							
	  							}
	  							//******************************		     
	  							
	  							SQL=null;
	  							rs.close();
	  							ps.close();		
	  							
	  					}catch(Exception e){System.out.println("Ошибка при попытке извлечь фото с базы данных");}
	  					
	  			 return photo_for_android;
	  		 }; 

 //==================================================================  	   
//Создание подключения к БД
  	private static Connection getConnection() throws Exception {
      String driver = "com.mysql.jdbc.Driver";
      String url = "jdbc:mysql://localhost:3306/club_friend";
      String username = "root";
      String password = "1";

      Class.forName(driver);
   // Параметры соединения с базой
      Properties connInfo = new Properties();

      connInfo.put("user",username);
      connInfo.put("password",password);

      connInfo.put("useUnicode","true");
      connInfo.put("characterEncoding","KOI8_R");

      Connection conn = DriverManager.getConnection(url, connInfo);
     // Connection conn = DriverManager.getConnection(url, username, password);
      return conn;
    }
	
}