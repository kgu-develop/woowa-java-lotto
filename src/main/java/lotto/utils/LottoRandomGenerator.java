package lotto.utils;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static lotto.utils.LottoConstants.*;

public class LottoRandomGenerator {
    public static List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(
                MIN_VALUE,
                MAX_VALUE,
                LOTTO_SIZE
        );
    }
}
