import java.util.Scanner;
import java.util.Random;

public class BlackJack{
  public static void main(String[] args) {
    int draw;
    do{
      System.out.println("カードを引きますか？ yes:0/no:1");
      Scanner scan = new Scanner(System.in);
      draw =scan.nextInt();
      if (draw == 0){
        System.out.println("カードを引きます");
        deal();

      } else if(draw == 1){
        System.out.println("カードを引きません");
      }
      else {
        System.out.println("もう一度お願いします");
      }
    }while(draw != 1);
  }
  private static void deal(){
    Random rnd1 = new Random();
    int ran1 = rnd1.nextInt(52);
    int number = ran1 % 13;
    int mark_num = ran1 % 4;
    String mark[] = {"ハート", "クラブ", "ダイヤ","スペード"};
    System.out.println(mark[mark_num ]+":"+number);
  }
}

