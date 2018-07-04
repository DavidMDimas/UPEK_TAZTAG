package com.authentec.java.ptapi.samples.basicsample;

import com.taztag.fingerprintservice.client.FingerprintActivity;
import com.upek.android.ptapi.PtConnectionAdvancedI;
import com.upek.android.ptapi.PtConstants;
import com.upek.android.ptapi.PtException;
import com.upek.android.ptapi.struct.PtInfo;
import com.upek.android.ptapi.struct.PtSessionCfgV5;

/**
 * Sample showing finger enrollment, identification, and removal.
 */

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SampleActivity extends FingerprintActivity 
{
    private PtConnectionAdvancedI mConn = null;
    private PtInfo mSensorInfo = null;
    private Thread mRunningOp = null;
    private final Object mCond = new Object();
        
    @Override
	public void onStart() {
    	Log.d("TazTag_BS", "onStart");
    	super.onStart();
    }
    
    @Override
	public void onResume() {
    	Log.d("TazTag_BS", "onResume");
    	super.onResume();
    }
    
    @Override
    public void onPause() {
    	Log.d("TazTag_BS", "onPause");
    	super.onPause();
    }
    
    @Override
    public void onStop() {
    	Log.d("TazTag_BS", "onStop 0");
    	synchronized(mCond)
        {
            Log.d("TazTag_BS", "onStop 1");
//            while(mRunningOp != null)
//            {
//                mRunningOp.interrupt();
//                try 
//                {
//                    mCond.wait();
//                } catch (InterruptedException e)
//                {
//                }
//            }
        }
    	super.onStop();
    }
    
    @Override
    public void onRestart() {
    	Log.d("TazTag_BS", "onRestart");
    	super.onRestart();
    }

	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        Log.d("TazTag_BS", "onCreate");
        setContentView(R.layout.main);
                
        SetQuitButtonListener();
    }
    
    /** Close PTAPI session. */
    @Override
    public void onDestroy()
    {       
        Log.d("TazTag_BS", "onDestroy 0");
        // Cancel running operation
        synchronized(mCond)
        {
            Log.d("TazTag_BS", "onDestroy 1");
//            while(mRunningOp != null)
//            {
//                mRunningOp.interrupt();
//                try 
//                {
//                    mCond.wait();
//                } catch (InterruptedException e)
//                {
//                }
//            }
        }     
        super.onDestroy();
    }
    
    /**
     * Set listener for an enrollment button.
     * @param buttonId Resource ID of a button
     * @param fingerId Finger ID.
     */
    private void setEnrollButtonListener(final int buttonId, final int fingerId)
    {
    	Button aButton = (Button)findViewById(buttonId);
    	OnClickListener aListener = new View.OnClickListener() 
    	{
            public void onClick(View view)
            {
                synchronized(mCond)
                {
                    if(mRunningOp == null)
                    {
                        mRunningOp = new OpEnroll(mConn, fingerId) 
                        {
                            @Override
                            protected void onDisplayMessage(String message) 
                            {
                                dislayMessage(message);
                            }
    
                            @Override
                            protected void onFinished()
                            {
                                synchronized(mCond)
                                {
                                    mRunningOp = null;
                                    mCond.notifyAll();  //notify onDestroy that operation has finished
                                }
                            }
                        };
                        mRunningOp.start();
                    }
                }
            }
        };
        aButton.setOnClickListener(aListener);
    }
    
    /**
     * Set listener for a identification button.
     */
    private void setIdentifyButtonListener()
    {
    	Button aButton = (Button)findViewById(R.id.ButtonVerifyAll);
        OnClickListener aListener = new View.OnClickListener()
        {
            public void onClick(View view)
            {
                synchronized(mCond)
                {
                    if(mRunningOp == null)
                    {
                        mRunningOp = new OpVerifyAll(mConn)
                        {
                            @Override
                            protected void onDisplayMessage(String message)
                            {
                                dislayMessage(message);
                            }
    
                            @Override
                            protected void onFinished()
                            {
                                synchronized(mCond)
                                {
                                    mRunningOp = null;
                                    mCond.notifyAll();  //notify onDestroy that operation has finished
                                }
                            }
                        };
                        mRunningOp.start();
                    }
                }
            }
        };
        aButton.setOnClickListener(aListener);
    }
    /**
     * Common helper for setNavigatexxxButtonListener()
     */
    private OpNavigate createNavigationOperationHelper(OpNavigateSettings aSettings)
    {
    	OpNavigate aOperation = new OpNavigate(mConn, aSettings) 
        {
            @Override
            protected void onDisplayMessage(String message) 
            {
                dislayMessage(message);
            }

            @Override
            protected void onFinished() 
            {
                synchronized(mCond)
                {
                    mRunningOp = null;
                    mCond.notifyAll();  //notify onDestroy that operation has finished
                }
            }
        };
    	return aOperation;
    }
    
    /**
     * Set listener for a navigate button in raw mode.
     */
    private void setNavigateRawButtonListener()
    {
    	Button aButton = (Button)findViewById(R.id.ButtonNavigateRaw);
    	//disable navigation for area sensors
    	if((mSensorInfo.sensorType & PtConstants.PT_SENSORBIT_STRIP_SENSOR) == 0)
    	{
    		aButton.setVisibility(Button.GONE);
    		return;
    	}
    	OnClickListener aListener = new View.OnClickListener() 
    	{
            public void onClick(View view)
            {
                synchronized(mCond)
                {
                    if(mRunningOp == null)
                    {
                        mRunningOp = createNavigationOperationHelper(null);
                        mRunningOp.start();
                    }
                }
            }
        };
        aButton.setOnClickListener(aListener);
    }
    
    /**
     * Set listener for a navigate button in mouse mode.
     */
    private void setNavigateMouseButtonListener()
    {
    	Button aButton = (Button)findViewById(R.id.ButtonNavigateMouse);
    	//disable navigation for area sensors
    	if((mSensorInfo.sensorType & PtConstants.PT_SENSORBIT_STRIP_SENSOR) == 0)
    	{
    		aButton.setVisibility(Button.GONE);
    		return;
    	}
    	OnClickListener aListener = new View.OnClickListener()
        {
            public void onClick(View view)
            {
                synchronized(mCond)
                {
                    if(mRunningOp == null)
                    {
                        mRunningOp = createNavigationOperationHelper(OpNavigateSettings.createDefaultMousePostprocessingParams()); 
                        mRunningOp.start();
                    }
                }
            }
        }; 
        aButton.setOnClickListener(aListener);
    }
    
    /**
     * Set listener for a navigate button in discrete mode.
     */
    private void setNavigateDiscreteButtonListener()
    {
    	Button aButton = (Button)findViewById(R.id.ButtonNavigateDiscrete);
    	//disable navigation for area sensors
    	if((mSensorInfo.sensorType & PtConstants.PT_SENSORBIT_STRIP_SENSOR) == 0)
    	{
    		aButton.setVisibility(Button.GONE);
    		return;
    	}
    	OnClickListener aListener = new View.OnClickListener()
    	{
            public void onClick(View view)
            {
                synchronized(mCond)
                {
                    if(mRunningOp == null)
                    {
                        mRunningOp = createNavigationOperationHelper(OpNavigateSettings.createDefaultDiscretePostprocessingParams()); 
                        mRunningOp.start();
                    }
                }
            }
        };
        aButton.setOnClickListener(aListener);
    }
    
    /**
     * Set listener for a delete all button.
     */
    private void setDeleteAllButtonListener()
    {
    	Button aButton = (Button)findViewById(R.id.ButtonDeleteAll);
    	OnClickListener aListener = new View.OnClickListener()
        {
            public void onClick(View view)
            {
                synchronized(mCond)
                {
                    if(mRunningOp == null)
                    {
                        try
                        {
                            // No interaction with a user needed
                            mConn.deleteAllFingers();
                            dislayMessage("All fingers deleted");
                        } 
                        catch (PtException e)
                        {
                            dislayMessage("Delete All failed - " + e.getMessage());
                        }
                    }
                }
            }
        };
        aButton.setOnClickListener(aListener);
    }
    
    /**
     * Set listener for a grab button.
     */
    private void setGrabButtonListener()
    {
    	Button aButton = (Button)findViewById(R.id.ButtonGrab);
        OnClickListener aListener = new View.OnClickListener()
        {
            public void onClick(View view)
            {
                synchronized(mCond)
                {
                    if(mRunningOp == null)
                    {
                        mRunningOp = new OpGrab(mConn,PtConstants.PT_GRAB_TYPE_THREE_QUARTERS_SUBSAMPLE,SampleActivity.this)
                        {
                            @Override
                            protected void onDisplayMessage(String message)
                            {
                                dislayMessage(message);
                            }
    
                            @Override
                            protected void onFinished()
                            {
                                synchronized(mCond)
                                {
                                    mRunningOp = null;
                                    mCond.notifyAll();  //notify onDestroy that operation has finished
                                }
                            }
                        };
                        mRunningOp.start();
                    }
                }
            }
        };
        aButton.setOnClickListener(aListener);
    }
    
    
    private void SetQuitButtonListener()
    {
    	Button aButton = (Button)findViewById(R.id.ButtonQuit);
    	OnClickListener aListener = new View.OnClickListener()
        {
            public void onClick(View view)
            {
            	System.exit(0);
            }
        };
        aButton.setOnClickListener(aListener);
    }
 
    private void configureOpenedDevice() throws PtException
    {
    	Log.d("TazTag_BS", "configureOpenedDevice");
    	 PtSessionCfgV5 sessionCfg = (PtSessionCfgV5) mConn.getSessionCfgEx(PtConstants.PT_CURRENT_SESSION_CFG);
         sessionCfg.sensorSecurityMode = PtConstants.PT_SSM_DISABLED;
         sessionCfg.callbackLevel |= PtConstants.CALLBACKSBIT_NO_REPEATING_MSG;
         mConn.setSessionCfgEx(PtConstants.PT_CURRENT_SESSION_CFG, sessionCfg);
    }
    
    /** 
     * Display message in TextView. 
     */
    public void dislayMessage(String text)
    {
    	Log.d("TazTag_BS", "dislayMessage : " + text);
        mHandler.sendMessage(mHandler.obtainMessage(0, 0, 0, text));
    }
    
    /**
     * Transfer messages to the main activity thread.
     */
    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message aMsg)
        {           
            ((TextView)findViewById(R.id.EnrollmentTextView)).setText((String) aMsg.obj);
        }
    };

	@Override
	public void onFingerprintReady(PtConnectionAdvancedI mConn)
	{
		this.mConn = mConn;
		
        // If PTAPI session is available, register listeners for  buttons 
        if(mConn != null)
        {

            try
    		{
            	mSensorInfo = mConn.info();
            	configureOpenedDevice();
    		} catch (PtException e)
    		{
    			e.printStackTrace();
    		}
        	
            setEnrollButtonListener(R.id.ButtonLeftThumb,  FingerId.LEFT_THUMB);
            setEnrollButtonListener(R.id.ButtonLeftIndex,  FingerId.LEFT_INDEX);
            setEnrollButtonListener(R.id.ButtonLeftMiddle, FingerId.LEFT_MIDDLE);
            setEnrollButtonListener(R.id.ButtonLeftRing,   FingerId.LEFT_RING);
            setEnrollButtonListener(R.id.ButtonLeftLittle, FingerId.LEFT_LITTLE);
            
            setEnrollButtonListener(R.id.ButtonRightThumb, FingerId.RIGHT_THUMB);
            setEnrollButtonListener(R.id.ButtonRightIndex, FingerId.RIGHT_INDEX);
            setEnrollButtonListener(R.id.ButtonRightMiddle,FingerId.RIGHT_MIDDLE);
            setEnrollButtonListener(R.id.ButtonRightRing,  FingerId.RIGHT_RING);
            setEnrollButtonListener(R.id.ButtonRightLittle,FingerId.RIGHT_LITTLE);
            
            setIdentifyButtonListener();
            setDeleteAllButtonListener();
            setGrabButtonListener();
            setNavigateRawButtonListener();
            setNavigateMouseButtonListener();
            setNavigateDiscreteButtonListener();
        }
	}

	@Override
	public void onFingerprintUnavailable()
	{
		mConn = null;
	}

	@Override
	public void onFingerprintError(String msg)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFingerprintConnectionLost()
	{
		cancelRunningOperation();
	}
	
	/**
	 * cancel running PTAPI Operation
	 */
	private void cancelRunningOperation()
	{

		synchronized (mCond)
		{
			while (mRunningOp != null)
			{
				mRunningOp.interrupt();
				try
				{
					mCond.wait();
				} catch (InterruptedException e)
				{}
			}
		}
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
	}
}