package com.example.jacob.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * MainActivity class
 *
 * @author Jacob Bryant
 * @version 2/11/16
 */
public class MainActivity extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    //instance variables for views
    protected Face face;
    protected RadioButton hairButton;
    protected RadioButton eyeButton;
    protected RadioButton skinButton;
    protected SeekBar redBar;
    protected SeekBar greenBar;
    protected SeekBar blueBar;
    protected TextView redText;
    protected TextView greenText;
    protected TextView blueText;
    protected Spinner hairSpinner;
    protected String[] hairEntries;
    protected Spinner eyeSpinner;
    protected String[] eyeEntries;
    protected Spinner noseSpinner;
    protected String[] noseEntries;
    protected Button randomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization of views and set appropriate listeners
        //buttons
        hairButton = (RadioButton)findViewById(R.id.hairButton);
        eyeButton = (RadioButton)findViewById(R.id.eyeButton);
        skinButton = (RadioButton)findViewById(R.id.skinButton);
        randomButton = (Button)findViewById(R.id.randomButton);
        randomButton.setOnClickListener(this);

        //seekbars
        redBar = (SeekBar)findViewById(R.id.redBar);
        redBar.setOnSeekBarChangeListener(this);
        greenBar = (SeekBar)findViewById(R.id.greenBar);
        greenBar.setOnSeekBarChangeListener(this);
        blueBar = (SeekBar)findViewById(R.id.blueBar);
        blueBar.setOnSeekBarChangeListener(this);

        //textViews
        redText = (TextView)findViewById(R.id.redText);
        greenText = (TextView)findViewById(R.id.greenText);
        blueText = (TextView)findViewById(R.id.blueText);

        //spinners, assign entry values and adapters
        hairSpinner = (Spinner)findViewById(R.id.hairSpinner);
        hairEntries = getResources().getStringArray(R.array.hair_styles_array);
        // create adapter with the strings
        ArrayAdapter hairAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, hairEntries);
        hairAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bind the spinner and adapter
        hairSpinner.setAdapter(hairAdapter);
        hairSpinner.setOnItemSelectedListener(new MySpinnerListener());

        eyeSpinner = (Spinner)findViewById(R.id.eyeSpinner);
        eyeEntries = getResources().getStringArray(R.array.eye_styles_array);
        // create adapter with the strings
        ArrayAdapter eyeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, eyeEntries);
        eyeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bind the spinner and adapter
        eyeSpinner.setAdapter(eyeAdapter);
        eyeSpinner.setOnItemSelectedListener(new MySpinnerListener());

        noseSpinner = (Spinner)findViewById(R.id.noseSpinner);
        noseEntries = getResources().getStringArray(R.array.nose_styles_array);
        // create adapter with the strings
        ArrayAdapter noseAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, noseEntries);
        noseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bind the spinner and adapter
        noseSpinner.setAdapter(noseAdapter);
        noseSpinner.setOnItemSelectedListener(new MySpinnerListener());

        //set face
        face = (Face)findViewById(R.id.surfaceView);

        //set seekbars and spinners
        setViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * onRadioButtonClicked(View view)
     *
     * Sets the seekBars to new values when a radio button is clicked
     *
     * @param view
     */
    public void onRadioButtonClicked(View view){
        //set seek bar and text view values to corresponding radio button values
        if (view == hairButton){
            //set seek bars
            redBar.setProgress(face.hairColorR);
            greenBar.setProgress(face.hairColorG);
            blueBar.setProgress(face.hairColorB);


            //set text views
            redText.setText("Red Value: " + redBar.getProgress());
            greenText.setText("Green Value: " + greenBar.getProgress());
            blueText.setText("Blue Value: " + blueBar.getProgress());
        }
        else if (view == eyeButton){
            //set seek bars
            redBar.setProgress(face.eyeColorR);
            greenBar.setProgress(face.eyeColorG);
            blueBar.setProgress(face.eyeColorB);

            //set text views
            redText.setText("Red Value: " + redBar.getProgress());
            greenText.setText("Green Value: " + greenBar.getProgress());
            blueText.setText("Blue Value: " + blueBar.getProgress());
        }
        else if (view == skinButton){
            //set seek bars
            redBar.setProgress(face.skinColorR);
            greenBar.setProgress(face.skinColorG);
            blueBar.setProgress(face.skinColorB);

            //set text views
            redText.setText("Red Value: " + redBar.getProgress());
            greenText.setText("Green Value: " + greenBar.getProgress());
            blueText.setText("Blue Value: " + blueBar.getProgress());
        }
    }

    /**
     * onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
     *
     * When the seekBars are changed, face is redrawn with the new values
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //change the color that corresponds to the seekBar and radio buttons
        if (seekBar == redBar){ //red colors
            if (hairButton.isChecked()){
                face.hairColorR = progress;
            }
            if (eyeButton.isChecked()){
                face.eyeColorR = progress;
            }
            if (skinButton.isChecked()){
                face.skinColorR = progress;
            }

            //change the text view
            redText.setText("Red Value: " + progress);
        }
        else if (seekBar == greenBar){ //green colors
            if (hairButton.isChecked()){
                face.hairColorG = progress;
            }
            if (eyeButton.isChecked()){
                face.eyeColorG = progress;
            }
            if (skinButton.isChecked()){
                face.skinColorG = progress;
            }

            //change the text view
            greenText.setText("Green Value: " + progress);
        }
        else if(seekBar == blueBar){ //blue colors
            if (hairButton.isChecked()){
                face.hairColorB = progress;
            }
            if (eyeButton.isChecked()){
                face.eyeColorB = progress;
            }
            if (skinButton.isChecked()){
                face.skinColorB = progress;
            }

            //change the text view
            blueText.setText("Blue Value: " + progress);
        }

        //draw new face
        face.draw();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    //nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    //nothing
    }

    /**
     * setViews()
     *
     * Helper method to change seekBars and Spinners to correct values
     */
    public void setViews(){
        //change seek bars and edit texts to reflect new values of selected radio button
        if(hairButton.isChecked()){
            //change seek bars
            redBar.setProgress(face.hairColorR);
            greenBar.setProgress(face.hairColorG);
            blueBar.setProgress(face.hairColorB);

            //change edit texts
            redText.setText("Red Value: " + face.hairColorR);
            greenText.setText("Green Value: " + face.hairColorG);
            blueText.setText("Blue Value: " + face.hairColorB);
        }
        if(eyeButton.isChecked()){
            //change seek bars
            redBar.setProgress(face.eyeColorR);
            greenBar.setProgress(face.eyeColorG);
            blueBar.setProgress(face.eyeColorB);

            //change edit texts
            redText.setText("Red Value: " + face.eyeColorR);
            greenText.setText("Green Value: " + face.eyeColorG);
            blueText.setText("Blue Value: " + face.eyeColorB);
        }
        if(skinButton.isChecked()){
            //change seek bars
            redBar.setProgress(face.skinColorR);
            greenBar.setProgress(face.skinColorG);
            blueBar.setProgress(face.skinColorB);

            //change edit texts
            redText.setText("Red Value: " + face.skinColorR);
            greenText.setText("Green Value: " + face.skinColorG);
            blueText.setText("Blue Value: " + face.skinColorB);
        }

        //set spinners
        hairSpinner.setSelection(face.hairStyleIndex);
        eyeSpinner.setSelection(face.eyeStyleIndex);
        noseSpinner.setSelection(face.noseStyleIndex);
    }

    /**
     * onClick(View v)
     *
     * When random button is clicked, face's values are randomized, redrawn, and view are reset
     * @param v
     */
    @Override
    public void onClick(View v) {
            //when random button is clicked, randomize is called
        if(v == randomButton){
            face.randomize();
            //change seek bar and text edit values
            setViews();
        }
    }

    /**
     External Citation
     Date: 9 February 2016
     Problem: Could not find spinner listener
     Resource:
     Steven Veghdal's GitHub Lab
     Solution: I used the example code from this lab.
     */
    /**
     * MySpinnerListener class
     *
     * class that handles our spinner's selection events
     */
    private class MySpinnerListener implements AdapterView.OnItemSelectedListener {

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
         *                  android.widget.AdapterView, android.view.View, int, long)
         */
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id) {
            //change face's index of the corresponding spinner
            if(parentView == hairSpinner){
                face.hairStyleIndex = position;
            }
            if(parentView == eyeSpinner){
                face.eyeStyleIndex = position;
            }
            if(parentView == noseSpinner){
                face.noseStyleIndex = position;
            }

            //redraw face
            face.draw();
        }


        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            //nothing
        }
    }
}
