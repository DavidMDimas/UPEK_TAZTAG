package com.authentec.java.ptapi.samples.basicsample;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import android.app.Activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class facial extends Activity implements SurfaceHolder.Callback {

	Camera camera;
	SurfaceView cameraView, transparentView;
	SurfaceHolder holder, holderTransparent;
	Button buttoncamara;

	Button buttonQRFacial, buttonFacialFacial, buttonFirmaFacial, buttonDactilarFacial, buttonNextFirma;

	private final String tag = "TAG";

	public float RectLeft, RectTop, RectRight, RectBottom;
	int deviceHeight, deviceWidth;

	private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
			+ "/misfotos/";
	private File file = new File(ruta_fotos);

	protected void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.capturafacial);

		buttonQRFacial = (Button) findViewById(R.id.btnQRFacial);
		buttonFacialFacial = (Button) this.findViewById(R.id.btnFacialFacial);
		buttonFirmaFacial = (Button) this.findViewById(R.id.btnFirmaFacial);
		buttonDactilarFacial = (Button) this.findViewById(R.id.btnDactilarFacial);
		buttonNextFirma = (Button) this.findViewById(R.id.btnNextFirma);
		buttoncamara = (Button) this.findViewById(R.id.btncamara);

		buttonQRFacial.setBackgroundColor(Color.parseColor("#222222"));
		buttonFacialFacial.setBackgroundColor(Color.parseColor("#222222"));
		buttonFirmaFacial.setBackgroundColor(Color.parseColor("#222222"));
		buttonDactilarFacial.setBackgroundColor(Color.parseColor("#222222"));
		buttonNextFirma.setBackgroundColor(Color.parseColor("#222222"));
		buttoncamara.setBackgroundColor(Color.parseColor("#222222"));

		buttonFacialFacial.setTextColor(Color.GREEN);
		buttonQRFacial.setEnabled(false);
		buttonFirmaFacial.setEnabled(false);
		buttonDactilarFacial.setEnabled(false);

		cameraView = (SurfaceView) findViewById(R.id.cameraView);
		holder = cameraView.getHolder();
		holder.addCallback((SurfaceHolder.Callback) this);
		// holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		cameraView.setSecure(true);
		// Create second surface with another holder (holderTransparent)
		transparentView = (SurfaceView) findViewById(R.id.TransparentView);
		holderTransparent = transparentView.getHolder();
		holderTransparent.addCallback((SurfaceHolder.Callback) this);
		holderTransparent.setFormat(PixelFormat.TRANSLUCENT);
		transparentView.setZOrderMediaOverlay(true);
		// getting the device heigth and width
		deviceWidth = getScreenWidth();
		deviceHeight = getScreenHeight();

		buttoncamara = (Button) findViewById(R.id.btncamara);

		buttoncamara.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String file = ruta_fotos + ".jpg";
				File mi_foto = new File(file);
				try {
					mi_foto.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				camera.takePicture(null, null, null, mPictureCallBack);
			}
		});

		/*
		 * buttoncamara.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { Intent intent = new
		 * Intent(MediaStore.ACTION_IMAGE_CAPTURE); startActivityForResult(intent,01); }
		 * });
		 */

		buttonNextFirma.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent firma = new Intent(facial.this, firma.class);
				startActivity(firma);
				finish();
			}
		});
	}

	Camera.PictureCallback mPictureCallBack = new Camera.PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			FileOutputStream outStream = null;
			try {
				outStream = new FileOutputStream("/misfotos/" + "Imagen.jpg");
				outStream.write(data);
				outStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
		}
	};

	private void Draw() {
		Canvas canvas = holderTransparent.lockCanvas(null);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(3);
		RectLeft = 70;
		RectTop = 70;
		RectRight = RectLeft + 200;
		RectBottom = RectTop + 300;
		Rect rec = new Rect((int) RectLeft, (int) RectTop, (int) RectRight, (int) RectBottom);
		canvas.drawRect(rec, paint);
		holderTransparent.unlockCanvasAndPost(canvas);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		try {
			synchronized (holder) {
				Draw();
			} // call a draw method
			camera = Camera.open(); // open a camera
		} catch (Exception e) {
			Log.i("Exception", e.toString());
			return;
		}
		Camera.Parameters param;
		param = camera.getParameters();
		// param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

		if (display.getRotation() == Surface.ROTATION_90) {
			camera.setDisplayOrientation(0);
		}
		camera.setParameters(param);
		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (Exception e) {
			return;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		refreshCamera(); // call method for refress camera
	}

	public void refreshCamera() {
		if (holder.getSurface() == null) {
			return;
		}
		try {
			camera.stopPreview();
		} catch (Exception e) {
		}
		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (Exception e) {
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.release(); // for release a camera
	}

	public static int getScreenWidth() {
		return Resources.getSystem().getDisplayMetrics().widthPixels;
	}

	public static int getScreenHeight() {
		return Resources.getSystem().getDisplayMetrics().heightPixels;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		Bitmap bm = (Bitmap) data.getExtras().get("data");
		// imagen.setImageBitmap(bm);
	}
}
