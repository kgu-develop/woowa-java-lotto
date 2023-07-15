package lotto.model;

import lotto.utils.LottoRandomGenerator;
import org.assertj.core.util.VisibleForTesting;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserLotto {
    private final List<Lotto> userLottos;

    private UserLotto(final int lottoPurchaseCount) {
        this.userLottos = createLottoByPurchaseCount(lottoPurchaseCount);
    }

    @VisibleForTesting
    public UserLotto(final List<Lotto> userLottos) {
        this.userLottos = userLottos;
    }

    public static UserLotto issueLottoByPurchaseCount(final int lottoPurchaseCount) {
        return new UserLotto(lottoPurchaseCount);
    }

    private List<Lotto> createLottoByPurchaseCount(final int lottoPurchaseCount) {
        return Stream.generate(() -> new Lotto(LottoRandomGenerator.generate()))
                .limit(lottoPurchaseCount)
                .collect(Collectors.toList());
    }

    public List<Lotto> getUserLottos() {
        return Collections.unmodifiableList(userLottos);
    }

    public int getLottoPurchseCount() {
        return userLottos.size();
    }

    public BigDecimal getLottoPurchaseAmount() {
        return BigDecimal.valueOf(1000).multiply(BigDecimal.valueOf(getLottoPurchseCount()));
    }
}
