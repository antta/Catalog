package testjar;

import vmware.controller.VMInstantiated;

public class Catalog {
	
	public static void main (String[] args) {
		
		VMInstantiated v1 = new VMInstantiated("https://192.168.176.15/sdk", "root", "*master%lbdl+");
		v1.showVMs();
	}

}
