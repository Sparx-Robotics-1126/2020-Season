package frc.subsystem;

import frc.acq.AcqCommand;

public class Acquisitions extends Subsystem{

	private AcqCommand acqCommand;
	
	@Override
	void execute() {
		if(acqCommand != null) {
			double output = acqCommand.execute();
			//Set Motor Values
			acqCommand = null;
		}
	}

	@Override
	public boolean isDone() {
		return acqCommand == null;
	}

}
