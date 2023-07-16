package lotto.domain.Lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswerLottoTest {
  @Test
  @DisplayName("당첨 번호 6자리와 보너스 번호 1자리를 입력하면 당첨 로또를 선언할 수 있다.")
  void canCreateAnswerLotto() {
    // given
    List<Integer> answerNumberList = List.of(1, 2, 3, 4, 5, 6, 7);
    AnswerLotto answerLotto = new AnswerLotto(answerNumberList);
    
    // then
    assertThat(answerLotto.getAnswerNumbers()).isEqualTo(answerNumberList);
  }
  
  @Test
  @DisplayName("당첨 로또 번호가 7개가 아니면 예외가 발생한다.")
  void throwException_wrongSize() {
    // given
    List<Integer> answerNumberList = List.of(1, 2, 3, 4, 5, 6);
    
    // then
    assertThatThrownBy(() -> new AnswerLotto(answerNumberList))
      .isInstanceOf(IllegalArgumentException.class);
  }
  
  @Test
  @DisplayName("당첨 로또 번호에 중복이 있으면 예외가 발생한다.")
  void throwException_hasDuplicate() {
    // given
    List<Integer> answerNumberList = List.of(1, 2, 5, 4, 5, 6);
    
    // then
    assertThatThrownBy(() -> new AnswerLotto(answerNumberList))
      .isInstanceOf(IllegalArgumentException.class);
  }
  
  @Test
  @DisplayName("당첨 로또 번호가 범위를 넘어가면 예외가 발생한다.")
  void throwException_invalidRange() {
    // given
    List<Integer> answerNumberList = List.of(1, 2, 5, 4, 5, 6);
    
    // then
    assertThatThrownBy(() -> new AnswerLotto(answerNumberList))
      .isInstanceOf(IllegalArgumentException.class);
  }
}