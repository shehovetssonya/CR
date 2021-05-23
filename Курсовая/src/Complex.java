import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Complex {

    private JLabel balanceLabel ;
    // создаём матрицу, в которой будут выполняться все действия игры
    int[][] a1 = {
            {0,0,0},
            {0,0,0},
            {0,0,0}
    };
    // массив кнопок, отражающий все действия, произведённые в матрице
    JButton a2[] = new JButton[9];
    JFrame frame;// рамка
    JPanel panelLabel;// панель
    JLabel label;// строка

    public Complex()
    {
        frame = new JFrame("2048");// инициализируем рамку
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelLabel=new JPanel();// инициализируем панель
        panelLabel.setPreferredSize(new Dimension(50,20));// размер панели
        frame.add(panelLabel, BorderLayout.CENTER);// добовляем панель в рамку

        label = new JLabel("Конец игры");// инициализируем строку
        panelLabel.add(label, BorderLayout.CENTER);// присваеваем к панели
        label.setVisible(false);// пока делаем строку не видемой

        frame.addKeyListener(new KeyListener() { // слушатель клавиатуры
                                 public void keyTyped(KeyEvent e) { }// Пустая функция, нужна, чтобы реализовать интерфейс
                                 @Override
                                 public void keyPressed(KeyEvent e)//Функция, которая обрабатывает нажатия клавиши

                                 {
                                     if(e.getKeyCode() == KeyEvent.VK_UP){ // действия при нажатия кнопки
                                         int str,sto,i;
                                         for (i=0;i<2;i++)
                                             for (sto=0;sto<3;sto++)
                                                 for (str=0;str < 2;str++){
                                                     if (a1[str][sto]==0){
                                                         a1[str][sto]=a1[str+1][sto];
                                                         a1[str+1][sto]=0;
                                                     }
                                                     if (a1[str][sto]== a1[str+1][sto]){
                                                         a1[str][sto]=a1[str][sto]+a1[str+1][sto];
                                                         a1[str+1][sto]=0;
                                                     }
                                                 }
                                         for (int a =0 ;!Filling(random(),random()) && a<5; a++);
                                         button();
                                         losing();
                                     }
                                     if(e.getKeyCode() == KeyEvent.VK_DOWN){
                                         int str,sto,i;
                                         for (i=0;i<2;i++)
                                             for (sto=0;sto<3;sto++)
                                                 for (str=2;str > 0;str--){
                                                     if (a1[str][sto]==0){
                                                         a1[str][sto]=a1[str-1][sto];
                                                         a1[str-1][sto]=0;
                                                     }
                                                     if (a1[str][sto]== a1[str-1][sto]){
                                                         a1[str][sto]=a1[str][sto]+a1[str-1][sto];
                                                         a1[str-1][sto]=0;
                                                     }
                                                 }
                                         for (int a =0 ;!Filling(random(),random()) && a<5; a++);
                                         button();
                                         losing();

                                     }
                                     if(e.getKeyCode() == KeyEvent.VK_LEFT){
                                         int str,sto,i;
                                         for (i=0;i<2;i++)
                                             for (str=0;str< 3;str++)
                                                 for (sto=0;sto<2;sto++){
                                                     if (a1[str][sto]==0){
                                                         a1[str][sto]=a1[str][sto+1];
                                                         a1[str][sto+1]=0;
                                                     }
                                                     if (a1[str][sto]== a1[str][sto+1]){
                                                         a1[str][sto]=a1[str][sto]+a1[str][sto+1];
                                                         a1[str][sto+1]=0;
                                                     }
                                                 }
                                         for (int a =0 ;!Filling(random(),random()) && a<5; a++);
                                         button();
                                         losing();
                                     }
                                     if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                                         int str,sto,i;
                                         for (i=0;i<2;i++)
                                             for (str=0;str< 3;str++)
                                                 for (sto=2;sto>0;sto--){
                                                     if (a1[str][sto]==0){
                                                         a1[str][sto]=a1[str][sto-1];
                                                         a1[str][sto-1]=0;
                                                     }
                                                     if (a1[str][sto]== a1[str][sto-1]){
                                                         a1[str][sto]=a1[str][sto]+a1[str][sto-1];
                                                         a1[str][sto-1]=0;
                                                     }
                                                 }
                                         for (int a =0 ;!Filling(random(),random()) && a<5; a++);
                                         button();
                                         losing();
                                     }
                                 }
                                 @Override
                                 public void keyReleased(KeyEvent e) {}//Функция, для обработки отжатия клавиши
                             }
        );
        JPanel btnPanel = new JPanel( new GridLayout(3,3));// создаем панель с матречным расположением элементов
        Font BigFontTR = new Font("TimesRoman", Font.BOLD, 30);//Тут все про шрифт

        // цикл для создания кнопок
        for (int i = 0; i < 9; i++){
            a2[i]= new JButton();
            a2[i].setEnabled(false);// чтобы было не возможно нажатие на кнопку
            a2[i].setPreferredSize(new Dimension(100, 100));
            btnPanel.add(a2[i]);
            a2[i].setFont(BigFontTR);// шрифт на кнопке
        }

        frame.add(btnPanel, BorderLayout.SOUTH);// расположения второй понели внизу
        frame.pack();// создает оптимальный размер окна под все элементы
        frame.setVisible(true);// влючение отоброжения окна
        Filling(random(),random());
        button();

    }
    // создаем рандом
    final int random () {
        return (int) (Math.random() * 3);
    }
    // добовления новой двойки в игру
    final boolean Filling(int x, int y) {
        if (a1[x][y]== 0){a1[x][y] = 2;return true;}
        else return false;
    }
    // метод для отоброжения матрицы на массиве кнопок и цвет кнопки
    final void button()
    {
        int i, j, k=0;
        JButton L [] = new JButton[11];
        for (i=0;i < 3;i++)
            for (j=0; j < 3; j++, k++){
                if(a1[i][j] > 0)
                {
                    if (a1[i][j]== 2) {
                        a2[k].setBackground(Color.decode("#f0f8ff"));
                    }
                    if (a1[i][j]== 4) {
                        a2[k].setBackground(Color.decode("#ffe5d4"));
                    }
                    if (a1[i][j]== 8) {
                        a2[k].setBackground(Color.decode("#ffdfd4"));
                    }
                    if (a1[i][j]== 16) {
                        a2[k].setBackground(Color.decode("#ffdfaa"));
                    }
                    if (a1[i][j]== 32) {
                        a2[k].setBackground(Color.decode("#ff9e81"));
                    }
                    if (a1[i][j]== 64) {
                        a2[k].setBackground(Color.decode("#d07684"));
                    }
                    if (a1[i][j]== 128) {
                        a2[k].setBackground(Color.decode("#b489ac"));
                    }
                    if (a1[i][j]== 256) {
                        a2[k].setBackground(Color.decode("#899ad5"));
                    }
                    if (a1[i][j]== 512) {
                        a2[k].setBackground(Color.decode("#4682B4"));
                    }
                    if (a1[i][j]== 1024) {
                        a2[k].setBackground(Color.decode("#B0C4DE"));
                    }
                    if (a1[i][j] >= 2048) {
                        a2[k].setBackground(Color.decode("#ADD8E6"));
                    }
                    a2[k].setText(Integer.toString(a1[i][j]));}
                else{
                    a2[k].setBackground(Color.WHITE);
                    a2[k].setText(" ");
                }
                a2[k].setForeground(Color.WHITE);
            }
    }
    // метод для проверки окончания игры
    final void losing(){
        int str,sto,i,s=0;
        for (i=0;i<2;i++){
            for (str=0;str< 3;str++)
                for (sto=2;sto>0;sto--){
                    if (a1[str][sto]== a1[str][sto-1] || a1[str][sto]==0){
                        s++;
                    }
                }
            for (str=0;str< 3;str++)
                for (sto=0;sto<2;sto++){
                    if (a1[str][sto]== a1[str][sto+1] || a1[str][sto]==0){
                        s++;
                    }
                }
            for (sto=0;sto<3;sto++)
                for (str=2;str > 0;str--){

                    if (a1[str][sto]== a1[str-1][sto] || a1[str][sto]==0){
                        s++;
                    }
                }
            for (sto=0;sto<3;sto++)
                for (str=0;str < 2;str++){
                    if (a1[str][sto]== a1[str+1][sto] || a1[str][sto]==0){
                        s++;
                    }
                }}
        if (s == 0){
            for (int k = 0; k < 9; k++){
                a2[k].setVisible(false);// отключаем отоброжения кнопки
            }
            label.setVisible(true);// делаем надпись видемой
        }
    }
}