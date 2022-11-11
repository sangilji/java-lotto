package lotto.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.ConstValue;

public class InputExceptionHandler {
	private static final String NUMBER_EXCEPTION_MESSAGE = "[ERROR] 구입 금액은 숫자로 입력받아야 합니다.";
	private static final String DIVISION_EXCEPTION_MESSAGE = "[ERROR] 구입 금액은 1000원 단위여야 합니다.";
	private static final String DUPLICATED_WINNING_NUMBER_EXCEPTION = "[ERROR] 당첨 번호는 중복이 안됩니다.";
	private static final String ONE_TO_FORTY_FIVE_EXCEPTION = "[ERROR] 1 ~ 45 사이의 숫자여야합니다.";
	private static final String WINNING_NUMBER_COUNT_EXCEPTION = "[ERROR] 당첨 번호는 6개를 입력해야 합니다.";
	private static final String WINNING_NUMBER_DIVISION_EXCEPTION = "[ERROR] 당첨 번호는 쉼표로 구분지어야 합니다.";
	private static final String DUPLICATED_BONUS_NUMBER_MESSAGE = "[ERROR] 보너스 번호는 당첨 번호와 중복되면 안됩니다.";
	private static final String NUMBER_REGEX = "^[0-9]*$";
	private static final int CASH_UNIT = 1000;
	private static final String REST_DIVISION_REGEX = ",";

	public void checkPurchaseAmountForm(String input) {
		isNumber(input);
		divideByThousand(input);
	}

	private void isNumber(String input) {
		if (!input.matches(NUMBER_REGEX)) {
			throw new IllegalArgumentException(NUMBER_EXCEPTION_MESSAGE);
		}
	}

	private void divideByThousand(String input) {
		if (Integer.parseInt(input) % CASH_UNIT != 0) {
			throw new IllegalArgumentException(DIVISION_EXCEPTION_MESSAGE);
		}
	}

	public void checkWinningNumberForm(String input) {
		List<Integer> numbers = checkDivisionByRest(input);
		isDuplicatedWinningNumber(numbers);
		checkNumberCount(numbers);
		checkOneToFortyFive(numbers);
	}

	private void checkOneToFortyFive(List<Integer> numbers) {
		boolean allOneToFortyFive = numbers.stream()
			.allMatch(number -> ConstValue.MIN_LOTTO_NUMBER <= number && ConstValue.MAX_LOTTO_NUMBER >= number);
		if (!allOneToFortyFive) {
			throw new IllegalArgumentException(ONE_TO_FORTY_FIVE_EXCEPTION);
		}
	}

	private void isDuplicatedWinningNumber(List<Integer> numbers) {
		int distinctNumberCount = (int)numbers.stream()
			.distinct()
			.count();
		if (distinctNumberCount != ConstValue.LOTTO_NUMBERS) {
			throw new IllegalArgumentException(DUPLICATED_WINNING_NUMBER_EXCEPTION);
		}
	}

	private void checkNumberCount(List<Integer> numbers) {
		if (numbers.size() != ConstValue.LOTTO_NUMBERS) {
			throw new IllegalArgumentException(WINNING_NUMBER_COUNT_EXCEPTION);
		}
	}

	private List<Integer> checkDivisionByRest(String input) {
		try {
			return Arrays.stream(input.split(REST_DIVISION_REGEX))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new IllegalArgumentException(WINNING_NUMBER_DIVISION_EXCEPTION);
		}
	}

	public void checkBonusNumberForm(List<Integer> numbers, int bonusNumber) {
		isOneToFortyFiveBonusNumber(bonusNumber);
		isDuplicatedBonusNumber(numbers, bonusNumber);
	}

	private void isOneToFortyFiveBonusNumber(int bonusNumber) {
		if (bonusNumber < ConstValue.MIN_LOTTO_NUMBER || bonusNumber > ConstValue.MAX_LOTTO_NUMBER) {
			throw new IllegalArgumentException(ONE_TO_FORTY_FIVE_EXCEPTION);
		}
	}

	private void isDuplicatedBonusNumber(List<Integer> numbers, int bonusNumber) {
		if (numbers.contains(bonusNumber)) {
			throw new IllegalArgumentException(DUPLICATED_BONUS_NUMBER_MESSAGE);
		}
	}

}
