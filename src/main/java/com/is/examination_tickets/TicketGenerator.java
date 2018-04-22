package com.is.examination_tickets;

//Пакет (package) представляют собой набор классов, объединённых по смыслу.
import java.util.ArrayList;
import java.util.Collections;

/* Подключаем библиотеки для работы с массивами и коллекциями */
public class TicketGenerator {
	/*
	 * Класс TicketGenerator будет генерировать список билетов public - модификатор
	 * доступа. Класс TicketGenerator доступен везде
	 */
	static ArrayList<ArrayList<String>> generator(ArrayList<String> questions, int numberOfTickets,
			int questionsInTickets) {
		/*
		 * static ArrayList<String> автоматически расширяемый массив.
		 * ArrayList<ArrayList<String> > автоматически расширяемый массив массивов.
		 * generator - метод который принимеает массив вопросов, количечесьво билетов,
		 * количесво вопросов в билете Данный метод генерирует билеты и возвращает из в
		 * виде двумерного массива строк
		 */

		ArrayList<ArrayList<String>> Ticket = new ArrayList<ArrayList<String>>();
		/*
		 * Создаем двумерный массив строк в который будем записывать сгенерированные
		 * билеы new создает экземпляр указанного класса и возвращает ссылку на него
		 */

		if (!check(numberOfTickets, questions.size(), questionsInTickets)) {
			// если требуемое число билетов меньше возможного то возвращаем сообщение об
			// этом
			ArrayList<String> tmp = new ArrayList<String>();
			tmp.add("Слишком мало вопросов для такого колличества билетов");
			Ticket.add(tmp);
			return null;
		}

		for (int i = 0; i < numberOfTickets; i++) {
			boo(questions, questionsInTickets, Ticket);
			/*
			 * В цикле выполняем фунцию "boo" - генерация очередного билета Передаем этой
			 * функцтт вопросы, колличесво вопросов в билете и список билетов Этот метод сам
			 * будет заполнять список билетов
			 */
		}
		return Ticket;
		/*
		 * В конце работы этой функции имеем массив билетов и возвращаем его туда где
		 * метод был вызван
		 */
	}

	private static void boo(ArrayList<String> questions, int questionsInTickets, ArrayList<ArrayList<String>> ticket) {
		/*
		 * private - поля и методы класса с таким модификатором доступа доступны только
		 * внутри этого класса. фунцию "boo" - генерация очередного билета Передаем этой
		 * функцтт вопросы, колличесво вопросов в билете и список билетов Этот метод сам
		 * будет заполнять список билетов
		 */
		ArrayList<String> tmp = new ArrayList<String>();
		/* Создаем временный массив в котором будем хранить текущий билет */
		for (int j = 0; j < questionsInTickets; j++) {
			/* Запускаем цикл по колличесву вопросов в билете */
			String s = foo(tmp, questions);
			/*
			 * Метод "foo" генерирует очередной вопрос в билете результат работы метода
			 * запишем строку
			 */
			tmp.add(s);
			/* Добавим сгенерированый вопрос в билет */
		}
		Collections.sort(tmp);
		/* Отсортирум вопросы в билете для удобства */
		if ((ticket != null) && (ticket.contains(tmp)))
			/* проверка не пустли билет и содержится ли в нем сгенерированный вопрос */
			boo(questions, questionsInTickets, ticket);
		/*
		 * если вопрос есть в билете то рекурсивно вызываем метод boo , генерируем снова
		 */
		else
			/* если список пуст или этого вопроса в билете нет то добавляем его в массив */
			ticket.add(tmp);
	}

	private static String foo(ArrayList<String> tmp, ArrayList<String> questions) {
		/*
		 * Метод foo мпринимает билет в которрый нужно сгенерировать вопрос и список
		 * вопросов
		 */
		int size = questions.size();
		/* Вычисляем колличество вопросов в списке вопроса */
		int num = (int) (Math.random() * size);
		/*
		 * Генерируе случайное число в диапазоне от 0 до числа вопросов в списке
		 * вопросов
		 */
		String ret = questions.get(num);
		/*
		 * получаем вопрос из списка , индекс попроса в массиве это число
		 * сгенерированное на предидущей строке
		 */
		if ((tmp != null) && (tmp.contains(ret))) {
			/* проверяем имеется ли этот вопрос в билете */
			ret = foo(tmp, questions);
			/*
			 * если этот вопрос уже есть то рекурсивно вызываем метод foo , генерируем
			 * вопрос снова
			 */
		}
		/*
		 * если вопроса в списке нет то завершаем метод возвращаем результат
		 */
		return ret;
	}

	static boolean check(int numberOfTickets, int questions, int questionsInTickets) {
		// метод check проверяет превышаетли заданное число билетов , возможное
		int possibleNumberOfTickets;
		// possibleNumberOfTickets переменная для хранения возможного числа билетов
		possibleNumberOfTickets = factorial(questions)
				/ (factorial(questionsInTickets) * factorial(questions - questionsInTickets));
		// расчитаем возможное число билетов
		// n! / (r!(n-r)!)

		return (possibleNumberOfTickets >= numberOfTickets);
		// возвращаем значение boolean (true||false )в зависимости от выполнения
		// выражения
	}

	private static int factorial(int n) {// метод factorial расчитывает факториал числа n
		if (n == 0)
			return 1;
		return n * factorial(n - 1);
	}
}