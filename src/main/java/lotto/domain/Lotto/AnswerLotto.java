package lotto.domain.Lotto;

import java.util.List;

public class AnswerLotto {
  private List<Integer> answerNumbers;
  
  public AnswerLotto(List<Integer> answerNumbers) {
    validateLottoSize(answerNumbers);
    validateLottoHasDuplicate(answerNumbers);
    validateLottoNumbersRange(answerNumbers);
    this.answerNumbers = answerNumbers;
  }
  
  private void validateLottoSize(List<Integer> numbers) {
    if (numbers.size() != 7) {
      throw new IllegalArgumentException("정답 로또 번호가 7자리가 아닙니다.");
    }
  }
  
  private void validateLottoHasDuplicate(List<Integer> numbers) {
    if (hasDuplicate(numbers)) {
      throw new IllegalArgumentException("로또 번호에 중복된 번호가 있습니다.");
    }
  }
  
  private boolean hasDuplicate(List<Integer> numbers) {
    return numbers.stream().distinct().count() != 7;
  }
  
  private void validateLottoNumbersRange(List<Integer> numbers) {
    if (isNotInRange(numbers)) {
      throw new IllegalArgumentException("로또 번호 중에 1 ~ 45가 아닌 것이 있습니다.");
    }
  }
  
  private boolean isNotInRange(List<Integer> numbers) {
    return numbers.stream().anyMatch(number -> number < 1 || number > 45);
  }
  
  public List<Integer> getAnswerNumbers() {
    return answerNumbers;
  }
}
