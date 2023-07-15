package lotto.domain.Lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {
  private LottoGenerator() {
  }
  
  public static List<Lotto> generateLottos(int money) {
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
}
