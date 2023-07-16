package lotto.service;

import lotto.domain.Lotto.Lotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResultServiceTest {
  private ResultService resultService;
  
  @BeforeEach
  void beforeEach() {
    resultService = new ResultService();
  }
  
  @Test
  @DisplayName("3개 일치 1개 당첨 통계를 구한다.")
  void getStatisticsOneThreeCollect() {
    // given
    List<Lotto> userLottos = List.of(
      new Lotto(new ArrayList<>(List.of(1, 2, 3, 11, 12, 13)))
    );
    List<Integer> answerNumbers = List.of(1, 2, 3, 4, 5, 6);
    int bonusNumber = 7;
    
    // when
    Map<String, Integer> statistics = resultService.getStatistics(userLottos, answerNumbers, bonusNumber);
    
    // then
    assertAll(
      () ->  assertThat(statistics.get("THREE")).isEqualTo(1),
      () ->  assertThat(statistics.get("FOUR")).isEqualTo(0),
      () ->  assertThat(statistics.get("FIVE")).isEqualTo(0),
      () ->  assertThat(statistics.get("FIVEPLUSBONUS")).isEqualTo(0),
      () -> assertThat(statistics.get("SIX")).isEqualTo(0)
    );
  }
  
  @Test
  @DisplayName("4개 일치 1개 당첨 통계를 구한다.")
  void getStatisticsOneFourCollect() {
    // given
    List<Lotto> userLottos = List.of(
      new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 11, 12)))
    );
    List<Integer> answerNumbers = List.of(1, 2, 3, 4, 5, 6);
    int bonusNumber = 7;
    
    // when
    Map<String, Integer> statistics = resultService.getStatistics(userLottos, answerNumbers, bonusNumber);
    
    // then
    assertAll(
      () ->  assertThat(statistics.get("THREE")).isEqualTo(0),
      () ->  assertThat(statistics.get("FOUR")).isEqualTo(1),
      () ->  assertThat(statistics.get("FIVE")).isEqualTo(0),
      () ->  assertThat(statistics.get("FIVEPLUSBONUS")).isEqualTo(0),
      () -> assertThat(statistics.get("SIX")).isEqualTo(0)
    );
  }
  
  @Test
  @DisplayName("5개 일치 1개 당첨 통계를 구한다.")
  void getStatisticsOneFiveCollect() {
    // given
    List<Lotto> userLottos = List.of(
      new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 11)))
    );
    List<Integer> answerNumbers = List.of(1, 2, 3, 4, 5, 6);
    int bonusNumber = 7;
    
    // when
    Map<String, Integer> statistics = resultService.getStatistics(userLottos, answerNumbers, bonusNumber);
    
    // then
    assertAll(
      () ->  assertThat(statistics.get("THREE")).isEqualTo(0),
      () ->  assertThat(statistics.get("FOUR")).isEqualTo(0),
      () ->  assertThat(statistics.get("FIVE")).isEqualTo(1),
      () ->  assertThat(statistics.get("FIVEPLUSBONUS")).isEqualTo(0),
      () -> assertThat(statistics.get("SIX")).isEqualTo(0)
    );
  }
  
  @Test
  @DisplayName("5개 일치, 보너스 볼 일치 1개 당첨 통계를 구한다.")
  void getStatisticsBonusCollect() {
    // given
    List<Lotto> userLottos = List.of(
      new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 7)))
    );
    List<Integer> answerNumbers = List.of(1, 2, 3, 4, 5, 6);
    int bonusNumber = 7;
    
    // when
    Map<String, Integer> statistics = resultService.getStatistics(userLottos, answerNumbers, bonusNumber);
    
    // then
    assertAll(
      () ->  assertThat(statistics.get("THREE")).isEqualTo(0),
      () ->  assertThat(statistics.get("FOUR")).isEqualTo(0),
      () ->  assertThat(statistics.get("FIVE")).isEqualTo(0),
      () ->  assertThat(statistics.get("FIVEPLUSBONUS")).isEqualTo(1),
      () -> assertThat(statistics.get("SIX")).isEqualTo(0)
    );
  }
  
  @Test
  @DisplayName("6개 일치 1개 당첨 통계를 구한다.")
  void getStatisticsOneSixCollect() {
    // given
    List<Lotto> userLottos = List.of(
      new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)))
    );
    List<Integer> answerNumbers = List.of(1, 2, 3, 4, 5, 6);
    int bonusNumber = 7;
    
    // when
    Map<String, Integer> statistics = resultService.getStatistics(userLottos, answerNumbers, bonusNumber);
  
    // then
    assertAll(
      () ->  assertThat(statistics.get("THREE")).isEqualTo(0),
      () ->  assertThat(statistics.get("FOUR")).isEqualTo(0),
      () ->  assertThat(statistics.get("FIVE")).isEqualTo(0),
      () ->  assertThat(statistics.get("FIVEPLUSBONUS")).isEqualTo(0),
      () -> assertThat(statistics.get("SIX")).isEqualTo(1)
    );
  }
  
  @Test
  @DisplayName("6개 일치 2개 당첨 통계를 구한다.")
  void getStatisticsTwoSixCollect() {
    // given
    List<Lotto> userLottos = List.of(
      new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6))),
      new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)))
    );
    List<Integer> answerNumbers = List.of(1, 2, 3, 4, 5, 6);
    int bonusNumber = 7;
    
    // when
    Map<String, Integer> statistics = resultService.getStatistics(userLottos, answerNumbers, bonusNumber);
    
    // then
    assertAll(
      () ->  assertThat(statistics.get("THREE")).isEqualTo(0),
      () ->  assertThat(statistics.get("FOUR")).isEqualTo(0),
      () ->  assertThat(statistics.get("FIVE")).isEqualTo(0),
      () ->  assertThat(statistics.get("FIVEPLUSBONUS")).isEqualTo(0),
      () -> assertThat(statistics.get("SIX")).isEqualTo(2)
    );
  }
}
