package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static lotto.utils.ExceptionConstants.LottoException.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LottoTest {
    @ParameterizedTest
    @MethodSource("invalidRange")
    @DisplayName("로또 번호의 범위가 1..45 이외라면 예외가 발생한다")
    void throwExceptionByBaseballIsNotInRange(List<Integer> baseballs) {
        assertThatThrownBy(() -> new Lotto(baseballs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_NUMBER_IS_NOT_IN_RANGE.message);
    }

    private static Stream<Arguments> invalidRange() {
        return Stream.of(
                Arguments.of(List.of(0, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 46))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidSize")
    @DisplayName("로또 번호의 개수가 6개가 아니면 예외가 발생한다")
    void throwExceptionByBaseballSizeNotFulfill(List<Integer> baseballs) {
        assertThatThrownBy(() -> new Lotto(baseballs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_SIZE_IS_NOT_FULFILL.message);
    }

    private static Stream<Arguments> invalidSize() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of(1)),
                Arguments.of(List.of(1, 2)),
                Arguments.of(List.of(1, 2, 3)),
                Arguments.of(List.of(1, 2, 3, 4)),
                Arguments.of(List.of(1, 2, 3, 4, 5))
        );
    }

    @ParameterizedTest
    @MethodSource("duplicateNumber")
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다")
    void throwExceptionByBaseballIsNotUnique(List<Integer> baseballs) {
        assertThatThrownBy(() -> new Lotto(baseballs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_NUMBER_MUST_BE_UNIQUE.message);
    }

    private static Stream<Arguments> duplicateNumber() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 1, 1, 2, 3, 4)),
                Arguments.of(List.of(1, 1, 1, 1, 2, 3)),
                Arguments.of(List.of(1, 1, 1, 1, 1, 2)),
                Arguments.of(List.of(1, 1, 1, 1, 1, 1))
        );
    }

    @Test
    @DisplayName("Lotto를 생성한다")
    void construct() {
        assertDoesNotThrow(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)));
    }
}
