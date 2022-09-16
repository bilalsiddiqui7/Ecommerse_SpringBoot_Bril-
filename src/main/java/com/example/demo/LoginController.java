package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CartItems;
import com.example.demo.model.ChangePassword;
import com.example.demo.model.Credentials;
import com.example.demo.model.ProductDetails;
import com.example.demo.model.RegisterUser;
import com.example.demo.repo.CartRepo;
import com.example.demo.repo.ProductRepo;

@RestController
@CrossOrigin("*")
public class LoginController {
//	@RequestBody Employee employee
	@PostMapping("/getstudents")
	public boolean checkLogin(@RequestBody Credentials cred) {
		String username = cred.getName();

		String password = cred.getPassword();
		boolean isLogin = false;

		String url = "jdbc:mysql://localhost/aliensdata";
		String uname = "root";
		String pass = "root";
		String query = "select * from loginDetails";
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, uname, pass);

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String u = rs.getString("userName");
				String p = rs.getString("password");
				if (u.equals(username) && p.equals(password)) {
					isLogin = true;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {

		}
		return isLogin;
	}

	@PostMapping("/changepassword")
	public boolean changePassword(@RequestBody ChangePassword changePassword) {
		String oldPass = changePassword.getOldPassword();
		String userName = changePassword.getUserName();
		String confirmPass = changePassword.getNewPassword();

		boolean isLogin = false;

		String url = "jdbc:mysql://localhost/aliensdata";
		String uname = "root";
		String pass = "root";
		String query = "select * from loginDetails";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pass);
			PreparedStatement ps1 = con.prepareStatement("update loginDetails set password=? where userName=?");
			ps1.setString(1, confirmPass);
			ps1.setString(2, userName);
			int count = ps1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static String vowelsChecker(String str) {
		String r = "";
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i) == 'A') || (str.charAt(i) == 'E') || (str.charAt(i) == 'I') || (str.charAt(i) == 'O')
					|| (str.charAt(i) == 'U')) {
				r = r + str.charAt(i);
			}
		}
		return r;
	}

	public static int sumOfIntegers(String str) {
		int sum = 0;
		int len = str.length();
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				sum = sum + Character.getNumericValue(str.charAt(i));
			}
		}
		return sum;
	}

	public static String userNameMaker(String input) {
		String res = input.substring(input.length() - 4);
		return res;
	}

	public static String passwordMaker(String firstName, String lastName, String mobileNum, String aadharNum,
			String dob) {
		String res = "";
		char firstChar = lastName.charAt(0);
		String s1 = "" + firstChar;
		char lastChar = firstName.charAt(firstName.length() - 1);
		String s2 = "" + lastChar;
		char a = mobileNum.charAt(0);
		char b = mobileNum.charAt(2);
		char c = mobileNum.charAt(4);
		char d = mobileNum.charAt(6);
		char e = mobileNum.charAt(8);
		String mobileOdd = new StringBuilder().append(a).append(b).append(c).append(d).append(e).toString();
		String vowels = vowelsChecker(aadharNum);
		int sumOfaadhar = sumOfIntegers(aadharNum) % 9;
		int sumOfDob = sumOfIntegers(dob) % 9;
		res = s1.toUpperCase() + s2.toUpperCase() + mobileOdd + vowels + Integer.toString(sumOfaadhar)
				+ Integer.toString(sumOfDob);

		return res;
	}

	public static String removeSpecialCharacterFromDate(String input) {
		String str;
		str = input.replaceAll("[^a-zA-Z0-9]", "");
		return str;
	}

	@PostMapping("/registration")
	public boolean registration(@RequestBody RegisterUser registerUser) {

//		String username = request.getParameter("name");
//		String password = request.getParameter("password");
		boolean isLogin = false;

		String url = "jdbc:mysql://localhost/aliensdata";
		String uname = "root";
		String pass = "root";
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uname, pass);

			String firstName = registerUser.getFirstName();
			String lastName = registerUser.getLastName();
			String mobileNum = registerUser.getMobileNum();
			String aadharNum = registerUser.getAadharNum();
			String dateOfBirth = removeSpecialCharacterFromDate(registerUser.getDob());
//			System.out.println("DOB-> " + dateOfBirth);

			String lastFourDigitsOfAadhar = userNameMaker(aadharNum);
			String newuName = firstName + lastFourDigitsOfAadhar;
			String newPass = passwordMaker(firstName, lastName, mobileNum, aadharNum, dateOfBirth);

			PreparedStatement ps = con.prepareStatement("insert into loginDetails values(?,?,?,?,?,?,?)");
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, mobileNum);
			ps.setString(4, aadharNum);
			ps.setString(5, dateOfBirth);
			ps.setString(6, newuName);
			ps.setString(7, newPass);
			int c = ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	@Autowired
	private ProductRepo productRepo;

	@RequestMapping(path = "/getProductDetails", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ProductDetails> getProducts() {
		return this.productRepo.findAll();
	}

	@Autowired
	private CartRepo cartRepo;

	@PostMapping("/addtocart")
	public void addToCart(@RequestBody ArrayList<CartItems> cartItems) {
		for (CartItems cI : cartItems) {
			System.out.println("The cI value is -> "+cI);
			cartRepo.save(cI);
		}
	}
}