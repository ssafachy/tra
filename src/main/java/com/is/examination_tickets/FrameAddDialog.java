package com.is.examination_tickets; // Пакет (package) представляют собой набор классов, объединённых по смыслу.
import java.awt.*; /* AWT – работа с цветами, шрифтами, отрисовка графических примитивов, менеджеры компоновки и т.д.
* Звездочка означает, что, если Java-транслятору потребуется какой-либо класс, для которого пакет не указан явно, он должен просмотреть все содержимое пакета */
import javax.swing.*; /* Swing это набор для создания богатого графического интерфейса пользователя (GUI) */
import java.awt.event.*; /* В пакете java.awt.event определены интерфейсы слушателей для каждого из определенных в нем типов событий (например, для событий MouseEvent определено два интерфейса слушателей: MouseListener и MouseMotionListener) */

public class FrameAddDialog extends JDialog {
/* public - модификатор доступа. Класс FrameAddDialog доступен везде
 * FrameAddDialog - класс реализует диологове окно обеспечивающее необходимое общение с пользователем
 * extends - это наследование класса
 * JDialog - диалоговые окна */
	private static final long serialVersionUID = 1L;
	/* уникальный номер версии данного класса. */
	JTextPane textPane = new JTextPane();
	/* JTextPane - элемент интерфейса способный поддерживать работу с текстом
	 * new создает экземпляр указанного класса и возвращает ссылку на него
	 *  JTextPane()- конструктор класса */
	String text="";
	/* Создание переменной типа строка и запись в нее пустой строки*/
	public FrameAddDialog() { /*конструкто класса */
		setTitle("Добавление"); /*Установим запись "Добавление" в шапку окна*/
		setBounds(100, 100, 380, 151);/* Установка размера рабочего пространства */
		setModal(true); /* Определяет, должно ли это диалоговое окно быть модальным 
		В нашем случае окно модальное, значит приложение будет ждать завершения работы этого окна*/
		getContentPane().setLayout(new BorderLayout());
		{
			/* getContentPane - обращение к панели
			 * setLayout - метод установки менеджера расположения 
			 * BorderLayout -  – это один из layout Java Swing, который используется для расположения компонентов на контейнере.*/
			JLabel label = new JLabel("\t\tВведите название");
			/* С помощью JLabel можно показать текст с иконкой. */
			label.setHorizontalAlignment(SwingConstants.CENTER); //Расположение текста по центру
			getContentPane().add(label, BorderLayout.NORTH);// Добавление лейбла на панель в верхнюю часть
		}
		{
			getContentPane().add(textPane, BorderLayout.CENTER);// Добавление textPane на панель по центру
		}
		{
			JPanel buttonPane = new JPanel();
			/* Панель на которой располагаются кнопки */
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			/* Расположим кнопки справа*/
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				/* добавим кнопки на панель */
				JButton okButton = new JButton("OK");
				/* Инициализация кнопкп "ОК" */
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						/* Добавляем к кнопке прослушиватель события "нажатие на кнопку" 
						 * Релизуем метод реакции на это событие */
						text=textPane.getText();/* получаем содержимое textPane и передаем в переменную text  */
						setVisible(false); /* Делае окно невидимым для пользователя */
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				/* Добавляем кнопку на панель кнопок */
				getRootPane().setDefaultButton(okButton);
			}
			/*Аналогичным способом созжаем кнопку "Cancel"*/
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
