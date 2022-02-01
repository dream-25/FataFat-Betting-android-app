package dream.fata.fat;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ResultActivity extends AppCompatActivity {
	private String Game;
	private String Game_sp;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private ChildEventListener _ff_results_child_listener;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private ChildEventListener _rr_links_child_listener;
	private Toolbar _toolbar;
	private Button ff_online;
	private DatabaseReference ff_results = this._firebase.getReference("ff_results");
	private Button gk_online;
	private LinearLayout linear1;
	private LinearLayout linear3;
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ListView listview1;
	private Button mb_online;
	private Intent rr_i = new Intent();
	private DatabaseReference rr_links = this._firebase.getReference("rr_links");
	private ArrayList<HashMap<String, Object>> rr_listmap = new ArrayList<>();
	private TextView textview1;

	/* access modifiers changed from: protected */
	@Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.result);
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
			/* class dream.fata.fat.ResultActivity.AnonymousClass1 */

			public void onClick(View _v) {
				ResultActivity.this.onBackPressed();
			}
		});
		this.linear1 = (LinearLayout) findViewById(R.id.linear1);
		this.linear3 = (LinearLayout) findViewById(R.id.linear3);
		this.listview1 = (ListView) findViewById(R.id.listview1);
		this.textview1 = (TextView) findViewById(R.id.textview1);
		this.ff_online = (Button) findViewById(R.id.ff_online);
		this.gk_online = (Button) findViewById(R.id.gk_online);
		this.mb_online = (Button) findViewById(R.id.mb_online);
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
		this._rr_links_child_listener = childEventListener;
		this.rr_links.addChildEventListener(childEventListener);
		this.ff_online.setOnClickListener(new View.OnClickListener() {
			public void onClick(View param1View) {
				ResultActivity.this.rr_links.addListenerForSingleValueEvent(new ValueEventListener() {
					public void onCancelled(DatabaseError param2DatabaseError) {}

					public void onDataChange(DataSnapshot param2DataSnapshot) {
						rr_listmap = new ArrayList();
						try {
							GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

							};
							Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
							while (iterator.hasNext()) {
								HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
								ResultActivity.this.rr_listmap.add(hashMap);
							}
						} catch (Exception exception) {
							exception.printStackTrace();
						}
						ResultActivity.this.rr_i.setAction("android.intent.action.VIEW");
						ResultActivity.this.rr_i.setData(Uri.parse(((HashMap)ResultActivity.this.rr_listmap.get(0)).get("ff_online").toString()));
						ResultActivity.this.startActivity(ResultActivity.this.rr_i);
					}
				});
			}
		});
		this.gk_online.setOnClickListener(new View.OnClickListener() {
			public void onClick(View param1View) {
				ResultActivity.this.rr_links.addListenerForSingleValueEvent(new ValueEventListener() {
					public void onCancelled(DatabaseError param2DatabaseError) {}

					public void onDataChange(DataSnapshot param2DataSnapshot) {
						rr_listmap = new ArrayList();
						try {
							GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

							};
							Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
							while (iterator.hasNext()) {
								HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
								ResultActivity.this.rr_listmap.add(hashMap);
							}
						} catch (Exception exception) {
							exception.printStackTrace();
						}
						ResultActivity.this.rr_i.setAction("android.intent.action.VIEW");
						ResultActivity.this.rr_i.setData(Uri.parse(((HashMap)ResultActivity.this.rr_listmap.get(0)).get("gk_online").toString()));
						ResultActivity.this.startActivity(ResultActivity.this.rr_i);
					}
				});
			}
		});
		this.mb_online.setOnClickListener(new View.OnClickListener() {
			public void onClick(View param1View) {
				ResultActivity.this.rr_links.addListenerForSingleValueEvent(new ValueEventListener() {
					public void onCancelled(DatabaseError param2DatabaseError) {}

					public void onDataChange(DataSnapshot param2DataSnapshot) {
						rr_listmap = new ArrayList();
						try {
							GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

							};
							Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
							while (iterator.hasNext()) {
								HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
								ResultActivity.this.rr_listmap.add(hashMap);
							}
						} catch (Exception exception) {
							exception.printStackTrace();
						}
						ResultActivity.this.rr_i.setAction("android.intent.action.VIEW");
						ResultActivity.this.rr_i.setData(Uri.parse(((HashMap)ResultActivity.this.rr_listmap.get(0)).get("mb_online").toString()));
						ResultActivity.this.startActivity(ResultActivity.this.rr_i);
					}
				});
			}
		});
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
				ResultActivity.this.ff_results.addListenerForSingleValueEvent(new ValueEventListener() {
					public void onCancelled(DatabaseError param2DatabaseError) {}

					public void onDataChange(DataSnapshot param2DataSnapshot) {
						listmap = new ArrayList();
						try {
							GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

							};
							Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
							while (iterator.hasNext()) {
								HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
								ResultActivity.this.listmap.add(hashMap);
							}
						} catch (Exception exception) {
							exception.printStackTrace();
						}
						ResultActivity.this.listview1.setAdapter((ListAdapter)new ResultActivity.Listview1Adapter(ResultActivity.this.listmap));
						((BaseAdapter)ResultActivity.this.listview1.getAdapter()).notifyDataSetChanged();
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
		this._ff_results_child_listener = childEventListener;
		this.ff_results.addChildEventListener(childEventListener);
	}

	private void initializeLogic() {
		this.listview1.setStackFromBottom(true);
		this.Game = getIntent().getStringExtra("Game").replace("_r", "");
		this.Game_sp = getIntent().getStringExtra("Game_sp");
		this.textview1.setText("Viewing Result Of:  ".concat(getIntent().getStringExtra("Game").replace("_r", "")).concat("  [").concat(getIntent().getStringExtra("Game_sp")).concat("]"));
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
			LayoutInflater _inflater = (LayoutInflater) ResultActivity.this.getBaseContext().getSystemService("layout_inflater");
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.result_, (ViewGroup) null);
			}
			LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			LinearLayout linearLayout = (LinearLayout) _view.findViewById(R.id.linear4);
			LinearLayout linearLayout2 = (LinearLayout) _view.findViewById(R.id.linear1);
			LinearLayout linearLayout3 = (LinearLayout) _view.findViewById(R.id.linear3);
			TextView textView = (TextView) _view.findViewById(R.id.textview1);
			linear2.setElevation(20.0f);
			linear2.setBackground(new GradientDrawable() {
				/* class dream.fata.fat.ResultActivity.Listview1Adapter.AnonymousClass1 */

				public GradientDrawable getIns(int a, int b, int c, int d) {
					setCornerRadius((float) a);
					setStroke(b, c);
					setColor(d);
					return this;
				}
			}.getIns(20, 1, -10453621, -1));
			((TextView) _view.findViewById(R.id.textview_bazi)).setText(((HashMap) ResultActivity.this.listmap.get(_position)).get("bazi").toString());
			((TextView) _view.findViewById(R.id.textview_date)).setText(((HashMap) ResultActivity.this.listmap.get(_position)).get("date").toString());
			((TextView) _view.findViewById(R.id.textview_digit)).setText(((HashMap) ResultActivity.this.listmap.get(_position)).get("digit").toString());
			((TextView) _view.findViewById(R.id.textview_game)).setText(((HashMap) ResultActivity.this.listmap.get(_position)).get("game").toString());
			if (!((HashMap) ResultActivity.this.listmap.get(_position)).get("game").toString().toLowerCase().contains(ResultActivity.this.Game.toLowerCase()) || !((HashMap) ResultActivity.this.listmap.get(_position)).get("game").toString().toLowerCase().contains(ResultActivity.this.Game_sp.toLowerCase())) {
				linear2.setVisibility(View.GONE);
			} else {
				linear2.setVisibility(View.VISIBLE);
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
