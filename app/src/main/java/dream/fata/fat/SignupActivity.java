package dream.fata.fat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SignupActivity extends AppCompatActivity {
	private AppBarLayout _app_bar;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private CoordinatorLayout _coordinator;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private Toolbar _toolbar;
	private ChildEventListener _user_child_listener;
	private ChildEventListener _users_child_listener;
	private FirebaseAuth auth;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private Button button_login;
	private Button button_signup;
	private EditText edittext_c_password;
	private EditText edittext_email;
	private EditText edittext_name;
	private EditText edittext_number;
	private EditText edittext_password;
	private Intent i = new Intent();
	private LinearLayout linear1;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear9;
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> map1 = new HashMap<>();
	private String path = "";
	private ProgressDialog pd;
	private TextView textview1;
	private DatabaseReference user = this._firebase.getReference("user_h");
	private DatabaseReference users = this._firebase.getReference("users");
	private ScrollView vscroll1;

	/* access modifiers changed from: protected */
	@Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.signup);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}

	private void initialize(Bundle _savedInstanceState) {
		this._app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		this._coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		Toolbar toolbar = (Toolbar) findViewById(R.id._toolbar);
		this._toolbar = toolbar;
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		this._toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.SignupActivity.AnonymousClass1 */

			public void onClick(View _v) {
				SignupActivity.this.onBackPressed();
			}
		});
		this.linear1 = (LinearLayout) findViewById(R.id.linear1);
		this.vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		this.linear2 = (LinearLayout) findViewById(R.id.linear2);
		this.linear3 = (LinearLayout) findViewById(R.id.linear3);
		this.linear9 = (LinearLayout) findViewById(R.id.linear9);
		this.linear6 = (LinearLayout) findViewById(R.id.linear6);
		this.linear7 = (LinearLayout) findViewById(R.id.linear7);
		this.textview1 = (TextView) findViewById(R.id.textview1);
		this.linear4 = (LinearLayout) findViewById(R.id.linear4);
		this.linear10 = (LinearLayout) findViewById(R.id.linear10);
		this.linear5 = (LinearLayout) findViewById(R.id.linear5);
		this.linear18 = (LinearLayout) findViewById(R.id.linear18);
		this.linear11 = (LinearLayout) findViewById(R.id.linear11);
		this.linear17 = (LinearLayout) findViewById(R.id.linear17);
		this.linear12 = (LinearLayout) findViewById(R.id.linear12);
		this.linear14 = (LinearLayout) findViewById(R.id.linear14);
		this.linear13 = (LinearLayout) findViewById(R.id.linear13);
		this.edittext_name = (EditText) findViewById(R.id.edittext_name);
		this.edittext_number = (EditText) findViewById(R.id.edittext_number);
		this.edittext_email = (EditText) findViewById(R.id.edittext_email);
		this.edittext_password = (EditText) findViewById(R.id.edittext_password);
		this.edittext_c_password = (EditText) findViewById(R.id.edittext_c_password);
		this.button_signup = (Button) findViewById(R.id.button_signup);
		this.button_login = (Button) findViewById(R.id.button_login);
		this.auth = FirebaseAuth.getInstance();
		this.button_signup.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.SignupActivity.AnonymousClass2 */

			public void onClick(View _view) {
				if (SignupActivity.this.edittext_email.getText().toString().trim().equals("")) {
					SketchwareUtil.showMessage(SignupActivity.this.getApplicationContext(), "Please Check Your Inputs Before Trying Again");
				} else if (!SignupActivity.this.edittext_password.getText().toString().trim().equals("")) {
					Task<AuthResult> createUserWithEmailAndPassword = SignupActivity.this.auth.createUserWithEmailAndPassword(SignupActivity.this.edittext_email.getText().toString(), SignupActivity.this.edittext_password.getText().toString());
					SignupActivity signupActivity = SignupActivity.this;
					createUserWithEmailAndPassword.addOnCompleteListener(signupActivity, signupActivity._auth_create_user_listener);
					SignupActivity.this.pd.show();
				} else {
					SketchwareUtil.showMessage(SignupActivity.this.getApplicationContext(), "Please Check Your Inputs Before Trying Again");
				}
			}
		});
		ChildEventListener childEventListener = new ChildEventListener() {
			public void onCancelled(DatabaseError param1DatabaseError) {
				param1DatabaseError.getCode();
				param1DatabaseError.getMessage();
			}

			public void onChildAdded(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
				SketchwareUtil.showMessage(SignupActivity.this.getApplicationContext(), "User Registration Complete Please Login Again");
				SignupActivity.this.i.setClass(SignupActivity.this.getApplicationContext(), SigninActivity.class);
				SignupActivity signupActivity = SignupActivity.this;
				signupActivity.startActivity(signupActivity.i);
			}

			public void onChildChanged(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildMoved(DataSnapshot param1DataSnapshot, String param1String) {}

			public void onChildRemoved(DataSnapshot param1DataSnapshot) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}
		};
		this._users_child_listener = childEventListener;
		this.users.addChildEventListener(childEventListener);
		childEventListener = new ChildEventListener() {
			public void onCancelled(DatabaseError param1DatabaseError) {
				param1DatabaseError.getCode();
				param1DatabaseError.getMessage();
			}

			public void onChildAdded(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildChanged(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildMoved(DataSnapshot param1DataSnapshot, String param1String) {}

			public void onChildRemoved(DataSnapshot param1DataSnapshot) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}
		};
		this._user_child_listener = childEventListener;
		this.user.addChildEventListener(childEventListener);
		this.auth_updateEmailListener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this.auth_updatePasswordListener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this.auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this.auth_deleteUserListener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this.auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			public void onComplete(Task<AuthResult> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this.auth_updateProfileListener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this.auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			public void onComplete(Task<AuthResult> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this._auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			public void onComplete(Task<AuthResult> param1Task) {
				SignupActivity signupActivity;
				boolean bool = param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					String str = param1Task.getException().getMessage();
				} else {
					String str = "";
				}
				SignupActivity.this.pd.dismiss();
				if (bool) {

					path = "users/".concat(edittext_email.getText().toString().replace(" @", "").replace(".", "").replace(" ", ""));
					SignupActivity.this.users.removeEventListener(SignupActivity.this._users_child_listener);
					SignupActivity signupActivity2 = SignupActivity.this;
					signupActivity2.users = signupActivity2._firebase.getReference(SignupActivity.this.path);
					SignupActivity.this.users.addChildEventListener(SignupActivity.this._users_child_listener);
					SignupActivity.this.map = new HashMap();
					SignupActivity.this.map.put("name", SignupActivity.this.edittext_name.getText().toString());
					SignupActivity.this.map.put("email", SignupActivity.this.edittext_email.getText().toString());
					SignupActivity.this.map.put("number", SignupActivity.this.edittext_number.getText().toString());
					SignupActivity.this.map.put("pending", "clear");
					SignupActivity.this.map.put("balance", "0");
					SignupActivity.this.map.put("withdraw_bankName", " ");
					SignupActivity.this.map.put("withdraw_bankAcc", " ");
					SignupActivity.this.map.put("withdraw_bankIFSC", " ");
					SignupActivity.this.map.put("withdraw_number", " ");
					SignupActivity.this.map.put("withdraw_wallet", " ");
					SignupActivity.this.map.put("pending_ammount", "0");
					SignupActivity.this.map.put("img1", "");
					SignupActivity.this.map.put("ref", "");
					SignupActivity.this.users.child(SignupActivity.this.edittext_email.getText().toString().replace(" @", "").replace(".", "").replace(" ", "")).updateChildren(SignupActivity.this.map);
					SignupActivity.this.user.child(SignupActivity.this.edittext_email.getText().toString().replace(" @", "").replace(".", "").replace(" ", "")).updateChildren(SignupActivity.this.map);
					return;
				}
				SketchwareUtil.showMessage(SignupActivity.this.getApplicationContext(), "Something Went Wrong");
			}
		};
		this._auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			public void onComplete(Task<AuthResult> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this._auth_reset_password_listener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				param1Task.isSuccessful();
			}
		};
	}

	private void initializeLogic() {
		this.users.removeEventListener(this._users_child_listener);
		setTitle("Signup");
		this.linear9.setElevation(30.0f);
		this.linear9.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.SignupActivity.AnonymousClass15 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(25, 2, -14273992, -1));
		this.button_signup.setElevation(30.0f);
		this.button_signup.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.SignupActivity.AnonymousClass16 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(90, 0, -14273992, -1499549));
		ProgressDialog progressDialog = new ProgressDialog(this);
		this.pd = progressDialog;
		progressDialog.setMessage("Loading");
		this.pd.setCancelable(false);
	}

	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, 0).show();
	}

	@Deprecated
	public int getLocationX(View _v) {
		int[] _location = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}

	@Deprecated
	public int getLocationY(View _v) {
		int[] _location = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}

	@Deprecated
	public int getRandom(int _min, int _max) {
		return new Random().nextInt((_max - _min) + 1) + _min;
	}

	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx)) {
				_result.add(Double.valueOf((double) _arr.keyAt(_iIdx)));
			}
		}
		return _result;
	}

	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(1, (float) _input, getResources().getDisplayMetrics());
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
