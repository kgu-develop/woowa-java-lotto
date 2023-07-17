package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static lotto.utils.ExceptionConstants.LottoMachineException.BONUS_NUMBER_IS_NOT_IN_RANGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BonusNumberTest {
    @ParameterizedTest
    @MethodSource("invalidRange")
    @DisplayName("보너스 번호의 범위가 1..45 이외라면 예외가 발생한다")
    void throwExceptionByBonusNumberIsNotInRange(int value) {
        assertThatThrownBy(() -> new BonusNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_IS_NOT_IN_RANGE.message);
    }

    private static Stream<Arguments> invalidRange() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(46)
        );
    }

    @ParameterizedTest
    @MethodSource("validRange")
    @DisplayName("BonusNumber를 생성한다")
    void success(int value) {
        assertDoesNotThrow(() -> new BonusNumber(value));
    }

    private static Stream<Arguments> validRange() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(10),
                Arguments.of(45)
        );
    }
}
