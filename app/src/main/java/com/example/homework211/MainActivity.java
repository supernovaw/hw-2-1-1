package com.example.homework211;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	private EditText moneyAmountInput;
	private CheckBox cardCheckBox, phoneCheckBox, cashCheckBox;
	private EditText additionalInput;
	private Button proceedButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();

		CheckboxGroup group = new CheckboxGroup();
		View.OnClickListener checkboxListener = v -> {
			int input;
			switch (v.getId()) {
				case R.id.cardCheckBox:
					input = InputType.TYPE_CLASS_NUMBER;
					break;
				case R.id.phoneCheckBox:
					input = InputType.TYPE_CLASS_PHONE;
					break;
				case R.id.cashCheckBox:
					input = InputType.TYPE_CLASS_TEXT;
					break;
				default:
					throw new Error("OnClick argument is neither of checkboxes, id=" + v.getId());
			}
			additionalInput.setInputType(input);
		};
		group.add(cardCheckBox, checkboxListener);
		group.add(phoneCheckBox, checkboxListener);
		group.add(cashCheckBox, checkboxListener);

		proceedButton.setOnClickListener(v -> proceed());
	}

	private void initViews() {
		moneyAmountInput = findViewById(R.id.moneyAmountInput);
		cardCheckBox = findViewById(R.id.cardCheckBox);
		phoneCheckBox = findViewById(R.id.phoneCheckBox);
		cashCheckBox = findViewById(R.id.cashCheckBox);
		additionalInput = findViewById(R.id.additionalInput);
		proceedButton = findViewById(R.id.proceedButton);
	}

	private void proceed() {
		int mode;
		if (cardCheckBox.isChecked())
			mode = R.string.mode_card;
		else if (phoneCheckBox.isChecked())
			mode = R.string.mode_phone;
		else if (cashCheckBox.isChecked())
			mode = R.string.mode_cash;
		else {
			Toast.makeText(this, R.string.toast_none_checked, Toast.LENGTH_SHORT).show();
			return;
		}
		String moneyString = moneyAmountInput.getText().toString();
		if (moneyString.isEmpty()) {
			Toast.makeText(this, R.string.type_money_amt, Toast.LENGTH_SHORT).show();
			return;
		}
		double money;
		try {
			money = Double.parseDouble(moneyString);
		} catch (NumberFormatException e) {
			Toast.makeText(this, R.string.invalid_money_amt, Toast.LENGTH_SHORT).show();
			return;
		}
		String additional = additionalInput.getText().toString();
		additional = additional.isEmpty() ? getString(R.string.additional_empty)
				: '"' + additional + '"';
		String toast = String.format(
				getString(R.string.proceeding), getString(mode), money, additional);
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
}
