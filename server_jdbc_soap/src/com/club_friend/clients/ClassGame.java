package com.club_friend.clients;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Properties;
import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "com.club_friend.clients.Game")

public class ClassGame {
	
	public static String status;
	public static String datetime_status;
	public static int id_table_game;
	static String table_table;
	private static String SQL;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static int timer;
	private static int what_friend;
	private static Savepoint savepoint_status_game;
	private static Savepoint savepoint_table;
	private static int not_table;
	private static int id_table;
	private static int status_table;
	private static String reg_table;
	private static int rt;
	private static Savepoint savepoint_tableOut;
	private static int in_game;
	private static Integer id;
	private static String[][] status_clienta;
	private static int control;
	private static String reg_client;
	private static String st_f_p;
	private static String st_p;
	private static String st_n_f_p;
	private static String fr_t;

	
	public static void main(String[] args) throws Exception
	{
//		int id_table=1;
//		int id =144;		
//		String new_status="free";//"play" "pause"//"block"
//		
//		//регистрируем клиента в игре
		
		String cl="287";
		String last_name="";
		String first_name="";
		String telephone="";
		String email="";
		String table="9";
		String new_status="free";
		
		status_game(cl,table, new_status) ;
//		status_game (id,id_table, new_status);
		
//		String table="2";
//		table_in_game(table);
		//table_out_game(table);
		
		
	}
	
//регистрируем стол
	 public static String table_in_game(String table) throws SQLException {	  	
			Connection conn = null;
 
		 	try{					                                      //блок кода, который отслеживает ошибки
			conn = getConnection();	  	
			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
			//set a Savepoint
	   savepoint_table = conn.setSavepoint("savepoint_table");
//************************************************************************************************			
			try{ 
				search_id_table(conn,table);//определить id стола по названию
				if(id_table!=0){
					status_table( conn,id_table);//проверить статус
					if(status_table==2){
						reg_table="030"; //стол не найден
					}else{
						if(status_table==0){
							in_game=1;
							change_status_table(conn,id_table,in_game);//если 0 - то поменять статус на 1
							if(rt==1){
							reg_table="032";
							}else{reg_table="034";}
						}else {
							reg_table="031"; //стол уже зарегистрирован
						}						
					}				
				}else{
					reg_table="030";//стол не найден
				}				
			}
			catch(Exception e){
				reg_table="030";
				System.out.println("Такого  cстола нет!");
			e.printStackTrace();}			
//************************************************************************************			
			conn.commit();
		 	}
		 	catch(SQLException se){
		    // If there is any error.
		 		reg_table="004";
		    conn.rollback(savepoint_table);
		    System.out.println("Произошла ошибка!Повторите действия.");}
		   catch(ClassNotFoundException cnfe){
		  	 reg_table="005";
		  	 System.out.println("Where Driver?");}
			 catch (Exception e) {
				 reg_table="003";
				  System.out.println("Ошибка запроса!!!Неверно задан id");
			  	e.printStackTrace();}
		   finally { if (conn != null) conn.close();
		   System.out.println(reg_table);}
			return reg_table;
		}	
	 
//закрываем стол
		 public static String table_out_game(String table) throws SQLException {	  	
				Connection conn = null;
	 
			 	try{					                                      //блок кода, который отслеживает ошибки
				conn = getConnection();	  	
				conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
				//set a Savepoint
		   savepoint_tableOut = conn.setSavepoint("savepoint_tableOut");
	//************************************************************************************************			
				try{ 
					search_id_table(conn,table);//определить id стола по названию
					if(id_table!=0){
						status_table( conn,id_table);//проверить статус
						if(status_table==2){
							reg_table="030"; //стол не найден
						}else{
							if(status_table==1){
								in_game=0;
								change_status_table(conn,id_table,in_game);//если 1 - то поменять статус на 0
								if(rt==1){
								reg_table="033";
								}else{reg_table="036";}//не удалось закрыть стол
							}else {
								reg_table="035"; //стол уже был закрыт
							}						
						}				
					}else{
						reg_table="030";//стол не найден
					}				
				}
				catch(Exception e){
					reg_table="030";
					System.out.println("Такого  cстола нет!");
				e.printStackTrace();}			
	//************************************************************************************			
				conn.commit();
			 	}
			 	catch(SQLException se){
			    // If there is any error.
			 		reg_table="004";
			    conn.rollback(savepoint_tableOut);
			    System.out.println("Произошла ошибка!Повторите действия.");}
			   catch(ClassNotFoundException cnfe){
			  	 reg_table="005";
			  	 System.out.println("Where Driver?");}
				 catch (Exception e) {
					 reg_table="003";
					  System.out.println("Ошибка запроса!!!Неверно задан id");
				  	e.printStackTrace();}
			   finally { if (conn != null) conn.close();
			   System.out.println(reg_table);}
				return reg_table;
			}	
//определяем id_table
		public static int search_id_table(Connection conn,String table) throws SQLException{
			try{
				SQL="SELECT `id_table` FROM `Table` WHERE `Table` = ?";
				ps=conn.prepareStatement(SQL);
				ps.setString(1, table);
				rs=ps.executeQuery();
				
				rs.last();
				int f=rs.getRow();
				rs.beforeFirst();
				
				if(f == 0){
					id_table=0;
				}
				else{
					while(rs.next()){
						id_table=rs.getInt(1);
					}
				}
				SQL=null;
				rs.close();
				ps.close();
			}catch(Exception e){
				id_table=0;
				System.out.println("Стол не найден!");
			}
			return id_table;		
		}
	 
//определяем status table
			public static int status_table(Connection conn,int id_table) throws SQLException{
				try{
					SQL="SELECT `in_game` FROM `Table` WHERE `id_table` = ?";
					ps=conn.prepareStatement(SQL);
					ps.setInt(1, id_table);
					rs=ps.executeQuery();
					
						while(rs.next()){
							status_table=rs.getInt(1);						
					}
					SQL=null;
					rs.close();
					ps.close();
				}catch(Exception e){
					status_table=2;
					System.out.println("Стол не найден!");
				}
				return status_table;		
			}

//меняем status table
			public static int change_status_table(Connection conn,int id_table, int in_game) throws SQLException{
				try{
					SQL="UPDATE `Table` SET `in_game`=?  WHERE `id_table` = ?";
					ps=conn.prepareStatement(SQL);
					ps.setInt(1, in_game);
					ps.setInt(2, id_table);
					ps.execute();
						rt=1;
					SQL=null;					
					ps.close();
				}catch(Exception e){
					rt=0;
					System.out.println("Стол не найден!");
				}
				return rt;		
			}		 	 
//==================================================================	
//регистрируем игру клиента
	 public static String status_game(String cl,String table, String new_status) throws SQLException {	  	
			Connection conn = null;
 
		 	try{					                                      //блок кода, который отслеживает ошибки
			conn = getConnection();	  	
			conn.setAutoCommit(false);                           //включили авт.выход после вып.действий
			//set a Savepoint
	   savepoint_status_game = conn.setSavepoint("savepoint_status_game");
//************************************************************************************************			
			try{ 

			id = Integer.valueOf(cl);
				if(id!=0){
				search_id_table(conn,table); //определяем id table
				System.out.println(id_table+" - id_table");
				what_status (id,conn);              //определяем status клиента на данный момент
			 if(status.compareToIgnoreCase("044")==0){
				 reg_client="044";
			 }else{
				if (new_status.compareToIgnoreCase("play") == 0){				//новый статус			
						if (status.compareToIgnoreCase("free") == 0){
							status_free_pause(conn,id,id_table,status,new_status); //меняем статус на play
							reg_client=st_f_p;
						}
						if(status.compareToIgnoreCase("pause") == 0){
							status_free_pause(conn,id,id_table,status,new_status); //меняем статус на play		
							reg_client="039";
						}
						if(status.compareToIgnoreCase("block") == 0){
							reg_client="043";
							System.out.println("Клиент заблокирован!!!");						
						}
						if(status.compareToIgnoreCase("play") == 0){	
							status_play(conn, id);	
							reg_client=st_p;
							System.out.println("Не удалось добавить клиента в игру!");
						}		
				}
				
				if (new_status.compareToIgnoreCase("pause") == 0){	      //новый статус	

					if(status.compareToIgnoreCase("play") == 0){
						search_table( conn,id);
						System.out.println(id_table +"="+id_table_game);
						if (id_table == id_table_game){
							timer( conn, datetime_status);
							System.out.println("timer "+timer);
							status_new_free_pause(conn, id,timer,id_table,new_status);
							System.out.println(" "+st_n_f_p);
							if(st_n_f_p.compareToIgnoreCase("047")==0){
								reg_client=st_n_f_p;
								System.out.println("reg_cl "+reg_client);
							}else{
							what_friend ( id,conn);
							friend_timer ( what_friend,id,conn, timer,id_table);
							reg_client="041";
							System.out.println("reg_cl "+reg_client);
							}
							}else{
								reg_client="038";
								System.out.println("Клиент не играет за данным столом!");}
					}else{
						reg_client="037";
						System.out.println("Клиент не зарегистрирован ни за одним столом!!!");}
				}
				
				if (new_status.compareToIgnoreCase("free") == 0){	      //новый статус	
					if(status.compareToIgnoreCase("play") == 0){
						search_table( conn,id);
						if (id_table == id_table_game){
							timer( conn, datetime_status);
							status_new_free_pause(conn, id,timer,id_table,new_status);
							what_friend ( id,conn);
							friend_timer ( what_friend,id,conn, timer,id_table);
							reg_client="040";
						}else{
							reg_client="038";
							System.out.println("Клиент не играет за данным столом!");}
					}else{
						reg_client="037";
						System.out.println("Клиент не зарегистрирован ни за одним столом!");}
				}
				}
			 }else{
					reg_client="013";
				}
			}
			catch(Exception e){
				reg_client="013";
				System.out.println("Такого клиента не существует!");
			e.printStackTrace();}			
//************************************************************************************			
			conn.commit();
		 	}
		 	catch(SQLException se){
		    // If there is any error.
		    conn.rollback(savepoint_status_game);
		    reg_client="004";
		    System.out.println("Произошла ошибка!Повторите действия.");
		 }
		   catch(ClassNotFoundException cnfe){
		  	 reg_client="005";
		  	 System.out.println("Where Driver?");}
			 catch (Exception e) {
				 reg_client="003";
				  System.out.println("Ошибка запроса!!!Неверно задан id");
			  	e.printStackTrace();
			}
		   finally { if (conn != null) conn.close(); }
			return reg_client;
		}	
	 
//==================================================================

//определяем status клиента на данный момент
	 private static String what_status (int id,Connection conn) throws SQLException {
		 try{ 
				SQL = "SELECT `status`, `Datetime` FROM Status WHERE `Clients_id_client` = ? ";
				ps = conn.prepareStatement(SQL);
				ps.setInt(1,id);
				rs = ps.executeQuery();			    										 //выполнение запроса
	   
				while (rs.next()) {																// вывод значений полей в виде строк
					status = rs.getString(1); 
					datetime_status=rs.getString(2);
				}
		//		System.out.println("У клиента статус - "+status);		 

				SQL=null;
				rs.close();
				ps.close();
			 
		
		 }catch (Exception e){
			 status="044";
			 System.out.println("Не найден клиент в журнале Status");}
			
		 return status;
	 }

//****************
//если	нажата 	кнопка	"play" 
//меняем статус на play
	private static String status_free_pause(Connection conn, int id,int id_table,String status,String new_status) throws SQLException {
			try{
		//____________меняем статус на play_____________________
			SQL = "UPDATE Status SET `status`=?  WHERE `Clients_id_client` = ?  ";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, new_status);
			ps.setInt(2,id);
			ps.execute();			    										 //выполнение запроса		   
			
			SQL=null;
			rs.close();
			ps.close();
			
		//	System.out.println("Статус изменен на 'play'.");
			
		//__________________добавляем запись в Game _________________
			int start=1;
			SQL = "INSERT INTO `Game`(`Clients_id_client`,`Table_id_table`,`start`) VALUE (?,?,?)";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1,id);
			ps.setInt(2,id_table);
			ps.setInt(3,start);
			ps.execute();
			st_f_p="039";
			System.out.println("Клиент в игре!");
			}catch (Exception e) {
				st_f_p="045";
				System.out.println("Не удалось зарегистрировать клиента в игре!");}
			return st_f_p;
	}
	 
