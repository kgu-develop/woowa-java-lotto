package lotto.model;

import java.util.List;

public class UserLotto {
    private final Lotto lotto;

    private UserLotto(final List<Integer> numbers) {
        this.lotto = new Lotto(numbers);
    }

    public static UserLotto createLotto(final List<Integer> numbers) {
        return new UserLotto(numbers);
    }

    public List<Integer> getLottoNumbers() {
        return lotto.getNumbers();
    }
}
