package com.example.homework211;

import android.view.View;
import android.widget.CheckBox;

import java.util.HashMap;

public class CheckboxGroup implements View.OnClickListener {
	private HashMap<CheckBox, View.OnClickListener> list = new HashMap<>();

	public void add(CheckBox c, View.OnClickListener l) {
		list.put(c, l);
		c.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		for (CheckBox c : list.keySet())
			c.setChecked(c.equals(v));
		list.get(v).onClick(v);
	}
}
