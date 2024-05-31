package com.poscodx.mysite.controller;

import java.util.Map;

import com.poscodx.mysite.controller.action.board.AddAction;
import com.poscodx.mysite.controller.action.board.AddFormAction;
import com.poscodx.mysite.controller.action.board.DeleteAction;
import com.poscodx.mysite.controller.action.board.ModifyAction;
import com.poscodx.mysite.controller.action.board.ModifyFormAction;
import com.poscodx.mysite.controller.action.board.ReplyAction;
import com.poscodx.mysite.controller.action.board.ReplyFormAction;
import com.poscodx.mysite.controller.action.board.ViewAction;
import com.poscodx.mysite.controller.action.main.BoardMainAction;

public class BoardServlet extends ActionServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Action> mapAction = Map.of(
			"add", new AddAction(),
			"addform", new AddFormAction(),
			"delete", new DeleteAction(),
			"view", new ViewAction(),
			"modifyform", new ModifyFormAction(),
			"modify", new ModifyAction(),
			"reply", new ReplyAction(),
			"replyform", new ReplyFormAction()
		);
		
		@Override
		protected Action getAction(String actionName) {
			return mapAction.getOrDefault(actionName, new BoardMainAction());
		}

}
