package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static lotto.utils.LottoConstants.*;

public class UserLotto {
    private final Lotto lotto;

    private UserLotto() {
        this.lotto = new Lotto(generateRandomLottoNumbers());
    }

    public static UserLotto createLottoByAutomatic() {
        return new UserLotto();
    }

    private List<Integer> generateRandomLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(
                MIN_VALUE,
                MAX_VALUE,
                LOTTO_SIZE
        );
    }

    public List<Integer> getLottoNumbers() {
        return lotto.getNumbers();
    }
}
