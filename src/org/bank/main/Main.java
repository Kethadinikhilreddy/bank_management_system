package org.bank.main;

import java.util.Scanner;

import org.bank.service.AdminService;
import org.bank.service.CustomerService;

public class Main {

		public static void main(String[] args) {
			
			CustomerService cs=new CustomerService();
			AdminService as=new AdminService();
			Scanner s = new Scanner(System.in);

//			String a = "❤‍🔥🦁🤺🤺 welcome to My Bank 🤺🤺🦁❤‍🔥";
//			
//			for(int i=0;i<=a.length()-1;i++)
//			{
//			System.out.print(a.charAt(i));
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
			
			
			while(true) {
				System.out.println(" \n Enter "
						+ "\n 1. For coustomer Registration "
						+ "\n 2. For customer login" 
						+ "\n 3. For Admin Login "
						+ "\n 4. For Exit");
				int request = s.nextInt();

				switch (request) {
				case 1:
					cs.customerRegistration();
					break;
		        
				case 2:
					System.out.println("Coustomer login");
					cs.customerLogin();
					break;
					
				case 3:
					System.out.println("Admin login");
					as.adminLogin();
					break;
				case 4:
					System.out.println("Thank You😊");
					System.exit(0);
				default:
					System.out.println("Invalid Login");	
					break;
				}
			}
		}

}
