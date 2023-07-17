package lotto.model;

import java.util.List;

import static lotto.utils.ExceptionConstants.LottoMachineException.BONUS_NUMBER_MUST_BE_UNIQUE;

public class LottoWinningMachine {
    private final Lotto winningLottery;
    private final BonusNumber bonusNumber;

    private LottoWinningMachine(
            final List<Integer> winningNumbers,
            final int bonusNumber
    ) {
        validateBonusNumberIsDuplicate(winningNumbers, bonusNumber);
        this.winningLottery = new Lotto(winningNumbers);
        this.bonusNumber = new BonusNumber(bonusNumber);
    }

    public static LottoWinningMachine drawWinningLottery(
            final List<Integer> winningNumbers,
            final int bonusNumber
    ) {
        return new LottoWinningMachine(winningNumbers, bonusNumber);
    }

    private void validateBonusNumberIsDuplicate(
            final List<Integer> winningNumbers,
            final int bonusNumber
    ) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_MUST_BE_UNIQUE.message);
        }
    }

    public List<Integer> getWinningLotteryNumbers() {
        return winningLottery.getNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber.getValue();
    }
}
