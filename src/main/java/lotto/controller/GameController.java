package lotto.controller;

import lotto.model.LottoStatistics;
import lotto.model.LottoWinningMachine;
import lotto.model.UserLottos;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class GameController {
    private static UserLottos userLottos;
    private static LottoWinningMachine lottoWinningMachine;

    public void run() {
        try {
            buyLotto();
            initLottoWinningMachine();
            displayLottoResult();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void buyLotto() {
        final int lottoPurchaseCount = InputView.readLottoPurchaseCount();
        userLottos = UserLottos.issueLottoByPurchaseCount(lottoPurchaseCount);
        OutputView.printPurchaseInformation(userLottos.getUserLottos());
    }

    private void initLottoWinningMachine() {
        final List<Integer> winingNumbers = InputView.readWiningNumbers();
        final int bonusNumber = InputView.readBonusNumber();
        lottoWinningMachine = LottoWinningMachine.drawWinningLottery(winingNumbers, bonusNumber);
    }

    private void displayLottoResult() {
        final LottoStatistics lottoStatistics = LottoStatistics.of(lottoWinningMachine, userLottos);
        OutputView.printWinningStatistics(lottoStatistics);
    }
}
