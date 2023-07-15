package lotto.domain.Lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {
  private LottoGenerator() {
  }
  
  public static List<Lotto> generateLottos(int money) {
    validateMoney(money);
    List<Lotto> lottos = new ArrayList<>();
    final int quantity = money / 1000;
    
    for (int i = 0; i < quantity; i++) {
      lottos.add(generateLotto());
    }
    
    return lottos;
  }
  
  private static Lotto generateLotto() {
    return new Lotto(getLottoNumbers());
  }
  
  private static List<Integer> getLottoNumbers() {
    return Randoms.pickUniqueNumbersInRange(1, 45, 6);
  }
  
  private static void validateMoney(int money) {
    validateMoneyIsPositive(money);
    validateMoneyIsMultiplesOfPrice(money);
  }
  
  private static void validateMoneyIsPositive(int money) {
    if (money < 0) {
      throw new IllegalArgumentException("구입금액은 음수를 입력할 수 없습니다.");
    }
  }
  
  private static void validateMoneyIsMultiplesOfPrice(int money) {
    if (isNotMultiplesOfPrice(money)) {
      throw new IllegalArgumentException("구입금액은 1000의 배수여야 합니다.");
    }
  }
  
  private static boolean isNotMultiplesOfPrice(int money) {
    return (money / 1000) < 0 || (money % 1000) > 0;
  }
}
