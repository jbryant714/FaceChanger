package com.example.jacob.myfirstapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Face class
 *
 * Draws a face on a surfaceView-like view
 *
 * @author Jacob Bryant
 * @version 2/11/16
 * */
public class Face extends SurfaceView {

    //instance variables
    int skinColorR;
    int skinColorG;
    int skinColorB;
    int eyeColorR;
    int eyeColorG;
    int eyeColorB;
    int hairColorR;
    int hairColorG;
    int hairColorB;
    Path[] hairStyles;
    int hairStyleIndex;
    Path[] eyeStyles;
    int eyeStyleIndex;
    Path[] noseStyles;
    int noseStyleIndex;

    /**
     * Face(Context context)
     *
     * Creates a face object with random values
     *
     * @param context
     */
    public Face(Context context) {
        super(context);
        setWillNotDraw(false);

        //create paths
        makePaths();
        //create a random face
        randomize();
    }

    /**
     * Face(Context context, AttributeSet attrs)
     *
     * Creates a face object with random values
     * @param context
     * @param attrs
     */
    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        //create paths
        makePaths();
        //create a random face
        randomize();
    }

    /**
     * Face(Context context, AttributeSet attrs, int defStyleAttr)
     *
     * Creates a Face object with random values
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);

        //create paths
        makePaths();
        //create a random face
        randomize();
    }

    /**
     * randomize()
     *
     * Sets the values of a Face object to random values
     */
    public void randomize(){
        //set random values
        hairColorR = (int)(255*Math.random());
        hairColorG = (int)(255*Math.random());
        hairColorB = (int)(255*Math.random());
        eyeColorR = (int)(255*Math.random());
        eyeColorG = (int)(255*Math.random());
        eyeColorB = (int)(255*Math.random());
        skinColorR = (int)(255*Math.random());
        skinColorG = (int)(255*Math.random());
        skinColorB = (int)(255*Math.random());
        hairStyleIndex = (int)(3*Math.random());
        eyeStyleIndex = (int)(3*Math.random());
        noseStyleIndex = (int)(3*Math.random());

        //draw new face
        this.invalidate();
    }

    /**
     * draw()
     *
     * Easy way to make a Face draw itself
     */
    public void draw(){
        invalidate();
    }

    /**
     * onDraw(Canvas canvas)
     *
     * Draws the face on a given Canvas
     *
     * @param canvas
     */
    public void onDraw(Canvas canvas){

        //draw head
        Paint skinPaint = new Paint();
        skinPaint.setARGB(255, skinColorR, skinColorG, skinColorB);
        canvas.drawOval(425.0f, 50.0f, 975.0f, 800.0f, skinPaint);

        //draw hair
        Paint hairPaint = new Paint();
        hairPaint.setARGB(255, hairColorR, hairColorG, hairColorB);
        canvas.drawPath(hairStyles[hairStyleIndex], hairPaint);

        //draw eyes
        Paint eyePaint = new Paint();
        eyePaint.setARGB(255, eyeColorR, eyeColorG, eyeColorB);
        canvas.drawPath(eyeStyles[eyeStyleIndex], eyePaint);

        //draw nose
        Paint nosePaint = new Paint();
        nosePaint.setColor(Color.BLACK);
        nosePaint.setStyle(Paint.Style.STROKE);
        nosePaint.setStrokeWidth(5.0f);
        canvas.drawPath(noseStyles[noseStyleIndex], nosePaint);

        // create and draw a smile
        Path smile = new Path();
        smile.addArc(525.0f, 500.0f, 875.0f, 650.0f, 0.0f, 180.0f);
        canvas.drawPath(smile, nosePaint);
    }

    /**
     * makePaths()
     *
     * A helper method to create all the different facial features of the Face
     */
    private void makePaths(){
        //fill hair styles array
        hairStyles = new Path[3];

        Path buzz = new Path();
        buzz.addArc(445.0f, 50.0f, 955.0f, 525.0f, 180.0f, 180.0f);

        Path mohawk = new Path();
        mohawk.addRoundRect(610.0f, 0.0f, 775.0f, 260.0f, 75.0f, 50.0f, Path.Direction.CW);

        Path bun = new Path();
        bun.addArc(445.0f, 50.0f, 955.0f, 525.0f, 180.0f, 180.0f);
        bun.addArc(750.0f, 0.0f, 950.0f, 200.0f, 0.0f, 360.0f);

        hairStyles[0] = buzz;
        hairStyles[1] = mohawk;
        hairStyles[2] = bun;

        //fill eye styles array
        eyeStyles = new Path[3];

        Path circleEyes = new Path();
        circleEyes.addCircle(620.0f, 380.0f, 30.0f, Path.Direction.CW);
        circleEyes.addCircle(770.0f, 380.0f, 30.0f, Path.Direction.CW);

        Path ovalHorizEyes = new Path();
        ovalHorizEyes.addOval(590.0f, 365.0f, 650.0f, 395.0f, Path.Direction.CW);
        ovalHorizEyes.addOval(740.0f, 365.0f, 800.0f, 395.0f, Path.Direction.CW);

        Path ovalVertEyes = new Path();
        ovalVertEyes.addOval(600.0f, 345.0f, 630.0f, 410.0f, Path.Direction.CW);
        ovalVertEyes.addOval(755.0f, 345.0f, 785.0f, 410.0f, Path.Direction.CW);

        eyeStyles[0] = circleEyes;
        eyeStyles[1] = ovalHorizEyes;
        eyeStyles[2] = ovalVertEyes;

        //fill nose style array
        noseStyles = new Path[3];

        Path triangleNose = new Path();
        triangleNose.moveTo(700.0f, 445.0f);
        triangleNose.lineTo(680.0f, 485.0f);
        triangleNose.lineTo(720.0f, 485.0f);
        triangleNose.lineTo(700.0f, 445.0f);

        Path ovalNose = new Path();
        ovalNose.addOval(665.0f, 445.0f, 735.0f, 485.0f, Path.Direction.CCW);

        Path squareNose = new Path();
        squareNose.addRect(680.0f, 445.0f, 720.0f, 485.0f, Path.Direction.CCW);

        noseStyles[0] = triangleNose;
        noseStyles[1] = squareNose;
        noseStyles[2] = ovalNose;
    }
}
