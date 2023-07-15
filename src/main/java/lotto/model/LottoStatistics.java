package lotto.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private final LottoWinningMachine lottoWinningMachine;
    private final UserLotto userLotto;
    private final Map<WinningRank, Integer> winningResult = new EnumMap<>(WinningRank.class);

    private LottoStatistics(
            final LottoWinningMachine lottoWinningMachine,
            final UserLotto userLotto
    ) {
        this.lottoWinningMachine = lottoWinningMachine;
        this.userLotto = userLotto;
        initWinningResult();
        calculateLotteryWinningResult();
    }

    public static LottoStatistics of(
            final LottoWinningMachine lottoWinningMachine,
            final UserLotto userLotto
    ) {
        return new LottoStatistics(lottoWinningMachine, userLotto);
    }

    private void initWinningResult() {
        for (WinningRank winningRank : WinningRank.values()) {
            winningResult.put(winningRank, 0);
        }
    }

    private void calculateLotteryWinningResult() {
        List<Integer> winningLotteryNumbers = lottoWinningMachine.getWinningLotteryNumbers();
        int bonusNumber = lottoWinningMachine.getBonusNumber();

        for (Lotto lotto : userLotto.getUserLottos()) {
            List<Integer> lottoNumbers = lotto.getNumbers();
            int matchCount = getLottoMatchCount(lottoNumbers, winningLotteryNumbers);
            boolean hasBonus = isBonusNumberExists(lottoNumbers, bonusNumber);

            WinningRank winningRank = WinningRank.of(matchCount, hasBonus);
            updateWinningResult(winningRank);
        }
    }

    private int getLottoMatchCount(
            final List<Integer> lottoNumbers,
            final List<Integer> winningLotteryNumbers
    ) {
        return (int) lottoNumbers.stream()
                .filter(winningLotteryNumbers::contains)
                .count();
    }

    private boolean isBonusNumberExists(
            final List<Integer> lottoNumbers,
            final int bonusNumber
    ) {
        return lottoNumbers.contains(bonusNumber);
    }

    private void updateWinningResult(final WinningRank winningRank) {
        winningResult.put(winningRank, winningResult.get(winningRank) + 1);
    }

    public Map<WinningRank, Integer> getWinningResult() {
        return Collections.unmodifiableMap(winningResult);
    }

    public int getWinningCountByRank(final WinningRank winningRank) {
        return winningResult.getOrDefault(winningRank, 0);
    }

    public BigDecimal getEarningRate() {
        final BigDecimal lottoPurchaseAmount = userLotto.getLottoPurchaseAmount();
        final BigDecimal totalWinningAmount = calculateTotalWinningAmount();

        return totalWinningAmount
                .multiply(BigDecimal.valueOf(100)) // 백분율
                .divide(lottoPurchaseAmount, 1, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTotalWinningAmount() {
        BigDecimal amount = BigDecimal.ZERO;
        for (WinningRank winningRank : winningResult.keySet()) {
            final BigDecimal addPrize = getAddPrize(winningRank);
            amount = amount.add(addPrize);
        }
        return amount;
    }

    private BigDecimal getAddPrize(final WinningRank winningRank) {
        final int reward = winningRank.getReward();
        final int count = winningResult.get(winningRank);

        return BigDecimal.valueOf((long) reward * count);
    }
}
