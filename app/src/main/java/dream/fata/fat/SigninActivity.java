package dream.fata.fat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.flatdialoglibrary.dialog.FlatDialog;
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
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class SigninActivity extends AppCompatActivity {
	private AppBarLayout _app_bar;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private CoordinatorLayout _coordinator;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private Toolbar _toolbar;
	private ChildEventListener _users_child_listener;
	private SharedPreferences admin;
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
	private EditText edittext_email;
	private EditText edittext_password;
	private Intent i = new Intent();
	private LinearLayout linear1;
	private LinearLayout linear10;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private String path = "";
	private ProgressDialog pd;
	private TextView textview1;
	private TextView textview_forgot;
	private SharedPreferences user;
	private DatabaseReference users = this._firebase.getReference("users");
	private ScrollView vscroll1;

	/* access modifiers changed from: protected */
	@Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.signin);
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
			/* class dream.fata.fat.SigninActivity.AnonymousClass1 */

			public void onClick(View _v) {
				SigninActivity.this.onBackPressed();
			}
		});
		this.linear1 = (LinearLayout) findViewById(R.id.linear1);
		this.vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		this.linear2 = (LinearLayout) findViewById(R.id.linear2);
		this.linear3 = (LinearLayout) findViewById(R.id.linear3);
		this.linear9 = (LinearLayout) findViewById(R.id.linear9);
		this.linear6 = (LinearLayout) findViewById(R.id.linear6);
		this.linear7 = (LinearLayout) findViewById(R.id.linear7);
		this.linear8 = (LinearLayout) findViewById(R.id.linear8);
		this.textview1 = (TextView) findViewById(R.id.textview1);
		this.linear4 = (LinearLayout) findViewById(R.id.linear4);
		this.linear10 = (LinearLayout) findViewById(R.id.linear10);
		this.linear5 = (LinearLayout) findViewById(R.id.linear5);
		this.edittext_email = (EditText) findViewById(R.id.edittext_email);
		this.edittext_password = (EditText) findViewById(R.id.edittext_password);
		this.button_login = (Button) findViewById(R.id.button_login);
		this.button_signup = (Button) findViewById(R.id.button_signup);
		this.textview_forgot = (TextView) findViewById(R.id.textview_forgot);
		this.auth = FirebaseAuth.getInstance();
		this.admin = getSharedPreferences("admin", 0);
		this.user = getSharedPreferences("user", 0);
		this.button_login.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.SigninActivity.AnonymousClass2 */

			public void onClick(View _view) {
				if (SigninActivity.this.edittext_email.getText().toString().trim().equals("")) {
					SketchwareUtil.showMessage(SigninActivity.this.getApplicationContext(), "Please Check Your Inputs Before Trying Again");
				} else if (!SigninActivity.this.edittext_password.getText().toString().trim().equals("")) {
					Task<AuthResult> signInWithEmailAndPassword = SigninActivity.this.auth.signInWithEmailAndPassword(SigninActivity.this.edittext_email.getText().toString(), SigninActivity.this.edittext_password.getText().toString());
					SigninActivity signinActivity = SigninActivity.this;
					signInWithEmailAndPassword.addOnCompleteListener(signinActivity, signinActivity._auth_sign_in_listener);
					SigninActivity.this.pd.show();
				} else {
					SketchwareUtil.showMessage(SigninActivity.this.getApplicationContext(), "Please Check Your Inputs Before Trying Again");
				}
			}
		});
		this.button_signup.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.SigninActivity.AnonymousClass3 */

			public void onClick(View _view) {
				SigninActivity.this.i.setClass(SigninActivity.this.getApplicationContext(), SignupActivity.class);
				SigninActivity signinActivity = SigninActivity.this;
				signinActivity.startActivity(signinActivity.i);
			}
		});
		this.textview_forgot.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.SigninActivity.AnonymousClass4 */

			public void onClick(View _view) {
				final FlatDialog flatDialog = new FlatDialog(SigninActivity.this);
				flatDialog.setTitle("Forgot Password").setSubtitle("Enter Your Email To Get Reset Password Link").setFirstTextFieldHint("email").setFirstButtonText("Send").setSecondButtonText("Cancel").withFirstButtonListner(new View.OnClickListener() {
					/* class dream.fata.fat.SigninActivity.AnonymousClass4.AnonymousClass2 */

					public void onClick(View view) {
						SigninActivity.this.auth.sendPasswordResetEmail(flatDialog.getFirstTextField()).addOnCompleteListener(SigninActivity.this._auth_reset_password_listener);
						SigninActivity.this.pd.show();
						flatDialog.dismiss();
					}
				}).withSecondButtonListner(new View.OnClickListener() {
					/* class dream.fata.fat.SigninActivity.AnonymousClass4.AnonymousClass1 */

					public void onClick(View view) {
						flatDialog.dismiss();
					}
				}).show();
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
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this._auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			public void onComplete(Task<AuthResult> param1Task) {
				SigninActivity signinActivity;
				boolean bool = param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					String str = param1Task.getException().getMessage();
				} else {
					String str = "";
				}
				if (bool) {
					if (SigninActivity.this.edittext_email.getText().toString().equals("ffadmin@ff.com")) {
						SigninActivity.this.admin.edit().putString("admin", "true").commit();
					} else {
						SigninActivity.this.admin.edit().putString("admin", "false").commit();
					}
					SigninActivity.this.path = "users/".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", ""));
					SigninActivity.this.users.removeEventListener(SigninActivity.this._users_child_listener);
					users = _firebase.getReference(SigninActivity.this.path);
					SigninActivity.this.users.addChildEventListener(SigninActivity.this._users_child_listener);
					SigninActivity.this.users.addListenerForSingleValueEvent(new ValueEventListener() {
						public void onCancelled(DatabaseError param2DatabaseError) {}

						public void onDataChange(DataSnapshot param2DataSnapshot) {
							listmap = new ArrayList();
							try {
								GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

								};
								Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
								while (iterator.hasNext()) {
									HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
									SigninActivity.this.listmap.add(hashMap);
								}
							} catch (Exception exception) {
								exception.printStackTrace();
							}
							SigninActivity.this.user.edit().putString("name", ((HashMap)SigninActivity.this.listmap.get(0)).get("name").toString()).commit();
							SigninActivity.this.user.edit().putString("email", ((HashMap)SigninActivity.this.listmap.get(0)).get("email").toString()).commit();
							SigninActivity.this.user.edit().putString("number", ((HashMap)SigninActivity.this.listmap.get(0)).get("number").toString()).commit();
							SigninActivity.this.user.edit().putString("balance", ((HashMap)SigninActivity.this.listmap.get(0)).get("balance").toString()).commit();
							SigninActivity.this.i.setClass(SigninActivity.this.getApplicationContext(), MainActivity.class);
							SigninActivity.this.startActivity(SigninActivity.this.i);
						}
					});
				} else {
					SketchwareUtil.showMessage(SigninActivity.this.getApplicationContext(), "Something Went Wrong");
				}
				SigninActivity.this.pd.dismiss();
			}
		};
		this._auth_reset_password_listener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				if (param1Task.isSuccessful()) {
					SketchwareUtil.showMessage(SigninActivity.this.getApplicationContext(), "A Password Reset Link Sent to Your Email");
				} else {
					SketchwareUtil.showMessage(SigninActivity.this.getApplicationContext(), "Something Went Wrong");
				}
				SigninActivity.this.pd.dismiss();
			}
		};
	}

	private void initializeLogic() {
		setTitle("Sign In");
		this.linear9.setElevation(30.0f);
		this.linear9.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.SigninActivity.AnonymousClass16 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(25, 2, -14273992, -1));
		this.button_login.setElevation(30.0f);
		this.button_login.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.SigninActivity.AnonymousClass17 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(90, 0, -14273992, -689152));
		this.button_signup.setElevation(30.0f);
		this.button_signup.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.SigninActivity.AnonymousClass18 */

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

	@Override // androidx.activity.ComponentActivity
	public void onBackPressed() {
		finishAffinity();
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
