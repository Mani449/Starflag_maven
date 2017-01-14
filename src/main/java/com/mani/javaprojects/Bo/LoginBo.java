package com.mani.javaprojects.Bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mani.javaprojects.Vo.LoginVO;
import com.mani.javaprojects.Vo.RegisterVO;
import com.mani.javaprojects.util.SingleTonConnection;



public class LoginBo {
	public static String check_login(LoginVO login) {
		try {
			Connection conn = SingleTonConnection.connection();
			String sql = "select accountno from starflag_login where accountno=? and password=?";
			PreparedStatement prep = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			prep.setInt(1, login.getAccountno());
			prep.setString(2, login.getPassword());
			ResultSet rs = prep.executeQuery();
			int accnum = -1;
			if (rs.next()) {
				accnum = rs.getInt(1);
				System.out.println("++++++++++++++++++++++++++++++++++" + accnum);
			}
			conn.close();
			if (accnum != -1)
				return "" + accnum;
			else
				return null;

		} catch (SQLException sql) {
			return sql.getMessage();
		} catch (Exception exp) {
			return exp.getMessage();
		}
	}

	public static String registerAccount(RegisterVO register) {

		int accountno = -1;
		String sql = "select starflag_account_generator.nextval as accountno from dual";
		String sql_1 = "insert into starflag_register values(?,?,?,?)";
		String sql_2 = "insert into starflag_login values (?,?)";
		String sql_3 = "insert into starflag_balance values(?,?)";
		try {
			Connection conn = SingleTonConnection.connection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				accountno = rs.getInt(1);
			}
			st.close();
			if (accountno == -1)
				throw new SQLException("Account number not created");
			register.setAccountno(accountno);
			System.out.println("Accountno:" + accountno);
			PreparedStatement prep = conn.prepareStatement(sql_1, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			prep.setInt(1, accountno);
			prep.setString(2, register.getName());
			prep.setString(3, register.getEmail());
			prep.setString(4, register.getAddress());
			prep.execute();
			System.out.println("Register updated");
			prep = conn.prepareStatement(sql_2);
			prep.setInt(1, accountno);
			prep.setString(2, register.getPassword());
			prep.execute();
			prep = conn.prepareStatement(sql_3);
			prep.setInt(1, accountno);
			prep.setDouble(2, register.getOpenBalance());
			prep.execute();
			System.out.println("Login and balance updated");
			conn.commit();
			System.out.println("committed the changes");
			conn.close();
			return "use '" + accountno + "' as username to login from next time";
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			try {
				Connection conn = SingleTonConnection.connection();
				conn.rollback();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Rollback failed");
				return e.getMessage();
			}
			System.out.println("Rollback successfull");
			return "Rollback successfull";
		} catch (Exception exp) {
			return exp.getMessage();
		}
	}

	public static boolean updateBalance(double amount, String accountno) throws Exception {
		String sql = "update starflag_balance set balance=? where accountno=?";
		try {
			Connection conn = SingleTonConnection.connection();
			PreparedStatement prep = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			prep.setString(2, accountno);
			prep.setDouble(1, amount);
			prep.execute();
			if (amount < 0) {
				System.out.println("rollback done here");
				conn.rollback();
				conn.close();
				return false;
			} else {
				conn.commit();
				conn.close();
				return true;
			}
		} catch (SQLException exp) {
			throw new Exception(exp.getMessage());
		} catch (Exception exp) {
			throw new Exception(exp.getMessage());
		}
	}

	public static double getBalance(String account) throws Exception {
		double balance = -1;
		try {
			Connection conn = SingleTonConnection.connection();
			String sql = "select balance from starflag_balance where accountno=?";
			PreparedStatement prep = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			prep.setInt(1, Integer.parseInt(account));
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				balance = rs.getDouble(1);
				System.out.println("++++++++++++++++++++++++++++++++++" + balance);
			}
			conn.close();
			return balance;
		} catch (SQLException sql) {
			throw new Exception(sql.getMessage());
		} catch (Exception exp) {
			throw new Exception(exp.getMessage());
		}
	}
}