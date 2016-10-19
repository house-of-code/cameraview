package com.google.android.cameraview.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by gsl on 01/11/15.
 */
public class FocusArea extends View
{

	boolean haveFocus;
	Paint drawingPaint;
	List<Camera.Area> focusAreas;

	public FocusArea(Context context) {
		super(context);
		haveFocus = false;
		focusAreas = new ArrayList<>();
		drawingPaint = new Paint();
		drawingPaint.setColor(getResources().getColor(R.color.primary));
		drawingPaint.setStyle(Paint.Style.STROKE);
		drawingPaint.setStrokeWidth(2);
	}

	public void setHaveFocus(boolean h)
	{
		haveFocus = h;
	}

	public void setFocusPoints(List<Camera.Area> areas)
	{
		focusAreas = areas;
	}

	@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onDraw(Canvas canvas)
	{
		if (haveFocus && focusAreas != null)
		{

			// Camera driver coordinates range from (-1000, -1000) to (1000, 1000).
			// UI coordinates range from (0, 0) to (width, height).

			int vWidth = getWidth();
			int vHeight = getHeight();

			for (int i = 0; i < focusAreas.size(); i++)
			{

				if (i == 0)
				{
					drawingPaint.setColor(Color.GREEN);
				}else{
					drawingPaint.setColor(Color.RED);
				}

				Camera.Area area = focusAreas.get(i);
				int l = area.rect.left;
				int t = area.rect.top;
				int r = area.rect.right;
				int b = area.rect.bottom;
				int left = ((l+1000) * vWidth/2000);
				int top  = ((t+1000) * vHeight/2000);
				int right = ((r+1000) * vWidth/2000);
				int bottom = ((b+1000) * vHeight/2000);
				canvas.drawRect(left, top, right, bottom, drawingPaint);
			}
		}
		else
		{
			canvas.drawColor(Color.TRANSPARENT);
		}
	}

}