//определяем за каким столом зарегистрирован клиент на данный момент	
	public static String status_play(Connection conn, int id) throws SQLException {			
	try{ 
		SQL = "SELECT `status`, `Datetime` FROM Status WHERE `Clients_id_client` = ? ";
		ps = conn.prepareStatement(SQL);
		ps.setInt(1,id);
		rs = ps.executeQuery();			    										 //выполнение запроса
		while (rs.next()) {																// вывод значений полей в виде строк
			status = rs.getString(1); 
			datetime_status=rs.getString(2);	
		}
		SQL=null;
		rs.close();
		ps.close();
		
		//_________________________________
		search_table_where_play(conn,id);
		//____________________________________
		if(id_table_game==0){
			st_p="046";
		}else{
		search_table_by_id_table(conn,id_table_game);
		st_p="У клиента статус "+status+".Он изменен в "+datetime_status+" за "+table_table+" столом.";
		System.out.println("У клиента статус "+status+".Он изменен в "+datetime_status+" за "+table_table+" столом.");}
	}catch(Exception e){
		st_p="003";
		System.out.println("Не найден статус клиента!");
	e.printStackTrace();}	
	return st_p;
}

//	
	public static int search_table_where_play(Connection conn,int id){
		try{
	SQL = "SELECT `Table_id_table` FROM Game WHERE (`Clients_id_client` = ? AND `start`=? ) ";
	ps = conn.prepareStatement(SQL);
	ps.setInt(1,id);
	ps.setInt(2,1);
	rs = ps.executeQuery();			    										 //выполнение запроса

	rs.last() ;															// вывод значений полей в виде строк
		id_table_game = rs.getInt(1); 
		System.out.println(id_table_game);
	SQL=null;
	rs.close();
	ps.close();
		}catch(Exception e){
			id_table_game=0;
			System.out.println("Не найден стол!");
  	e.printStackTrace();}
		return id_table_game;
	}
	
