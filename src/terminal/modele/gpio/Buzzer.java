package terminal.modele.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;

/**
 * 
 * Classe permettant d'utiliser un buzzer passif
 * 
 * @author Bank-era Corp.
 *
 */
public class Buzzer {

	private static final int DEFAULT_PIN = 26;

	/**
	 * The PWM GPIO pin
	 */
	private GpioPinPwmOutput pwm = null;

	/**
	 * Default constructor using the default pin
	 */
	public Buzzer() {
		this(DEFAULT_PIN);
	}

	/**
	 * Constructor receiving the pin number. This number can range from 0 to 31. A
	 * diagram of the pinout is available at
	 * 
	 * @param pinNumber the pin number
	 */
	public Buzzer(int pinNumber) {

		// Pin setup
		final GpioController gpio = GpioFactory.getInstance();
		Pin pin = RaspiPin.getPinByAddress(pinNumber);
		pwm = gpio.provisionPwmOutputPin(pin);

		// PWM setup
		Gpio.pwmSetMode(Gpio.PWM_MODE_MS);
	}

	/**
	 * Makes the buzzer buzz at the specified frequency until it is changed or
	 * stopped
	 * 
	 * @param frequency the frequency to buzz at
	 */
	public void buzz(int frequency) {
		if (frequency == 0) {
			pwm.setPwm(frequency);
		} else {
			int range = 600000 / frequency;
			Gpio.pwmSetRange(range);
			pwm.setPwm(frequency / 2);
		}
	}

	/**
	 * Makes the buzzer buzz at the specified frequency for the specified duration
	 * (in ms)
	 * 
	 * @param frequency the frequency to buzz at
	 * @param duration  the duration of the buzz (ms)
	 */
	public void buzz(int frequency, long duration) {
		buzz(frequency);
		Gpio.delay(duration);
		stop();
	}

	/**
	 * Play a sound
	 * 
	 * @param sound the sound to play
	 */
	public void playSong(BuzzerSounds sound) {
		int[] notes = sound.getNotes();
		int[] temps = sound.getTemps();

		for (int i = 0; i < sound.getNotes().length; i++) {
			this.buzz(notes[i], temps[i] * sound.getDuration());
		}

		this.stop();
	}

	/**
	 * Stops the buzzing
	 */
	public void stop() {
		buzz(0);
	}
}
