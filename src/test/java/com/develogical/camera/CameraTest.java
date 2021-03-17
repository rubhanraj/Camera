package com.develogical.camera;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class CameraTest {
	MemoryCard memoryCard;
	WriteCompleteListener writeCompleteListener;
	Sensor sensor;
	Camera underTest;

	@Before
	public void initialize() {
		memoryCard = mock(MemoryCard.class);
		writeCompleteListener = mock(WriteCompleteListener.class);
		sensor = mock(Sensor.class);
		underTest = new Camera(sensor, memoryCard);
	}

	@Test
	public void switchingTheCameraOnPowersUpTheSensor() {
		underTest.powerOn();
		verify(sensor).powerUp();
	}

	@Test
	public void switchingTheCameraOffPowersDownTheSensor() {
		underTest.powerOff();
		verify(sensor).powerDown();
	}

	//pressing the shutter with the power on copies data from the sensor to the memory card
	//Read Data
	@Test
	public void whenPressingShutterWithPowerOneShouldCopyData() {
		underTest.powerOn();
		underTest.pressShutter();

		verify(sensor).readData();

	}

	//pressing the shutter with the power on copies data from the sensor to the memory card
	//Write Data
	@Test
	public void whenPressingShutterWithPowerOnShouldWriteData() {
		underTest.powerOn();
		underTest.pressShutter();
		verify(memoryCard).write(sensor.readData(), underTest);

	}

	//pressing the shutter with the power off does not copy data from the sensor to the memory card

	@Test
	public void whenPressingShutterWithPowerOffShouldNotCopyData() {
		underTest.powerOff();
		underTest.pressShutter();
		verifyZeroInteractions(memoryCard);

	}

	// if data is currently being written, switching the camera off does not power down the  sensor
	@Test
    public void switchingTheCameraOffDoesNotPowersDownTheSensorWhenDataIsWritten() {
        underTest.powerOn();
	    underTest.pressShutter();
		underTest.powerOff();
		verify(sensor, times(0)).powerDown();

	}

    // once writing the data has completed, then the camera powers down the sensor
    @Test
    public void switchingTheCameraOffDoesAtLeastPowerDownTheSensorWhenDataIsWritten() {
        underTest.powerOn();
        underTest.pressShutter();
        underTest.powerOff();
        verify(sensor, times(0)).powerDown();
        underTest.writeComplete();
        verify(sensor, times(1)).powerDown();


    }
}
