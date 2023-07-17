package lotto.view;

import lotto.model.Lotto;
import lotto.model.LottoStatistics;
import lotto.model.WinningRank;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ENTER = "\n";
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] %s";
    private static final String WINNING_FORMAT = "%s (%s원) - %d개";
    private static final String REWARD_FORMAT = "#,###";
    private static final String EARNING_FORMAT = "총 수익률은 %s%%입니다.";

    public static void printPurchaseInformation(final List<Lotto> userLottos) {
        System.out.printf("%d개를 구매했습니다." + ENTER, userLottos.size());
        userLottos.forEach(System.out::println);
    }

    public static void printWinningStatistics(final LottoStatistics lottoStatistics) {
        StringBuilder result = new StringBuilder("당첨 통계" + ENTER + "---" + ENTER);
        addWinningStatistics(lottoStatistics, result);
        addEarningRate(lottoStatistics, result);
        System.out.println(result);
    }

    private static void addWinningStatistics(
            final LottoStatistics lottoStatistics,
            final StringBuilder result
    ) {
        List<WinningRank> filteredWinningRank = getFilteredWinningRank();
        for (WinningRank winningRank : filteredWinningRank) {
            result.append(
                    String.format(
                            WINNING_FORMAT,
                            winningRank.getDescription(),
                            refineReward(winningRank.getReward()),
                            lottoStatistics.getWinningCountByRank(winningRank)
                    )
            ).append(ENTER);
        }
    }

    private static List<WinningRank> getFilteredWinningRank() {
        return Arrays.stream(WinningRank.values())
                .filter(winningRank -> winningRank != WinningRank.NONE)
                .sorted(Collections.reverseOrder()) // Enum Position DESC
                .collect(Collectors.toList());
    }

    private static String refineReward(final int reward) {
        return new DecimalFormat(REWARD_FORMAT).format(reward);
    }

    private static void addEarningRate(
            final LottoStatistics lottoStatistics,
            final StringBuilder result
    ) {
        result.append(String.format(EARNING_FORMAT, lottoStatistics.getEarningRate()));
    }

    public static void printErrorMessage(final String message) {
        System.out.printf(ERROR_MESSAGE_FORMAT, message);
    }
}
