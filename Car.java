package edu.cuny.csi.csc330.lab4;
public class Car {
	//delcaration of variables
	private String make;
	private  String model;
	private int year;
	private  int speed;
	private  boolean isOn;
	 
	 //constructors
	public Car(){
		
	}
	public Car(String make, String model, int year){
		 this.make = make;
		 this.model = model;
		 this.year = year;
		 speed = 0;
	 }
	 
	 // accessor and mutator methods
	 public void setMake(String s) {
		 make = s;
	 }
	 
	 public String getMake() {
		 return make;
	 }
	 
	 public void setModel(String s) {
		 model = s;
	 }
	 
	 public String getModel() {
		 return model;
	 }
	 
	 public void setYear(int year) {
		 this.year = year;
	 }
	 
	 public int getYear() {
		 return year;
	 }
	 
	 private boolean getStatus() {
		 return isOn;
	 }
	 
	 public int getSpeed(){
		 return speed;
	 }
	 
	 //other methods
	 public void startCar(){
		isOn = true;
	}
	 
	 public void Accelerate() {
		 if (!getStatus()) {
			 System.out.println("Car must be turned on!!");
			 return;}
		 speed+=10;
	 }
	 
	 public void Stop() {
		 if(!getStatus()) {
			 System.out.println("Car must be turned on!! ");
			 return;
		 }
		 speed = 0;
	 }
	 public void slowDown() {
		 if (!getStatus())
			 return;
		 speed-=5;
	 }
	 public void ShutCar() {
		 if (speed != 0) {
			 System.out.println("You must stop the car before shutting it off!!");
			 return;
		 }
		 isOn = false;
	 }
	 @Override
		public String toString() {
			return "Car [make=" + make + ", model=" + model + ", year=" + year + ", speed=" + speed + ", isOn=" + isOn
					+ "]";
		}

	
	
     //driver code
	public static void main(String[] args) {
		// TODO Auto-generated method stub
          Car car1 = new Car("Toyota", "RAV-4", 2017); //first instance of the car
          car1.startCar();
          car1.Accelerate();
          car1.Accelerate();
          System.out.println("car1 ");
          System.out.println(car1.toString());
          
          car1.Stop();
          car1.ShutCar();
          System.out.println("\n");
          System.out.println("car1 after car is turned off ");
          System.out.println(car1.toString());
          
          Car car2 = new Car();
          car2.setMake("Honda");
          car2.setModel("Civic");
          car2.setYear(2014);
          System.out.print("\n");
          System.out.println("car2");
          System.out.println(car2.toString());
          
          car2.startCar();
          car2.Accelerate();
          car2.Accelerate();
          car2.Accelerate();
          car2.Accelerate();
          car2.Accelerate();
          
          System.out.print("\n");
          System.out.println("car2 after turned on");
          System.out.println(car2.toString());
          System.out.println("Trying to shut down car while accelerating");
          car2.ShutCar();
         
          System.out.print("\n");
          car2.Stop();
          car2.ShutCar();
          System.out.println("Status of car2:");
          System.out.println(car2.toString());
          
          System.exit(0);
	}
}

	
