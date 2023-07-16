package lotto.domain.Lotto;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class MoneyGenerator {
  private MoneyGenerator() {
  }
  
  public static int generateMoney() {
    int money = inputMoney();
    validateMoney(money);
    return money;
  }
  
  private static int inputMoney() {
    String inputMoney = readLine();
    return convertMoneyTypeStringToInt(inputMoney);
  }
  
  private static int convertMoneyTypeStringToInt(String inputMoney) {
    return Integer.parseInt(inputMoney);
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
