package com.lotus.deals.fragments.publish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lotus.deals.R;
import com.lotus.deals.model.Deal;

public class PublishFragmentPage3 extends Fragment implements OnClickListener {

	private Deal deal;
	private PublishFragmentPage3Listener listener;
	
	private ImageView imageView;
	private Button browseButton;
	private Button cameraButton;
	private Button uploadButton;
	private Button previewButton;
	
	static int RESULT_LOAD_IMAGE = 0;
	static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
	
	public interface PublishFragmentPage3Listener {
		public void previewButtonPage3Clicked(Deal deal);
		public void uploadButtonPage3Clicked(Deal deal);
	}
	
	public PublishFragmentPage3() {
		// TODO Auto-generated constructor stub
	}
	
	public static PublishFragmentPage3 newInstance(Deal deal) {
		PublishFragmentPage3 publishFragmentPage3 = new PublishFragmentPage3();
        Bundle args = new Bundle();
        args.putSerializable("deal", deal);
        publishFragmentPage3.setArguments(args);
        return publishFragmentPage3;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_publish_page3, container, false);
      
      imageView = (ImageView) view.findViewById(R.id.uploadPicture);
      
      browseButton = (Button) view.findViewById(R.id.browseButton);
      cameraButton = (Button) view.findViewById(R.id.cameraButton);
      previewButton = (Button) view.findViewById(R.id.previewButton);
      uploadButton = (Button) view.findViewById(R.id.uploadButton);
      
      browseButton.setOnClickListener(this);
      cameraButton.setOnClickListener(this);
      previewButton.setOnClickListener(this);
      uploadButton.setOnClickListener(this);
      
      return view;
    }

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if(activity instanceof PublishFragmentPage3Listener) {
			this.listener = (PublishFragmentPage3Listener) activity;
		} else {
			throw new ClassCastException(activity.toString()
		            + " must implement MyListFragment.OnItemSelectedListener");
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getActivity().getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			// String picturePath contains the path of selected Image
			imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
		} else if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
			Bitmap photo = (Bitmap) data.getExtras().get("data"); 
			imageView.setImageBitmap(photo);
		}
	}
	
	// http://viralpatel.net/blogs/pick-image-from-galary-android-app/
	private void browseButtonPressed() {
		Intent i = new Intent(
				Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				 
				startActivityForResult(i, RESULT_LOAD_IMAGE);
	}
	
	private void cameraButtonPressed() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	private void uploadButtonPressed() {
		if(validateData()) {
			this.listener.uploadButtonPage3Clicked(deal);
		} else {
			Context context = getActivity().getApplicationContext();
	        Toast.makeText(context, "Validation failed", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void previewButtonPressed() {
		if(validateData()) {
			this.listener.previewButtonPage3Clicked(deal);
		} else {
			Context context = getActivity().getApplicationContext();
	        Toast.makeText(context, "Validation failed", Toast.LENGTH_SHORT).show();
		}
	}
	
	private boolean validateData() {
		if(imageView.getDrawable() != null) {
			return true;
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.browseButton:
			browseButtonPressed();
			break;
		case R.id.cameraButton:
			cameraButtonPressed();
			break;
		case R.id.uploadButton:
			uploadButtonPressed();
			break;
		case R.id.previewButton:
			previewButtonPressed();
			break;
		default:
			break;
		}
	}
	
}
