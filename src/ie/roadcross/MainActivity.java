package ie.roadcross;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	public String msg = "WATCHING";
	public float playerSpeed=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	public void play(View view)
	{
	  startActivity(new Intent(this, Accelerometer.class));
	  
	}
	
	public void showInstructions(View view)
	{
	  startActivity(new Intent(this, Instructions.class));
	  
	}
	
	public void showRanking(View view)
	{
	  startActivity(new Intent(this, Ranking.class));
	  
	}
	
	public void exitApp(View paramView)
	  {
		Log.d(this.msg, "<<<<<<<<<<<EXIT>>>>>>>>>>>>>>>");
		this.finish();
	    //System.exit(1);
	  }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
