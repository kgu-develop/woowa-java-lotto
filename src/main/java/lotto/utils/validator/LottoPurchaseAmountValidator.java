package lotto.utils.validator;

import static lotto.utils.ExceptionConstants.InputException.PURCHASE_AMOUNT_MUST_BE_POSITIVE;
import static lotto.utils.ExceptionConstants.InputException.PURCHASE_AMOUNT_MUST_BE_THOUSAND_UNIT;

public class LottoPurchaseAmountValidator extends Validator {
    private static final int PURCHASE_UNIT = 1000;

    @Override
    public void validate(final String userInput) {
        validateInputHasSpace(userInput);
        validateInputIsNumeric(userInput);
        validatePurchaseAmountIsPositive(userInput);
        validateUnitOfAmountIsThousand(userInput);
    }

    private void validatePurchaseAmountIsPositive(final String userInput) {
        if (isUserInputNegative(userInput)) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_MUST_BE_POSITIVE.message);
        }
    }

    private boolean isUserInputNegative(final String userInput) {
        return Integer.parseInt(userInput) < 0;
    }

    private void validateUnitOfAmountIsThousand(final String userInput) {
        if (Integer.parseInt(userInput) % PURCHASE_UNIT != 0) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_MUST_BE_THOUSAND_UNIT.message);
        }
    }
}
