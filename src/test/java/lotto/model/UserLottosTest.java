package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserLottosTest {
    @ParameterizedTest
    @MethodSource("issueCase")
    @DisplayName("구매한 개수만큼 UseLotto를 발급받는다")
    void issueLottoByPurchaseCount(int lottoPurchaseCount, BigDecimal purchaseAmount) {
        // when
        final UserLottos userLottos = UserLottos.issueLottoByPurchaseCount(lottoPurchaseCount);

        // then
        assertAll(
                () -> assertThat(userLottos.getLottoPurchseCount()).isEqualTo(lottoPurchaseCount),
                () -> assertThat(userLottos.getLottoPurchaseAmount()).isEqualTo(purchaseAmount)
        );
    }

    private static Stream<Arguments> issueCase() {
        return Stream.of(
                Arguments.of(5, BigDecimal.valueOf(5_000)),
                Arguments.of(10, BigDecimal.valueOf(10_000)),
                Arguments.of(1_000_000, BigDecimal.valueOf(1_000_000_000))
        );
    }
}
