package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoStatisticsTest {
    @Test
    @DisplayName("구매한 로또 N장에 대한 당첨 통계를 조회한다")
    void getWinningResult() {
        // given
        final LottoWinningMachine lottoWinningMachine = createLottoWinningMachine();
        final UserLotto userLottoCaseA = createUserLottosCaseA();
        final UserLotto userLottoCaseB = createUserLottosCaseB();
        final UserLotto userLottoCaseC = createUserLottosCaseB();

        // when
        final LottoStatistics caseA = LottoStatistics.checkLotteryResult(lottoWinningMachine, userLottoCaseA);
        final LottoStatistics caseB = LottoStatistics.checkLotteryResult(lottoWinningMachine, userLottoCaseB);
        final LottoStatistics caseC = LottoStatistics.checkLotteryResult(lottoWinningMachine, userLottoCaseC);

        // then
        final Map<WinningRank, Integer> winningResultA = caseA.getWinningResult();
        assertAll(
                () -> assertThat(winningResultA.get(WinningRank.FIRST)).isEqualTo(1),
                () -> assertThat(winningResultA.get(WinningRank.SECOND)).isEqualTo(1),
                () -> assertThat(winningResultA.get(WinningRank.THIRD)).isEqualTo(1),
                () -> assertThat(winningResultA.get(WinningRank.FOURTH)).isEqualTo(2),
                () -> assertThat(winningResultA.get(WinningRank.FIFTH)).isEqualTo(2),
                () -> assertThat(winningResultA.get(WinningRank.NONE)).isEqualTo(5)
        );

        final Map<WinningRank, Integer> winningResultB = caseB.getWinningResult();
        assertAll(
                () -> assertThat(winningResultB.get(WinningRank.FIRST)).isEqualTo(0),
                () -> assertThat(winningResultB.get(WinningRank.SECOND)).isEqualTo(0),
                () -> assertThat(winningResultB.get(WinningRank.THIRD)).isEqualTo(0),
                () -> assertThat(winningResultB.get(WinningRank.FOURTH)).isEqualTo(0),
                () -> assertThat(winningResultB.get(WinningRank.FIFTH)).isEqualTo(3),
                () -> assertThat(winningResultB.get(WinningRank.NONE)).isEqualTo(14)
        );

        final Map<WinningRank, Integer> winningResultC = caseC.getWinningResult();
        assertAll(
                () -> assertThat(winningResultC.get(WinningRank.FIRST)).isEqualTo(0),
                () -> assertThat(winningResultC.get(WinningRank.SECOND)).isEqualTo(0),
                () -> assertThat(winningResultC.get(WinningRank.THIRD)).isEqualTo(0),
                () -> assertThat(winningResultC.get(WinningRank.FOURTH)).isEqualTo(0),
                () -> assertThat(winningResultC.get(WinningRank.FIFTH)).isEqualTo(3),
                () -> assertThat(winningResultC.get(WinningRank.NONE)).isEqualTo(14)
        );
    }

    @Test
    @DisplayName("구매한 로또 N장에 대한 수익률을 조회한다")
    void getEarningRate() {
        // given
        final LottoWinningMachine lottoWinningMachine = createLottoWinningMachine();
        final UserLotto userLottoCaseA = createUserLottosCaseA();
        final UserLotto userLottoCaseB = createUserLottosCaseB();
        final UserLotto userLottoCaseC = createUserLottosCaseC();

        // when
        final LottoStatistics caseA = LottoStatistics.checkLotteryResult(lottoWinningMachine, userLottoCaseA);
        final LottoStatistics caseB = LottoStatistics.checkLotteryResult(lottoWinningMachine, userLottoCaseB);
        final LottoStatistics caseC = LottoStatistics.checkLotteryResult(lottoWinningMachine, userLottoCaseC);

        // then
        assertAll(
                () -> assertThat(caseA.getEarningRate()).isEqualTo(BigDecimal.valueOf(16930083.3)),
                () -> assertThat(caseB.getEarningRate()).isEqualTo(BigDecimal.valueOf(88.2)),
                () -> assertThat(caseC.getEarningRate()).isEqualTo(BigDecimal.valueOf(62.5))
        );
    }

    private LottoWinningMachine createLottoWinningMachine() {
        return LottoWinningMachine.drawWinningLottery(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                7
        );
    }

    private UserLotto createUserLottosCaseA() {
        return new UserLotto(
                List.of(
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(1, 2, 3, 11, 12, 13)), // 5등 (당첨 3개)
                        new Lotto(Arrays.asList(1, 2, 3, 7, 12, 13)), // 5등 (당첨 3개 + 보너스 1개)
                        new Lotto(Arrays.asList(1, 2, 3, 4, 12, 13)), // 4등 (당첨 4개)
                        new Lotto(Arrays.asList(1, 2, 3, 4, 7, 13)), // 4등 (당첨 4개 + 보너스 1개)
                        new Lotto(Arrays.asList(1, 2, 3, 4, 5, 13)), // 3등 (당첨 5개)
                        new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)), // 2등 (당첨 5개 + 보너스 1개)
                        new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)) // 1등 (당첨 6개)
                )
        );

        /**
         * 구매 금액 = 12_000
         * 당첨 금액 = 2,031,610,000
         * -> 수익률 = 169,300.83333333333333333333333333... = 16930083.33% = 16930083.3%
         */
    }

    private UserLotto createUserLottosCaseB() {
        return new UserLotto(
                List.of(
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(8, 9, 10, 11, 12, 13)), // None
                        new Lotto(Arrays.asList(1, 2, 3, 11, 12, 13)), // 5등 (당첨 3개)
                        new Lotto(Arrays.asList(1, 2, 3, 11, 12, 13)), // 5등 (당첨 3개)
                        new Lotto(Arrays.asList(1, 2, 3, 7, 12, 13)) // 5등 (당첨 3개 + 보너스 1개)
                )
        );

        /**
         * 구매 금액 = 17000
         * 당첨 금액 = 15000
         * -> 수익률 = 0.88235294117647058823529411764706... = 88.23% = 88.2%
         */
    }

    private UserLotto createUserLottosCaseC() {
        return new UserLotto(
                List.of(
                        new Lotto(Arrays.asList(8, 21, 23, 41, 42, 43)), // None
                        new Lotto(Arrays.asList(3, 5, 11, 16, 32, 38)), // None
                        new Lotto(Arrays.asList(7, 11, 16, 35, 36, 44)), // None
                        new Lotto(Arrays.asList(1, 8, 11, 31, 41, 42)), // None
                        new Lotto(Arrays.asList(13, 14, 16, 38, 42, 45)), // None
                        new Lotto(Arrays.asList(7, 11, 30, 40, 42, 43)), // None
                        new Lotto(Arrays.asList(2, 13, 22, 32, 38, 45)), // None
                        new Lotto(Arrays.asList(1, 3, 5, 14, 22, 45)) // 5등 (당첨 3개)
                )
        );

        /**
         * 구매 금액 = 8000
         * 당첨 금액 = 5000
         * -> 수익률 = 0.625 = 62.5%
         */
    }
}
