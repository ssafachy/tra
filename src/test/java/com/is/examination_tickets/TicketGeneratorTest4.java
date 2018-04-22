package com.is.examination_tickets;

import static org.junit.Assert.*;

import org.junit.Test;

public class TicketGeneratorTest4 {

	// Тест проверки
	// Перед генерацией расчитывется сколько билетов возможно составить из
	// определенного списка вопросов
	// проверяется возможно ли сгенерировать заданное колличество вопросов.
	@Test
	public void testCheckTrue() {
		// Входные данные
		// Колличество билетов - 10
		int numberOfTickets = 10;
		// Колличество вопросов в билете - 2
		int questionsInTickets = 2;
		// Колличество вопросов для составления - 5
		int questions = 5;
		// check действительное значение
		boolean check;
		check = TicketGenerator.check(numberOfTickets, questions, questionsInTickets);
		// assertTrue - Булевы утверждения
		// Из 5 вопросов можно составить 10 билетов, значит check должен быть true
		assertTrue(check);
	}

	// Тест проверки
	// В качестве входных параметров используем граничное значение.
	@Test
	public void testCheckFalse() {
		// Входные данные
		// Колличество билетов - 11 (больше чем должно быть)
		int numberOfTickets = 11;
		int questionsInTickets = 2;
		int questions = 5;
		boolean check;
		// check действительное значение
		check = TicketGenerator.check(numberOfTickets, questions, questionsInTickets);
		// assertFalse - Булевы утверждения, тест успешний когда лог. утверждение ложно
		// Из 5 вопросов можно составить 10 билетов, а заданно 11 значит check должен
		// быть false
		assertFalse(check);
	}
}
