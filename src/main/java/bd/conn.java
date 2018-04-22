package bd; // Пакет (package) представляют собой набор классов, объединённых по смыслу.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/* Подключем библиотеки необходимиые для работы с базами данных */
/* Подключем библиотеки необходимиые для работы с массивами */

/* Класс conn обеспечивает все необходимые взаимодейсвия с бд
 * Класс с модификатором доступа public доступен всем классам и обьектам */
public class conn {
	/* Класс Connection беспечивает соединение с бд */
	public static Connection conn;
	//Для использования SQL запросов существуют 3 типа объектов:
    //1.Statement: используется для простых случаев без параметров
	public static Statement statmt;
	/* ResultSet обеспечивает работу с результатами запросов */
	//resSet это указатель на первую строку с выборки
	public static ResultSet resSet;
	
	
	// --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
	public void Conn() throws ClassNotFoundException, SQLException 
	   {
		   conn = null;
		 //Загружаем драйвер
		   Class.forName("org.sqlite.JDBC");
		 //Создаём соединение
		  conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/dbBilet.s3db");
		   System.out.println("База Подключена!");
		   statmt = conn.createStatement();
	   }
	
	// --------Заполнение таблиц--------
	//Добавление записи в таблицу Disciplines
		public void AddDisciplin(Integer IdDisciplin,String NameDiscipline) throws SQLException
		{
			// Вставить запись в таблицу Disciplines
			statmt.execute("INSERT INTO [Disciplines] ([IdDiscipline], [NameDiscipline]) VALUES ("+IdDisciplin+", '"+NameDiscipline+"');");
			// В результате успешного выполнения вставки выведем в консоль сообщение
			System.out.println("Таблица Disciplin заполнена");
		}
		 // аналогичным образом организуем заполнение остальных таблиц
		//Добавление записи в таблицу Groups
		public void AddGroup(Integer IdGroups,String NameGroup) throws SQLException
		{
			// Вставить запись в таблицу Groups
			  statmt.execute("INSERT INTO Groups (IdGroups, NameGroup) VALUES ("+IdGroups+", '"+NameGroup+"');");
			// В результате успешного выполнения вставки выведем в консоль сообщение
			   System.out.println("Таблица Groups заполнена");
		}
		//Добавление записи в таблицу Questions
		public void AddQuestion(Integer IdQuestion,Integer IdDiscipline,String TextQuestion) throws SQLException
		{
			// Вставить запись в таблицу Questions
			  statmt.execute("INSERT INTO Questions (IdQuestion, IdDiscipline, TextQuestion) "
			  		+ "VALUES ("+IdQuestion+", "+IdDiscipline+", '"+TextQuestion+"');");
			// В результате успешного выполнения вставки выведем в консоль сообщение
			   System.out.println("Таблица Questions заполнена");
		}
		//Добавление записи в таблицу Tickets
		public void AddTicket(Integer IdTicket,Integer IdDiscipline,Integer IdGroup,Integer IdQuestion,Integer TicketNumber) throws SQLException
		{
			// Вставить запись в таблицу Tickets
			  statmt.execute("INSERT INTO Tickets (IdTicket, IdDiscipline, IdGroup, IdQuestion,TicketNumber) "
			  		+ "VALUES ("+IdTicket+", "+IdDiscipline+", "+IdGroup+", "+IdQuestion+" , "+TicketNumber+");");
			
			   System.out.println("Таблица Tickets заполнена");
		}
		
		// --------Удаление--------
		//Удаление записи из таблици Disciplines
				public void DelDisciplin(Integer IdDisciplin,String NameDiscipline) throws SQLException
				{
					// Удалить запись из таблици Disciplines
					  statmt.execute("DELETE FROM Disciplines WHERE NameDiscipline='"+NameDiscipline+"' OR IdDiscipline="+IdDisciplin+";");
					// Удалить запись из связанных таблиц Tickets и Questions
					  statmt.execute("DELETE FROM Tickets WHERE IdDiscipline="+IdDisciplin+";");
					  statmt.execute("DELETE FROM Questions WHERE IdDiscipline="+IdDisciplin+";");
					  System.out.println("Запись Discipline удалена ");
				}
				//Аналогичным образом организуем удаление из других таблиц
				//Удаление записи из таблици Groups
				public void DelGroup(Integer IdGroups,String NameGroup) throws SQLException
				{
					  statmt.execute("DELETE FROM Groups WHERE IdGroups="+IdGroups+" OR NameGroup='"+NameGroup+"';");
					  statmt.execute("DELETE FROM Tickets WHERE IdGroup="+IdGroups+";");
					  System.out.println("Запись Group удалена ");
				}
				//Удаление записи из таблици Questions
				public void DelQuestion(Integer IdQuestion,String TextQuestion) throws SQLException
				{
					  resSet =  statmt.executeQuery("SELECT * FROM Questions WHERE TextQuestion='"+TextQuestion+"';");
					  String s=resSet.getString("IdQuestion");
					  statmt.execute("DELETE FROM Questions WHERE"
					  		+ " IdQuestion="+IdQuestion+" OR  TextQuestion='"+TextQuestion+"';");
					  statmt.execute("DELETE FROM Tickets WHERE IdQuestion="+s+";");
					  System.out.println("Запись Question удалена ");
				}
				//Удаление записи из таблици Tickets
				public void DelTicket(Integer IdTicket) throws SQLException
				{
					  statmt.execute("DELETE FROM Tickets WHERE "
					  		+ "IdTicket="+IdTicket+";");
					  System.out.println("Запись Tickets удалена ");
				}
				//Удаление записи из таблици Tickets 
				public void DelTicketQuestion(Integer IdTicket,Integer IdQuestion) throws SQLException
				{
					  statmt.execute("DELETE FROM Tickets WHERE "
					  		+ "IdTicket="+IdTicket+" AND IdQuestion="+IdQuestion+";");
					
					  System.out.println("Запись Tickets удалена ");
				}
				//Удаление записи из таблици Tickets 
				public void DelTicketQuestion(Integer IdQuestion) throws SQLException
				{
					  statmt.execute("DELETE FROM Tickets WHERE IdQuestion="+IdQuestion+";");
					
					  System.out.println("Запись Tickets удалена ");
				}
				public void DelTicket(int idDisciplin, int IdGroup) throws SQLException {
					 statmt.execute("DELETE FROM Tickets WHERE "
						  		+ "IdDiscipline="+idDisciplin+" AND IdGroup="+ IdGroup+";");
						   System.out.println("Таблица Tickets заполнена");
				}
				

