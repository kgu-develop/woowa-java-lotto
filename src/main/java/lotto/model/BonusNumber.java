package lotto.model;

import static lotto.utils.ExceptionConstants.LottoMachineException.BONUS_NUMBER_IS_NOT_IN_RANGE;
import static lotto.utils.LottoConstants.MAX_VALUE;
import static lotto.utils.LottoConstants.MIN_VALUE;

public class BonusNumber {
    private final int value;

    public BonusNumber(final int value) {
        validateBonusNumberIsInRange(value);
        this.value = value;
    }

    private void validateBonusNumberIsInRange(final int value) {
        if (isOutOfRange(value)) {
            throw new IllegalArgumentException(BONUS_NUMBER_IS_NOT_IN_RANGE.message);
        }
    }

    private boolean isOutOfRange(final int value) {
        return value < MIN_VALUE || value > MAX_VALUE;
    }

    public int getValue() {
        return value;
    }
}
