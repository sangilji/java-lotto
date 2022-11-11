package lotto.exception;

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

	private void isNumber(String input) {
		if (!input.matches(input)) {
			throw new IllegalArgumentException(NUMBER_EXCEPTION_MESSAGE);
		}
	}

	private void divideByThousand(String input) {
		if (Integer.parseInt(input) % CASH_UNIT != 0) {
			throw new IllegalArgumentException(DIVISION_EXCEPTION_MESSAGE);
		}
	}

}
