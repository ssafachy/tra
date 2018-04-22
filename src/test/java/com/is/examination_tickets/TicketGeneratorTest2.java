package com.is.examination_tickets;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TicketGeneratorTest2 {

	// проверим колличество вопросов в каждом билете
	@Test
	public void testQuestionsInTicketsTrue() {
		// Входные данные
		// questions - список вопросов
		ArrayList<String> questions = new ArrayList<String>(
				Arrays.asList("question_1", "question_2", "question_3", "question_4", "question_5"));
		// колличество билетов 10
		int numberOfTickets = 10;
		// Колличество вопросов в билете - 2
		// questionsInTickets ожидаемое значение
		int questionsInTickets = 2;
		// realNumberOfTickets - колличество вопросов в билете, действительное значение
		// Пока что инициализируем нулем
		int realQuestionsInTickets = 0;
		// Генерируем список билетов
		ArrayList<ArrayList<String>> ticket = TicketGenerator.generator(questions, numberOfTickets, questionsInTickets);
		// Циклом пробегаемся по всем билетам
		for (ArrayList<String> t : ticket) {
			// Записываем действительное значение колличества вопросов в билете
			realQuestionsInTickets = t.size();
			// Сравниваем действительное значение с ожидаемым
			assertEquals(questionsInTickets, realQuestionsInTickets);
		}
	}

	// Проверим каждый билет на уникальность
	// Билеты в списке не должны повторяться
	@Test
	public void testTicketUnique() {
		// Входные данные
		// questions - список вопросов
		ArrayList<String> questions = new ArrayList<String>(
				Arrays.asList("question_1", "question_2", "question_3", "question_4", "question_5"));
		// Колличество билетов 10
		int numberOfTickets = 10;
		// Колличество вопросов в билете - 2
		int questionsInTickets = 2;
		// Генерируем список билетов
		ArrayList<ArrayList<String>> tickets = TicketGenerator.generator(questions, numberOfTickets,
				questionsInTickets);
		// Циклом пробегаемся по всем билетам
		for (int t1 = 0; t1 < tickets.size(); t1++)
			// Вложенным циклом пробигаемся по билетам следующими за текущим
			for (int t2 = t1 + 1; t2 < tickets.size(); t2++) {
				// count - счетчик повторений (действительное значение)
				int count = 0;
				// Циклом пробегаемся по вопросам в билете
				for (int q1 = 0; q1 < tickets.get(t1).size(); q1++)
					// сравниваем соответсвующие вопросы в разных билетах
					if (tickets.get(t1).get(q1) == tickets.get(t2).get(q1))
						// В случае совпадения увеличиваем счетчик
						count++;
				// assertTrue - Булевы утверждения
				// тест считается провальным если нашелось хотябы два билета с одинаковыми
				// вопросами
				assertTrue(count < questionsInTickets);
			}
	}
}
