package lotto.controller;

import lotto.domain.Lotto.AnswerLotto;
import lotto.domain.Lotto.Lotto;
import lotto.domain.user.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static camp.nextstep.edu.missionutils.Console.readLine;
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
  
    System.out.println("당첨 번호를 입력해 주세요.");
    String answerLottoNumbers = readLine();
    System.out.println("보너스 번호를 입력해 주세요.");
    Integer bonusLottoNumber = Integer.valueOf(readLine());
    List<Integer> answerLotto = Stream.of(answerLottoNumbers.split(","))
      .map(Integer::valueOf).collect(Collectors.toList());
    new AnswerLotto(answerLotto, bonusLottoNumber);
  }
  
  private void buyLotto() {
    user = new User(generateLottos(generateMoney()));
  }
}
