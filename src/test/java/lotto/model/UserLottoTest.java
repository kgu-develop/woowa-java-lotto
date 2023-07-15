package lotto.model;

import lotto.utils.LottoRandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.utils.LottoConstants.LOTTO_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserLottoTest {
    @Test
    @DisplayName("UserLotto를 생성한다")
    void construct() {
        // when
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final UserLotto userLotto = UserLotto.createLotto(numbers);
        final UserLotto userLottoByAuto = UserLotto.createLotto(LottoRandomGenerator.generate());

        // then
        assertAll(
                () -> assertThat(userLotto.getLottoNumbers()).containsExactlyElementsOf(numbers), // 수동 생성
                () -> assertThat(userLottoByAuto.getLottoNumbers()).hasSize(LOTTO_SIZE), // 자동 생성 - size validation
                () -> assertThat(
                        userLottoByAuto.getLottoNumbers()
                                .stream()
                                .distinct()
                                .count()
                ).isEqualTo(LOTTO_SIZE) // 자동 생성 - has duplicate validation
        );
    }
}
