package lotto.domain.Lotto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import static lotto.domain.Lotto.MoneyGenerator.generateMoney;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyGeneratorTest {
  private InputStream in;
  
  @AfterEach
  void afterEach() throws IOException {
    in.close();
  }
  
  @Test
  @DisplayName("올바른 구입 금액(1000원 단위로 1000의 배수 구입 금액)은 입력할 수 있다.")
  void createMoneyValidFormat() {
    // given
    String money = "5000";
    in = new ByteArrayInputStream(money.getBytes());
  
    // when
    System.setIn(in);
    
    // then
    assertThat(5000).isEqualTo(generateMoney());
  }
  
  @ParameterizedTest
  @MethodSource("invalidMoney")
  @DisplayName("올바른 구입 금액(1000원 단위로 1000의 배수 구입 금액)이 아니면 예외가 발생한다.")
  void createMoneyInvalidFormat(String money) {
    // given
    in = new ByteArrayInputStream(money.getBytes());
    
    // when
    System.setIn(in);
    
    // then
    assertThatThrownBy(() -> generateMoney())
      .isInstanceOf(IllegalArgumentException.class);
  }
  
  private static Stream<Arguments> invalidMoney() {
    return Stream.of(
      Arguments.of("5001"),
      Arguments.of("500"),
      Arguments.of("-1000")
    );
  }
}