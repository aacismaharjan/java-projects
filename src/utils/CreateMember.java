package utils;
import components.*;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CreateMember {
	public static void main(String[] args) {
		Member m = new Member();
		m.setId("0007ABCD");
		m.setLastName("Bacchan");
		m.setFirstName("Abhishek");
		m.setAddress("Bafal");
		m.setCity("Kathmandu");
		m.setState("KTM");
		m.setZip("04333");
		
		Main main = new Main();
		try {
			main.writeMember(m, new BufferedWriter(new FileWriter("./members.txt", true)));
		}catch (Exception err){
			err.printStackTrace();
		}
	}
}
