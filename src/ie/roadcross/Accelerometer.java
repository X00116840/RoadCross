package ie.roadcross;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Date;

public class Accelerometer extends MainActivity  implements SensorEventListener
{
  private int TIME_OUT = 6;
  private Sensor accelerometer;
  private ImageView amberLight;
  Date date = new Date();
  private float deltaX = 0.0F;
  private float deltaY = 0.0F;
  private float deltaZ = 0.0F;
  private ImageView greenLight;
  private float lastX;
  private float lastY;
  private float lastZ;
  private ImageView redLight;
  private SensorManager sensorManager;
  Long time;
  int timeChange = 0;
  private TextView totalSpeed;
  
  public void onCreate(Bundle savedInstanceState)
  {		
	  
	super.onCreate(savedInstanceState);
	setContentView(R.layout.accelerometer);
		
    initializeViews();
    
    this.sensorManager = ((SensorManager)getSystemService("sensor"));
    if (this.sensorManager.getDefaultSensor(1) != null)
    {
      this.accelerometer = this.sensorManager.getDefaultSensor(1);
      this.sensorManager.registerListener(this, this.accelerometer, 3);
      this.time = Long.valueOf(60 * (60 * this.date.getHours() + this.date.getMinutes()) + this.date.getSeconds());
    }
  }
  
  public void initializeViews()
  {
		redLight = (ImageView) findViewById(R.id.imageView1);
		amberLight = (ImageView) findViewById(R.id.imageView2);
		greenLight = (ImageView) findViewById(R.id.imageView3);
		//totalSpeed = (TextView) findViewById(R.id.textView1);
		
    	this.redLight.setImageResource(R.drawable.redlight);
		this.amberLight.setImageResource(R.drawable.lightoff);
		this.greenLight.setImageResource(R.drawable.lightoff);
  }
  
  public boolean changingLights(int clock)
  {

    if (clock == time+2)
    {
    	this.redLight.setImageResource(R.drawable.lightoff);
		this.amberLight.setImageResource(R.drawable.lightoff);
		this.greenLight.setImageResource(R.drawable.greenlight);	
		return true;
    }
    if (clock == time+4)
    {
    	this.redLight.setImageResource(R.drawable.lightoff);
		this.amberLight.setImageResource(R.drawable.amberlight);
		this.greenLight.setImageResource(R.drawable.lightoff);	
		return true;
    }
    if (clock == time+6)
    {
    	this.redLight.setImageResource(R.drawable.redlight);
		this.amberLight.setImageResource(R.drawable.lightoff);
		this.greenLight.setImageResource(R.drawable.lightoff);	
		return true;
    }
    if (clock == time+8)
    {
    	this.redLight.setImageResource(R.drawable.redlight);
		this.amberLight.setImageResource(R.drawable.lightoff);
		this.greenLight.setImageResource(R.drawable.lightoff);	
		onPause();
	    startActivity(new Intent(this, MainActivity.class));
	    finish();
		return true;
    }
    return false;
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    timer();
    displayCurrentValues();
    
    // set the last known values of x,y,z
    this.lastX = paramSensorEvent.values[0];
    this.lastY = paramSensorEvent.values[1];
    this.lastZ = paramSensorEvent.values[2];
    
    this.deltaX = Math.abs(this.lastX - paramSensorEvent.values[0]);
    this.deltaY = Math.abs(this.lastY - paramSensorEvent.values[1]);
    this.deltaZ = Math.abs(this.lastZ - paramSensorEvent.values[2]);
    
	// if the change is below 10.3, it is just plain noise
	if (deltaX < 10.3)
		deltaX = 0;
	if (deltaY < 10.3)
		deltaY = 0;
	if (deltaZ < 10.3)
		deltaZ = 0;

    
  //current speed is given by the sum of the most relevant movement for each axe of the accelerometer
    		float currentSpeed= (deltaX+deltaY+deltaZ);

    		if (playerSpeed<currentSpeed)
    			playerSpeed=currentSpeed;

  }
  
   
  public void displayCurrentValues()
  {
	  //totalSpeed.setText(String.format("%.2f", totalSpeed));
  }
  
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onBackPressed()
  {
    moveTaskToBack(true);
    finish();
  }
  

  
  protected void onPause()
  {
    super.onPause();
    Log.d(this.msg, "onPause *********************************");
    this.sensorManager.unregisterListener(this);

  }
  
  protected void onResume()
  {
    super.onResume();
    Log.d(this.msg, "onResume *********************************");
    this.sensorManager.registerListener(this, this.accelerometer, 3);
  }
  

  public void restart(View paramView)
  {
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }
  
  public void timer()
  {
    Date localDate = new Date();
    long currentTime = 60 * (60 * localDate.getHours() + localDate.getMinutes()) + localDate.getSeconds();
    changingLights((int)currentTime);
    
  }
}
