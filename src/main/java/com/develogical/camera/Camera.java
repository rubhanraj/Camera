package com.develogical.camera;

public class Camera {
	private WriteCompleteListener writeCompleteListener;
	private MemoryCard memoryCard;
	private Sensor sensor;
	public Camera(Sensor sensor, MemoryCard memoryCard, WriteCompleteListener writeCompleteListener) {
	    this.sensor = sensor;
	    this.memoryCard = memoryCard;
	    this.writeCompleteListener = writeCompleteListener;
	}

	public void pressShutter() {
        memoryCard.write(sensor.readData(), this.writeCompleteListener);
    }

    public void powerOn() {
        sensor.powerUp();
    }

    public void powerOff() {
      sensor.powerDown();
    }
}