	// -------- Вывод таблицы--------
				//Вывод записей из таблици Disciplines 
	public ArrayList<String[]> ReadDisciplin() throws ClassNotFoundException, SQLException
	   {
		//result это указатель на первую строку с выборки
		resSet = statmt.executeQuery("SELECT * FROM Disciplines");
		// Результат запишем в массив
		ArrayList<String[]> list = new ArrayList<String[]>();
		//чтобы вывести данные мы будем использовать 
        //метод next() , с помощью которого переходим к следующему элементу
		while(resSet.next())
		{
			String[] s=new String[] {resSet.getString("IdDiscipline"),resSet.getString("NameDiscipline")};
			list.add(s);
		}	
		System.out.println("Таблица Disciplines выведена");
		// Возвращаем массив с результатами
		return list;
	    }
	// Аналогичным образом организовываем вывод записей из остальных таблиц
	// ReadGroup выводит в массив все группы
	public ArrayList<String[]> ReadGroup() throws ClassNotFoundException, SQLException
	   {
		resSet = statmt.executeQuery("SELECT * FROM Groups");
		ArrayList<String[]> list = new ArrayList<String[]>();
		while(resSet.next())
		{
			String[] s=new String[] {resSet.getString("IdGroups"),resSet.getString("NameGroup")};
			list.add(s);
		}	
		
		System.out.println("Таблица Groups выведена");
		return list;
	    }
	// ReadQuestion``1 выводит в массив все вопросы
	public ArrayList<String[]> ReadQuestion() throws ClassNotFoundException, SQLException
			   {
				resSet = statmt.executeQuery("SELECT * FROM Questions");
				ArrayList<String[]> list = new ArrayList<String[]>();
				while(resSet.next())
				{
					String[] s=new String[] {resSet.getString("IdQuestion"),resSet.getString("IdDiscipline"),resSet.getString("TextQuestion")};
					list.add(s);
				}	
				
				System.out.println("Таблица Questions выведена");
				return list;
			    }
	// ReadQuestionWHERE выводит в массив все вопросы по дисциплинам
			public ArrayList<String[]> ReadQuestionWHERE(int DisciplinId) throws ClassNotFoundException, SQLException
			   {
				resSet = statmt.executeQuery("SELECT * FROM Questions WHERE IdDiscipline="+DisciplinId);
				ArrayList<String[]> list = new ArrayList<String[]>();
				while(resSet.next())
				{
					String[] s=new String[] {resSet.getString("IdQuestion"),resSet.getString("IdDiscipline"),resSet.getString("TextQuestion")};
					list.add(s);
				}	
				
				System.out.println("Таблица Question выведена");
				return list;
			    }
			// ReadQuestionWHEREidQuestion выводит в массив вопрос по id
			public String[] ReadQuestionWHEREidQuestion(int IdQuestion) throws ClassNotFoundException, SQLException
			   {
				resSet = statmt.executeQuery("SELECT * FROM Questions WHERE IdQuestion="+IdQuestion);
				String[] list=new String[] {resSet.getString("IdQuestion"),resSet.getString("IdDiscipline"),resSet.getString("TextQuestion")};
				System.out.println("Таблица Questions выведена");
				return list;
			    }
			// ReadTicket выводит в массив все билеты
			public ArrayList<String[]> ReadTicket() throws ClassNotFoundException, SQLException
			   {
				resSet = statmt.executeQuery("SELECT * FROM Tickets");
				ArrayList<String[]> list = new ArrayList<String[]>();
				while(resSet.next())
				{
					String[] s=new String[] {resSet.getString("IdTicket"),resSet.getString("IdDiscipline"),resSet.getString("IdGroup"),resSet.getString("IdQuestion")};
					list.add(s);
				}	
				System.out.println("Таблица Tickets выведена");
				return list;
			    }
			// ReadTicketWHERE выводит в массив все билеты по определенной группе 
			public ArrayList<String[]> ReadTicketWHERE(int DisciplinId,int GroupId) throws ClassNotFoundException, SQLException
			   {
				resSet = statmt.executeQuery("SELECT * FROM Tickets WHERE IdDiscipline="+DisciplinId+" AND IdGroup="+GroupId);
				ArrayList<String[]> list = new ArrayList<String[]>();
				while(resSet.next())
				{
					String[] s=new String[] {resSet.getString("IdTicket"),resSet.getString("IdDiscipline"),
							resSet.getString("IdGroup"),resSet.getString("IdQuestion"),resSet.getString("TicketNumber")};
					list.add(s);
				}	
				System.out.println("Таблица выведена");
				return list;
			    }
		// --------Закрытие--------
		public void CloseDB() throws ClassNotFoundException, SQLException
		   {
			//закрываем соединение
			conn.close();
			resSet.close();
			System.out.println("Соединения закрыты");
		   }
}
