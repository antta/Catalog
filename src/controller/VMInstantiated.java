package controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import vmware.controller.VMachine;

import com.vmware.vim25.MethodFault;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.VirtualMachineCapability;
import com.vmware.vim25.VirtualMachineConfigInfo;
import com.vmware.vim25.mo.Datacenter;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;


public class VMInstantiated {

	private ServiceInstance myConnection;
	private Folder myFolder;
	private ManagedEntity[] myManagedEntities;
	private ArrayList<VirtualMachine> myVirtualMachines;
	private ArrayList<VMachine> myVMs;
	
	
	public ArrayList<VMachine> getMyVMs() {
		return myVMs;
	}

	public void setMyVMs(ArrayList<VMachine> myVMs) {
		this.myVMs = myVMs;
	}

	public VMInstantiated(String url, String user, String password) {

		try {
			this.myConnection = new ServiceInstance(new URL(url), user, password, true);
			this.myFolder = this.myConnection.getRootFolder();
			this.myManagedEntities = new InventoryNavigator(this.myFolder).searchManagedEntities("VirtualMachine");
			this.managedEntitiesToVirtualMachine();
		} catch (RemoteException | MalformedURLException e) {
			System.out.println("Can't connect to the server");
			e.printStackTrace();
		}
		
		this.myVMs = new ArrayList<VMachine>();
		this.setAllVMachine();
	}

	private boolean listVMisEmpty() {

		return this.myManagedEntities.length == 0 ? true : false;

	}
	
	public ArrayList<String> getListofVMName() {
		
		ArrayList<String> myList = new ArrayList<String>();
		for (VirtualMachine vm : this.myVirtualMachines) {
			myList.add(vm.getName());
		}
		
		return myList;
	}

	public String showVMs() {

		String retour = "";
		//int i = 0;
		//System.out.println("These are all VM instantiated :");
		for (VirtualMachine vm : this.myVirtualMachines) {
			retour += vm.getName()+"<br/>";
			//System.out.println("\t"+(++i)+" - "+vm.getName());
		}
		return retour;
	}

	public void listInfoVM(int vmNumber) {

		VirtualMachine vmSelected = this.myVirtualMachines.get(vmNumber);
		VirtualMachineConfigInfo vminfo = vmSelected.getConfig();
		VirtualMachineCapability vmc = vmSelected.getCapability();


		try {
			vmSelected.getResourcePool();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(">>Information v1 by ME<<");
		System.out.println("\tVM Name : "+vmSelected.getName());
		System.out.println("\tOS Name : " +vminfo.getGuestFullName());
		System.out.println("\tMemory Allocated : "+vminfo.getMemoryAllocation());
		System.out.println("\tLocation : "+vminfo.getLocationId());
		System.out.println("\tMultiple snapshot : "+vmc.isMultipleSnapshotsSupported());
		System.out.println("\tAnnotation : "+vminfo.getAnnotation());
		System.out.println("\tAlternate Guest Name : "+vminfo.getAlternateGuestName());
		System.out.println("\tDataStore url : "+vminfo.getDatastoreUrl());
		System.out.println("\tVersion : "+vminfo.getVersion());
		System.out.println(">>Information v2 by pierreLx<<");
		System.out.println("\tGuest OS:"+vmSelected.getSummary().getConfig().getGuestFullName());  
		System.out.println("\tVM Version:"+vmSelected.getConfig().getVersion());  
		System.out.println("\tCPU:"+vmSelected.getConfig().getHardware().getNumCPU()+" vCPU");  
		System.out.println("\tMemory:"+vmSelected.getConfig().getHardware().getMemoryMB()+" MB");  
		System.out.println("\tMemory Overhead:"+(long)vmSelected.getConfig().getInitialOverhead().getInitialMemoryReservation()/1000000f+" MB");  
		System.out.println("\tVMware Tools:"+vmSelected.getGuest().getToolsRunningStatus());  
		System.out.println("\tIP Addresses:"+vmSelected.getSummary().getGuest().getIpAddress());         
		System.out.println("\tState:"+vmSelected.getGuest().getGuestState());  
	}
	
	private void setAllVMachine() {
		
		for (VirtualMachine vm : this.myVirtualMachines) {
			String name = vm.getName();
			String description = "";

			VirtualMachineConfigInfo vminfo = vm.getConfig();
			VirtualMachineCapability vmc = vm.getCapability();
			
			try {
				vm.getResourcePool();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			description += "<table align=\"center\">";
			description += "<tr><td>Guest OS:</td><td>"+vm.getSummary().getConfig().getGuestFullName()+"</td></tr>";
			description += "<tr><td>VM Version:</td><td>"+vm.getConfig().getVersion()+"</td></tr>";
			description += "<tr><td>CPU:</td><td>"+vm.getConfig().getHardware().getNumCPU()+" vCPU"+"</td></tr>";
			description += "<tr><td>Memory:</td><td>"+vm.getConfig().getHardware().getMemoryMB()+" MB"+"</td></tr>";
			description += "<tr><td>Memory Overhead:</td><td>"+(long)vm.getConfig().getInitialOverhead().getInitialMemoryReservation()/1000000f+" MB"+"</td></tr>";
			description += "<tr><td>VMware Tools:</td><td>"+vm.getGuest().getToolsRunningStatus()+"</td></tr>";
			description += "<tr><td>IP Addresses:</td><td>"+vm.getSummary().getGuest().getIpAddress()+"</td></tr>";
			description += "<tr><td>State:</td><td>"+vm.getGuest().getGuestState()+"</td></tr>";
			description += "</table>";
			
			this.myVMs.add(new VMachine(name, description));
		}
	}

	private void managedEntitiesToVirtualMachine() {

		if(!this.listVMisEmpty()) {
			this.myVirtualMachines = new ArrayList<VirtualMachine>();
			for (int i = 0; i < this.myManagedEntities.length; i++) {
				this.myVirtualMachines.add((VirtualMachine) this.myManagedEntities[i]);
			}
		}
		else {
			System.out.println("Caution : No VM available");
		}
	}

	public void logout() {

		this.myConnection.getServerConnection().logout();
	}
}
