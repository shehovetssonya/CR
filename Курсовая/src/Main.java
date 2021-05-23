import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        System.out.println("Выберете уровень игры: ");
        System.out.println("1.Легкий");
        System.out.println("2.Средний");
        System.out.println("3.Сложний");
        Scanner n = new Scanner(System.in);
        int N = n.nextInt();
        if ( N == 1){
            Easy app = new Easy();
        }
        if ( N == 2){
            Window app = new Window();
        }
        if ( N == 3){
            Complex app = new Complex();
        }
    }
}
