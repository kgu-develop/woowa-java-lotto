package lotto.utils.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.utils.ExceptionConstants.InputException.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LottoPurchaseAmountValidatorTest {
    private static final LottoPurchaseAmountValidator LOTTO_PURCHASE_AMOUNT_VALIDATOR
            = new LottoPurchaseAmountValidator();

    @Test
    @DisplayName("로또 구입금액에 공백이 존재하면 예외가 발생한다")
    void throwExceptionByInputHasSpace() {
        assertThatThrownBy(() -> LOTTO_PURCHASE_AMOUNT_VALIDATOR.validate("8000 "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INPUT_MUST_NOT_CONTAINS_SPACE.message);
    }

    @Test
    @DisplayName("로또 구입금액이 숫자가 아니면 예외가 발생한다")
    void throwExceptionByInputIsNotNumeric() {
        assertThatThrownBy(() -> LOTTO_PURCHASE_AMOUNT_VALIDATOR.validate("abcde"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INPUT_MUST_BE_NUMERIC.message);
    }

    @Test
    @DisplayName("로또 구입금액이 1000원 단위가 아니면 예외가 발생한다")
    void throwExceptionByUnitOfAmountIsNotThousand() {
        assertThatThrownBy(() -> LOTTO_PURCHASE_AMOUNT_VALIDATOR.validate("800"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_AMOUNT_MUST_BE_THOUSAND_UNIT.message);
    }

    @Test
    @DisplayName("로또 구입금액 검증에 성공한다")
    void success() {
        assertDoesNotThrow(() -> LOTTO_PURCHASE_AMOUNT_VALIDATOR.validate("8000"));
    }
}
