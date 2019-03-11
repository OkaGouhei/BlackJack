import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class BlackJack{
  public static void main(String[] args) {
    //1デッキのカードを用意する
    int card_size = 52;
    boolean[] card = new boolean[card_size];
    for(int i =0;i < card_size;i++){
      card[i] =true;
    }

    //player のカードが入るListを用意する
    List<Integer> player_card = new ArrayList<Integer>();
    System.out.println("playerのカード");
    deal(card,player_card);
    deal(card,player_card);
    show_card(player_card);

    //dealer のカードが入るListを用意する
    List<Integer> dealer_card = new ArrayList<Integer>();
    System.out.println("dealerのカード");
    deal(card,dealer_card);
    deal(card,dealer_card);
    dealer_show_card(dealer_card);

    // System.out.println("現在のカードは");
    // for(int i =0 ; i < player_card.size() ; i++){
    //     current_card(player_card.get(i));
    // }

    int draw;
    Scanner scan = new Scanner(System.in);
    do{
      System.out.print("カードを引きますか？ (yes 0/no 1)");
      // Scanner scan = new Scanner(System.in);
      draw =scan.nextInt();
      if (draw == 0){
        System.out.println("カードを引きます");
        current_card(deal(card,player_card));//引いたカードを表示
      } else if(draw == 1){
        System.out.println("カードを引きません");
      }
      else {
        System.out.println("もう一度お願いします");
      }
    }while(draw != 1 && point_card(player_card) <= 21);//1 を選択しない限り繰り返します

    scan.close();//scanner は閉じる習慣をつける
    //現在のカードを見せる
    System.out.println("playerのカードは");
    show_card(player_card);
    if (point_card(player_card) > 21){
      System.out.println("playerはバーストしました");
    }else{
      System.out.println("playerのポイントは"+point_card(player_card));
    }
    //dealer のカード 17以上になるまでカードを引く
    do{
      deal(card,dealer_card);
    }while(point_card(dealer_card) < 17);
    System.out.println("dealerのカードは");
    show_card(dealer_card);
    if (point_card(dealer_card) > 21){
      System.out.println("dealerはバーストしました");
    }else{
      System.out.println("dealerのポイントは"+point_card(dealer_card));
    }

    //勝敗を決定する
    if(point_card(player_card) > 21){
      System.out.println("dealer の勝ちです");
    }else if (point_card(dealer_card)>21 || point_card(player_card)> point_card(dealer_card)){
      System.out.println("player の勝ちです");
    }else if (point_card(player_card)<point_card(dealer_card)){
      System.out.println("dealer の勝ちです");
    }else{
      System.out.println("引き分けです");
    }
  }

  /**
  * deal メソッド
  * トランプ1セット（52枚)からランダムに1枚提示する
  *  number には 1-13 までが格納される
  *  mark_num には 0-3までが格納される
  */
  private static int deal(boolean[] card,List<Integer> player_card){
    Random rnd1 = new Random();
    int ran1;
    do{
      ran1 = rnd1.nextInt(52);
    }while(card[ran1] != true);
    card[ran1]= false;
    player_card.add(ran1) ;
    return ran1;
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
    String mark[] = {"❤️ ", "♣️ ", "♦️ ","♠️ "};
    System.out.println(mark[mark_num ]+":"+number);
  }
  /**
  * show_card メソッド
  * player dealerそれぞれの持ちカードを表示する
  *  number には 1-13 までが格納される
  *  mark_num には 0-3までが格納される
  */
  private static void show_card(List<Integer> card){
    for(Integer i : card ){//拡張 for文
      int number = i % 13 + 1;
      int mark_num = i % 4;
      String mark[] = {"❤️ ", "♣️ ", "♦️ ","♠️ "};
      System.out.println(mark[mark_num ]+":"+number);
    }
  }

  /**
  * dealer_show_card メソッド
  * dealer の持ちカードを表示する
  * ただし Hole Card は表示しない
  *  number には 1-13 までが格納される
  *  mark_num には 0-3までが格納される
  */
  private static void dealer_show_card(List<Integer> card){
    System.out.println("Hole Card");
    for(int i =1 ; i < card.size() ; i++){
      int number = card.get(i) % 13 + 1;
      int mark_num = card.get(i) % 4;
      String mark[] = {"❤️ ", "♣️ ", "♦️ ","♠️ "};
      System.out.println(mark[mark_num ]+":"+number);
    }
  }
  /**
  * point_card メソッド
  * 持ちカードのポイントを表示する
  * とりあえずAは1とカウント(後で場合分けして修正)
  * 返り値はポイント数
  */
  private static int point_card(List<Integer> card){
    int point = 0;
    for(int i =0 ; i < card.size() ; i++){
      int mark_num =  card.get(i) % 13 + 1;
      if(mark_num < 10){
        point += mark_num;
      }else{
        point += 10;
      }
    }
    return point;
  }
}




