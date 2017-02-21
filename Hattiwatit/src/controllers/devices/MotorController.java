package controllers.devices;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class MotorController extends DeviceController {
	private enum Direction {
		backward, forward
	}

	private Direction directionR = Direction.forward,
					  directionL = Direction.forward;
	private EV3LargeRegulatedMotor motorR,
								   motorL;
	private int speedR,
				speedL,
				defaultSpeed;

	public MotorController(Port right, Port left, int defaultSpeed) {
		motorR = new EV3LargeRegulatedMotor(right);
		motorL = new EV3LargeRegulatedMotor(left);
		this.defaultSpeed = defaultSpeed;
	}

	@Override
	protected void action() {
		motorR.setSpeed(speedR);

		switch (directionR) {
		case forward:
			motorR.forward();
			break;
		case backward:
			motorR.backward();
			break;
		}

		motorL.setSpeed(speedL);

		switch (directionL) {
		case forward:
			motorL.forward();
			break;
		case backward:
			motorL.backward();
			break;
		}
	}

	public void backward() {
		this.backward(defaultSpeed);
	}

	public void backward(int speed) {
		setSpeed(speed);
		directionR = Direction.forward;
		directionL = Direction.forward;
	}

	@Override
	protected void cleanUp() {
		motorR.close();
		motorL.close();
	}

	@Override
	public void disable() {
		halt();
		super.disable();
	}

	@Override
	public void enable() {
		speedR = defaultSpeed;
		speedL = defaultSpeed;
		super.enable();
	}

	public void forward() {
		this.forward(defaultSpeed);
	}

	public void forward(int speed) {
		setSpeed(speed);
		directionR = Direction.backward;
		directionL = Direction.backward;
	}

	public void halt() {
		setSpeed(0);
		motorR.stop(true);
		motorL.stop(true);
	}

	public void left() {
		this.left(defaultSpeed);
	}

	public void left(int speed) {
		setSpeed(speed);
		directionR = Direction.backward;
		directionL = Direction.forward;
	}

	public void right() {
		this.right(defaultSpeed);
	}

	public void right(int speed) {
		setSpeed(speed);
		directionR = Direction.forward;
		directionL = Direction.backward;
	}

	public void setSpeed(int speed) {
		speedR = speed;
		speedL = speed;
	}
}