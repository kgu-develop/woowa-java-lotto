package lotto.controller;

import lotto.domain.user.User;

import static lotto.domain.Lotto.LottoGenerator.generateLottos;
import static lotto.domain.Lotto.MoneyGenerator.generateMoney;

public class Controller {
  private User user;
  
  public void run() {
    System.out.println("구입금액을 입력해 주세요.");
    buyLotto();
  }
  
  private void buyLotto() {
    user = new User(generateLottos(generateMoney()));
  }
}
