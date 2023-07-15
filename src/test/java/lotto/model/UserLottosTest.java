package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class UserLottosTest {
    @ParameterizedTest
    @ValueSource(ints = {5, 10})
    @DisplayName("구매한 개수만큼 UseLotto를 발급받는다")
    void construct(int lottoPurchaseCount) {
        // when
        final UserLottos userLottos = UserLottos.issueLottoByPurchaseCount(lottoPurchaseCount);

        // then
        assertThat(userLottos.getUserLottos()).hasSize(lottoPurchaseCount);
    }
}
