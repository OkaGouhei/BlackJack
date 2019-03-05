import java.util.Scanner;
import java.util.Random;

public class BlackJack_1{
  public static void main(String[] args) {

    int i ;
    for (i = 3; i< 52 ; i = i+4){
      int number = i % 13 + 1;
      int mark_num = i % 4;
      String mark[] = {"ハート", "クラブ", "ダイヤ","スペード"};
      System.out.println(mark[mark_num ]+":"+number);
    }
  }
}

