package dream.fata.fat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class WithdrawMoneyActivity extends AppCompatActivity {
	private ChildEventListener _add_money2_child_listener;
	private AppBarLayout _app_bar;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private CoordinatorLayout _coordinator;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private Timer _timer = new Timer();
	private Toolbar _toolbar;
	private ChildEventListener _user_noti_child_listener;
	private ChildEventListener _users_child_listener;
	private DatabaseReference add_money2 = this._firebase.getReference("add_money2");
	private FirebaseAuth auth;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private double balance = 0.0d;
	private Button button1;
	private Button button3;
	private Calendar c = Calendar.getInstance();
	private EditText edittext_ammo;
	private EditText edittext_ref;
	private Intent i = new Intent();
	private LinearLayout linear1;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> map2 = new HashMap<>();
	private double n = 0.0d;
	private String path2 = "";
	private String path3 = "";
	private String path_noti = "";
	private ProgressDialog pd;
	private TimerTask t;
	private TextView textview1;
	private TextView textview2;
	private TextView textview5;
	private TextView textview6;
	private DatabaseReference user_noti = this._firebase.getReference("user_noti");
	private DatabaseReference users = this._firebase.getReference("users");

	/* access modifiers changed from: protected */
	@Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.withdraw_money);
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
			/* class dream.fata.fat.WithdrawMoneyActivity.AnonymousClass1 */

			public void onClick(View _v) {
				WithdrawMoneyActivity.this.onBackPressed();
			}
		});
		this.linear1 = (LinearLayout) findViewById(R.id.linear1);
		this.linear2 = (LinearLayout) findViewById(R.id.linear2);
		this.linear4 = (LinearLayout) findViewById(R.id.linear4);
		this.button1 = (Button) findViewById(R.id.button1);
		this.button3 = (Button) findViewById(R.id.button3);
		this.textview1 = (TextView) findViewById(R.id.textview1);
		this.linear13 = (LinearLayout) findViewById(R.id.linear13);
		this.linear5 = (LinearLayout) findViewById(R.id.linear5);
		this.linear12 = (LinearLayout) findViewById(R.id.linear12);
		this.textview6 = (TextView) findViewById(R.id.textview6);
		this.textview2 = (TextView) findViewById(R.id.textview2);
		this.edittext_ammo = (EditText) findViewById(R.id.edittext_ammo);
		this.textview5 = (TextView) findViewById(R.id.textview5);
		this.edittext_ref = (EditText) findViewById(R.id.edittext_ref);
		this.auth = FirebaseAuth.getInstance();
		this.button1.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.WithdrawMoneyActivity.AnonymousClass2 */

			public void onClick(View _view) {
				WithdrawMoneyActivity.this.c = Calendar.getInstance();
				if (WithdrawMoneyActivity.this.edittext_ammo.getText().toString().trim().equals("")) {
					SketchwareUtil.showMessage(WithdrawMoneyActivity.this.getApplicationContext(), "Please Enter A Valid Ammount (500₹ - 50,000₹)");
				} else if (Double.parseDouble(WithdrawMoneyActivity.this.edittext_ammo.getText().toString()) >= WithdrawMoneyActivity.this.balance) {
					SketchwareUtil.showMessage(WithdrawMoneyActivity.this.getApplicationContext(), "Insufficient Balance");
				} else if (Double.parseDouble(WithdrawMoneyActivity.this.edittext_ammo.getText().toString()) >= 50001.0d) {
					SketchwareUtil.showMessage(WithdrawMoneyActivity.this.getApplicationContext(), "Please Enter A Valid Ammount (500₹ - 50,000₹)");
				} else if (Double.parseDouble(WithdrawMoneyActivity.this.edittext_ammo.getText().toString()) <= 499.0d) {
					SketchwareUtil.showMessage(WithdrawMoneyActivity.this.getApplicationContext(), "Please Enter A Valid Ammount (500₹ - 50,000₹)");
				} else if (WithdrawMoneyActivity.this.edittext_ref.getText().toString().trim().equals("")) {
					SketchwareUtil.showMessage(WithdrawMoneyActivity.this.getApplicationContext(), "Please Enter Your TXN REF NO");
				} else {
					WithdrawMoneyActivity.this.map = new HashMap();
					WithdrawMoneyActivity.this.path3 = "users/".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", ""));
					WithdrawMoneyActivity.this.path2 = "user_h";
					WithdrawMoneyActivity.this.map.put("pending_ammount", WithdrawMoneyActivity.this.edittext_ammo.getText().toString());
					WithdrawMoneyActivity.this.map.put("ref", WithdrawMoneyActivity.this.edittext_ref.getText().toString());
					WithdrawMoneyActivity.this.map.put("pending", "withdraw");
					WithdrawMoneyActivity.this.map.put("img1", "null");
					WithdrawMoneyActivity.this.users.removeEventListener(WithdrawMoneyActivity.this._users_child_listener);
					WithdrawMoneyActivity withdrawMoneyActivity = WithdrawMoneyActivity.this;
					withdrawMoneyActivity.users = withdrawMoneyActivity._firebase.getReference(WithdrawMoneyActivity.this.path3);
					WithdrawMoneyActivity.this.users.addChildEventListener(WithdrawMoneyActivity.this._users_child_listener);
					WithdrawMoneyActivity.this.users.child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", "").concat("")).updateChildren(WithdrawMoneyActivity.this.map);
					WithdrawMoneyActivity.this.add_money2.removeEventListener(WithdrawMoneyActivity.this._add_money2_child_listener);
					WithdrawMoneyActivity withdrawMoneyActivity2 = WithdrawMoneyActivity.this;
					withdrawMoneyActivity2.add_money2 = withdrawMoneyActivity2._firebase.getReference(WithdrawMoneyActivity.this.path2);
					WithdrawMoneyActivity.this.add_money2.addChildEventListener(WithdrawMoneyActivity.this._add_money2_child_listener);
					WithdrawMoneyActivity.this.add_money2.child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", "").concat("")).updateChildren(WithdrawMoneyActivity.this.map);
					WithdrawMoneyActivity.this.pd.show();
					WithdrawMoneyActivity.this.pd.setMessage("Loading");
					WithdrawMoneyActivity.this.c = Calendar.getInstance();
					WithdrawMoneyActivity.this.map2 = new HashMap();
					WithdrawMoneyActivity.this.map2.put("rs", WithdrawMoneyActivity.this.edittext_ammo.getText().toString());
					WithdrawMoneyActivity.this.map2.put("action", "withdraw");
					WithdrawMoneyActivity.this.map2.put("time", new SimpleDateFormat("dd-MM-yyyy hh:mm aa").format(WithdrawMoneyActivity.this.c.getTime()));
					WithdrawMoneyActivity.this.map2.put(NotificationCompat.CATEGORY_STATUS, "Pending");
					WithdrawMoneyActivity.this.path_noti = "userHistory/".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", "").concat("/Transactions".concat("")));
					WithdrawMoneyActivity.this.user_noti.removeEventListener(WithdrawMoneyActivity.this._user_noti_child_listener);
					WithdrawMoneyActivity withdrawMoneyActivity3 = WithdrawMoneyActivity.this;
					withdrawMoneyActivity3.user_noti = withdrawMoneyActivity3._firebase.getReference(WithdrawMoneyActivity.this.path_noti);
					WithdrawMoneyActivity.this.user_noti.addChildEventListener(WithdrawMoneyActivity.this._user_noti_child_listener);
					WithdrawMoneyActivity.this.user_noti.push().updateChildren(WithdrawMoneyActivity.this.map2);
					WithdrawMoneyActivity.this.t = new TimerTask() {
						/* class dream.fata.fat.WithdrawMoneyActivity.AnonymousClass2.AnonymousClass1 */

						public void run() {
							WithdrawMoneyActivity.this.runOnUiThread(new Runnable() {
								/* class dream.fata.fat.WithdrawMoneyActivity.AnonymousClass2.AnonymousClass1.AnonymousClass1 */

								public void run() {
									SketchwareUtil.showMessage(WithdrawMoneyActivity.this.getApplicationContext(), "Request Sent Successfully You Can Check Details In Transaction Option Under Accounts and History");
									WithdrawMoneyActivity.this.finish();
								}
							});
						}
					};
					WithdrawMoneyActivity.this._timer.schedule(WithdrawMoneyActivity.this.t, 2000);
				}
			}
		});
		this.button3.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.WithdrawMoneyActivity.AnonymousClass3 */

			public void onClick(View _view) {
				WithdrawMoneyActivity.this.i.setClass(WithdrawMoneyActivity.this.getApplicationContext(), MyAccountActivity.class);
				WithdrawMoneyActivity withdrawMoneyActivity = WithdrawMoneyActivity.this;
				withdrawMoneyActivity.startActivity(withdrawMoneyActivity.i);
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
		this._add_money2_child_listener = childEventListener;
		this.add_money2.addChildEventListener(childEventListener);
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
		this._user_noti_child_listener = childEventListener;
		this.user_noti.addChildEventListener(childEventListener);
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
		ProgressDialog progressDialog = new ProgressDialog(this);
		this.pd = progressDialog;
		progressDialog.setMessage("Loading");
		this.pd.setProgress(0);
		this.pd.setMax(100);
		this.pd.setCancelable(false);
		this.pd.show();
		this.path3 = "users/".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", ""));
		this.users.removeEventListener(this._users_child_listener);
		DatabaseReference reference = this._firebase.getReference(this.path3);
		this.users = reference;
		reference.addChildEventListener(this._users_child_listener);
		this.users.addListenerForSingleValueEvent(new ValueEventListener() {

			public void onDataChange(DataSnapshot param1DataSnapshot) {
				listmap = new ArrayList();
				try {
					GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

					};
					Iterator<DataSnapshot> iterator = param1DataSnapshot.getChildren().iterator();
					while (iterator.hasNext()) {
						HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
						WithdrawMoneyActivity.this.listmap.add(hashMap);
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				WithdrawMoneyActivity withdrawMoneyActivity = WithdrawMoneyActivity.this;
				withdrawMoneyActivity.balance = Double.parseDouble(((HashMap) withdrawMoneyActivity.listmap.get(0)).get("balance").toString());
				WithdrawMoneyActivity.this.textview6.setText("Wallet Balance :".concat(String.valueOf((long) WithdrawMoneyActivity.this.balance)));
				WithdrawMoneyActivity.this.pd.dismiss();
			}

			@Override // com.google.firebase.database.ValueEventListener
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
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
