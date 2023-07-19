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

        // when
        final LottoStatistics caseA = LottoStatistics.checkLotteryResult(lottoWinningMachine, userLottoCaseA);
        final LottoStatistics caseB = LottoStatistics.checkLotteryResult(lottoWinningMachine, userLottoCaseB);

        // then
        final Map<WinningRank, Integer> winningResultA = caseA.getWinningResult();
        assertAll(
                () -> assertThat(winningResultA.get(WinningRank.FIRST)).isEqualTo(1),
                () -> assertThat(winningResultA.get(WinningRank.SECOND)).isEqualTo(1),
                () -> assertThat(winningResultA.get(WinningRank.THIRD)).isEqualTo(1),
                () -> assertThat(winningResultA.get(WinningRank.FOURTH)).isEqualTo(1),
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
    }

    @Test
    @DisplayName("구매한 로또 N장에 대한 수익률을 조회한다")
    void getEarningRate() {
        // given
        final LottoWinningMachine lottoWinningMachine = createLottoWinningMachine();
        final UserLotto userLottoCaseA = createUserLottosCaseA();
        final UserLotto userLottoCaseB = createUserLottosCaseB();

        // when
        final LottoStatistics caseA = LottoStatistics.checkLotteryResult(lottoWinningMachine, userLottoCaseA);
        final LottoStatistics caseB = LottoStatistics.checkLotteryResult(lottoWinningMachine, userLottoCaseB);

        // then
        assertAll(
                () -> assertThat(caseA.getEarningRate()).isEqualTo(BigDecimal.valueOf(18468727.3)),
                () -> assertThat(caseB.getEarningRate()).isEqualTo(BigDecimal.valueOf(88.2))
        );
    }

    private LottoWinningMachine createLottoWinningMachine() {
        return LottoWinningMachine.drawWinningLottery(
                Arrays.asList(1, 9, 10, 12, 22, 37),
                40
        );
    }

    private UserLotto createUserLottosCaseA() {
        return new UserLotto(
                List.of(
                        new Lotto(Arrays.asList(8, 21, 23, 41, 42, 43)), // None
                        new Lotto(Arrays.asList(3, 5, 11, 16, 32, 38)), // None
                        new Lotto(Arrays.asList(7, 11, 16, 35, 36, 44)), // None
                        new Lotto(Arrays.asList(13, 14, 16, 38, 42, 45)), // None
                        new Lotto(Arrays.asList(12, 14, 16, 38, 42, 45)), // None
                        new Lotto(Arrays.asList(1, 9, 11, 22, 41, 42)), // 5등
                        new Lotto(Arrays.asList(7, 10, 22, 37, 42, 43)), // 5등
                        new Lotto(Arrays.asList(1, 10, 22, 37, 38, 45)), // 4등
                        new Lotto(Arrays.asList(1, 9, 10, 12, 37, 39)), // 3등
                        new Lotto(Arrays.asList(1, 9, 10, 12, 37, 40)), // 2등
                        new Lotto(Arrays.asList(1, 9, 10, 12, 22, 37)) // 1등
                )
        );

        /**
         * 구매 금액 = 11_000
         * 당첨 금액 = 2_031_560_000
         * -> 수익률 = 184,687.27272727272727272727272727... = 18468727.27% = 18468727.3%
         */
    }

    private UserLotto createUserLottosCaseB() {
        return new UserLotto(
                List.of(
                        new Lotto(Arrays.asList(8, 21, 23, 41, 42, 43)), // None
                        new Lotto(Arrays.asList(3, 5, 11, 16, 32, 38)), // None
                        new Lotto(Arrays.asList(7, 11, 16, 35, 36, 44)), // None
                        new Lotto(Arrays.asList(13, 14, 16, 38, 42, 45)), // None
                        new Lotto(Arrays.asList(12, 14, 16, 38, 42, 45)), // None
                        new Lotto(Arrays.asList(8, 21, 23, 41, 42, 43)), // None
                        new Lotto(Arrays.asList(3, 5, 11, 16, 32, 38)), // None
                        new Lotto(Arrays.asList(7, 11, 16, 35, 36, 44)), // None
                        new Lotto(Arrays.asList(13, 14, 16, 38, 42, 45)), // None
                        new Lotto(Arrays.asList(12, 14, 16, 38, 42, 45)), // None
                        new Lotto(Arrays.asList(13, 14, 16, 38, 42, 45)), // None
                        new Lotto(Arrays.asList(12, 14, 16, 38, 42, 45)), // None
                        new Lotto(Arrays.asList(12, 14, 16, 38, 42, 45)), // None
                        new Lotto(Arrays.asList(12, 14, 16, 38, 42, 45)), // None
                        new Lotto(Arrays.asList(1, 9, 11, 22, 41, 42)), // 5등
                        new Lotto(Arrays.asList(1, 9, 11, 22, 41, 42)), // 5등
                        new Lotto(Arrays.asList(1, 9, 11, 22, 41, 42)) // 5등
                )
        );

        /**
         * 구매 금액 = 17000
         * 당첨 금액 = 15000
         * -> 수익률 = 0.88235294117647058823529411764706... = 88.23% = 88.2%
         */
    }
}
