package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.vmware.vim25.mo.VirtualMachine;

import controller.VMachine;

public class VMInstanciable {

	public File directory;
	private ArrayList<VMachine> listVM;
	
	public ArrayList<VMachine> getListVM() {
		return listVM;
	}

	public VMInstanciable () {
		this.directory = new File("/home/nicolas/listVM");
		listVM = new ArrayList<VMachine>();
		this.fillInListVM();
	}
	
	public VMInstanciable(String directory) {
		this.directory = new File(directory);
		listVM = new ArrayList<VMachine>();
		this.fillInListVM();
	}
	
	private void fillInListVM() {
		
		for (File subDirectory : directory.listFiles()) {

			VMachine temp = new VMachine(subDirectory.getName());
			temp.fillInlistDifferentTypeVM(subDirectory.listFiles());
			listVM.add(temp);
		}
	}
	
	public String showVMs() {
		
		String retour = "";
		//char c = 'a';
		//System.out.println("These are all instanciable VM :");
		for (VMachine machine : listVM) {
			retour += machine.getName()+"<br/>";
			//System.out.println("\t"+(c++)+") "+machine.getName());
		}
		return retour;
	}
	
	public void getDescription(int vmNumber) {
		
		System.out.println(this.listVM.get(vmNumber).getDescription());
	}
	
	public ArrayList<String> getListofVMName() {
		
		ArrayList<String> myList = new ArrayList<String>();
		for (VMachine vm : this.listVM) {
			myList.add(vm.getName());
		}
		
		return myList;
	}

}
