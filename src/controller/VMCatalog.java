package controller;

import java.util.Scanner;

import controller.VMCatalog;
import controller.VMInstanciable;
import controller.VMInstantiated;

public class VMCatalog {
	
	private boolean continu;
	private Scanner sc;
	private VMInstantiated myVMInstatiated;
	private VMInstanciable myInstanciableVM;
	
	public VMCatalog() {
		
		this.continu = true;
		this.sc = new Scanner(System.in);
		this.myVMInstatiated = new VMInstantiated("https://192.168.176.15/sdk", "root", "*master%lbdl+");
		this.myInstanciableVM = new VMInstanciable("/home/nicolas/listVM");
		
		this.menu();
	}
	
	private void menu() {
		
		System.out.println("/*****=====================*****\\");
		System.out.println("|========== VM Catalog =========|");
		System.out.println("\\*****=====================*****/");
		while(continu) {
			System.out.println("1) Show list of instanciable VM");
			System.out.println("2) Show list of VM instantiated");
			System.out.println("3) Get some information about a VM");
			System.out.println("4) Launch a existing VM");
			System.out.println("5) Create a new VM");
			System.out.println("6) Take a snapshot");
			System.out.println("0) Exit!");
			treatResponce(sc.nextLine());
		}
	}
	
	private void treatResponce(String nextLine) {
		
		switch (nextLine) {
		case "1":
			this.myInstanciableVM.showVMs();
			break;
		case "2":
			this.myVMInstatiated.showVMs();
			break;
		case "3":
			this.getInformations();
			break;
		case "0":
			this.myVMInstatiated.logout();
			break;
		default:
			break;
		}
	}

	private void getInformations() {
		
		System.out.println("Of which VM do you want info ?");
		this.myInstanciableVM.showVMs();
		this.myVMInstatiated.showVMs();
		String rep = this.sc.nextLine();
		try {
			this.myVMInstatiated.listInfoVM(Integer.decode(rep));
		} catch (NumberFormatException e) {
			char[] repc = rep.toCharArray();
			this.myInstanciableVM.getDescription((int) repc[0] - 97);
		}
	}

	public static void main (String[] args) {
		
		new VMCatalog();
	}
}
