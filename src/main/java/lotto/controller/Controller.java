package lotto.controller;

import lotto.domain.user.LottoGenerator;
import lotto.domain.user.User;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Controller {
  private User user;
  
  private void buyLotto() {
    user = new User(LottoGenerator.generateLottos(inputMoney()));
  }
  
  private int inputMoney() {
    String inputMoney = readLine();
    return convertMoneyTypeStringToInt(inputMoney);
  }
  
  private int convertMoneyTypeStringToInt(String inputMoney) {
    return Integer.parseInt(inputMoney);
  }
}
