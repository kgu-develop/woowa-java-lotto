package lotto.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.domain.user.LottoGenerator.generateLottos;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoGeneratorTest {
  @Test
  @DisplayName("입력한 돈에 따른 갯수의 로또를 발행한다.")
  void generateLotto() {
    // given
    int money = 8000;
    int quantity = money / 1000;
    
    // when
    List<Lotto> lottos = generateLottos(money);
  
    // then
    assertThat(lottos.size()).isEqualTo(quantity);
  }
  
  @Test
  @DisplayName("구입금액으로 음수를 입력하면 예외가 발생한다.")
  void inputNegativeMoney() {
    // given
    int money = -1000;
      
    // then
    assertThatThrownBy(() -> generateLottos(money))
      .isInstanceOf(IllegalArgumentException.class);
  }
  
  @Test
  @DisplayName("구입금액으로 음수를 입력하면 예외가 발생한다.")
  void inputNotMultipleMoney() {
    // given
    int money = 1100;
    
    // then
    assertThatThrownBy(() -> generateLottos(money))
      .isInstanceOf(IllegalArgumentException.class);
  }
}
