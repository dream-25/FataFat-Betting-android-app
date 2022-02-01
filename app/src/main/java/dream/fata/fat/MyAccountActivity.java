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
import java.util.Random;

public class MyAccountActivity extends AppCompatActivity {
	private AppBarLayout _app_bar;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private CoordinatorLayout _coordinator;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private Toolbar _toolbar;
	private ChildEventListener _users_child_listener;
	private FirebaseAuth auth;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private Button button1;
	private EditText edittextAcc;
	private EditText edittext_IFSC;
	private EditText edittext_WalletName;
	private EditText edittext_Wallet_Number;
	private EditText edittext_bankName;
	private Intent i = new Intent();
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private HashMap<String, Object> map = new HashMap<>();
	private String path3 = "";
	private ProgressDialog pd;
	private TextView textview1;
	private TextView textview2;
	private TextView textview3;
	private TextView textview4;
	private TextView textview5;
	private TextView textview6;
	private TextView textview7;
	private DatabaseReference users = this._firebase.getReference("users");

	/* access modifiers changed from: protected */
	@Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.my_account);
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
			/* class dream.fata.fat.MyAccountActivity.AnonymousClass1 */

			public void onClick(View _v) {
				MyAccountActivity.this.onBackPressed();
			}
		});
		this.linear8 = (LinearLayout) findViewById(R.id.linear8);
		this.linear1 = (LinearLayout) findViewById(R.id.linear1);
		this.linear2 = (LinearLayout) findViewById(R.id.linear2);
		this.linear3 = (LinearLayout) findViewById(R.id.linear3);
		this.linear4 = (LinearLayout) findViewById(R.id.linear4);
		this.linear5 = (LinearLayout) findViewById(R.id.linear5);
		this.linear6 = (LinearLayout) findViewById(R.id.linear6);
		this.linear7 = (LinearLayout) findViewById(R.id.linear7);
		this.linear9 = (LinearLayout) findViewById(R.id.linear9);
		this.textview1 = (TextView) findViewById(R.id.textview1);
		this.textview2 = (TextView) findViewById(R.id.textview2);
		this.edittext_bankName = (EditText) findViewById(R.id.edittext_bankName);
		this.textview3 = (TextView) findViewById(R.id.textview3);
		this.edittextAcc = (EditText) findViewById(R.id.edittextAcc);
		this.textview4 = (TextView) findViewById(R.id.textview4);
		this.edittext_IFSC = (EditText) findViewById(R.id.edittext_IFSC);
		this.textview5 = (TextView) findViewById(R.id.textview5);
		this.textview6 = (TextView) findViewById(R.id.textview6);
		this.edittext_WalletName = (EditText) findViewById(R.id.edittext_WalletName);
		this.textview7 = (TextView) findViewById(R.id.textview7);
		this.edittext_Wallet_Number = (EditText) findViewById(R.id.edittext_Wallet_Number);
		this.button1 = (Button) findViewById(R.id.button1);
		this.auth = FirebaseAuth.getInstance();
		this.button1.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.MyAccountActivity.AnonymousClass2 */

			public void onClick(View _view) {
				MyAccountActivity.this.map = new HashMap();
				MyAccountActivity.this.map.put("withdraw_bankName", MyAccountActivity.this.edittext_bankName.getText().toString());
				MyAccountActivity.this.map.put("withdraw_bankAcc", MyAccountActivity.this.edittextAcc.getText().toString());
				MyAccountActivity.this.map.put("withdraw_bankIFSC", MyAccountActivity.this.edittext_IFSC.getText().toString());
				MyAccountActivity.this.map.put("withdraw_number", MyAccountActivity.this.edittext_Wallet_Number.getText().toString());
				MyAccountActivity.this.map.put("withdraw_wallet", MyAccountActivity.this.edittext_WalletName.getText().toString());
				MyAccountActivity.this.users.child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", "").concat("")).updateChildren(MyAccountActivity.this.map);
			}
		});
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
				SketchwareUtil.showMessage(getApplicationContext(), "Withdrawal Method Updated Successfully");
				finish();
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
		this.auth_updateEmailListener = new OnCompleteListener<Void>() {
			/* class dream.fata.fat.MyAccountActivity.AnonymousClass4 */

			@Override // com.google.android.gms.tasks.OnCompleteListener
			public void onComplete(Task<Void> _param1) {
				_param1.isSuccessful();
				if (_param1.getException() != null) {
					_param1.getException().getMessage();
				}
			}
		};
		this.auth_updatePasswordListener = new OnCompleteListener<Void>() {
			/* class dream.fata.fat.MyAccountActivity.AnonymousClass5 */

			@Override // com.google.android.gms.tasks.OnCompleteListener
			public void onComplete(Task<Void> _param1) {
				_param1.isSuccessful();
				if (_param1.getException() != null) {
					_param1.getException().getMessage();
				}
			}
		};
		this.auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			/* class dream.fata.fat.MyAccountActivity.AnonymousClass6 */

			@Override // com.google.android.gms.tasks.OnCompleteListener
			public void onComplete(Task<Void> _param1) {
				_param1.isSuccessful();
				if (_param1.getException() != null) {
					_param1.getException().getMessage();
				}
			}
		};
		this.auth_deleteUserListener = new OnCompleteListener<Void>() {
			/* class dream.fata.fat.MyAccountActivity.AnonymousClass7 */

			@Override // com.google.android.gms.tasks.OnCompleteListener
			public void onComplete(Task<Void> _param1) {
				_param1.isSuccessful();
				if (_param1.getException() != null) {
					_param1.getException().getMessage();
				}
			}
		};
		this.auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			/* class dream.fata.fat.MyAccountActivity.AnonymousClass8 */

			@Override // com.google.android.gms.tasks.OnCompleteListener
			public void onComplete(Task<AuthResult> task) {
				task.isSuccessful();
				if (task.getException() != null) {
					task.getException().getMessage();
				}
			}
		};
		this.auth_updateProfileListener = new OnCompleteListener<Void>() {
			/* class dream.fata.fat.MyAccountActivity.AnonymousClass9 */

			@Override // com.google.android.gms.tasks.OnCompleteListener
			public void onComplete(Task<Void> _param1) {
				_param1.isSuccessful();
				if (_param1.getException() != null) {
					_param1.getException().getMessage();
				}
			}
		};
		this.auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			/* class dream.fata.fat.MyAccountActivity.AnonymousClass10 */

			@Override // com.google.android.gms.tasks.OnCompleteListener
			public void onComplete(Task<AuthResult> task) {
				task.isSuccessful();
				if (task.getException() != null) {
					task.getException().getMessage();
				}
			}
		};
		this._auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			/* class dream.fata.fat.MyAccountActivity.AnonymousClass11 */

			@Override // com.google.android.gms.tasks.OnCompleteListener
			public void onComplete(Task<AuthResult> _param1) {
				_param1.isSuccessful();
				if (_param1.getException() != null) {
					_param1.getException().getMessage();
				}
			}
		};
		this._auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			/* class dream.fata.fat.MyAccountActivity.AnonymousClass12 */

			@Override // com.google.android.gms.tasks.OnCompleteListener
			public void onComplete(Task<AuthResult> _param1) {
				_param1.isSuccessful();
				if (_param1.getException() != null) {
					_param1.getException().getMessage();
				}
			}
		};
		this._auth_reset_password_listener = new OnCompleteListener<Void>() {
			/* class dream.fata.fat.MyAccountActivity.AnonymousClass13 */

			@Override // com.google.android.gms.tasks.OnCompleteListener
			public void onComplete(Task<Void> _param1) {
				_param1.isSuccessful();
			}
		};
	}

	private void initializeLogic() {
		ProgressDialog progressDialog = new ProgressDialog(this);
		this.pd = progressDialog;
		progressDialog.setCancelable(false);
		this.pd.setMessage("Loading");
		this.pd.show();
		this.path3 = "users/".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", ""));
		this.users.removeEventListener(this._users_child_listener);
		DatabaseReference reference = this._firebase.getReference(this.path3);
		this.users = reference;
		reference.addChildEventListener(this._users_child_listener);
		this.users.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				listmap = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						listmap.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				MyAccountActivity.this.edittext_bankName.setText(((HashMap) MyAccountActivity.this.listmap.get(0)).get("withdraw_bankName").toString().trim());
				MyAccountActivity.this.edittextAcc.setText(((HashMap) MyAccountActivity.this.listmap.get(0)).get("withdraw_bankAcc").toString().trim());
				MyAccountActivity.this.edittext_IFSC.setText(((HashMap) MyAccountActivity.this.listmap.get(0)).get("withdraw_bankAcc").toString().trim());
				MyAccountActivity.this.edittext_WalletName.setText(((HashMap) MyAccountActivity.this.listmap.get(0)).get("withdraw_bankName").toString().trim());
				MyAccountActivity.this.edittext_Wallet_Number.setText(((HashMap) MyAccountActivity.this.listmap.get(0)).get("withdraw_number").toString().trim());
				MyAccountActivity.this.pd.dismiss();
			}

			@Override // com.google.firebase.database.ValueEventListener
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}

	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
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
