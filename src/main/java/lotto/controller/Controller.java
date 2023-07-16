package lotto.controller;

import lotto.domain.Lotto.Lotto;
import lotto.domain.user.User;

import java.util.List;

import static lotto.domain.Lotto.LottoGenerator.generateLottos;
import static lotto.domain.Lotto.MoneyGenerator.generateMoney;

public class Controller {
  private User user;
  
  public void run() {
    System.out.println("구입금액을 입력해 주세요.");
    buyLotto();
    List<Lotto> lottos = user.getLottos();
    System.out.println(lottos.size() + "개를 구매했습니다.");
    for (Lotto lotto : lottos) {
      System.out.println(lotto);
    }
  }
  
  private void buyLotto() {
    user = new User(generateLottos(generateMoney()));
  }
}