//находим стол по id_table
	public static String search_table_by_id_table(Connection conn,int id_table_game){
		try{
	//____________________________________
	SQL = "SELECT `Table` FROM `Table` WHERE `id_table` = ?  ";
	ps = conn.prepareStatement(SQL);
	ps.setInt(1,id_table_game);
	rs = ps.executeQuery();			    										 //выполнение запроса

	while (rs.next()) {																// вывод значений полей в виде строк
	  table_table = rs.getString(1); 														
	}
	SQL=null;
	rs.close();
	ps.close();
		}catch(Exception e){
			table_table="Стол не найден!";
			System.out.println("Не найден стол!");
  	e.printStackTrace();}
		return table_table;
	}
//***************
//	если	нажата pause или free	
//поиск id стола,за которым играл клиент до pause или free
	public static int search_table(Connection conn,int id){
		try{
			SQL = "SELECT `Table_id_table` FROM Game WHERE (`Clients_id_client` = ? ) ";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1,id);
			rs = ps.executeQuery();			    										 //выполнение запроса
	 
			rs.last() ;															// вывод значений полей в виде строк

				id_table_game = rs.getInt(1); 
				System.out.println(id_table_game);

			SQL=null;
			rs.close();
			ps.close();
		}catch(Exception e){
			id_table_game=0;
			System.out.println("Не найдена регистрация игры!");
  	e.printStackTrace();}
		return id_table_game;
	}
	
