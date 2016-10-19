package com.google.android.cameraview.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;



/**
 * Created by gsl on 01/11/15.
 */
public class FocusArea extends View
{

	boolean haveFocus;
	Paint drawingPaint;
	Rect mRect;

	public FocusArea(Context context) {
		super(context);
		haveFocus = false;
		mRect = new Rect();
		drawingPaint = new Paint();
		drawingPaint.setColor(getResources().getColor(R.color.primary));
		drawingPaint.setStyle(Paint.Style.STROKE);
		drawingPaint.setStrokeWidth(2);
	}

	public void setHaveFocus(boolean h)
	{
		haveFocus = h;
	}

	public void setFocusPoint(Rect rect)
	{
		mRect = rect;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if (haveFocus && mRect != null)
		{
			canvas.drawRect(mRect, drawingPaint);
		}
		else
		{
			canvas.drawColor(Color.TRANSPARENT);
		}
	}

}
