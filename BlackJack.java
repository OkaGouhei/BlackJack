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

    int draw;
    Scanner scan = new Scanner(System.in);
    do{
      System.out.print("カードを引きますか？ (yes 0/no 1)");
      // Scanner scan = new Scanner(System.in);
      draw =scan.nextInt();
      if (draw == 0){
        System.out.println("カードを引きます");
        card_no_mark(deal(card,player_card));//引いたカードを表示
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
    while(point_card(dealer_card) < 17){
      deal(card,dealer_card);
    }
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
  *  ran1 には 0-51 までが代入される
  *  player_card には 0-51までが格納される
  */
  private static int deal(boolean[] card,List<Integer> player_card){
    Random rnd1 = new Random();
    int ran1;
    do{
      ran1 = rnd1.nextInt(52);//0~51までの数字がランダムにran1に格納される
    }while(card[ran1] != true);
    card[ran1]= false;
    player_card.add(ran1) ;
    return ran1;
  }
  /**
  * card_no_mark メソッド
  * トランプのナンバー、マークを表示する
  *  number には 1-13 までが格納される
  *  mark_num には 0-3までが格納される
  */
  private static void card_no_mark(int card_no_mark){
    int number = card_no_mark % 13 +1;
    int mark_num = card_no_mark % 4;
    String mark[] = {"❤️ ", "♣️ ", "♦️ ","♠️ "};
    System.out.println(mark[mark_num ]+":"+number);
  }
  /**
  * show_card メソッド
  * player dealerそれぞれの持ちカードを表示する
  */
  private static void show_card(List<Integer> show_card){
    for(int i =0; i < show_card.size() ; i++){
      card_no_mark(show_card.get(i));
    }
  }

  /**
  * dealer_show_card メソッド
  * dealer の持ちカードを表示する
  * ただし Hole Card は表示しない
  */
  private static void dealer_show_card(List<Integer> dealer_show_card){
    System.out.println("Hole Card");
    for(int i =1; i < dealer_show_card.size() ; i++){//i = 1 と
      card_no_mark(dealer_show_card.get(i));
    }
  }
  /**
  * point_card メソッド
  * 持ちカードのポイントを表示する
  * とりあえずAは1とカウント(後で場合分けして修正)
  * 返り値はポイント数
  */
  private static int point_card(List<Integer> point_card){
    int point = 0;
    for(Integer i : point_card){
      int mark_num =  i % 13 + 1;
      if(mark_num < 10){
        point += mark_num;
      }else{
        point += 10;
      }
    }
    return point;
  }
}




