package com.is.examination_tickets;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TicketGeneratorTest3 {

	// Проверим кажды вопрос в билете уникальный
	// В билете не должго встретиться два или больше одинаковых вопроса
	@Test
	public void testQuestionsInTicketUnique() {
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
		// Пробегаемся циклом по каждому билету
		for (int t1 = 0; t1 < tickets.size(); t1++) {
			// count - счетчик повторений
			int count = 0;
			// Циклом пробегаемся по каждому вопросу в текущем билете
			for (int q1 = 0; q1 < tickets.get(t1).size(); q1++)
				// Вложенным циклом пробегаемся по каждому вопросу следущим за текущим
				for (int q2 = q1 + 1; q2 < tickets.get(t1).size(); q2++)
					// Сравниваем разные вопросы в одном билете
					if (tickets.get(t1).get(q1) == tickets.get(t1).get(q2))
						// Если нашлось совпадение то увеличиваем счетчик
						count++;
			// assertTrue - Булевы утверждения
			// тест считается провальным если нашелось хотябы одо совадение
			// Повторений в билете быть не должно
			assertTrue(count == 0);
		}
	}

	// Проверим уникальность билетов для разных групп
	@Test
	public void testTicketGroupUnique() {
		// Входные данные
		// questions - список вопросов
		ArrayList<String> questions = new ArrayList<String>(
				Arrays.asList("question_1", "question_2", "question_3", "question_4", "question_5"));
		// Колличество билетов 10
		int numberOfTickets = 10;
		// Колличество вопросов в билете - 2
		int questionsInTickets = 2;
		// Генерируем два списка билетов
		ArrayList<ArrayList<String>> ticket1 = TicketGenerator.generator(questions, numberOfTickets,
				questionsInTickets);
		ArrayList<ArrayList<String>> ticket2 = TicketGenerator.generator(questions, numberOfTickets,
				questionsInTickets);
		// assertFalse - Булевы утверждения, тест считется успешным если логическое
		// выражене ложно
		// Тест считается провальным если списки эквивалентны
		assertFalse(ticket1.toArray().equals(ticket2.toArray()));
	}

}
