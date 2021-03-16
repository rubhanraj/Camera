package com.develogical.camera;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

public class CameraTest {
    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        Sensor sensor = mock(Sensor.class);
        Camera underTest = new Camera(sensor);
        underTest.powerOn();

        verify(sensor).powerUp();
    }
    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {
        Sensor sensor = mock(Sensor.class);
        Camera underTest = new Camera(sensor);
        underTest.powerOff();

        verify(sensor).powerDown();
    }

    //pressing the shutter with the power on copies data from the sensor to the memory card
    //Read Data
    @Test
    public void whenPressingShutterWithPowerOnShouldCopyData() {
        Sensor sensor = mock(Sensor.class);
        MemoryCard memoryCard = mock(MemoryCard.class);
        Camera underTest = new Camera(sensor);
        underTest.powerOn();
        underTest.pressShutter();

        verify(sensor).readData();

    }

    //pressing the shutter with the power on copies data from the sensor to the memory card
    //Write Data
    @Test
    public void whenPressingShutterWithPowerOnShouldWriteData() {
        WriteCompleteListener writeCompleteListener = mock(WriteCompleteListener.class);
        Sensor sensor = mock(Sensor.class);
        MemoryCard memoryCard = mock(MemoryCard.class);

        Camera underTest = new Camera(sensor);
        underTest.powerOn();
        underTest.pressShutter();

        verify(memoryCard).write(sensor.readData(), writeCompleteListener);

    }
}
