package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        final UserLottos userLottosCaseA = createUserLottosCaseA();
        final UserLottos userLottosCaseB = createUserLottosCaseB();

        // when
        LottoStatistics caseA = LottoStatistics.of(lottoWinningMachine, userLottosCaseA);
        LottoStatistics caseB = LottoStatistics.of(lottoWinningMachine, userLottosCaseB);

        // then
        Map<WinningRank, Integer> winningResultA = caseA.getWinningResult();
        assertAll(
                () -> assertThat(winningResultA.get(WinningRank.FIRST)).isEqualTo(1),
                () -> assertThat(winningResultA.get(WinningRank.SECOND)).isEqualTo(1),
                () -> assertThat(winningResultA.get(WinningRank.THIRD)).isEqualTo(1),
                () -> assertThat(winningResultA.get(WinningRank.FOURTH)).isEqualTo(1),
                () -> assertThat(winningResultA.get(WinningRank.FIFTH)).isEqualTo(2),
                () -> assertThat(winningResultA.get(WinningRank.NONE)).isEqualTo(5)
        );

        Map<WinningRank, Integer> winningResultB = caseB.getWinningResult();
        assertAll(
                () -> assertThat(winningResultB.get(WinningRank.FIRST)).isEqualTo(0),
                () -> assertThat(winningResultB.get(WinningRank.SECOND)).isEqualTo(0),
                () -> assertThat(winningResultB.get(WinningRank.THIRD)).isEqualTo(0),
                () -> assertThat(winningResultB.get(WinningRank.FOURTH)).isEqualTo(0),
                () -> assertThat(winningResultB.get(WinningRank.FIFTH)).isEqualTo(3),
                () -> assertThat(winningResultB.get(WinningRank.NONE)).isEqualTo(14)
        );
    }

    private LottoWinningMachine createLottoWinningMachine() {
        return LottoWinningMachine.drawWinningLottery(
                Arrays.asList(1, 9, 10, 12, 22, 37),
                40
        );
    }

    private UserLottos createUserLottosCaseA() {
        return new UserLottos(
                List.of(
                        UserLotto.createLotto(Arrays.asList(8, 21, 23, 41, 42, 43)), // None
                        UserLotto.createLotto(Arrays.asList(3, 5, 11, 16, 32, 38)), // None
                        UserLotto.createLotto(Arrays.asList(7, 11, 16, 35, 36, 44)), // None
                        UserLotto.createLotto(Arrays.asList(13, 14, 16, 38, 42, 45)), // None
                        UserLotto.createLotto(Arrays.asList(12, 14, 16, 38, 42, 45)), // None
                        UserLotto.createLotto(Arrays.asList(1, 9, 11, 22, 41, 42)), // 5등
                        UserLotto.createLotto(Arrays.asList(7, 10, 22, 37, 42, 43)), // 5등
                        UserLotto.createLotto(Arrays.asList(1, 10, 22, 37, 38, 45)), // 4등
                        UserLotto.createLotto(Arrays.asList(1, 9, 10, 12, 37, 39)), // 3등
                        UserLotto.createLotto(Arrays.asList(1, 9, 10, 12, 37, 40)), // 2등
                        UserLotto.createLotto(Arrays.asList(1, 9, 10, 12, 22, 37)) // 1등
                )
        );

        /**
         * 구매 금액 = 10_000
         * 당첨 금액 = 2_031_560_000
         * -> 수익률 = 203156%
         */
    }

    private UserLottos createUserLottosCaseB() {
        return new UserLottos(
                List.of(
                        UserLotto.createLotto(Arrays.asList(8, 21, 23, 41, 42, 43)), // None
                        UserLotto.createLotto(Arrays.asList(3, 5, 11, 16, 32, 38)), // None
                        UserLotto.createLotto(Arrays.asList(7, 11, 16, 35, 36, 44)), // None
                        UserLotto.createLotto(Arrays.asList(13, 14, 16, 38, 42, 45)), // None
                        UserLotto.createLotto(Arrays.asList(12, 14, 16, 38, 42, 45)), // None
                        UserLotto.createLotto(Arrays.asList(8, 21, 23, 41, 42, 43)), // None
                        UserLotto.createLotto(Arrays.asList(3, 5, 11, 16, 32, 38)), // None
                        UserLotto.createLotto(Arrays.asList(7, 11, 16, 35, 36, 44)), // None
                        UserLotto.createLotto(Arrays.asList(13, 14, 16, 38, 42, 45)), // None
                        UserLotto.createLotto(Arrays.asList(12, 14, 16, 38, 42, 45)), // None
                        UserLotto.createLotto(Arrays.asList(13, 14, 16, 38, 42, 45)), // None
                        UserLotto.createLotto(Arrays.asList(12, 14, 16, 38, 42, 45)), // None
                        UserLotto.createLotto(Arrays.asList(12, 14, 16, 38, 42, 45)), // None
                        UserLotto.createLotto(Arrays.asList(12, 14, 16, 38, 42, 45)), // None
                        UserLotto.createLotto(Arrays.asList(1, 9, 11, 22, 41, 42)), // 5등
                        UserLotto.createLotto(Arrays.asList(7, 10, 22, 37, 42, 43)), // 5등
                        UserLotto.createLotto(Arrays.asList(1, 10, 22, 35, 38, 45)) // 5등
                )
        );

        /**
         * 구매 금액 = 17000
         * 당첨 금액 = 15000
         * -> 수익률 = 88.2%
         */
    }
}
