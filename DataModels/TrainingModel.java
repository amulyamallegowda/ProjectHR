import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrainingModel {

	DataAccess dataAccess;

	public List<Object> getApprovalTrainings(){
		List<Object> dataset = new ArrayList();		
		try {
			dataAccess = new DataAccess();	
			dataset = dataAccess.getTrainingsToApprove();			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}	
		return dataset;		
	}

	public List<Object> getApprovedTrainings(){
		List<Object> approvedDataset  = new ArrayList();		
		try {
			dataAccess = new DataAccess();
			approvedDataset = dataAccess.getApprovedTrainings();					 

		} 
		catch(Exception e) {
			e.printStackTrace();
		}	
		return approvedDataset;		
	}

	public boolean approveTrainingsAssigned(int Id){
		try {
			dataAccess = new DataAccess();
			if(!dataAccess.approveTrainingStatus(Id)){
				System.out.println("Error in approving the training");
				return false;
			}			 
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
		return true;
	}

	public boolean rejectTrainingsAssigned(int Id){
		try {
			dataAccess = new DataAccess();
			if(!dataAccess.rejectTraining(Id)){
				System.out.println("Error in rejecting the training");
				return false;
			}			 
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
		return true;
	}

}
