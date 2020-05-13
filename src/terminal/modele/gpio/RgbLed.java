package terminal.modele.gpio;

import java.util.function.Consumer;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Classe permettant de contrôler une lumière DEL RGB sur un Raspberry Pi
 * 
 * @author Bank-era Corp.
 *
 */
public class RgbLed {

	public static final byte RED = 0b0001;
	public static final byte GREEN = 0b0010;
	public static final byte BLUE = 0b0100;

	private static final int DEFAULT_RED_PIN = 23;
	private static final int DEFAULT_BLUE_PIN = 24;
	private static final int DEFAULT_GREEN_PIN = 25;

	/**
	 * The pins for each color. [0] is red, [1] is green and [2] is blue
	 */
	private GpioPinDigitalOutput[] pins;

	/**
	 * Constructor using the default pins
	 */
	public RgbLed() {
		this(DEFAULT_RED_PIN, DEFAULT_GREEN_PIN, DEFAULT_BLUE_PIN);
	}

	/**
	 * Constructor that takes the number of each pin
	 * 
	 * @param redPinNum   the red pin number
	 * @param greenPinNum the green pin number
	 * @param bluePinNum  the blue pin number
	 */
	public RgbLed(int redPinNum, int greenPinNum, int bluePinNum) {
		pins = new GpioPinDigitalOutput[3];
		final GpioController gpio = GpioFactory.getInstance();

		pins[0] = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(redPinNum));
		pins[1] = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(greenPinNum));
		pins[2] = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(bluePinNum));
	}

	/**
	 * Blink one or multiple colors
	 * 
	 * @param colors   the color to blink
	 * @param duration the duration to blink (in ms)
	 */
	public void blink(int colors, long duration) {
		executeFunctionOnSelectColors(colors, i -> pins[i].blink(duration));
	}

	/**
	 * Stop one or more colors from blinking
	 * 
	 * @param colors the color(s) to stop blinking
	 */
	public void stopBlink(int colors) {
		executeFunctionOnSelectColors(colors, i -> {
			pins[i].blink(0);
			pins[i].setState(PinState.LOW);
		});
	}

	/**
	 * Stop the led from blinking
	 */
	public void stopAllBlink() {
		for (int i = 0; i < 3; i++) {
			pins[i].blink(0);
			pins[i].setState(PinState.LOW);
		}
	}

	/**
	 * Sets all specified colors to high
	 * 
	 * @param colors the colors to set high
	 */
	public void setLow(int colors) {
		this.setState(colors, PinState.LOW);
	}

	/**
	 * Sets all specified colors to low
	 * 
	 * @param colors the colors to set low
	 */
	public void setHigh(int colors) {
		this.setState(colors, PinState.HIGH);
	}

	/**
	 * Sets all the specified colors to the specified state
	 * 
	 * @param colors the colors to set
	 * @param state  the state to set the pins to
	 */
	public void setState(int colors, PinState state) {
		executeFunctionOnSelectColors(colors, i -> pins[i].setState(state));
	}

	/**
	 * Pulls every pin to low
	 */
	public void setAllLow() {
		for (GpioPinDigitalOutput pin : pins) {
			pin.setState(PinState.LOW);
		}
	}
	
	/**
	 * Execute a function on the selected colors
	 * 
	 * @param colors the colors to execute the function on
	 * @param function the function to execute on every pin of the specified colors
	 */
	private void executeFunctionOnSelectColors(int colors, Consumer<Integer> function) {
		for (int i = 0; i < 3; i++) {
			if (((0b0001 << i) & colors) != 0) {
				function.accept(i);
			}
		}
	}
}