//считаем время игры
	private static int timer(Connection conn,String datetime_status){
		try{	
			long epoch1 = System.currentTimeMillis()/1000;  //время сейчас
			long epoch = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime_status).getTime() / 1000; //конвертируем полученное время в UTC
		//	System.out.println("UNIX время на данный момент "+epoch);
		//	System.out.println("UNIX время зарегистрированное "+epoch1);
			
			timer = (int) (epoch1-epoch);   //расчет времени
		//	System.out.println("timer = "+timer);
		}catch(Exception e){e.printStackTrace();}
		return timer;
	}
	
//меняем статус на free или pause 
	private static String status_new_free_pause(Connection conn, int id,int timer,int id_table_game,String new_status) throws SQLException {
	try{
	//__________________изменяем статус в Status _________________
			SQL = "UPDATE Status SET `status`=?  WHERE `Clients_id_client` = ?  ";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, new_status);
			ps.setInt(2,id);
			ps.execute();			    										 //выполнение запроса		   
	
			SQL=null;
			rs.close();
			ps.close();
	
			System.out.println("Статус изменен на "+new_status+".");
	
//__________________добавляем запись в Game _________________
	
			SQL = "INSERT INTO `Game`(`Clients_id_client`,`Table_id_table`,`start`) VALUE (?,?,?)";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1,id);
			ps.setInt(2,id_table_game);
			ps.setInt(3,0);
			ps.execute();
	
