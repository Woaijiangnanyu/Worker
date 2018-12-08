package com.example.guojialin.worker.view;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
 
 
/**
 * Package com.hc.studyview
 * Created by HuaChao on 2016/6/3.
 */
public class PaintView extends View {
 
	private Context context;
	private boolean isPaint = false;//空心正方形
	private boolean isText = false;//文字
	private boolean isMove = true;//移动
	private boolean isZoom = false;//缩放
	private Paint mPaint;//画笔
	private Bitmap originalBitmap;//原始图片
	private Bitmap bitmap;//处理后图片
	private List<Object> originalGraphical; //原始图形
	private float rX, rY;//矩形起点
	private float x1,y1,x2,y2;//手指移动矩形坐标
	private float x3,y3;//图形移动起始坐标
	private int currentLeft,currentTop;//当前图形偏移量
	private Rect mSrcRect, mDestRect;//图片绘画区域
	private double nLenStart;//缩放起始长度
	private double zoom=1;//缩放比例
	private int fontSize = 50;
 
	public PaintView(Context context) {
		super(context);
		this.context = context;
		this.originalGraphical = new ArrayList<>();
		this.mPaint = new Paint();
	}
 
 
	public PaintView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.originalGraphical = new ArrayList<>();
		this.mPaint = new Paint();
	}
	//切换至矩形绘图
	public void paint(){
		isPaint = true;
		isText = false;
		isMove = false;
		isZoom = false;
	}
	//矩形坐标填充
	private void rectPoint(float x,float y){
		x1 = rX>x?x:rX;
		y1 = rY>y?y:rY;
		x2 = rX<x?x:rX;
		y2 = rY<y?y:rY;
	}

	//切换至移动图片
	public void move(){
		isPaint = false;
		isText = false;
		isMove = true;
		isZoom = false;
	}
	//图形移动偏移量计算
	private void img_move(float x,float y){
		int moveLeft = x>x3?15:-15;
		int moveTop = y>y3?15:-15;
		currentLeft = currentLeft + moveLeft;
		currentTop = currentTop + moveTop;
		x3 = x;
		y3 = y;
	}
	//缩放
	public void zoom(){
		isPaint = false;
		isText = false;
		isMove = false;
		isZoom = true;
	}
	public void reset(){
		isPaint = false;
		isText = false;
		isMove = false;
		isZoom = false;
		zoom =1;
		invalidate();
	}
	//缩放比例计算
	private void zoomComputer(MotionEvent event){
		if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN ){
			int xlen = Math.abs((int) event.getX(0) - (int) event.getX(1));
			int ylen = Math.abs((int) event.getY(0) - (int) event.getY(1));
			nLenStart = Math.sqrt((double) xlen * xlen + (double) ylen * ylen);
		} else {
			int xlen = Math.abs((int) event.getX(0) - (int) event.getX(1));
			int ylen = Math.abs((int) event.getY(0) - (int) event.getY(1));
			double nLenEnd = Math.sqrt((double) xlen * xlen + (double) ylen * ylen);
			double f = nLenEnd / nLenStart;
			if (f != 1) {
				zoom += (f > 1 ? +0.02 : -0.02);
			}
			if (zoom > 2) {
				zoom = 2;
			}
			if (zoom < 0.5) {
				zoom = 0.5;
			}
		}
	}
 
 
	//图片绘画计算
	public void bitmapTranslate() {
		Matrix matrix = new Matrix();
		matrix.setScale((float)zoom,(float)zoom);
		bitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.getWidth(), originalBitmap.getHeight(), matrix, true);
		mSrcRect.right = bitmap.getWidth();
		mSrcRect.bottom = bitmap.getHeight();
		mDestRect.left = currentLeft;
		mDestRect.right = currentLeft + bitmap.getWidth();
		mDestRect.top = currentTop;
		mDestRect.bottom = currentTop + bitmap.getHeight();
	}
 
	//矩形绘画计算
	private Rect rectTranslate(Rect rect){
		return new Rect((int)(rect.left*zoom+currentLeft),(int)(rect.top*zoom+currentTop),(int)(rect.right*zoom+currentLeft),(int)(rect.bottom*zoom+currentTop));
	}
 
 
 
	@Override
		protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(bitmap!=null){
			bitmapTranslate();
			canvas.drawBitmap(bitmap,mSrcRect, mDestRect,mPaint );
		}
		for(Object o : originalGraphical) {
			if (o instanceof Rect) {
				mPaint.setStyle(Paint.Style.STROKE);
				canvas.drawRect(rectTranslate((Rect)o) , mPaint);
			}
		}
		//手指移动绘制矩形
		if(isPaint) {
			mPaint.setStyle(Paint.Style.STROKE);
			canvas.drawRect(x1, y1, x2, y2, mPaint);
		}
			//canvas.drawPath(mPath, mEraserPaint);
	}
 
 
	// 初始化画笔
	private void Init_Paint(){
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(2);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
 
 
			int nCnt = event.getPointerCount();
			if(nCnt==2){
				isMove = false;
				isPaint = false;
				isText = false;
				zoomComputer(event);
				isMove = true;
				invalidate();
				return true;
			}
 
			float x = event.getX();
			float y = event.getY();
			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					touch_down(x, y);
					break;
 
				case MotionEvent.ACTION_MOVE:
					touch_move(x,y);
					invalidate();
					break;
 
				case MotionEvent.ACTION_UP:
					touch_up(x,y);
					invalidate();
					break;
			}
			return true;
	}
	//手指按下
	private void touch_down(float x, float y) {
		if(isPaint){
			this.rX = x;
			this.rY = y;
		}
	}
	//手指移动
	private void touch_move(float x, float y) {
		if(isPaint){
			rectPoint(x,y);
		}else if(isMove){
			img_move(x,y);
		}
 
	}
	//手指弹起
	private void touch_up(final float x,final float y){
		if(isPaint){
			rectPoint(x,y);
			Rect r = new Rect((int)(x1/zoom-currentLeft/zoom),(int)(y1/zoom-currentTop/zoom),(int)(x2/zoom-currentLeft/zoom),(int)(y2/zoom-currentTop/zoom));
			originalGraphical.add(r);
		}
 
	}
	//初始化图片
	public void initBitmap(Bitmap bitmap) {
		Init_Paint();
		this.bitmap = bitmap;
		this.originalBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), new Matrix(), true);
		mSrcRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		mDestRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		invalidate();
	}
	//保存图片
	public void save() {
		Bitmap copyBitmap = Bitmap.createBitmap(originalBitmap.getWidth(), originalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(copyBitmap);
		c.drawBitmap(originalBitmap,new Matrix(),mPaint);
		for(Object o : originalGraphical) {
			if (o instanceof Rect) {
				mPaint.setStyle(Paint.Style.STROKE);
				c.drawRect((Rect) o, mPaint);
			}
		}
 
		FileOutputStream fos;
		String imagePath = "";
		try {
			// 判断手机设备是否有SD卡
			boolean isHasSDCard = Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED);
			if (isHasSDCard) {
				// SD卡根目录
				File sdRoot = Environment.getExternalStorageDirectory();
				File file = new File(sdRoot, Calendar.getInstance().getTimeInMillis()+".png");
				fos = new FileOutputStream(file);
				imagePath = file.getAbsolutePath();
//				showCustomToast(file.getPath());
			} else
				throw new Exception("创建文件失败!");
 
			copyBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
 
			fos.flush();
			fos.close();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		this.destroyDrawingCache();
	}
 
 
 
 
 
 
}

