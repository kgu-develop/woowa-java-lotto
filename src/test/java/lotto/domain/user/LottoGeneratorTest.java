package lotto.domain.user;

import lotto.domain.Lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.domain.Lotto.LottoGenerator.generateLottos;
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
}
