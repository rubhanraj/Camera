package com.develogical.camera;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

public class CameraTest {
    MemoryCard memoryCard;
    WriteCompleteListener writeCompleteListener;

    @Before public void initialize() {
      memoryCard = mock(MemoryCard.class);
      writeCompleteListener = mock(WriteCompleteListener.class);
    }

    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        Sensor sensor = mock(Sensor.class);
        Camera underTest = new Camera(sensor, memoryCard, writeCompleteListener);
        underTest.powerOn();

        verify(sensor).powerUp();
    }
    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {
        Sensor sensor = mock(Sensor.class);
        Camera underTest = new Camera(sensor, memoryCard, writeCompleteListener);
        underTest.powerOff();

        verify(sensor).powerDown();
    }

    //pressing the shutter with the power on copies data from the sensor to the memory card
    //Read Data
    @Test
    public void whenPressingShutterWithPowerOnShouldCopyData() {
        Sensor sensor = mock(Sensor.class);
        MemoryCard memoryCard = mock(MemoryCard.class);
        Camera underTest = new Camera(sensor, memoryCard, writeCompleteListener);
        underTest.powerOn();
        underTest.pressShutter();

        verify(sensor).readData();

    }

    //pressing the shutter with the power on copies data from the sensor to the memory card
    //Write Data
    @Test
    public void whenPressingShutterWithPowerOnShouldWriteData() {
        Sensor sensor = mock(Sensor.class);
        MemoryCard memoryCard = mock(MemoryCard.class);

        Camera underTest = new Camera(sensor, memoryCard, writeCompleteListener);
        underTest.powerOn();
        underTest.pressShutter();

        verify(memoryCard).write(sensor.readData(), writeCompleteListener);

    }

    //pressing the shutter with the power off does not copy data from the sensor to the memory card

    @Test
    public void whenPressingShutterWithPowerOffShouldNotCopyData() {
        Sensor sensor = mock(Sensor.class);
        MemoryCard memoryCard = mock(MemoryCard.class);

        Camera underTest = new Camera(sensor, memoryCard, writeCompleteListener);
        underTest.powerOff();
        underTest.pressShutter();
        verifyZeroInteractions(memoryCard);

    }
}
