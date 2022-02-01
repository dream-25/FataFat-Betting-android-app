package dream.fata.fat;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
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

public class TransactionHistoryActivity extends AppCompatActivity {
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private Toolbar _toolbar;
	private ChildEventListener _user_noti_child_listener;
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ListView listview1;
	private String path_noti = "";
	private DatabaseReference user_noti = this._firebase.getReference("user_noti");

	/* access modifiers changed from: protected */
	@Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.transaction_history);
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
			/* class dream.fata.fat.TransactionHistoryActivity.AnonymousClass1 */

			public void onClick(View _v) {
				TransactionHistoryActivity.this.onBackPressed();
			}
		});
		this.listview1 = (ListView) findViewById(R.id.listview1);
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
				TransactionHistoryActivity.this.user_noti.addListenerForSingleValueEvent(new ValueEventListener() {
					public void onCancelled(DatabaseError param2DatabaseError) {}

					public void onDataChange(DataSnapshot param2DataSnapshot) {
						listmap = new ArrayList();
						try {
							GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

							};
							Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
							while (iterator.hasNext()) {
								HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
								TransactionHistoryActivity.this.listmap.add(hashMap);
							}
						} catch (Exception exception) {
							exception.printStackTrace();
						}
						TransactionHistoryActivity.this.listview1.setAdapter((ListAdapter)new TransactionHistoryActivity.Listview1Adapter(TransactionHistoryActivity.this.listmap));
						((BaseAdapter)TransactionHistoryActivity.this.listview1.getAdapter()).notifyDataSetChanged();
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
		this._user_noti_child_listener = childEventListener;
		this.user_noti.addChildEventListener(childEventListener);
	}

	private void initializeLogic() {
		this.listview1.setStackFromBottom(true);
		this.path_noti = "userHistory/".concat(getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "").concat("/Transactions".concat("")));
		this.user_noti.removeEventListener(this._user_noti_child_listener);
		DatabaseReference reference = this._firebase.getReference(this.path_noti);
		this.user_noti = reference;
		reference.addChildEventListener(this._user_noti_child_listener);
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

		public View getView(int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater) TransactionHistoryActivity.this.getBaseContext().getSystemService("layout_inflater");
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.transaction, (ViewGroup) null);
			}
			LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			((TextView) _view.findViewById(R.id.textview1)).setText(((HashMap) TransactionHistoryActivity.this.listmap.get(_position)).get("time").toString());
			((TextView) _view.findViewById(R.id.textview2)).setText(((HashMap) TransactionHistoryActivity.this.listmap.get(_position)).get("action").toString());
			((TextView) _view.findViewById(R.id.textview3)).setText(((HashMap) TransactionHistoryActivity.this.listmap.get(_position)).get("rs").toString());
			((TextView) _view.findViewById(R.id.textview4)).setText(((HashMap) TransactionHistoryActivity.this.listmap.get(_position)).get(NotificationCompat.CATEGORY_STATUS).toString());
			if (((HashMap) TransactionHistoryActivity.this.listmap.get(_position)).get(NotificationCompat.CATEGORY_STATUS).toString().equals("Pending")) {
				linear1.setElevation(30.0f);
				linear1.setBackground(new GradientDrawable() {
					/* class dream.fata.fat.TransactionHistoryActivity.Listview1Adapter.AnonymousClass1 */

					public GradientDrawable getIns(int a, int b, int c, int d) {
						setCornerRadius((float) a);
						setStroke(b, c);
						setColor(d);
						return this;
					}
				}.getIns(20, 1, -14273992, -5317));
			} else if (((HashMap) TransactionHistoryActivity.this.listmap.get(_position)).get(NotificationCompat.CATEGORY_STATUS).toString().equals("Deposited")) {
				linear1.setElevation(30.0f);
				linear1.setBackground(new GradientDrawable() {
					/* class dream.fata.fat.TransactionHistoryActivity.Listview1Adapter.AnonymousClass2 */

					public GradientDrawable getIns(int a, int b, int c, int d) {
						setCornerRadius((float) a);
						setStroke(b, c);
						setColor(d);
						return this;
					}
				}.getIns(20, 1, -14273992, -10044566));
			} else if (((HashMap) TransactionHistoryActivity.this.listmap.get(_position)).get(NotificationCompat.CATEGORY_STATUS).toString().equals("Withdrawaled")) {
				linear1.setElevation(30.0f);
				linear1.setBackground(new GradientDrawable() {
					/* class dream.fata.fat.TransactionHistoryActivity.Listview1Adapter.AnonymousClass3 */

					public GradientDrawable getIns(int a, int b, int c, int d) {
						setCornerRadius((float) a);
						setStroke(b, c);
						setColor(d);
						return this;
					}
				}.getIns(20, 1, -14273992, -16728876));
			} else {
				linear1.setElevation(30.0f);
				linear1.setBackground(new GradientDrawable() {
					/* class dream.fata.fat.TransactionHistoryActivity.Listview1Adapter.AnonymousClass4 */

					public GradientDrawable getIns(int a, int b, int c, int d) {
						setCornerRadius((float) a);
						setStroke(b, c);
						setColor(d);
						return this;
					}
				}.getIns(20, 1, -14273992, -769226));
			}
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
