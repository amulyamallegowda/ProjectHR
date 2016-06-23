
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Trainings {

	static TrainingModel model = new TrainingModel();
	Scanner input = new Scanner(System.in);

	public void trainingsMenu(){

		try {

			System.out.println("Press 1 to view the new trainings assigned and to approve");
			System.out.println("Press 2 to view the approved trainings");			

			int choice = input.nextInt();

			if(choice==1){

				ApprovalTrainings();			

			}			
			else if(choice==2){
				printApprovedTrainings();

			}
			else{
				System.out.println("Invalid choice");	
			}


		} catch (Exception e) {		
			e.printStackTrace();
		}
	}

	private void ApprovalTrainings(){
		List<Object> list =  model.getApprovalTrainings();
		HashMap<String,Object> data; 
		for (int i=0; i<list.size();i++) {			
			data = (HashMap<String,Object>)list.get(i);
			Object[] columns = data.keySet().toArray();
			Object[] values = data.values().toArray();
			String leftAlignFormat = "| %-5s ";
			System.out.format(
					"%n+--------------------------------------------------------------------------------------------+%n");
			for (int j = 0; j < columns.length; j++) {
				System.out.format("|" + columns[j] + "	");
			}
			System.out.format(
					"%n+--------------------------------------------------------------------------------------------+%n");
			for (int j = 0; j < values.length; j++) {
				System.out.format(leftAlignFormat, values[j].toString());
			}
			System.out.format(
					"%n+--------------------------------------------------------------------------------------------+%n");
		}
		if(!list.isEmpty()){
			System.out.println("Press 1 to approve trainings");			
			System.out.println("Press 2 to reject training");

			int trainingChoice = input.nextInt();

			if(trainingChoice==1){
				System.out.println("Enter Training Id: ");
				int id = input.nextInt();
				if(approveTraining(id))
					System.out.println("Approved Trainings for Employee Id: "+id);

			}
			else if(trainingChoice == 2){
				System.out.println("Enter Training Id: ");
				int id = input.nextInt();
				if(rejectTraining(id))
					System.out.println("Rejected Trainings for Employee Id: "+id);

			}
			else{
				System.out.println("Invalid entry");
			}

		}
		else{
			System.out.println("No trainings!");
		}

	}

	private void printApprovedTrainings(){
		List<Object> list = model.getApprovedTrainings();
		HashMap<String,Object> data;
		for (int i = 0; i < list.size(); i++) {
			data = (HashMap<String,Object>)list.get(i);
			Object[] columns = data.keySet().toArray();
			Object[] values = data.values().toArray();
			String leftAlignFormat = "| %-5s ";
			System.out.format(
					"%n+--------------------------------------------------------------------------------------------+%n");
			for (int j = 0; j < columns.length; j++) {
				System.out.format("|" + columns[j] + "	");
			}
			System.out.format(
					"%n+--------------------------------------------------------------------------------------------+%n");
			for (int j = 0; j < values.length; j++) {
				System.out.format(leftAlignFormat, values[j].toString());
			}
			System.out.format(
					"%n+--------------------------------------------------------------------------------------------+%n");
		}


	}

	private boolean approveTraining(int id){
		return model.approveTrainingsAssigned(id);
	}

	private boolean rejectTraining(int id){
		return model.rejectTrainingsAssigned(id);
	}
}
