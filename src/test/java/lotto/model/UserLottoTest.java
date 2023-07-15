package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.utils.LottoConstants.LOTTO_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserLottoTest {
    @Test
    @DisplayName("UserLotto 생성한다")
    void construct() {
        // when
        final UserLotto userLotto = UserLotto.createLottoByAutomatic();

        // then
        assertAll(
                () -> assertThat(userLotto.getLottoNumbers()).hasSize(LOTTO_SIZE), // size
                () -> assertThat(
                        userLotto.getLottoNumbers()
                                .stream()
                                .distinct()
                                .count()
                ).isEqualTo(LOTTO_SIZE) // has duplicate
        );
    }
}
