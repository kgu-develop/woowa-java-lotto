package lotto.model;

import lotto.utils.LottoRandomGenerator;
import org.assertj.core.util.VisibleForTesting;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserLottos {
    private final List<UserLotto> userLottos;

    private UserLottos(final int lottoPurchaseCount) {
        this.userLottos = createLottoByPurchaseCount(lottoPurchaseCount);
    }

    @VisibleForTesting
    public UserLottos(final List<UserLotto> userLottos) {
        this.userLottos = userLottos;
    }

    public static UserLottos issueLottoByPurchaseCount(final int lottoPurchaseCount) {
        return new UserLottos(lottoPurchaseCount);
    }

    private List<UserLotto> createLottoByPurchaseCount(final int lottoPurchaseCount) {
        return Stream.generate(() -> UserLotto.createLotto(LottoRandomGenerator.generate()))
                .limit(lottoPurchaseCount)
                .collect(Collectors.toList());
    }

    public List<UserLotto> getUserLottos() {
        return Collections.unmodifiableList(userLottos);
    }

    public int getLottoPurchseCount() {
        return userLottos.size();
    }

    public BigDecimal getLottoPurchaseAmount() {
        return BigDecimal.valueOf(1000).multiply(BigDecimal.valueOf(getLottoPurchseCount()));
    }
}
