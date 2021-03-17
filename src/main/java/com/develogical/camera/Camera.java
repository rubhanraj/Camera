package com.develogical.camera;

public class Camera implements WriteCompleteListener {
	private MemoryCard memoryCard;
	private Sensor sensor;
	private boolean poweredOn;
	private boolean writingInProgress;

	public Camera(Sensor sensor, MemoryCard memoryCard) {
	    this.sensor = sensor;
	    this.memoryCard = memoryCard;

	}

	public void pressShutter() {
		if(!poweredOn){
			return;
		}
		this.writingInProgress = true;
        memoryCard.write(sensor.readData(), this);
    }

    public void powerOn() {
        sensor.powerUp();
        this.poweredOn = true;
    }

    public void powerOff() {
	 if(!writingInProgress)	{
	 	sensor.powerDown();
	 }
      this.poweredOn = false;
    }

	@Override
	public void writeComplete() {
		this.writingInProgress = false;
		this.sensor.powerDown();
	}
}

