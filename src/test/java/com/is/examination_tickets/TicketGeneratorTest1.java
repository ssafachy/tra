package com.is.examination_tickets;

/**Для использования, к проекту необходимо подключить библиотеку, которую можно взять отсюда.
 *  Далее импортируются необходимые классы аннотаций
 */
import org.junit.Test;
/**  
 * Импорт статических методов класса Assert 
 */
import static org.junit.Assert.*;
/** 
 * Подключаем библиотеки для работы с ArrayList и Arrays 
 */
import java.util.ArrayList;
import java.util.Arrays;

public class TicketGeneratorTest1 {

/**
 *проверим соответсвует ли колличество составленных билетов заданному 
 */
	@Test
	public void testNumberOfTicketsTrue() {
		/** Опишем вхолдные данные
		* questions - список вопросов
		 */
		ArrayList<String> questions = new ArrayList<String>(
				Arrays.asList("question_1", "question_2", "question_3", "question_4", "question_5"));
		/** 
		 * numberOfTickets - колличество билетов, ожидаемое значение
		 */
		int numberOfTickets = 8;
		/** 
		 * questionsInTickets - колличество вопросов в билете
		 */
		int questionsInTickets = 2;
		/** 
		 * realNumberOfTickets - колличество билетов, действительное значение
		 */
		/**
		 *  пока что инициализируем нулем
		   */
		int realNumberOfTickets = 8;
		/** 
		 * ticket - список билетов
		 TicketGenerator.generator - тестируемый метод*/
		ArrayList<ArrayList<String>> ticket = TicketGenerator.generator(questions, numberOfTickets, questionsInTickets);
		/** 
		 * Запишем в realNumberOfTickets действительное колличество билетов
		*/
		realNumberOfTickets = ticket.size();
		/** assertEquals-Утверждение эквивалентности
		* первый параметр (expected) - ожидаемое значение
		* Второй параметр (actual) - действительное значение контролируемого аргумента
		*/
		assertEquals(numberOfTickets, realNumberOfTickets);
		/**
		 *  тест считается успешным если значения эквивалентны
		*/
	}
}
