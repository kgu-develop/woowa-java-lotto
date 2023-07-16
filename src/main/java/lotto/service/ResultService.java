package lotto.service;

import lotto.domain.Lotto.Lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultService {
  private Map<Integer, String> collectMap = new HashMap<>(){{
    put(3, "THREE");
    put(4, "FOUR");
    put(5, "FIVE");
    put(6, "SIX");
  }};
  private Map<String, Integer> statisticCollectCntMap = new HashMap<>(){{
    put("THREE", 0);
    put("FOUR", 0);
    put("FIVE", 0);
    put("FIVEPLUSBONUS", 0);
    put("SIX", 0);
  }};
  
  public Map<String, Integer> getStatistics(List<Lotto> userLottos, List<Integer> answerNumbers, int bonusNumber) {
    int bonusCnt;
    int collectCnt;
    
    for (Lotto userLotto : userLottos) {
      List<Integer> eachLottoNumbers = userLotto.getNumbers();
      bonusCnt = isContainsBonusNumber(eachLottoNumbers, bonusNumber);
      collectCnt = getCollectCnt(eachLottoNumbers, answerNumbers);
      
      try {
        if (bonusCnt == 1 && collectCnt == 5) {
          statisticCollectCntMap.compute("FIVEPLUSBONUS", (k, v) -> Integer.valueOf(v + 1));
        } else {
          statisticCollectCntMap.compute(collectMap.get(collectCnt), (k, v) -> Integer.valueOf(v + 1));
        }
      } catch (NullPointerException e) {
      
      }
    }
    
    return statisticCollectCntMap;
  }
  
  private int isContainsBonusNumber(List<Integer> eachLottoNumbers, Integer bonusNumber) {
    if (eachLottoNumbers.contains(bonusNumber)) {
      return 1;
    }
    return 0;
  }
  
  private int getCollectCnt(List<Integer> eachLottoNumbers, List<Integer> answerNumbers) {
    eachLottoNumbers.retainAll(answerNumbers);
    return eachLottoNumbers.size();
  }
  
  public double getReturnRate(int money) {
    return (double) 100 * (
      statisticCollectCntMap.get("THREE") * 5000 +
        statisticCollectCntMap.get("FOUR") * 50000 +
        statisticCollectCntMap.get("FIVE") * 1500000 +
        statisticCollectCntMap.get("FIVEPLUSBONUS") * 30000000 +
        statisticCollectCntMap.get("SIX") * 2000000000
      ) / money;
  }
}
