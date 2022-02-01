package dream.fata.fat;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
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
import java.util.Iterator;
import java.util.Random;

public class UsersActivity extends AppCompatActivity {
	private AppBarLayout _app_bar;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private CoordinatorLayout _coordinator;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private Toolbar _toolbar;
	private ChildEventListener _user_child_listener;
	private FirebaseAuth auth;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private Intent i = new Intent();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ListView listview1;
	private DatabaseReference user = this._firebase.getReference("user_h");

	/* access modifiers changed from: protected */
	@Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.users);
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
			/* class dream.fata.fat.UsersActivity.AnonymousClass1 */

			public void onClick(View _v) {
				UsersActivity.this.onBackPressed();
			}
		});
		this.listview1 = (ListView) findViewById(R.id.listview1);
		this.auth = FirebaseAuth.getInstance();
		this.listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			/* class dream.fata.fat.UsersActivity.AnonymousClass2 */

			@Override // android.widget.AdapterView.OnItemClickListener
			public void onItemClick(AdapterView<?> adapterView, View _param2, int _param3, long _param4) {
				UsersActivity.this.i.setClass(UsersActivity.this.getApplicationContext(), UserDetailsActivity.class);
				UsersActivity.this.i.putExtra("email", ((HashMap) UsersActivity.this.listmap.get(_param3)).get("email").toString());
				UsersActivity usersActivity = UsersActivity.this;
				usersActivity.startActivity(usersActivity.i);
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
				UsersActivity.this.user.addListenerForSingleValueEvent(new ValueEventListener() {
					public void onCancelled(DatabaseError param2DatabaseError) {}

					public void onDataChange(DataSnapshot param2DataSnapshot) {
						listmap = new ArrayList();
						try {
							GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

							};
							Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
							while (iterator.hasNext()) {
								HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
								UsersActivity.this.listmap.add(hashMap);
							}
						} catch (Exception exception) {
							exception.printStackTrace();
						}
						UsersActivity.this.listview1.setAdapter((ListAdapter)new UsersActivity.Listview1Adapter(UsersActivity.this.listmap));
						((BaseAdapter)UsersActivity.this.listview1.getAdapter()).notifyDataSetChanged();
					}
				});
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
	}

	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;

		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			this._data = _arr;
		}

		public int getCount() {
			return this._data.size();
		}

		public HashMap<String, Object> getItem(int _index) {
			return this._data.get(_index);
		}

		public long getItemId(int _index) {
			return (long) _index;
		}

		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater) UsersActivity.this.getBaseContext().getSystemService("layout_inflater");
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.cus_users, (ViewGroup) null);
			}
			LinearLayout linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
			LinearLayout linearLayout = (LinearLayout) _view.findViewById(R.id.linear1);
			LinearLayout linearLayout2 = (LinearLayout) _view.findViewById(R.id.linear3);
			Button button3 = (Button) _view.findViewById(R.id.button3);
			LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			Button button1 = (Button) _view.findViewById(R.id.button1);
			Button button2 = (Button) _view.findViewById(R.id.button2);
			((TextView) _view.findViewById(R.id.textview1)).setText(String.valueOf((long) (_position + 1)));
			((TextView) _view.findViewById(R.id.textview_mobile)).setText(this._data.get(_position).get("email").toString());
			((TextView) _view.findViewById(R.id.textview_email)).setText("[".concat(this._data.get(_position).get("name").toString().concat("]")));
			linear4.setElevation(30.0f);
			linear4.setBackground(new GradientDrawable() {
				/* class dream.fata.fat.UsersActivity.Listview1Adapter.AnonymousClass1 */

				public GradientDrawable getIns(int a, int b, int c, int d) {
					setCornerRadius((float) a);
					setStroke(b, c);
					setColor(d);
					return this;
				}
			}.getIns(20, 1, -3155748, -1));
			if (this._data.get(_position).get("pending").toString().equals("clear")) {
				linear2.setBackground(new GradientDrawable() {
					/* class dream.fata.fat.UsersActivity.Listview1Adapter.AnonymousClass2 */

					public GradientDrawable getIns(int a, int b, int c, int d) {
						setCornerRadius((float) a);
						setStroke(b, c);
						setColor(d);
						return this;
					}
				}.getIns(90, 0, -3155748, -11751600));
			} else if (this._data.get(_position).get("pending").toString().equals("deposit")) {
				linear2.setBackground(new GradientDrawable() {
					/* class dream.fata.fat.UsersActivity.Listview1Adapter.AnonymousClass3 */

					public GradientDrawable getIns(int a, int b, int c, int d) {
						setCornerRadius((float) a);
						setStroke(b, c);
						setColor(d);
						return this;
					}
				}.getIns(90, 0, -3155748, -769226));
			} else if (this._data.get(_position).get("pending").toString().equals("withdraw")) {
				linear2.setBackground(new GradientDrawable() {
					/* class dream.fata.fat.UsersActivity.Listview1Adapter.AnonymousClass4 */

					public GradientDrawable getIns(int a, int b, int c, int d) {
						setCornerRadius((float) a);
						setStroke(b, c);
						setColor(d);
						return this;
					}
				}.getIns(90, 0, -3155748, -16728876));
			}
			button1.setOnClickListener(new View.OnClickListener() {
				/* class dream.fata.fat.UsersActivity.Listview1Adapter.AnonymousClass5 */

				public void onClick(View _view) {
					UsersActivity.this.i.setClass(UsersActivity.this.getApplicationContext(), PlayHistoryActivity.class);
					UsersActivity.this.i.putExtra("email", ((HashMap) UsersActivity.this.listmap.get(_position)).get("email").toString());
					UsersActivity.this.startActivity(UsersActivity.this.i);
				}
			});
			button2.setOnClickListener(new View.OnClickListener() {
				/* class dream.fata.fat.UsersActivity.Listview1Adapter.AnonymousClass6 */

				public void onClick(View _view) {
					UsersActivity.this.i.setClass(UsersActivity.this.getApplicationContext(), TransactionHistoryActivity.class);
					UsersActivity.this.i.putExtra("email", ((HashMap) UsersActivity.this.listmap.get(_position)).get("email").toString());
					UsersActivity.this.startActivity(UsersActivity.this.i);
				}
			});
			button3.setOnClickListener(new View.OnClickListener() {
				/* class dream.fata.fat.UsersActivity.Listview1Adapter.AnonymousClass7 */

				public void onClick(View _view) {
					UsersActivity.this.i.setClass(UsersActivity.this.getApplicationContext(), UserDetailsActivity.class);
					UsersActivity.this.i.putExtra("email", ((HashMap) UsersActivity.this.listmap.get(_position)).get("email").toString());
					UsersActivity.this.startActivity(UsersActivity.this.i);
				}
			});
			return _view;
		}
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
