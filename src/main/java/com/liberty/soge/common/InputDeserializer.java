package com.liberty.soge.common;

import com.liberty.soge.action.Action;

public interface InputDeserializer {
	Action deserializeInput(Action action);
}