//__________________добавляем запись в Game_Table _________________
		//	System.out.println("id="+id+" id_table="+id_table+" timer="+timer);
			SQL = "INSERT INTO `Game_table`(`Clients_id_client`,`Table_id_table`,`Timer`) VALUE (?,?,?)";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1,id);
			ps.setInt(2,id_table_game);
			ps.setInt(3,timer);
			ps.execute();
			st_n_f_p="040";
			System.out.println("Клиент вышел с игры!!!!!!");
			}catch (Exception e) {
				st_n_f_p="047";
				System.out.println("Не удалось вывести клиента с игры!");
			e.printStackTrace();}
	return st_n_f_p;
		}

//FRIEND
//определяем friend
	 private static int what_friend (int id,Connection conn) throws SQLException {
		 try{ 
				SQL = "SELECT `Clients_id_client` FROM Clients WHERE `id_client` = ? ";
				ps = conn.prepareStatement(SQL);
				ps.setInt(1,id);
				rs = ps.executeQuery();			    										 //выполнение запроса
	   
				while (rs.next()) {																// вывод значений полей в виде строк
					what_friend = rs.getInt(1); 
				}
			System.out.println("id друга - "+what_friend);		 

				SQL=null;
				rs.close();
				ps.close();			 
		
		 }catch (Exception e){
			 what_friend=0;
			 System.out.println("Ошибка при поиске друга!");}
			
		 return what_friend;
	 }	
	
	 private static String friend_timer (int what_friend,int id,Connection conn,int timer,int id_table) throws SQLException {
		 if (what_friend != 0){
			 try{
			//__________________добавляем запись в Game_Table _________________
				SQL = "INSERT INTO `Game_table`(`Clients_id_client`,`Table_id_table`,`Timer`) VALUE (?,?,?)";
				ps = conn.prepareStatement(SQL);
				ps.setInt(1,what_friend);
				ps.setInt(2,id_table);
				ps.setInt(3,timer);
				ps.execute();
				}catch (Exception e) {
					fr_t="048";
					System.out.println("Не удалось добавить бонус другу!!!");
				e.printStackTrace();}
			}	
		 return fr_t;
	 }
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
