package lotto.controller;

import lotto.domain.Lotto.AnswerLotto;
import lotto.domain.Lotto.Lotto;
import lotto.domain.user.User;
import lotto.service.ResultService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static lotto.domain.Lotto.LottoGenerator.generateLottos;
import static lotto.domain.Lotto.MoneyGenerator.generateMoney;

public class Controller {
  private User user;
  private ResultService resultService;
  
  public Controller() {
    this.resultService = new ResultService();
  }
  
  public void run() {
    System.out.println("구입금액을 입력해 주세요.");
    int money = generateMoney();
  
    buyLotto(money);
  
    List<Lotto> lottos = user.getLottos();
    System.out.println(lottos.size() + "개를 구매했습니다.");
    for (Lotto lotto : lottos) {
      System.out.println(lotto);
    }
  
    System.out.println("당첨 번호를 입력해 주세요.");
    String answerNumbers = readLine();
    System.out.println("보너스 번호를 입력해 주세요.");
    int bonusLottoNumber = Integer.parseInt(readLine());
    List<Integer> answerLottoNumbers = Stream.of(answerNumbers.split(","))
      .map(Integer::valueOf).collect(Collectors.toList());
    AnswerLotto answerLotto = new AnswerLotto(answerLottoNumbers, bonusLottoNumber);
  
    Map<String, Integer> statistics = resultService.getStatistics(lottos, answerLotto.getAnswerNumbers(), answerLotto.getBonusNumber());
    double returnRate = resultService.getReturnRate(money);
    
    System.out.println("당첨 통계");
    System.out.println("---");
    System.out.println("3개 일치 (5,000원)" + "-" +  statistics.get("THREE") + "개");
    System.out.println("4개 일치 (50,000원)" + "-" +  statistics.get("FOUR") + "개");
    System.out.println("5개 일치 (1,500,000원)" + "-" +  statistics.get("FIVE") + "개");
    System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원)" + "-" +  statistics.get("FIVEPLUSBONUS") + "개");
    System.out.println("6개 일치 (2,000,000,000원)" + "-" +  statistics.get("SIX") + "개");
    System.out.println(String.format("총 수익률은 %.1f입니다.", returnRate));
  }
  
  private void buyLotto(int money) {
    user = new User(generateLottos(money));
  }
}
