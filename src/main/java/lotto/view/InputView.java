package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.utils.validator.BonusNumberValidator;
import lotto.utils.validator.LottoPurchaseAmountValidator;
import lotto.utils.validator.WinningNumberValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String COMMA = ",";
    private static final LottoPurchaseAmountValidator LOTTO_PURCHASE_AMOUNT_VALIDATOR
            = new LottoPurchaseAmountValidator();
    private static final WinningNumberValidator WINNING_NUMBER_VALIDATOR
            = new WinningNumberValidator();
    private static final BonusNumberValidator BONUS_NUMBER_VALIDATOR
            = new BonusNumberValidator();

    public static int readLottoPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");

        final String userInput = Console.readLine();
        LOTTO_PURCHASE_AMOUNT_VALIDATOR.validate(userInput);

        return Integer.parseInt(userInput);
    }

    public static List<Integer> readWiningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");

        final String userInput = Console.readLine();
        WINNING_NUMBER_VALIDATOR.validate(userInput);

        return convertUserInputToIntegerList(userInput);
    }

    private static List<Integer> convertUserInputToIntegerList(final String userInput) {
        return Arrays.stream(userInput.split(COMMA))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");

        final String userInput = Console.readLine();
        BONUS_NUMBER_VALIDATOR.validate(userInput);

        return Integer.parseInt(userInput);
    }
}
