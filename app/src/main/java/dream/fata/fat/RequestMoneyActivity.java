package dream.fata.fat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.content.ClipData;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.OnProgressListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Continuation;
import android.net.Uri;
import java.io.File;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.app.Activity;
import android.content.SharedPreferences;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import android.view.View;
import com.bumptech.glide.Glide;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class RequestMoneyActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP1 = 101;
	public final int REQ_CD_FP2 = 102;
	public final int REQ_CD_FP3 = 103;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String img1 = "";
	private String img3 = "";
	private String img2 = "";
	private String name = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String path = "";
	private String path2 = "";
	private String path3 = "";
	private HashMap<String, Object> map1 = new HashMap<>();
	private HashMap<String, Object> map2 = new HashMap<>();
	private String path_noti = "";
	
	private ArrayList<String> list_str = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private Button button1;
	private TextView textview1;
	private LinearLayout linear5;
	private LinearLayout linear7;
	private TextView textview2;
	private EditText edittext_ammo;
	private TextView textview3;
	private EditText edittext_ref;
	private TextView textview4;
	private ImageView imageview1;
	
	private Intent fp1 = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent fp2 = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent fp3 = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference str = _firebase_storage.getReference("str");
	private OnCompleteListener<Uri> _str_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _str_download_success_listener;
	private OnSuccessListener _str_delete_success_listener;
	private OnProgressListener _str_upload_progress_listener;
	private OnProgressListener _str_download_progress_listener;
	private OnFailureListener _str_failure_listener;
	
	private DatabaseReference add_money = _firebase.getReference("add_money");
	private ChildEventListener _add_money_child_listener;
	private ProgressDialog pd;
	private StorageReference str2 = _firebase_storage.getReference("str");
	private OnCompleteListener<Uri> _str2_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _str2_download_success_listener;
	private OnSuccessListener _str2_delete_success_listener;
	private OnProgressListener _str2_upload_progress_listener;
	private OnProgressListener _str2_download_progress_listener;
	private OnFailureListener _str2_failure_listener;
	
	private StorageReference str3 = _firebase_storage.getReference("str");
	private OnCompleteListener<Uri> _str3_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _str3_download_success_listener;
	private OnSuccessListener _str3_delete_success_listener;
	private OnProgressListener _str3_upload_progress_listener;
	private OnProgressListener _str3_download_progress_listener;
	private OnFailureListener _str3_failure_listener;
	
	private TimerTask t;
	private Calendar c = Calendar.getInstance();
	private SharedPreferences user;
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	
	private DatabaseReference add_money2 = _firebase.getReference("add_money2");
	private ChildEventListener _add_money2_child_listener;
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private DatabaseReference user_noti = _firebase.getReference("user_noti");
	private ChildEventListener _user_noti_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.request_money);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		button1 = findViewById(R.id.button1);
		textview1 = findViewById(R.id.textview1);
		linear5 = findViewById(R.id.linear5);
		linear7 = findViewById(R.id.linear7);
		textview2 = findViewById(R.id.textview2);
		edittext_ammo = findViewById(R.id.edittext_ammo);
		textview3 = findViewById(R.id.textview3);
		edittext_ref = findViewById(R.id.edittext_ref);
		textview4 = findViewById(R.id.textview4);
		imageview1 = findViewById(R.id.imageview1);
		fp1.setType("image/*");
		fp1.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		fp2.setType("image/*");
		fp2.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		fp3.setType("image/*");
		fp3.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		user = getSharedPreferences("user", Activity.MODE_PRIVATE);
		auth = FirebaseAuth.getInstance();
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				c = Calendar.getInstance();
				if (edittext_ammo.getText().toString().trim().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Please Enter A Valid Ammount (100₹ - 50,000₹)");
				}
				else {
					if (edittext_ref.getText().toString().trim().equals("")) {
						SketchwareUtil.showMessage(getApplicationContext(), "Please Enter Your TXN REF NO");
					}
					else {
						if (Double.parseDouble(edittext_ammo.getText().toString()) < 50001) {
							if (Double.parseDouble(edittext_ammo.getText().toString()) > 99) {
								if (edittext_ref.getText().toString().trim().equals("")) {
									SketchwareUtil.showMessage(getApplicationContext(), "Please Enter Your TXN REF NO");
								}
								else {
									if (imageview1.getAlpha() == 1) {
										map = new HashMap<>();
										map.put("pending_ammount", edittext_ammo.getText().toString());
										map.put("ref", edittext_ref.getText().toString());
										map.put("pending", "deposit");
										map.put("img1", img1);
										path3 = "users/".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", ""));
										users.removeEventListener(_users_child_listener);
										users = _firebase.getReference(path3);
										users.addChildEventListener(_users_child_listener);
										users.child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", "").concat("")).updateChildren(map);
										path2 = "user_h";
										add_money2.removeEventListener(_add_money2_child_listener);
										add_money2 = _firebase.getReference(path2);
										add_money2.addChildEventListener(_add_money2_child_listener);
										add_money2.child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", "").concat("")).updateChildren(map);
										pd.show();
										pd.setMessage("Loading");
										map2 = new HashMap<>();
										map2.put("rs", edittext_ammo.getText().toString());
										map2.put("action", "deposit");
										map2.put("time", new SimpleDateFormat("dd-MM-yyyy hh:mm aa").format(c.getTime()));
										map2.put("status", "Pending");
										path_noti = "userHistory/".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", "").concat("/Transactions".concat("")));
										user_noti.removeEventListener(_user_noti_child_listener);
										user_noti = _firebase.getReference(path_noti);
										user_noti.addChildEventListener(_user_noti_child_listener);
										user_noti.push().updateChildren(map2);
										t = new TimerTask() {
											@Override
											public void run() {
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														SketchwareUtil.showMessage(getApplicationContext(), "Request Sent Successfully You Can Check Details In Transaction Option Under Accounts and History");
														finish();
													}
												});
											}
										};
										_timer.schedule(t, (int)(2000));
									}
									else {
										SketchwareUtil.showMessage(getApplicationContext(), "Please Upload A Image First");
									}
								}
							}
							else {
								SketchwareUtil.showMessage(getApplicationContext(), "Please Enter A Valid Ammount (100₹ - 50,000₹)");
							}
						}
						else {
							SketchwareUtil.showMessage(getApplicationContext(), "Please Enter A Valid Ammount (100₹ - 50,000₹)");
						}
					}
				}
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				c = Calendar.getInstance();
				name = new SimpleDateFormat("ddMMyyhhmmssaa").format(c.getTime());
				startActivityForResult(fp1, REQ_CD_FP1);
			}
		});
		
		_str_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				pd.setProgress((int)_progressValue);
			}
		};
		
		_str_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_str_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				img1 = _downloadUrl;
				Glide.with(getApplicationContext()).load(Uri.parse(_downloadUrl)).into(imageview1);
				imageview1.setAlpha((float)(1));
				pd.dismiss();
			}
		};
		
		_str_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_str_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_str_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				SketchwareUtil.showMessage(getApplicationContext(), _message);
				pd.dismiss();
			}
		};
		
		_add_money_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		add_money.addChildEventListener(_add_money_child_listener);
		
		_str2_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				pd.setProgress((int)_progressValue);
			}
		};
		
		_str2_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_str2_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				pd.dismiss();
				img2 = _downloadUrl;
				
				
			}
		};
		
		_str2_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_str2_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_str2_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				pd.dismiss();
				SketchwareUtil.showMessage(getApplicationContext(), _message);
			}
		};
		
		_str3_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				pd.setProgress((int)_progressValue);
			}
		};
		
		_str3_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_str3_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				pd.dismiss();
				img3 = _downloadUrl;
				
				
			}
		};
		
		_str3_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_str3_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_str3_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				pd.dismiss();
				SketchwareUtil.showMessage(getApplicationContext(), _message);
			}
		};
		
		_add_money2_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		add_money2.addChildEventListener(_add_money2_child_listener);
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		users.addChildEventListener(_users_child_listener);
		
		_user_noti_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		user_noti.addChildEventListener(_user_noti_child_listener);
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		img1 = "https://firebasestorage.googleapis.com/v0/b/fotafot-2eaff.appspot.com/o/20210810_005137.jpg?alt=media&token=bf5cc88f-3a91-4d0a-98f1-2455ea834d76";
		img2 = "https://firebasestorage.googleapis.com/v0/b/fotafot-2eaff.appspot.com/o/20210810_005137.jpg?alt=media&token=bf5cc88f-3a91-4d0a-98f1-2455ea834d76";
		img3 = "https://firebasestorage.googleapis.com/v0/b/fotafot-2eaff.appspot.com/o/20210810_005137.jpg?alt=media&token=bf5cc88f-3a91-4d0a-98f1-2455ea834d76";
		pd = new ProgressDialog(RequestMoneyActivity.this);
		pd.setMessage("Uploading");
		pd.setProgress((int)0);
		pd.setMax((int)100);
		pd.setCancelable(false);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP1:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				str.child(name).putFile(Uri.fromFile(new File(_filePath.get((int)(0))))).addOnFailureListener(_str_failure_listener).addOnProgressListener(_str_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return str.child(name).getDownloadUrl();
					}}).addOnCompleteListener(_str_upload_success_listener);
				pd.show();
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP2:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				str2.child(name).putFile(Uri.fromFile(new File(_filePath.get((int)(0))))).addOnFailureListener(_str2_failure_listener).addOnProgressListener(_str2_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return str2.child(name).getDownloadUrl();
					}}).addOnCompleteListener(_str2_upload_success_listener);
				pd.show();
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP3:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				str3.child(name).putFile(Uri.fromFile(new File(_filePath.get((int)(0))))).addOnFailureListener(_str3_failure_listener).addOnProgressListener(_str3_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return str3.child(name).getDownloadUrl();
					}}).addOnCompleteListener(_str3_upload_success_listener);
				pd.show();
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
