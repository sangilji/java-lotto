package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {
	private int bonusNumber;

	public WinningLotto(List<Integer> numbers) {
		super(numbers);
	}

	public void setBonusNumber(String bonusNumber) {
		this.bonusNumber = Integer.parseInt(bonusNumber);
	}
}
