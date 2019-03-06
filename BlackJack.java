import java.util.Scanner;
import java.util.Random;

public class BlackJack{
  public static void main(String[] args) {
    int card_size = 52;
    boolean[] card = new boolean[card_size];
    for(int i =0;i < card_size;i++){
      card[i] =true;
    }

    //現在のカードの状況を表示
    // for(int i =0;i < card_size;i++){
    //   System.out.println( card[i] );
    // }

    int draw;
    do{
      System.out.println("カードを引きますか？ yes:0/no:1");
      Scanner scan = new Scanner(System.in);
      draw =scan.nextInt();
      if (draw == 0){
        System.out.println("カードを引きます");
        deal(card);
        System.out.println("現在のポイントは"+point_card(card,card_size));

      } else if(draw == 1){
        System.out.println("カードを引きません");
      }
      else {
        System.out.println("もう一度お願いします");
      }
    }while(draw != 1 && point_card(card,card_size) <= 21);//1 を選択しない限り繰り返します

    //現在のカードを見せる
    System.out.println("現在のカードは");
    for(int i =0 ; i < card_size ; i++){
      if (card[i] == false){
        current_card(i);
      }
    }
    System.out.println("現在のポイントは"+point_card(card,card_size));
  }
  /**
  * deal メソッド
  * トランプ1セット（52枚)からランダムに1枚提示する
  *  number には 1-13 までが格納される
  *  mark_num には 0-3までが格納される
  */
  private static void deal(boolean[] card){
    Random rnd1 = new Random();
    int ran1;
    do{
      ran1 = rnd1.nextInt(52);
    }while(card[ran1] != true);
    card[ran1]= false;
    current_card(ran1);
  }
  /**
  * current_card メソッド
  * トランプのナンバー、マークを表示する
  *  number には 1-13 までが格納される
  *  mark_num には 0-3までが格納される
  */
  private static void current_card(int card_no){
    int number = card_no % 13 + 1;
    int mark_num = card_no % 4;
    String mark[] = {"ハート", "クラブ", "ダイヤ","スペード"};
    System.out.println(mark[mark_num ]+":"+number);
  }
  /**
  * point_card メソッド
  * 持ちカードのポイントを表示する
  * とりあえずAは1とカウント(後で場合分けして修正)
  * 返り値はポイント数
  */
  private static int point_card(boolean[] card,int card_size){
    int point = 0;
    for(int i =0 ; i < card_size ; i++){
      if (card[i] == false){
        int mark_num = i % 13 + 1;
        if(mark_num < 10){
          point += mark_num;
        }else{
          point += 10;
        }
      }
    }
    return point;
  }
}


