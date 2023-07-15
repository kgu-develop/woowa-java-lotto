package lotto.utils.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.utils.ExceptionConstants.InputException.INPUT_MUST_BE_NUMERIC;
import static lotto.utils.ExceptionConstants.InputException.INPUT_MUST_NOT_CONTAINS_SPACE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class WinningNumberValidatorTest {
    private static final WinningNumberValidator WINNING_NUMBER_VALIDATOR
            = new WinningNumberValidator();

    @Test
    @DisplayName("당첨번호에 공백이 존재하면 예외가 발생한다")
    void throwExceptionByInputHasSpace() {
        assertThatThrownBy(() -> WINNING_NUMBER_VALIDATOR.validate("1,2,3,4, 5, 6,  "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INPUT_MUST_NOT_CONTAINS_SPACE.message);
    }

    @Test
    @DisplayName("당첨번호의 각 번호들이 숫자가 아니면 예외가 발생한다")
    void throwExceptionByInputIsNotNumeric() {
        assertThatThrownBy(() -> WINNING_NUMBER_VALIDATOR.validate("1,2,3,a,4,5,b,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INPUT_MUST_BE_NUMERIC.message);
    }

    @Test
    @DisplayName("당첨번호 검증에 성공한다")
    void success() {
        assertDoesNotThrow(() -> WINNING_NUMBER_VALIDATOR.validate("1,2,3,4,5,6"));
    }
}
