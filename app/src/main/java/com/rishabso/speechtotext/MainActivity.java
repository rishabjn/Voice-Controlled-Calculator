package com.rishabso.speechtotext;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

	private TextView txvResult,resView;
    public ArrayList<String> result;
   public int strr[] = new int[100];
   float m1,m2;
   String input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txvResult = (TextView) findViewById(R.id.txvResult);
		resView = (TextView) findViewById(R.id.resView);

	}

	public void getSpeechInput(View view) {

		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(intent, 10);
		} else {
			Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case 10:
				if (resultCode == RESULT_OK && data != null) {
					result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
					txvResult.setText(result.get(0));
					input = txvResult.getText().toString();
					System.out.println(input);
					String[] s = input.split(" ");
					for(String str : s){
						System.out.println(str);
					}
					int v = input.length();
					System.out.println(v);

					calc(s,v);
				}
				break;


		}
	}
	public void calc(String s[], int v){

		String add = "+",sub = "-", mul = "x", div = "/";
		// for add say eg. "23 plus 16 ".
		// for multiplication say "23 x 2 ". Here x is letter x.
		// for division say "15 divided by 14".
		// for subtraction say "15 minus 7"

		//Accepts floating values.

		m1 = Float.parseFloat(s[0]);
		m2 = Float.parseFloat(s[2]);

			if (s[1].equals(div)) {
				System.out.println(m1 + "\t" + m2);
				String xy = String.valueOf(m1 / m2);
				resView.setText(xy);
			}
			else if(s[1].equals(add)) {
				System.out.println(m1 + "\t" + m2);
				String xy = String.valueOf(m1 + m2);
				resView.setText(xy);
			}
			else if (s[1].equals(sub)) {
				System.out.println(m1 + "\t" + m2);
				String xy = String.valueOf(m1 - m2);
				resView.setText(xy);
			}

			else if (s[1].equals(mul)) {
				System.out.println(m1 + "\t" + m2);
				String xy = String.valueOf(m1 * m2);
				resView.setText(xy);
			}


	}

}

