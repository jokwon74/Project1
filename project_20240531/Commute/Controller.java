package Commute;

import login.LoginVO;

public class Controller {

	private DAO dao = new DAO();

	public void logIn(int id, String pw) throws Exception {

		LoginVO login = new LoginVO(id, pw);

		dao.logIn(login);

	}

	public void logOut(int id, String pw) throws Exception {

		LoginVO login = new LoginVO(id, pw);

		dao.logOut(login);

	}

}
